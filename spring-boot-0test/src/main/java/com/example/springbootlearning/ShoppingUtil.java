package com.example.springbootlearning;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.kakarote.crm.entity.VO.FengQiaoPushRouteVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Configuration
public class ShoppingUtil {

    private static RestTemplate restTemplate;

    @Autowired
    public void getRestTemplate(RestTemplate restTemplate){
        ShoppingUtil.restTemplate = restTemplate;
    }

    public static final String UAT_URL = "https://ol-gate-api.uat.vshanghai.tech";
    public static final String PRE_URL = "https://ol-gate.pre.vshanghai.tech";
    public static final String PRO_URL = "https://gate.jinjiangonline.net";

    @Value("${ol.yu.wei.booking.app.url:http://ol-yuwei-booking-app:9035/mgmt_inner}")
    private static String host = null;

    /**
     * 商城接口成功响应码
     */

    public static final Integer SUCCESS_CODE = 200;
    public static final Integer PUSH_NUM = 3;
    /**
     * 物流状态推送接口
     */
    public static final String PUSH_LOGISTICS_STATUS_URL= "/order/logistics/status";

    /**
     * 物流状态推送接口
     */
    public static final String PUSH_OUTBOUND_STATUS_URL= "/order/cold/chain/status";

    // 物流状态接口
    public static final String LOGISTICS_STATUS = "/ol-yuwei-booking-app/mgmt_inner/order/logistics";

    public static JSONObject pushLogisticsStatusToTheShopping(FengQiaoPushRouteVO pushRouteVO){
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        //设置请求体
        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("orderNo", pushRouteVO.getOrderNo());
        bodyMap.put("logisticsNo", pushRouteVO.getMailno());
        bodyMap.put("logisticsCompanyName", "顺丰");
        bodyMap.put("opCode",pushRouteVO.getOpCode());
        bodyMap.put("opCodeName", pushRouteVO.getOpCodeName());
        bodyMap.put("remark",pushRouteVO.getRemark());
        bodyMap.put("acceptAddress",pushRouteVO.getAcceptAddress());
        bodyMap.put("acceptTime",pushRouteVO.getAcceptTime());
        bodyMap.put("estimatedDeliveryTime", pushRouteVO.getEstimatedDeliveryTime());
//        ResponseEntity<JSONObject> stringResponseEntity = restTemplate.postForEntity(ShoppingUtil.UAT_URL+ShoppingUtil.PUSH_LOGISTICS_STATUS_URL, bodyMap, JSONObject.class);
        ResponseEntity<JSONObject> stringResponseEntity = restTemplate.postForEntity(ShoppingUtil.host+ShoppingUtil.PUSH_LOGISTICS_STATUS_URL, bodyMap, JSONObject.class);
        JSONObject body = stringResponseEntity.getBody();
        log.error("出货单物流状态推送给商城报文：" + JSONUtil.parseObj(bodyMap));
        log.error("出货单物流状态推送给商城返回报文：" + body.getString("msg"));
        return body;
    }

    public static JSONObject pushOutboundStatusToTheShopping(FengQiaoPushRouteVO pushRouteVO){
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        //设置请求体
        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("orderNo", pushRouteVO.getOrderNo());
        bodyMap.put("logisticsNo", pushRouteVO.getMailno());
        bodyMap.put("logisticsCompanyName", "顺丰");
        bodyMap.put("opCode",pushRouteVO.getOpCode());
        bodyMap.put("opCodeName", pushRouteVO.getOpCodeName());
        bodyMap.put("remark",pushRouteVO.getRemark());
        bodyMap.put("acceptAddress",pushRouteVO.getAcceptAddress());
        bodyMap.put("acceptTime",pushRouteVO.getAcceptTime());
        bodyMap.put("estimatedDeliveryTime", pushRouteVO.getEstimatedDeliveryTime());
        ResponseEntity<JSONObject> stringResponseEntity = restTemplate.postForEntity(ShoppingUtil.UAT_URL+ShoppingUtil.PUSH_OUTBOUND_STATUS_URL, bodyMap, JSONObject.class);
        JSONObject body = stringResponseEntity.getBody();
        log.error("出货单状态推送给商城报文：" + JSONUtil.parseObj(bodyMap));
        log.error("出货单状态推送给商城返回报文：" + body.getString("msg"));
        return body;
    }
}
