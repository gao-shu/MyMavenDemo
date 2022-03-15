package com.sf.gateway;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;

public class Main {
	public static void main(String[] args) throws Exception{

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 500000; i++) {
			final int k = i;
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					//Demo.post("http://10.202.106.175:8080/testOutBound?id="+k, "ee");	
					String rs = Demo.post("http://localhost:8080/testOutBound?id="+k, "ee");
					System.out.println(rs);
					//Demo.post("http://10.202.106.175:8088/testBeetl?id="+k, "ee");	
					//Demo.post("http://10.118.66.23:8088/testBeetl?id="+k, "ee");	
					
				}
			});
		}
		
		//executorService.shutdownNow();
		System.err.println("end................");
	}
}
