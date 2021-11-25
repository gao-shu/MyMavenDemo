package com.gaoshu.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Title: MyMetaObjectHandler
 * @Description: mybatis-plus 默认插入处理
 * @author: gaoshu
 * @date: 2021/11/24 19:36
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    public static final String FIELD_CREATE_TIME = "createTime";
    public static final String FIELD_UPDATE_TIME = "updateTime";
    public static final String FIELD_CREATE_USER = "createUserId";
    public static final String FIELD_TEXT = "sex";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, FIELD_TEXT,  String.class, "男");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, FIELD_TEXT,  String.class, "女");
    }
}
