import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * @Title: UserTest
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/15 15:15
 */
public class RefundTest {


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
            String sFormId = "AR_REFUNDBILL";
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
                    "    \"IsAutoSubmitAndAudit\": \"true\",\n" +
                    "    \"Model\": {\n" +
                    "        \"FID\": 0,\n" +
                    "        \"FBillTypeID\": {\n" +
                    "            \"FNUMBER\": \"SKTKDLX01_SYS\"\n" +
                    "        },\n" +
                    "        \"FDATE\": \"2021-08-23 00:00:00\",\n" +
                    "        \"FCONTACTUNITTYPE\": \"BD_Customer\",\n" +
                    "        \"FISINIT\": false,\n" +
                    "        \"FCONTACTUNIT\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FSETTLERATE\": 1.0,\n" +
                    "        \"FDOCUMENTSTATUS\": \"Z\",\n" +
                    "        \"FRECTUNITTYPE\": \"BD_Customer\",\n" +
                    "        \"FRECTUNIT\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FCURRENCYID\": {\n" +
                    "            \"FNumber\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FSETTLEORGID\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FSALEORGID\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FSALEDEPTID\": {\n" +
                    "            \"FNumber\": \"BM000014\"\n" +
                    "        },\n" +
                    "        \"FSALEERID\": {\n" +
                    "            \"FNumber\": \"001_GW000022_1\"\n" +
                    "        },\n" +
                    "        \"FBUSINESSTYPE\": \"1\",\n" +
                    "        \"FEXCHANGERATE\": 1.0,\n" +
                    "        \"FCancelStatus\": \"A\",\n" +
                    "        \"FPAYORGID\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FISSAMEORG\": true,\n" +
                    "        \"FSETTLECUR\": {\n" +
                    "            \"FNUMBER\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FISB2C\": false,\n" +
                    "        \"FIsWriteOff\": false,\n" +
                    "        \"FISCARRYRATE\": false,\n" +
                    "        \"FSETTLEMAINBOOKID\": {\n" +
                    "            \"FNUMBER\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FREFUNDBILLENTRY\": [\n" +
                    "            {\n" +
                    "                \"FSETTLETYPEID\": {\n" +
                    "                    \"FNumber\": \"JSFS01_SYS\"\n" +
                    "                },\n" +
                    "                \"FPURPOSEID\": {\n" +
                    "                    \"FNumber\": \"SFKYT01_SYS\"\n" +
                    "                },\n" +
                    "                \"FREFUNDAMOUNTFOR\": 1254.0,\n" +
                    "                \"FREFUNDAMOUNTFOR_E\": 1254.0,\n" +
                    "                \"FNOTE\": \"这是退款的额备注\",\n" +
                    "                \"FREFUNDAMOUNT_E\": 1254.0,\n" +
                    "                \"FPOSTDATE\": \"2021-08-23 00:00:00\",\n" +
                    "                \"FISPOST\": true,\n" +
                    "                \"FRuZhangType\": \"1\",\n" +
                    "                \"FPayType\": \"A\",\n" +
                    "                \"FNOTVERIFICATEAMOUNT\": 1254.0\n" +
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
                    "    \"FormId\": \"SAL_SaleOrder\",\n" +
                    "    \"FieldKeys\": \"FBillNo,FSaleOrgId,FDocumentStatus,FCancelStatus,FCancelStatus\",\n" +
                    "    \"FilterString\": \"\",\n" +
                    "    \"OrderString\": \"\",\n" +
                    "    \"TopRowCount\": 0,\n" +
                    "    \"StartRow\": 0,\n" +
                    "    \"Limit\": 0\n" +
                    "}";
            InvokeHelper.ViewList(sFormId, sContent);
            System.out.println("hola success");
        }
    }

    private static void getListTest() throws Exception {
        String sFormId = "";
        String sContent = "{\n" +
                "    \"FormId\": \"BD_Empinfo\",\n" +
                "    \"FieldKeys\": \"FName,FForbidStatus,FCreateDate,FModifyDate,FMobile,FEmail,FUseOrgId,FPOSTID,FPost,FPost.FName,fid\",\n" +
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
