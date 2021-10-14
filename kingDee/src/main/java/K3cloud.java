import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: K3cloud
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/15 14:14
 */
public class K3cloud {

    //生成登录的json串
    public static String buildLogin(String dbid, String userName, String password, int lang){
        Map<String,Object> param = new HashMap<>(4);
        param.put("acctID",dbid);
        param.put("username",userName);
        param.put("password",password);
        param.put("lcid",lang);
//        return JSON.toJSONString(param);
        return JSONUtil.toJsonStr(param);
    }
    //保存的json
    public static String buildMaterial(String template,String formid) {
        JSONObject basic = JSONUtil.parseObj(template);
//        JSONObject basic = JSONObject.(template);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("formid",formid);
//        jsonObject.put("data",JSON.toJSONString(basic));
        jsonObject.put("data",JSONUtil.toJsonStr(basic));
//        return JSON.toJSONString(jsonObject);
        return JSONUtil.toJsonStr(jsonObject);

    }

//    @Scheduled(fixedRate=200000)
    public static String login() throws IOException{
        // 替换自己的id号  用户名 和 密码
        String loginParam = buildLogin("60dbce6d0de376", "demo", "666666", 2052);
        String url = "http://119.3.232.131/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc";
        String post = HttpUtil.post(url, loginParam);
//        ResponseEntity<String> responseEntity = HttpUtil.httpPost(url, loginParam);
        String login_cookie="";
//        if(response.getStatusCode()== HttpStatus.OK) {
//            Set<String> keys=response.getHeaders().keySet();
//            for(String key:keys){
//                if (key.equalsIgnoreCase("Set-Cookie")) {
//                    List<String> cookies = response.getHeaders().get(key);
//                    for(String cookie:cookies){
//                        if(cookie.startsWith("kdservice-sessionid")){
//                            login_cookie=cookie;
//                            System.out.println("登录K3成功啦："+login_cookie);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
        return login_cookie;
    }

    public static void main(String[] args) throws IOException {
        String cookie = login();
//        save(cookie);
//        submit(cookie);
//        audit(cookie);
    }

    // 需替换api复制下来的json串 参数需自己拼
    // 复制api中的表单ID
    //替换api相应的url
    //替换自己的单据编号
//    public static void save(String cookie) {
//
//        String jsons = " 复制api提供的json串 " ;
//        String url = "http://***.***.***.***/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc";
//
//        String material = buildMaterial(jsons, "表单ID");
//        Map<String, Object> header = new HashMap<>();
//        header.put("Cookie",cookie);
//
//        String result = HttpUtil.httpPost(url, header, material);
//        JSONObject jsonObject = JSON.parseObject(result);
//        System.out.println("保存结果："+jsonObject);
//
//    }
}
