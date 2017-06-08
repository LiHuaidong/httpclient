package main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.HttpService;

public class TestApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(  
                "classpath:app*.xml");  
        System.out.println(context);  
  
        HttpService httpService = context.getBean("httpService", HttpService.class);  
        System.out.println(httpService);  
        try {  
            // Map<String, String> maps = new HashMap<String, String>();  
            // maps.put("wd", "java");  
            // String string = httpService.doGet("http://www.baidu.com/s");  
            // System.out.println(string);  
  
            Map<String, Object> maps = new HashMap<String, Object>();  
            maps.put("id", "1");  
            String string = httpService.doGet("http://localhost:8080/httpclient/hdli/test/get.do", maps);  
            System.out.println(string);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

}
