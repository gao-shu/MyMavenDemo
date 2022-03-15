import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.List;

/**
 * @Title: UserTest
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/15 15:15
 */
public class ProductTest {


    static String dbId = "60dbce6d0de376";
    static String uid = "demo";
    static String pwd = "666666";
    static int lang = 2052;




    public static void main(String[] args) throws Exception {
//        saveOrUpdate();
//        getOne();
        getList();
//        getListTest();
//        getListTest2();
//        login(dbId, uid, pwd, lang);


    }

    private static void login(String dbId, String user, String pwd, int lang){
        JSONArray jParas = new JSONArray();
        jParas.put(dbId);// 帐套Id
        jParas.put(user);// 用户名
        jParas.put(pwd);// 密码
        jParas.put(lang);// 语言
        JSONObject object = new JSONObject();
        object.set("dbId", dbId);
        object.set("user", user);
        object.set("pwd", pwd);
        object.set("lang", lang);
//        jParas.put(dbId);// 帐套Id
//        jParas.put(user);// 用户名
//        jParas.put(pwd);// 密码
//        jParas.put(lang);// 语言

        String result = HttpRequest.post("http://119.3.232.131/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc")
                .cookie("")
                .body(object.toString())
                .timeout(2000)
                .execute().body();
        System.out.println(result);
    }
    /**
     *
     * @throws Exception
     */
    private static void saveOrUpdate() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "BD_Empinfo";
            String sContent = "{\n" +
                    "    \"NeedUpDateFields\": [],\n" +
                    "    \"NeedReturnFields\": [],\n" +
                    "    \"IsDeleteEntry\": \"true\",\n" +
                    "    \"SubSystemId\": \"\",\n" +
                    "    \"IsVerifyBaseDataField\": \"false\",\n" +
                    "    \"IsEntryBatchFill\": \"true\",\n" +
                    "    \"ValidateFlag\": \"true\",\n" +
                    "    \"NumberSearch\": \"true\",\n" +
                    "    \"IsAutoAdjustField\": \"false\",\n" +
                    "    \"InterationFlags\": \"\",\n" +
                    "    \"Model\": {\n" +
                    "        \"FID\": 0,\n" +
                    "        \"FName\": \"我是张三2\",\n" +
                    "        \"FStaffNumber\": \"113\",\n" +
                    "        \"FUseOrgId\": {\n" +
                    "            \"FNumber\": \"101\"\n" +
                    "        },\n" +
                    "        \"FCreateOrgId\": {\n" +
                    "            \"FNumber\": \"101\"\n" +
                    "        },\n" +
                    "        \"FCreateSaler\": false,\n" +
                    "        \"FCreateUser\": false,\n" +
                    "        \"FCreateCashier\": false,\n" +
                    "        \"FJoinDate\": \"2021-07-15 00:00:00\",\n" +
                    "        \"FSHRMapEntity\": {},\n" +
                    "        \"FPostEntity\": [\n" +
                    "            {\n" +
                    "                \"FWorkOrgId\": {\n" +
                    "                    \"FNumber\": \"101\"\n" +
                    "                },\n" +
                    "                \"FPostDept\": {\n" +
                    "                    \"FNumber\": \"BM000016\"\n" +
                    "                },\n" +
                    "                \"FPost\": {\n" +
                    "                    \"FNumber\": \"GW000023\"\n" +
                    "                },\n" +
                    "                \"FStaffStartDate\": \"2021-07-15 00:00:00\",\n" +
                    "                \"FIsFirstPost\": true\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";
            InvokeHelper.Save(sFormId, sContent);
            System.out.println("hola success");
        }
    }

    private static void getOne() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "BD_Empinfo";
            String sContent = "{\n" +
                    "    \"CreateOrgId\": 0,\n" +
                    "    \"Number\": \"112\",\n" +
                    "    \"Id\": \"\"\n" +
                    "}";
            InvokeHelper.View(sFormId, sContent);
            System.out.println("hola success");
        }
    }

    private static void getList() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "";
            String sContent = "{\n" +
                    "    \"FormId\": \"BD_MATERIAL\",\n" +
                    "    \"FieldKeys\": \"FName,FNumber,FBaseUnitId.Fname,FBaseUnitId.Fnumber,FDescription,FCreatorId,FCreateDate,FModifyDate,FNETWEIGHT,FMATERIALID,FUseOrgId,FUseOrgId.Fnumber,FUseOrgId.Fname\",\n" +
//                    "    \"FilterString\": \" FMATERIALID = 110449\",\n" +
//                    "    \"FilterString\": \" FName = '40*62灰色60克'\",\n" +
//                    "    \"FilterString\": \" FName = '40*62增白55克'\",\n" +
//                    "    \"FilterString\": \" FName = '50×82增白55g覆膜'\",\n" +
//                    "    \"FilterString\": \" FUseOrgId = 1 and FForbidStatus = 'A' and FCreatorId = '101465'\",\n" +
                    "    \"FilterString\": \"  FUseOrgId = 1  and FForbidStatus = 'A' and FDocumentStatus = 'C'\",\n" +
                    "    \"OrderString\": \"\",\n" +
                    "    \"TopRowCount\": 0,\n" +
                    "    \"StartRow\": 0,\n" +
                    "    \"Limit\": 0\n" +
                    "}";
            String products = InvokeHelper.ViewList(sFormId, sContent);
            List<List> productsList = JSONUtil.toList(JSONUtil.parseArray(products), List.class);
//            File file = new File("C:\\Users\\Administrator\\Desktop\\1.txt");
//            FileUtil.cleanEmpty(file);
//            FileUtil.appendUtf8String(products, file);

            System.out.println("hola success");
        }
    }

    private static void getListTest() throws Exception {
        String sFormId = "";
        String sContent = "{\n" +
                "    \"FormId\": \"BD_MATERIAL\",\n" +
                "    \"FieldKeys\": \"\",\n" +
                "    \"FilterString\": \"\",\n" +
                "    \"OrderString\": \"\",\n" +
                "    \"TopRowCount\": 0,\n" +
                "    \"StartRow\": 0,\n" +
                "    \"Limit\": 0\n" +
                "}";
        String result = InvokeHelper.ViewList(sFormId, sContent);

        System.out.println(result);
        System.out.println("hola success");
    }


}
