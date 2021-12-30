package com.gaoshu.springbootmybatisplus.common;

public enum SexEnum {

        MAN(0, "男"),
        WOMAN(1, "女"),
        OTHER(2, "未知");

        private int code;
        private String msg;
        SexEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static String getMsgByCode(int code) {
            for (SexEnum m : SexEnum.values()) {
                if (m.getCode() == code) {
                    return m.getMsg();
                }
            }
            return OTHER.msg;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
}
