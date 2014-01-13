package br.paiter.blogmongo.exemplo;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldMongoDBSparkFreemakerStyle {
	
public static void main(String[] args) throws UnknownHostException {
		
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldFreemakerStyle.class, "/");		
		
		
			MongoClient client  = new MongoClient(new ServerAddress("localhost", 27017));
			
			DB database  = client.getDB("course");
			final DBCollection collection =  database.getCollection("hello");
			
			
		Spark.get(new Route("/"){

			@Override
			public Object handle(Request request, Response response) {
				
				StringWriter writer = new StringWriter();

				Template helloTemplate;
				try {
					helloTemplate = configuration.getTemplate("hello.ftl");
					
					DBObject document = collection.findOne();
					
					DBCursor find = collection.find();
					
					
					/*for (Iterator<DBObject> iterator = find.iterator(); iterator.hasNext();) {
						type type = (type) iterator2.next();
						
					}*/
					
					helloTemplate.process(document, writer);
					
					
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
