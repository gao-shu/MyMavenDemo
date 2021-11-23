import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title: test
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/19 11:31
 */
public class test {
    @Test
    public void demo1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(1);
        list.add(1, 100);
        list.add(2, 100);

        System.out.println(list);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("1", 1);
        hashMap.put("1", 1);
        hashMap.put("1", 1);
        hashMap.put("1", 1);
        System.out.println(hashMap);
        hashMap.clear();
        System.out.println(hashMap);
    }

    @Test
    public void demo2(){
        List<Integer> arr = new ArrayList<>();
         arr.add(null);
        System.out.println(arr.size());

    }

    @Test
    public void demo3(){
        String result = "{\"Result\":{\"ResponseStatus\":{\"ErrorCode\":500,\"IsSuccess\":false,\"Errors\":[{\"FieldName\":null,\"Message\":\"“demo”使用业务单据：“销售订单”业务操作-“[销售订单-2021-28-修改]”冲突，请稍候再使用。\",\"DIndex\":0}],\"SuccessEntitys\":[],\"SuccessMessages\":[],\"MsgCode\":0}}}";
        boolean isSuccess = JSONUtil.parseObj(result).getJSONObject("Result").getJSONObject("ResponseStatus").getBool("IsSuccess");
        System.out.println(isSuccess);

        String Message = JSONUtil.parseObj(result).getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors").getJSONObject(0).getStr("Message");
        System.out.println(Message);

    }

   @Test
   public void demo4(){
       String s1 = "{\"垫付\":[1021],\"代收\":[1021]}";
       String s2 = "选1,选2,选3";
       Set<String> strings = JSONUtil.parseObj(s1).keySet();
       String join = CollectionUtil.join(strings, ",");
       System.out.println(join);
       if ((false && true)) {
           System.out.println(1);
       }else {
           System.out.println(2);
       }

   }

   @Test
   public void demo5(){
       String s = "2021-08-10 09:41:54.297";
//       String s = "2021-08-10T09:42:56.07Z";
//       String s = "2021-08-10 09:42:56.07Z";
       DateTime parse = DateUtil.parse(s);
       System.out.println(parse);
   }

   @Test
   public void demo6(){
       JSONArray objects = JSONUtil.parseArray("[[106168,\"灰袋子仓库\",\"A\",\"CK005\",\"领杰包装有限公司\",\"101\"],[106169,\"白袋子仓\",\"A\",\"CK006\",\"领杰包装有限公司\",\"101\"],[106170,\"打包带仓\",\"A\",\"CK007\",\"领杰包装有限公司\",\"101\"],[106171,\"打包带仓\",\"A\",\"CK002\",\"打包带公司\",\"102\"],[106172,\"和信仓\",\"A\",\"CK002\",\"和信包装公司\",\"103\"],[106173,\"商丘仓库\",\"A\",\"CK002\",\"商丘公司\",\"104\"],[106174,\"辽宁仓\",\"A\",\"CK002\",\"辽宁分仓\",\"105\"],[106175,\"湖北分仓\",\"A\",\"CK002\",\"湖北分仓\",\"106\"],[106176,\"广东分仓\",\"A\",\"CK002\",\"广东分仓\",\"107\"],[106177,\"江西分仓\",\"A\",\"CK002\",\"江西分仓\",\"108\"],[106178,\"西安\",\"A\",\"CK001\",\"西安\",\"109\"],[106179,\"临沂市场仓\",\"A\",\"CK001\",\"临沂市场部\",\"110\"],[106180,\"王力公司仓\",\"A\",\"CK001\",\"王力公式\",\"111\"],[106181,\"小祝公司仓\",\"A\",\"CK001\",\"小祝公式\",\"112\"],[112156,\"A仓\",\"A\",\"CK007\",\"领杰集团\",\"100\"],[114350,\"B仓库\",\"A\",\"CK002\",\"西安\",\"109\"],[114351,\"C仓库\",\"A\",\"CK003\",\"西安\",\"109\"]]");
       System.out.println(objects);
   }

   @Test
   public void demo7(){
       String s = "[[{\"Result\":{\"ResponseStatus\":{\"ErrorCode\":500,\"IsSuccess\":false,\"Errors\":[{\"FieldName\":null,\"Message\":\"会话信息已丢失，请重新登录\",\"DIndex\":0}],\"SuccessEntitys\":[],\"SuccessMessages\":[],\"MsgCode\":1}}}]]";
       String[] msgCodes = s.split("MsgCode");
       if ("1".equals(msgCodes[1].substring(2, 3))) {
           System.out.println(2);
       }
   }

   @Test
   public void demo8(){
       String addressKd = ",江苏省,";
       String[] split = addressKd.toString().split(",");
       System.out.println(split.length);
   }

   @Test
   public void demo9(){
       List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
       strings.stream().peek(r -> r = r + "234").collect(Collectors.toList());
//       strings.stream().map((String)s -> System.out.println(s));
       strings.forEach(s -> System.out.println(s));
   }

}
