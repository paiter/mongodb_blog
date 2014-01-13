package br.paiter.blogmongo.exemplo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldFreemakerStyle {

	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldFreemakerStyle.class, "/");
		
		
		try {
			
			Template helloTemplate = configuration.getTemplate("hello.ftl");
			
			StringWriter writer = new StringWriter();
			
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "Romildo Paiter");
			
			
			helloTemplate.process(helloMap, writer);
			
			System.out.println(writer);
			
			
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
