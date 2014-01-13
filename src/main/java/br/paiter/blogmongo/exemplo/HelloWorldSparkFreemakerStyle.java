package br.paiter.blogmongo.exemplo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemakerStyle {
	
	public static void main(String[] args) {
		
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldFreemakerStyle.class, "/");		
		
		Spark.get(new Route("/"){

			@Override
			public Object handle(Request request, Response response) {
				
				StringWriter writer = new StringWriter();

				Template helloTemplate;
				try {
					helloTemplate = configuration.getTemplate("hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Romildo Paiter");
					
					
					helloTemplate.process(helloMap, writer);
					
					System.out.println(writer);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					halt(500);
					e.printStackTrace();
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					halt(500);
					e.printStackTrace();
				}
				
				return writer;
				
			}
			
		});
		
	}

}
