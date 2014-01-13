package br.paiter.blogmongo.test;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkRoutes {
	
	public static void main(String[] args) {
		
		Spark.get(new Route("/"){

			@Override
			public Object handle(Request request, Response response) {
				return "Hello World Spark /";
			}
			
		});
		
		Spark.get(new Route("/test"){

			@Override
			public Object handle(Request request, Response response) {
				return "Hello World page Teste";
			}
			
		});

		Spark.get(new Route("/test/:name"){

			@Override
			public Object handle(Request request, Response response) {
				return "Hello " + request.params(":name") + "!";
			}
			
		});		
		
	}

}
