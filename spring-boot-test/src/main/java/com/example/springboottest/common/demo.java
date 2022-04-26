package com.example.springboottest.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gaoshu
 * @describe demo
 * @date 2022/04/13 17:56
 **/
@Component
@Data
public class demo {
    @Value("${ol.yu.wei.booking.app.url}")
    private String name;
}
