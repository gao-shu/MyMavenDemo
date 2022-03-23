import Entity.Person;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

//    private BigDecimal num;
//   @Test
//   public void demo10(){
//
//       BigDecimal oldDeliveryNum = BigDecimal.ZERO;
//       BigDecimal b = BigDecimal.ZERO;
//       BigDecimal deliveryNum = (oldDeliveryNum.subtract(oldCrmContractDelivery.getNum())).add(crmContractDelivery.getNum());
////       System.out.println(bigDecimal.setScale(4));
//   }

    @Test
    public void demo10(){
        ConcurrencyTester concurrencyTester = ThreadUtil.concurrencyTest(5, () -> {
            saveCustomer();
        });
    }

    public void saveCustomer(){
        String url = "http://127.0.0.1:8443/crmCustomer/add";
        String json = "{\"entity\":{\"num\":\"\",\"customerName\":\""+ RandomUtil.getRandom() +"\",\"fgroupId\":\"\",\"ownerUserId\":17558,\"mobile\":\"\",\"telephone\":\"\",\"salesVolume\":\"\",\"peopleNum\":\"\",\"demandAmount\":\"\",\"nextTime\":\"\",\"remark\":\"\",\"address\":\"河南省,洛阳市,老城区\",\"detailAddress\":\"洛阳市老城区兴华街中州人民医院东侧约170米老城十字街夜市\",\"location\":\"洛阳市老城区兴华街中州人民医院东侧约170米老城十字街夜市\",\"lng\":112.48526950037781,\"lat\":34.689474324251215},\"field\":[{\"fieldName\":\"source\",\"fieldType\":2,\"name\":\"客户来源\",\"type\":3,\"fieldId\":1101828,\"value\":\"\"},{\"fieldName\":\"industry\",\"fieldType\":2,\"name\":\"客户行业\",\"type\":3,\"fieldId\":1101832,\"value\":\"\"},{\"fieldName\":\"level\",\"fieldType\":2,\"name\":\"客户级别\",\"type\":3,\"fieldId\":1101833,\"value\":\"\"},{\"fieldName\":\"fliedJeqgso\",\"fieldType\":0,\"name\":\"联系人姓名\",\"type\":1,\"fieldId\":1101976,\"value\":\"联系人\"},{\"fieldName\":\"fliedDncmme\",\"fieldType\":0,\"name\":\"部门\",\"type\":12,\"fieldId\":1102051,\"value\":\"\"},{\"fieldName\":\"fliedEmxwgd\",\"fieldType\":0,\"name\":\"明细表格\",\"type\":45,\"fieldId\":1102061,\"value\":[]}]}";
        String result = HttpRequest.post(url)
                .header("Admin-Token", "84b4336f89544d93a8176659554e9c8f")
                .body(json)
                .timeout(20000)
                .execute().body();
    }


    @Test
    public void demo11(){
        String url = "http://127.0.0.1:8443/crmKingdee/queryListWarehouse";
//        String json = "{\"entity\":{\"num\":\"\",\"customerName\":\""+ RandomUtil.getRandom() +"\",\"fgroupId\":\"\",\"ownerUserId\":17558,\"mobile\":\"\",\"telephone\":\"\",\"salesVolume\":\"\",\"peopleNum\":\"\",\"demandAmount\":\"\",\"nextTime\":\"\",\"remark\":\"\",\"address\":\"河南省,洛阳市,老城区\",\"detailAddress\":\"洛阳市老城区兴华街中州人民医院东侧约170米老城十字街夜市\",\"location\":\"洛阳市老城区兴华街中州人民医院东侧约170米老城十字街夜市\",\"lng\":112.48526950037781,\"lat\":34.689474324251215},\"field\":[{\"fieldName\":\"source\",\"fieldType\":2,\"name\":\"客户来源\",\"type\":3,\"fieldId\":1101828,\"value\":\"\"},{\"fieldName\":\"industry\",\"fieldType\":2,\"name\":\"客户行业\",\"type\":3,\"fieldId\":1101832,\"value\":\"\"},{\"fieldName\":\"level\",\"fieldType\":2,\"name\":\"客户级别\",\"type\":3,\"fieldId\":1101833,\"value\":\"\"},{\"fieldName\":\"fliedJeqgso\",\"fieldType\":0,\"name\":\"联系人姓名\",\"type\":1,\"fieldId\":1101976,\"value\":\"联系人\"},{\"fieldName\":\"fliedDncmme\",\"fieldType\":0,\"name\":\"部门\",\"type\":12,\"fieldId\":1102051,\"value\":\"\"},{\"fieldName\":\"fliedEmxwgd\",\"fieldType\":0,\"name\":\"明细表格\",\"type\":45,\"fieldId\":1102061,\"value\":[]}]}";
        String json = "";
        HttpUtil.post(url, json);
    }

    @Test
    public void demo12(){
        ArrayList<Integer> aa = new ArrayList<>();
        aa.add(1);
        aa.add(2);
        aa.add(3);
        for (Integer integer : aa) {
            for (Integer integer1 : aa) {
                if (integer > integer1) {
                    continue;
                }
                System.out.println("integer1"+integer1);
            }
            System.out.println("integer"+integer);
        }
    }

    @Test
    public void demo13(){
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        Collection<Integer> result2 = CollectionUtil.union(list1, list2);
        System.out.println(list2+""+list1+"并集"+result2);
        Collection<Integer> result3 = CollectionUtil.intersection(list1, list2);
        System.out.println(list2+""+list1+"交集"+result3);
        Collection<Integer> result4 = CollectionUtil.subtractToList(list1, list2);
        System.out.println(list2+""+list1+"交集"+result4);
        List<Integer> result5 = CollectionUtil.subtractToList(list2, list1);
        System.out.println(list2+""+list1+"交集"+result5);
        Collection<Integer> result6 = CollectionUtil.subtract(list2, list1);
        System.out.println(list2+""+list1+"交集"+result6);


    }

    @Test
    public void demo14(){
        String s = "[{children=[{children=[{label=A1-1, value=A1-1}, {label=A1-2, value=A1-2}, {label=A2-3, value=A2-3}], label=A1, value=A1}, {children=[{label=A2-1, value=A2-1}, {label=A2-2, value=A2-2}, {label=A2-3, value=A2-3}], label=A2, value=A2}, {children=[{label=A3-1, value=A3-1}, {label=A3-2, value=A3-2}, {label=A3-3, value=A3-3}], label=A3, value=A3}], label=A, value=A}, {children=[{label=A1-1, value=A1-1}, {label=A1-2, value=A1-2}, {label=A2-3, value=A2-3}], label=A1, value=A1}, {label=A2-3, value=A2-3}]";
        Object parse = JSONObject.parse(s);
    }

    @Test
    public void demo15(){
        Person person = new Person();
        person.setAge(2);
        person.setName("张三");
        person.setAddress("");
        JSON json = JSONUtil.parse(person);
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(person, false);
        System.out.println(json);
        System.out.println(jsonObject);
    }

    @Test
    public void demo16() throws InterruptedException {
        DateTime date = DateUtil.date();
        System.out.println(date);
//        Thread.sleep(3000);
        System.out.println(date.getTime());
        System.out.println();
        System.out.println(DateUtil.date(date.getTime()));
        System.out.println(DateUtil.date(0));
    }
}
