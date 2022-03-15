package com.sf.gateway;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.sf.coms.gateway.encryption.AESCipher;
import com.sf.coms.gateway.encryption.HmacSha512CoderFactory;

/**
 * 顺丰订单接口调用DEMO
 * 
 * @author 01377259 
 * 2018年9月27日
 */
public class Demo_inbound {

	public static final int TIMEOUT = 30000;
	public static final String CHARSET = "UTF-8";
//	public static final String AES256_KEY = "alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";// key
//	public static final String MACSHA_512 = "W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";// 盐
	//public static final String REQUES_URL = "http://scs-oms2-bspwms.sit.sf-express.com/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
//	public static final String REQUES_URL = "http://scs-oms2-bspwms.sit.sf-express.com/index.do?appId=111111&method=transport&source=yunnanxianhua&appToken=yunnanxianhuaapptoken&v=1.0&timestamp=123456789&userToken=yunnanxianhuatoken";
	//public static final String REQUES_URL = "http://localhost:8080/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";

	public static final String AES256_KEY = "alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";// key
	public static final String MACSHA_512 = "W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";// 盐
	public static final String REQUES_URL_0 = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=inbound&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&userToken=sfdemotoken&format=json";

	/**
	 * 示例
	 * 
	 * @param source 业务报文
	 * @throws IOException
	 */
	public static void demo(String source) throws IOException {
		
		// STEP.1 业务报文urlencode
		String urlSource = URLEncoder.encode(source, "UTF-8");
		
		// STEP.2 业务报文加密 [注:AESCipher非线程安全]
		AESCipher aesciphe = new AESCipher(AES256_KEY.getBytes(CHARSET));
		String encrySource = aesciphe.getEncryptedMessage(urlSource);
		
		System.out.println("加密报文:"+encrySource);

		// STEP.3 生成摘要
		String sourceDiges = HmacSha512CoderFactory.getHmacSha512Coder(MACSHA_512, encrySource);

		// STEP.4 报文及摘要再次转码
		String encrySourceEncode = URLEncoder.encode(encrySource, CHARSET);
		String sourceDigesEncode = URLEncoder.encode(sourceDiges, CHARSET);

		// STEP.5 准备参数报文
		RequestBean request = new RequestBean(new RequestBean.Request(encrySourceEncode, sourceDigesEncode));

		String json = new Gson().toJson(request);

		// STEP.5 HTTP调用顺丰接口 [注:响应为明文]
		String response = post(REQUES_URL_0, json);

		System.out.println("远程接口响应:"+response);
	}

	/**
	 * HTTP -> POST
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String param) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();

			HttpPost postmethod = new HttpPost(url);

			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT)
					.build();
			postmethod.setConfig(requestConfig);
			postmethod.addHeader("content-type", "application/json");
			postmethod.setEntity(new StringEntity(param, CHARSET));

			response = httpclient.execute(postmethod);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		String source = "{\n" +
				"    \"supplierCode\":\"CARREFOUR\",\n" +
				"    \"warehouseCode\":\"P024CSB\",\n" +
				"    \"sfOrderType\":\"PI\",\n" +
				"    \"licensePlateNumber\":\"221\",\n" +
				"    \"orderTime\":\"2018-09-20 15:50:50\",\n" +
				"    \"distributionType\":\"Y\",\n" +
				"    \"tradePlatform\":\"JD\",\n" +
				"    \"erpOrder\":\"11000853401260\",\n" +
				"    \"userDef1\":\"\",\n" +
				"    \"item\":[\n" +
				"        {\n" +
				"            \"lotatt02\":\"2018-09-20 15:50:50\",\n" +
				"            \"qtyUm\":\"EA\",\n" +
				"            \"lotatt03\":\"2018-09-20 15:50:50\",\n" +
				"            \"lotatt01\":\"2018-09-20 15:50:50\",\n" +
				"            \"usetItemDef4\":\"\",\n" +
				"            \"usetItemDef3\":\"\",\n" +
				"            \"usetItemDef2\":\"\",\n" +
				"            \"inventoryStatus\":\"10\",\n" +
				"            \"lotatt05\":\"1234\",\n" +
				"            \"usetItemDef1\":\"\",\n" +
				"            \"remark\":\"\",\n" +
				"            \"erpOrderLineNum\":\"123123\",\n" +
				"            \"lot\":\"123\",\n" +
				"            \"expirationTime\":6,\n" +
				"            \"price\":11,\n" +
				"            \"qty\":\"2616\",\n" +
				"            \"skuNo\":\"LF15050041001\",\n" +
				"            \"usetItemDef8\":\"\",\n" +
				"            \"usetItemDef7\":\"\",\n" +
				"            \"usetItemDef6\":\"\",\n" +
				"            \"usetItemDef5\":\"\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"tradeOrder\":\"111\",\n" +
				"    \"originalNo\":\"\",\n" +
				"    \"userDef8\":\"\",\n" +
				"    \"userDef6\":\"\",\n" +
				"    \"requirement\":\"\",\n" +
				"    \"userDef7\":\"\",\n" +
				"    \"userDef4\":\"\",\n" +
				"    \"userDef5\":\"\",\n" +
				"    \"userDef2\":\"\",\n" +
				"    \"buyer\":\"\",\n" +
				"    \"driverCalls\":\"\",\n" +
				"    \"erpOrderType\":\"10\",\n" +
				"    \"userDef3\":\"\",\n" +
				"    \"buyerPhone\":\"\",\n" +
				"    \"driver\":\"\",\n" +
				"    \"expectDate\":\"2018-09-20 15:50:50\",\n" +
				"    \"customer\":{\n" +
				"        \"companyCode\":\"7550057640\",\n" +
				"        \"customerMonthlyCard\":\"7550057640\"\n" +
				"    }\n" +
				"}";
		Demo_inbound.demo(source);
	}
}

class RequestBean {
	
	private Request param_json;

	public Request getParamJson() {
		return param_json;
	}
	public void setParamJson(Request paramJson) {
		this.param_json = paramJson;
	}
	public RequestBean(Request param_json) {
		super();
		this.param_json = param_json;
	}

	static class Request {
		private String msgData;
		private String dataDigest;
		
		public Request(String msgData, String dataDigest) {
			super();
			this.msgData = msgData;
			this.dataDigest = dataDigest;
		}
		public String getMsgData() {
			return msgData;
		}
		public void setMsgData(String msgData) {
			this.msgData = msgData;
		}
		public String getDataDigest() {
			return dataDigest;
		}
		public void setDataDigest(String dataDigest) {
			this.dataDigest = dataDigest;
		}
	}
}
