package com.nevergoes.objects;

import java.util.ArrayList;
import java.util.List;

public class Endpoint {

	public int id;
	public int datacenterLatency;
	public List<EndpointCache> endpointCaches = new ArrayList<>();

}
