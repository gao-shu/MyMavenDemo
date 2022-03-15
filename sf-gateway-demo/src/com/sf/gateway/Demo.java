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
public class Demo {

	public static final int TIMEOUT = 30000;
	public static final String CHARSET = "UTF-8";
//	public static final String AES256_KEY = "uG9NgFmNFH7BHqtHAFFZiTtsrWYD6HxK";// key
//	public static final String MACSHA_512 = "Gmnf7y2TKMhCZ6lDWmsCOid3wRK3UMNp";// 盐
//	public static final String REQUES_URL = "http://scs-oms2-bspwms.sit.sf-express.com/index.do?appId=111111&method=inbound&soource=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
	//public static final String REQUES_URL = "http://localhost:8080/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";

	public static final String AES256_KEY = "alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";// key
	public static final String MACSHA_512 = "W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";// 盐
//	public static final String REQUES_URL = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=inbound&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=sfdemotoken&format=json";
	public static final String REQUES_URL = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=outbound&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=sfdemotoken&format=json";
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
		String source = "{\n" +
				"    \"carrierCode\":\"CP\",\n" +
				"    \"carrierServiceType\":\"SE0004\",\n" +
				"    \"erpOrder\":\"68121638522904\",\n" +
				"    \"orderTime\":\"2018-10-09 12:01:15\",\n" +
				"    \"sfOrderType\":\"PO\",\n" +
				"    \"warehouseCode\":\"P024CSB\",\n" +
				"    \"remark\":\"订单备注：不要辣\",\n" +
				"    \"paymentTypeCode\":\"PR_ACCOUNT\",\n" +
				"    \"deliveryDate\":\"2018-10-15 12:01:15\",\n" +
				"    \"customer\":{\n" +
				"        \"companyCode\":\"7550137864\",\n" +
				"        \"customerMonthlyCard\":\"7550760040\"\n" +
				"    },\n" +
				"    \"shipper\":{\n" +
				"        \"shipperContactName\":\"李小明\",\n" +
				"        \"shipperContactPhone\":\"10086\",\n" +
				"        \"shipperContactTel\":\"18645787890\",\n" +
				"        \"shipperContactEmail\":\"ling.lina@126.com\",\n" +
				"        \"shipperCountryName\":\"中国\",\n" +
				"        \"shipperCityName\":\"梅州市\",\n" +
				"        \"shipperDistrictName\":\"平远县\",\n" +
				"        \"shipperRegionName\":\"大柘镇\",\n" +
				"        \"shipperLocationName\":\"广东省梅州市平远县大柘镇光明乳业分公司\",\n" +
				"        \"shipperCompany\":\"深圳光明乳业有限公司平远分公司\"\n" +
				"    },\n" +
				"    \"consignee\":{\n" +
				"        \"consigneeCityName\":\"深圳市\",\n" +
				"        \"consigneeContactName\":\"莫怡然\",\n" +
				"        \"consigneeContactPhone\":\"67627773\",\n" +
				"        \"consigneeLocationName\":\"广东省深圳市龙岗区横岗街道红棉4路森城工业区A2栋\",\n" +
				"        \"consigneeProvinceName\":\"广东省\",\n" +
				"        \"consigneeCountryName\":\"中国\",\n" +
				"        \"consigneeContactEmail\":\"shangbai@123.com\",\n" +
				"        \"consigneeContactZipCode\":\"7553121251\",\n" +
				"        \"consigneeContactTel\":\"1524545412222\",\n" +
				"        \"consigneeDistrictName\":\"龙岗\",\n" +
				"        \"consigneeRegionName\":\"龙岗区\",\n" +
				"        \"consigneeCompany\":\"瑞意森城有限公司\"\n" +
				"    },\n" +
				"    \"addedService\":[\n" +
				"        {\n" +
				"            \"serviceCode\":\"VA0021\",\n" +
				"            \"value\":\"1000\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"serviceCode\":\"VA0003\",\n" +
				"            \"value\":\"Y\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"serviceCode\":\"VA0042\",\n" +
				"            \"value\":\"1000\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"serviceCode\":\"VA0019\",\n" +
				"            \"value\":\"4000\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"detail\":[\n" +
				"        {\n" +
				"            \"erpOrderLineNum\":1,\n" +
				"            \"skuNo\":\"123456658572\",\n" +
				"            \"skuName\":\"Topfer特福芬 新妈妈茶（混合花草冲饮） 200g\",\n" +
				"            \"packageUnitCode\":\"件\",\n" +
				"            \"lot\":\"1505444444\",\n" +
				"            \"quantity\":10\n" +
				"        },\n" +
				"        {\n" +
				"            \"erpOrderLineNum\":2,\n" +
				"            \"skuNo\":\"123456658572\",\n" +
				"            \"skuName\":\"WEI-I味一 旗鱼松 105g\",\n" +
				"            \"packageUnitCode\":\"包\",\n" +
				"            \"lot\":\"1505444444\",\n" +
				"            \"quantity\":10\n" +
				"        }\n" +
				"    ]\n" +
				"}";
		Demo.demo(source);
	}
}

class RequestBean1 {
	
	private Request param_json;

	public Request getParamJson() {
		return param_json;
	}
	public void setParamJson(Request paramJson) {
		this.param_json = paramJson;
	}
	public RequestBean1(Request param_json) {
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
