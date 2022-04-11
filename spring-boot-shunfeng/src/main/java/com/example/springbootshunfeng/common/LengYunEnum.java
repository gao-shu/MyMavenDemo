package com.example.springbootshunfeng.common;

public enum LengYunEnum {

    INBOUND("inbound","入库单", ""),
    INBOUNDCANCEL("inboundcancel","入库单取消", ""),
    INBOUNDQUERY("inboundquery","入库单查询", ""),
    OUTBOUND("outbound","出库单接受",
            "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=outbound&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&userToken=sfdemotoken"),
    OUTBOUNDCANCEL("outboundcancel","出库单取消",
            "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=outboundcancel&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&userToken=sfdemotoken"),
    OUTBOUNDDETAILQUERY("outbounddetailquery","出库单明细查询",
            "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=outbounddetailquery&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&userToken=sfdemotoken"),
    INVENTORYQUERY("inventoryquery","库存查询", ""),
    ROUTEQUERY("routequery","路由查询",
            "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=routequery&source=sfdemo&appToken=sfdemoapptoken&v=1.0&timestamp=123456789&userToken=sfdemotoken"),
    ERROR("error", "error", "");

    private String code;
    private String msg;
    private String url;
    LengYunEnum(String code, String msg, String url) {
        this.code = code;
        this.msg = msg;
        this.url = url;
    }
    public static String msg(String code) {
        for (LengYunEnum m : LengYunEnum.values()) {
            if (m.getCode().equals(code)) {
                return m.getMsg();
            }
        }
        return ERROR.getMsg();
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
