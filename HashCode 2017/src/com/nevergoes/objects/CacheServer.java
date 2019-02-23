package com.nevergoes.objects;

import java.util.ArrayList;
import java.util.List;

public class CacheServer {

	public int id;
	public int load;
	public List<Video> videos;
	public List<EndpointCache> endpointCache = new ArrayList<>();

}
