package com.nevergoes.objects;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<CacheServer> orderCacheServersByEndpointsAmountAsc(CacheServer[] cacheServers) {

		List<CacheServer> orderedServers = new ArrayList<>();
		for (int i = 0; i < cacheServers.length; i++) {
			orderedServers.add(cacheServers[i]);
		}

		orderedServers.sort((s1, s2) -> Integer.compare(s1.endpointCache.size(), s2.endpointCache.size()));

		return orderedServers;

	}

}
