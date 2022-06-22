package com.example.springbootdemo1;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
public class ShoppingUtil {


    public static final String UAT_URL = "https://ol-gate-api.uat.vshanghai.tech";
    public static final String PRE_URL = "https://ol-gate.pre.vshanghai.tech";
    public static final String PRO_URL = "https://gate.jinjiangonline.net";

    @Value("${ol.yu.wei.booking.app.url:http://ol-yuwei-booking-app:9035/mgmt_inner}")
    private static String host;

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

    public static String q232() {
        System.out.println(host);
        return host;
    }
}
