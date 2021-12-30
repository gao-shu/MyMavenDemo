import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Title: InvokeHelper
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/14 18:26
 */
public class InvokeHelper {
    // K3 Cloud WebSite URL Example "http://192.168.19.113/K3Cloud/"
    public static String POST_K3CloudURL = "http://119.3.232.131/K3Cloud/";

    // Cookie 值
    private static String CookieVal = "123";


//    private static String dbId = "6135ceac73672e";
//    private static String uid = "demo";
//    private static String pwd = "666666";
    // crm测试001
//    private static String dbId = "613b19bcd51010";
//    private static String uid = "demo";
//    private static String pwd = "888888";
    // 临沂包装有限公司
//    private static String uid = "demo";
//    private static String dbId = "613704993c0e98";
//    private static String pwd = "888888";

    // 正式环境
    private static String uid = "crm";
    private static String dbId = "613704993c0e98";
    private static String pwd = "888888";

    // 11-9开发
//    private static String uid = "crm";
//    private static String dbId = "6189d1a09f4efb";
//    private static String pwd = "888888";

    private static int lang = 2052;

    private static Map map = new HashMap();
    static {
        map.put("Save",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc");
        map.put("View",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.View.common.kdsvc");
        map.put("Submit",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Submit.common.kdsvc");
        map.put("Audit",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Audit.common.kdsvc");
        map.put("UnAudit",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.UnAudit.common.kdsvc");
        map.put("StatusConvert",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.StatusConvert.common.kdsvc");
        map.put("ViewList",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.ExecuteBillQuery.common.kdsvc");
        map.put("Delete",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Delete.common.kdsvc");
    }

    // HttpURLConnection
    private static HttpURLConnection initUrlConn(String url, JSONArray paras)
            throws Exception {
        URL postUrl = new URL(POST_K3CloudURL.concat(url));
        HttpURLConnection connection = (HttpURLConnection) postUrl
                .openConnection();
        if (CookieVal != null) {
            connection.setRequestProperty("Cookie", CookieVal);
        }
        if (!connection.getDoOutput()) {
            connection.setDoOutput(true);
        }
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        DataOutputStream out = new DataOutputStream(
                connection.getOutputStream());

        UUID uuid = UUID.randomUUID();
        int hashCode = uuid.toString().hashCode();

        JSONObject jObj = new JSONObject();

        jObj.put("format", 1);
        jObj.put("useragent", "ApiClient");
        jObj.put("rid", hashCode);
        jObj.put("parameters", chinaToUnicode(paras.toString()));
        jObj.put("timestamp", new Date().toString());
        jObj.put("v", "1.0");

        out.writeBytes(jObj.toString());
        out.flush();
        out.close();

        return connection;
    }

    // Login
    public static boolean Login()
            throws Exception {

        boolean bResult = false;

        String sUrl = "Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc";

        JSONArray jParas = new JSONArray();
        jParas.put(dbId);// 帐套Id
        jParas.put(uid);// 用户名
        jParas.put(pwd);// 密码
        jParas.put(lang);// 语言

        HttpURLConnection connection = initUrlConn(sUrl, jParas);
// 获取Cookie
        String key = null;
        for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
            if (key.equalsIgnoreCase("Set-Cookie")) {
                String tempCookieVal = connection.getHeaderField(i);
                if (tempCookieVal.startsWith("kdservice-sessionid")) {
                    CookieVal = tempCookieVal;
                    break;
                }
            }
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ");
        System.out.println(" ============================= ");
        while ((line = reader.readLine()) != null) {
            String sResult = new String(line.getBytes(), "utf-8");
            System.out.println(sResult);
            bResult = line.contains("\"LoginResultType\":1");
        }
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ends ");
        System.out.println(" ============================= ");
        reader.close();

        connection.disconnect();

        return bResult;
    }

    // Save
    public static String Save(String formId, String content) throws Exception {
        return Invoke("Save", formId, content);
    }

    // View
    public static String View(String formId, String content) throws Exception {
        return Invoke("View", formId, content);
    }

    // Submit
    public static String Submit(String formId, String content) throws Exception {
        return Invoke("Submit", formId, content);
    }

    // Audit
    public static String Audit(String formId, String content) throws Exception {
        return Invoke("Audit", formId, content);
    }

    // UnAudit
    public static String UnAudit(String formId, String content) throws Exception {
        return Invoke("UnAudit", formId, content);
    }

    // StatusConvert
    public static String StatusConvert(String formId, String content)
            throws Exception {
        return Invoke("StatusConvert", formId, content);
    }

    // viewList
    public static String ViewList(String formId, String content)
            throws Exception {
        return Invoke("ViewList", formId, content);
    }

    // delete
    public static String Delete(String formId, String content)
            throws Exception {
        return Invoke("Delete", formId, content);
    }


    static String getUrl(String deal){
        return map.get(deal).toString();
    }

    private static String Invoke(String deal, String formId, String content)
            throws Exception {

        String sUrl = map.get(deal).toString();
        JSONArray jParas = new JSONArray();
        if (formId != null && formId != "") {
            jParas.put(formId);
        }
        jParas.put(content);

        HttpURLConnection connectionInvoke = initUrlConn(sUrl, jParas);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connectionInvoke.getInputStream()));

        String line;
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ");
        System.out.println(" ============================= ");
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            String sResult = new String(line.getBytes(), "utf-8");
            sb.append(sResult);
            System.out.println(sResult);
        }
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ends ");
        System.out.println(" ============================= ");
        reader.close();

        connectionInvoke.disconnect();
        // 验证下;
        if (sb.toString().contains("MsgCode")) {
            String[] msgCodes = sb.toString().split("MsgCode");
            if (msgCodes[1].charAt(2) == 1) {
                Login();
                Invoke(deal, formId, content);
            }
        }
//        JSONArray objects = JSONUtil.parseArray(sb.toString());
//        Optional.ofNullable(objects.get(0)).orElse("");
//        if (objects.get(0) != null) {
//            JSONArray jsonArray = JSONUtil.parseArray(objects.get(0));
//            if (jsonArray.get(0) != null) {
//                System.out.println(jsonArray.get(0));
//                if (JSONUtil.parseObj(jsonArray.get(0)).get("Result") != null) {
//                    String result = JSONUtil.parseObj(jsonArray.get(0)).get("Result").toString();
//                    if (JSONUtil.parseObj(result).get("ResponseStatus") != null) {
//                        if (JSONUtil.parseObj(result).getJSONObject("ResponseStatus").get("MsgCode").toString() != "0") {
//                            Login("60dbce6d0de376", "demo", "666666", 2052);
//                            Invoke(deal, formId, content);
//                        }
//                    }
//                }
//            }
//        }
        return sb.toString();
    }

    /**
     * 把中文转成Unicode码
     *
     * @param str
     * @return
     */
    public static String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }
}
