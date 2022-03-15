import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * @Title: UserTest
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/15 15:15
 */
public class ReceivablesTest {


    static String dbId = "60dbce6d0de376";
    static String uid = "demo";
    static String pwd = "666666";
    static int lang = 2052;




    public static void main(String[] args) throws Exception {
//        saveOrUpdate();
//        saveOrUpdate2();
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
            String sFormId = "AR_RECEIVEBILL";
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
                    "        \"FBillNo\": 667,\n" +
                    "        \"FBillTypeID\": {\n" +
                    "            \"FNUMBER\": \"SKDLX01_SYS\"\n" +
                    "        },\n" +
                    "        \"FDATE\": \"2021-08-23 00:00:00\",\n" +
                    "        \"FCONTACTUNITTYPE\": \"BD_Customer\",\n" +
                    "        \"FCONTACTUNIT\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FPAYUNITTYPE\": \"BD_Customer\",\n" +
                    "        \"FPAYUNIT\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FCURRENCYID\": {\n" +
                    "            \"FNumber\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FPAYORGID\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FSETTLERATE\": 1.0,\n" +
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
                    "        \"FDOCUMENTSTATUS\": \"Z\",\n" +
                    "        \"FBUSINESSTYPE\": \"1\",\n" +
                    "        \"FISINIT\": false,\n" +
                    "        \"FEXCHANGERATE\": 1.0,\n" +
                    "        \"FCancelStatus\": \"A\",\n" +
                    "        \"FSETTLECUR\": {\n" +
                    "            \"FNUMBER\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FISB2C\": false,\n" +
                    "        \"FIsWriteOff\": false,\n" +
                    "        \"FSETTLEMAINBOOKID\": {\n" +
                    "            \"FNUMBER\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FISCARRYRATE\": false,\n" +
                    "        \"FRECEIVEBILLENTRY\": [\n" +
                    "            {\n" +
                    "                \"FSETTLETYPEID\": {\n" +
                    "                    \"FNumber\": \"JSFS01_SYS\"\n" +
                    "                },\n" +
                    "                \"FPURPOSEID\": {\n" +
                    "                    \"FNumber\": \"SFKYT01_SYS\"\n" +
                    "                },\n" +
                    "                \"FRECTOTALAMOUNTFOR\": 123.0,\n" +
                    "                \"FRECAMOUNTFOR_E\": 123.0,\n" +
                    "                \"FRECAMOUNT_E\": 123.0,\n" +
                    "                \"FPOSTDATE\": \"2021-08-23 00:00:00\",\n" +
                    "                \"FNOTVERIFICATEAMOUNT\": 123.0\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";
            InvokeHelper.Save(sFormId, sContent);
            System.out.println("hola success");
        }
    }
    /**
     *
     * @throws Exception
     */
    private static void saveOrUpdate2() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "AR_RECEIVEBILL";
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
                    "        \"FBillTypeID\": {\n" +
                    "            \"FNUMBER\": \"SKDLX01_SYS\"\n" +
                    "        },\n" +
                    "        \"FDATE\": \"2021-09-28 00:00:00\",\n" +
                    "        \"FCONTACTUNITTYPE\": \"BD_Customer\",\n" +
                    "        \"FCONTACTUNIT\": {\n" +
                    "            \"FNumber\": \"KH-20210927-23\"\n" +
                    "        },\n" +
                    "        \"FPAYUNITTYPE\": \"BD_Customer\",\n" +
                    "        \"FPAYUNIT\": {\n" +
                    "            \"FNumber\": \"KH-20210927-23\"\n" +
                    "        },\n" +
                    "        \"FCURRENCYID\": {\n" +
                    "            \"FNumber\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FPAYORGID\": {\n" +
                    "            \"FNumber\": \"109\"\n" +
                    "        },\n" +
                    "        \"FSETTLERATE\": 1.0,\n" +
                    "        \"FSETTLEORGID\": {\n" +
                    "            \"FNumber\": \"109\"\n" +
                    "        },\n" +
                    "        \"FSALEORGID\": {\n" +
                    "            \"FNumber\": \"109\"\n" +
                    "        },\n" +
                    "        \"FSALEDEPTID\": {\n" +
                    "            \"FNumber\": \"BM000016\"\n" +
                    "        },\n" +
                    "        \"FSALEERID\": {\n" +
                    "            \"FNumber\": \"yg-1_GW000027_100098\"\n" +
                    "        },\n" +
                    "        \"FDOCUMENTSTATUS\": \"Z\",\n" +
                    "        \"FBUSINESSTYPE\": \"1\",\n" +
                    "        \"FISINIT\": false,\n" +
                    "        \"FEXCHANGERATE\": 1.0,\n" +
                    "        \"FCancelStatus\": \"A\",\n" +
                    "        \"FSETTLECUR\": {\n" +
                    "            \"FNUMBER\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FISB2C\": false,\n" +
                    "        \"FIsWriteOff\": false,\n" +
                    "        \"FSETTLEMAINBOOKID\": {\n" +
                    "            \"FNUMBER\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FISCARRYRATE\": false,\n" +
                    "        \"FRECEIVEBILLENTRY\": [\n" +
                    "            {\n" +
                    "                \"FSETTLETYPEID\": {\n" +
                    "                    \"FNumber\": \"JSFS07_SYS\"\n" +
                    "                },\n" +
                    "                \"FPURPOSEID\": {\n" +
                    "                    \"FNumber\": \"SFKYT01_SYS\"\n" +
                    "                },\n" +
                    "                \"FPOSTDATE\": \"2021-09-28 00:00:00\",\n" +
                    "                \"F_mff_skdz\": false\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"FBILLRECEIVABLEENTRY\": [\n" +
                    "            {\n" +
                    "                \"FBILLID\": {\n" +
                    "                    \"FNumber\": \"BR0004\"\n" +
                    "                },\n" +
                    "                \"FUSEDAMOUNTFOR\": 25.00,\n" +
                    "                \"FBILLPARAMOUNT\": 25.00,\n" +
                    "                \"FPARLEFTAMOUNTSTD\": 25.00,\n" +
                    "                \"FUSEDAMOUNTSTD\": 25.00,\n" +
                    "                \"FTempOrgId\": {\n" +
                    "                    \"FNumber\": \"109\"\n" +
                    "                }\n" +
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
                    "    \"FormId\": \"AR_RECEIVEBILL\",\n" +
                    "    \"FieldKeys\": \"FBillNo,FSaleOrgId,FDocumentStatus,FCancelStatus,FCancelStatus\",\n" +
//                    "    \"FilterString\": \"\",\n" +
                    "    \"FilterString\": \"FID  = '117781'\",\n" +
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
