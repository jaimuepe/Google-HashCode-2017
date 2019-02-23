package com.nevergoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nevergoes.io.Parser;
import com.nevergoes.objects.CacheServer;
import com.nevergoes.objects.Endpoint;
import com.nevergoes.objects.EndpointCache;
import com.nevergoes.objects.Request;
import com.nevergoes.objects.Video;

public class App {

	public static Video[] videos;
	public static Request[] requests;
	public static Endpoint[] endpoints;
	public static CacheServer[] cacheServers;
	// Size of all CacheServers
	public static int X;

	public static void main(String[] args) {

		Parser.parseFile("me_at_the_zoo.in");

		Map<Integer, List<Request>> endpointIdToRequests = new HashMap<>();

		for (Request request : requests) {

			Endpoint endpoint = endpoints[request.endpointId];
			if (!endpointIdToRequests.containsKey(endpoint.id)) {
				endpointIdToRequests.put(endpoint.id, new ArrayList<>());
			}
			endpointIdToRequests.get(endpoint.id).add(request);
		}

		Map<Integer, List<Request>> cacheIdToRequests = new HashMap<>();

		for (CacheServer cacheServer : cacheServers) {
			int[] endpoints = cacheServer.endpointCache.stream().mapToInt(ec -> ec.endpointId).toArray();
			for (Integer endpointId : endpoints) {
				if (endpointIdToRequests.containsKey(endpointId)) {
					if (!cacheIdToRequests.containsKey(cacheServer.id)) {
						cacheIdToRequests.put(cacheServer.id, new ArrayList<>());
					}
					cacheIdToRequests.get(cacheServer.id).addAll(endpointIdToRequests.get(endpointId));
				}
			}
		}

		for (CacheServer cacheServer : cacheServers) {

			System.out.println("Cache " + cacheServer.id);

			List<Request> requests = cacheIdToRequests.get(cacheServer.id);

			if (requests != null) {

				System.out.println(requests.size() + " requests");

				Map<Integer, List<Request>> videoIdToRequests = requests.stream()
						.collect(Collectors.groupingBy(r -> r.videoId));

				System.out.println(videoIdToRequests.keySet().size() + " unique videos");

				Map<Integer, Integer> videoIdToScore = new HashMap<>();

				for (Integer videoId : videoIdToRequests.keySet()) {

					List<Request> requestsForVideo = videoIdToRequests.get(videoId);
					int score = 0;

					for (Request r : requestsForVideo) {

						Endpoint e = endpoints[r.endpointId];

						EndpointCache ec = e.endpointCaches.stream().filter(ec1 -> ec1.cacheId == cacheServer.id)
								.findFirst().get();

						score += (e.datacenterLatency - ec.latency) * r.amount;
					}

					videoIdToScore.put(videoId, score);
				}

				List<Video> vv = videoIdToRequests.keySet().stream().map(vId -> videos[vId])
						.collect(Collectors.toList());

				List<List<Video>> combinations = Jaime.getCombinations(vv, 100);

				int maxScore = -100;
				int maxScoreIdx = -1;

				for (int i = 0; i < combinations.size(); i++) {

					List<Video> combination = combinations.get(i);

					int combScore = combination.stream().mapToInt(v -> videoIdToScore.get(v.id)).sum();
					if (combScore > maxScore) {
						maxScoreIdx = i;
						maxScore = combScore;
					}
				}

				System.out.println("Cache "
						+ cacheServer.id + " better combination is: " + combinations.get(maxScoreIdx).stream()
								.map(v -> v.id + "(" + v.size + ")").collect(Collectors.joining(", "))
						+ " -> " + maxScore);
				System.out.println();
			}
		}
	}
}