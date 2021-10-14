import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * @Title: other
 * @Description:
 * @author: gaoshu
 * @date: 2021/8/30 16:57
 */
public class other {
    public static void main(String[] args) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportFormId", "AR_AgingAnalysis");
        System.out.println("req--url-->" + "http://119.3.232.131/K3Cloud/KingdeeExtendWebApi.sdccExtendService.ExecuteService,KingdeeExtendWebApi.common.kdsvc");
        System.out.println("req--map-->" + paramMap);
        System.out.println("req--JSONUtil.parseObj(paramMap)-->" + JSONUtil.parseObj(paramMap));
        String result= HttpUtil.post("http://119.3.232.131/K3Cloud/KingdeeExtendWebApi.sdccExtendService.ExecuteService,KingdeeExtendWebApi.common.kdsvc", JSONUtil.parseObj(paramMap));
        String result1= HttpUtil.post("http://119.3.232.131/K3Cloud/KingdeeExtendWebApi.sdccExtendService.ExecuteService,KingdeeExtendWebApi.common.kdsvc", new JSONObject().set("reportFormId", "AR_AgingAnalysis"),1000);

        HttpRequest post = HttpUtil.createPost("http://119.3.232.131/K3Cloud/KingdeeExtendWebApi.sdccExtendService.ExecuteService,KingdeeExtendWebApi.common.kdsvc");
        HttpRequest body = post.body("{\n" +
                "    \"reportFormId\":\"AR_AgingAnalysis\"\n" +
                "}");
        post.header("Content-Type", "text/html; charset=utf-8");
        post.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        HttpResponse execute = post.execute();
        System.out.println(execute.body());





        System.out.println("res-->" + result);
        System.out.println("res1-->" + result1);
    }
    public void demo1(){

    }
}
