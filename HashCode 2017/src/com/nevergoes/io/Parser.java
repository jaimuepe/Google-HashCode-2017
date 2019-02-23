package com.nevergoes.io;

import java.util.List;

import com.nevergoes.App;
import com.nevergoes.objects.CacheServer;
import com.nevergoes.objects.Endpoint;
import com.nevergoes.objects.EndpointCache;
import com.nevergoes.objects.Request;
import com.nevergoes.objects.Video;

public class Parser {

	public static void parseFile(String fileName) {

		List<String> lineas = MyFiles.readFile(fileName);
		int videosSize = 0;
		int endpointsSize = 0;
		int requestsSize = 0;
		int cacheServersSize = 0;

		// DATOS GENERALES
		String dataLine = lineas.get(0);
		String[] dataLineSplit = dataLine.split(" ");

		// Rellenamos los sizes de cada dato
		videosSize = Integer.parseInt(dataLineSplit[0]);
		App.videos = new Video[videosSize];
		endpointsSize = Integer.parseInt(dataLineSplit[1]);
		App.endpoints = new Endpoint[endpointsSize];
		requestsSize = Integer.parseInt(dataLineSplit[2]);
		App.requests = new Request[requestsSize];
		cacheServersSize = Integer.parseInt(dataLineSplit[3]);
		App.cacheServers = new CacheServer[cacheServersSize];
		for (int i = 0; i < cacheServersSize; i++) {
			CacheServer cacheServer = new CacheServer();
			cacheServer.id = i;
			App.cacheServers[i] = cacheServer;
		}
		App.X = Integer.parseInt(dataLineSplit[4]);

		// DATOS DEL TAMAÑO DE CADA VIDEO
		String lineVideosSize = lineas.get(1);
		String[] dataVideosSplit = lineVideosSize.split(" ");
		for (int i = 0; i < videosSize; i++) {
			Video video = new Video();
			video.id = i;
			video.size = Integer.parseInt(dataVideosSplit[i]);
			App.videos[i] = video;

		}

		// La siguiente línea tendrá la información del endpoint y su latencia al
		// datacenter, y a cuántas cachés estará asociado

		// Esto se repetirá para cada endpointSize informado en la primera línea
		// (empezaremos en la segunda línea del fichero)
		int lineNumber = 2;
		for (int i = 0; i < endpointsSize; i++) {
			String[] endpointInformation = lineas.get(lineNumber).split(" ");
			Endpoint endpoint = new Endpoint();
			endpoint.id = i;
			endpoint.datacenterLatency = Integer.parseInt(endpointInformation[0]);
			int numberOfCachesAssociated = Integer.parseInt(endpointInformation[1]);

			App.endpoints[i] = endpoint;

			lineNumber++;

			if (numberOfCachesAssociated > 0) {
				for (int j = 0; j < numberOfCachesAssociated; j++) {

					String[] endpointCacheInformation = lineas.get(lineNumber).split(" ");
					EndpointCache endpointCache = new EndpointCache();
					endpointCache.cacheId = Integer.parseInt(endpointCacheInformation[0]);
					endpointCache.latency = Integer.parseInt(endpointCacheInformation[1]);
					endpointCache.endpointId = endpoint.id;
					endpoint.endpointCaches.add(endpointCache);

					App.cacheServers[Integer.parseInt(endpointCacheInformation[0])].endpointCache.add(endpointCache);

					lineNumber++;
				}
			}
		}

		// PARSE REQUESTS
		int requestId = 0;
		while (lineNumber < lineas.size()) {
			String[] requestLineInformation = lineas.get(lineNumber).split(" ");
			Request request = new Request();
			request.videoId = Integer.parseInt(requestLineInformation[0]);
			request.endpointId = Integer.parseInt(requestLineInformation[1]);
			request.amount = Integer.parseInt(requestLineInformation[2]);
			App.requests[requestId] = request;

			lineNumber++;
			requestId++;
		}

	}

}
