package com.sf.gateway;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;

public class Main2 {
	public static void main(String[] args) throws Exception{

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 500000; i++) {
			final int k = i;
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					int a = Main2.randomId();
					int b = Main2.randomId();
					
					String rs = Demo.post("http://localhost:8080/testJs?a="+a+"&b="+b, "ee");
					//System.out.println("a:"+a+ " b:"+b + " rs:" + rs);
					if ((a + b) != Integer.parseInt(rs)) {
						System.err.println("a:"+a+ " b:"+b + " rs:" + rs);
					} else {
						System.out.println("a:"+a+ " b:"+b + " rs:" + rs);
					}
				}
			});
		}
		
		//executorService.shutdownNow();
		System.err.println("end................");
	}
	
	public static int randomId() {
		return new Random().nextInt(100);
	}
}
