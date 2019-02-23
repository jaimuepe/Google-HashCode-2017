package com.nevergoes;

import com.nevergoes.io.Parser;
import com.nevergoes.objects.CacheServer;
import com.nevergoes.objects.Endpoint;
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
	}
}