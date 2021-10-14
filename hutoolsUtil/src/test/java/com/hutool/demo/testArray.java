package com.hutool.demo;

import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.ArrayUtil;
import org.junit.Test;

/**
 * @Title: testArray
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/9 17:18
 */
public class testArray {

    private static final Integer[] arr =  {1, 2, 3, 4, 5, 6};
    private static final Integer[] arr2 =  {8, 9, 10};
    @Test
    public void demo1(){
        System.out.println( ArrayUtil.isAllEmpty(arr));
    }
    
    @Test
    public void demo2(){
        System.out.println(ArrayUtil.addAll(arr, arr2).toString());
    }
    
    @Test
    public void demo3(){
        System.out.println(ArrayUtil.filter(arr, (Editor<Integer>) t -> (t % 2==0)? t : 0));
    }
}
