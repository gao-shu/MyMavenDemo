package com.sf.gateway;

import com.google.gson.Gson;
import com.sf.coms.gateway.encryption.AESCipher;
import com.sf.coms.gateway.encryption.HmacSha512CoderFactory;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * 顺丰订单接口调用DEMO
 * 
 * @author 01377259 
 * 2018年9月27日
 */
public class Demo_iterm {

	public static final int TIMEOUT = 30000;
	public static final String CHARSET = "UTF-8";
//	public static final String AES256_KEY = "uG9NgFmNFH7BHqtHAFFZiTtsrWYD6HxK";// key
//	public static final String MACSHA_512 = "Gmnf7y2TKMhCZ6lDWmsCOid3wRK3UMNp";// 盐
//	public static final String REQUES_URL = "http://scs-oms2-bspwms.sit.sf-express.com/index.do?appId=111111&method=inbound&soource=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
	//public static final String REQUES_URL = "http://localhost:8080/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";

	public static final String AES256_KEY = "alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";// key
	public static final String MACSHA_512 = "W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";// 盐
//	public static final String REQUES_URL = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=inbound&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=sfdemotoken&format=json";
	public static final String REQUES_URL = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=item&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&userToken=sfdemotoken";
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
		
		System.err.println(json);

		// STEP.5 HTTP调用顺丰接口 [注:响应为明文]
		String response = post(REQUES_URL, json);

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
//		String source = "hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容: page:";
		String source = "[\n" +
				"    {\n" +
				"        \"transactionId\":\"308042871316268001Y\",\n" +
				// 货主编码
//				"        \"companyCode\":\"7550137864\",\n" +
				"        \"companyCode\":\"OMS2TEST\",\n" +
//				"        \"skuNo\":\"123456658572\",\n" +
//				"        \"skuNo\":\"666666\",\n" +
				"        \"skuNo\":\"6907992822725\",\n" +
				"        \"skuName\":\"蚝油牛肉\",\n" +
				"        \"foreignName\":\"7002111317\",\n" +
				"        \"shortName\":\"\",\n" +
				"        \"brand\":\"哈根达斯\",\n" +
				"        \"brandDesc\":\"哈根达斯\",\n" +
				"        \"description\":\"哈根达斯\",\n" +
				"        \"drugMfrs\":\"\",\n" +
				"        \"drugMadeIn\":\"\",\n" +
				"        \"itemStyle\":\"\",\n" +
				"        \"transportProperty\":\"1\",\n" +
				"        \"transportTempRange\":\"4\",\n" +
				"        \"storageTempRange\":\"4\",\n" +
				"        \"shelfLife\":\"21\",\n" +
				"        \"inLifeDays\":\"5\",\n" +
				"        \"outLifeDays\":\"5\",\n" +
				"        \"nearValidityDays\":\"\",\n" +
				"        \"invAgeWarnDays\":\"\",\n" +
				"        \"qtymin\":\"100\",\n" +
				"        \"typeCode\":\"\",\n" +
				"        \"color\":\"red\",\n" +
				"        \"size\":\"21\",\n" +
				"        \"storageTemplate\":\"\",\n" +
				"        \"style\":\"\",\n" +
				"        \"qualityProportion\":\"\",\n" +
				"        \"qcDesc\":\"\",\n" +
				"        \"isFragile\":\"N\",\n" +
				"        \"isVirtualProduct\":\"\",\n" +
				"        \"serialNumTrackInventory\":\"\",\n" +
				"        \"serialNumTrackInbound\":\"\",\n" +
				"        \"serialNumTrackOutbound\":\"\",\n" +
				"        \"bomAction\":\"\",\n" +
				"        \"itemClassify1\":\"\",\n" +
				"        \"itemClassify2\":\"\",\n" +
				"        \"itemClassify3\":\"\",\n" +
				"        \"itemClassify4\":\"\",\n" +
				"        \"itemClassify5\":\"\",\n" +
				"        \"itemClassify6\":\"\",\n" +
				"        \"itemClassify7\":\"\",\n" +
				"        \"price\":\"26.8\",\n" +
				"        \"refItem1\":\"54554226644444\",\n" +
				"        \"refItem2\":\"\",\n" +
				"        \"refItem3\":\"\",\n" +
				"        \"refItem4\":\"\",\n" +
				"        \"userdefined1\":\"\",\n" +
				"        \"userdefined2\":\"\",\n" +
				"        \"userdefined3\":\"\",\n" +
				"        \"userdefined4\":\"\",\n" +
				"        \"userdefined5\":\"\",\n" +
				"        \"userdefined6\":\"\",\n" +
				"        \"userdefined7\":\"\",\n" +
				"        \"userdefined8\":\"\",\n" +
				"        \"userdefined9\":\"\",\n" +
				"        \"userdefined10\":\"\",\n" +
				"        \"userdefined11\":\"\",\n" +
				"        \"userdefined12\":\"\",\n" +
				"        \"userdefined13\":\"\",\n" +
				"        \"userdefined14\":\"\",\n" +
				"        \"userdefined15\":\"\",\n" +
				"        \"sequence3\":\"\",\n" +
				"        \"conversionQty3\":\"10\",\n" +
				"        \"height3\":2,\n" +
				"        \"width3\":4,\n" +
				"        \"length3\":3,\n" +
				"        \"weight3\":3,\n" +
				"        \"lotTemplate\":\"255522545555454\"\n" +
				"    }\n" +
				"]";
		Demo_iterm.demo(source);
	}
}

class RequestBean4 {
	
	private Request param_json;

	public Request getParamJson() {
		return param_json;
	}
	public void setParamJson(Request paramJson) {
		this.param_json = paramJson;
	}
	public RequestBean4(Request param_json) {
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
