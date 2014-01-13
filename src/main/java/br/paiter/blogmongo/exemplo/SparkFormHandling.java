package br.paiter.blogmongo.exemplo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkFormHandling {
	
	public static void main(String[] args) {
		
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");
		
		Spark.get(new Route("/"){

			@Override
			public Object handle(Request request, Response response) {

				try {
					
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits", Arrays.asList("maça","abacaxi","uva", "morango"));
				
					Template fruitPickersTemplate = configuration.getTemplate("fruitPicker.ftl");
					
					StringWriter writer = new StringWriter();
					
					fruitPickersTemplate.process(fruitsMap, writer);
					
					
					
					return writer;					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					halt(500);
					e.printStackTrace();
					return null;
				}
			}
		});
		
		
		Spark.post(new Route("/favorite_fruit"){
			@Override
			public Object handle(Request request, Response response) {

				final String fruit =  request.queryParams("fruit");
				
				if(fruit == null){
					return "Você não escolheu uma fruta!";
				}
				else{
					return "Sua fruta favorita é: " + fruit;
				}
			}
		});
		
		
		
	}

}
