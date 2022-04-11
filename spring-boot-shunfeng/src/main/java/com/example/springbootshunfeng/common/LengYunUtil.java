package com.example.springbootshunfeng.common;

import com.google.gson.Gson;
import com.sf.coms.gateway.encryption.AESCipher;
import com.sf.coms.gateway.encryption.HmacSha512CoderFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
public class LengYunUtil {

    @Value("${sfly.url}")
    public static String url;

    @Value("${sfly.source}")
    public static String source;

    @Value("${sfly.appToken}")
    public static String appToken;

    @Value("${sfly.v}")
    public static String v;

    @Value("${sfly.userToken}")
    public static String userToken;

    /**
     * 顺丰冷运相关数据
     */
    public static final int TIMEOUT = 30000;
    public static final String CHARSET = "UTF-8";
    public static final String AES256_KEY = "alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";
    public static final String MACSHA_512 = "W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";
    public static final String OUTBOUND = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=outbound&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=sfdemotoken&format=json";
    public static final String INBOUND = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=inbound&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=sfdemotoken&format=json";

    /**
     * 给冷运传输数据
     * @param source
     * @param lengYunEnum
     * @return
     * @throws IOException
     */
    public static String postLengYun(String source, LengYunEnum lengYunEnum) throws IOException {
        System.out.println(lengYunEnum.getMsg() + "传参检验:" + source );
        String urlSource = URLEncoder.encode(source, "UTF-8");
        AESCipher aesciphe = new AESCipher("alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg".getBytes("UTF-8"));
        String encrySource = aesciphe.getEncryptedMessage(urlSource);
        System.out.println("加密报文:" + encrySource);
        String sourceDiges = HmacSha512CoderFactory.getHmacSha512Coder("W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6", encrySource);
        String encrySourceEncode = URLEncoder.encode(encrySource, "UTF-8");
        String sourceDigesEncode = URLEncoder.encode(sourceDiges, "UTF-8");
        RequestBean request = new RequestBean(new RequestBean.Request(encrySourceEncode, sourceDigesEncode));
        String json = (new Gson()).toJson(request);
        System.err.println(json);
        String response = post(lengYunEnum.getUrl(), json);
        System.out.println("远程接口响应:" + response);
        return response;
    }


    /**
     * 请求冷运接口并处理数据
     * @param url
     * @param param
     * @return
     */
    public static String post(String url, String param) {
        String result = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost postmethod = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000).setSocketTimeout(30000).build();
            postmethod.setConfig(requestConfig);
            postmethod.addHeader("content-type", "application/json");
            postmethod.setEntity(new StringEntity(param, "UTF-8"));
            response = httpclient.execute(postmethod);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException var16) {
                var16.printStackTrace();
            }

        }
        return result;
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
