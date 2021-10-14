import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * @Title: UserTest
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/15 15:15
 */
public class ReturnsTest {


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
            String sFormId = "SAL_RETURNSTOCK";
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
                    "            \"FNUMBER\": \"XSTHD01_SYS\"\n" +
                    "        },\n" +
                    "        \"FDate\": \"2021-08-23 00:00:00\",\n" +
                    "        \"FSaleOrgId\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FRetcustId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FSaledeptid\": {\n" +
                    "            \"FNumber\": \"BM000014\"\n" +
                    "        },\n" +
                    "        \"FSalesManId\": {\n" +
                    "            \"FNumber\": \"001_GW000022_1\"\n" +
                    "        },\n" +
                    "        \"FStockOrgId\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FReceiveCustId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FSettleCustId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FPayCustId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FOwnerTypeIdHead\": \"BD_OwnerOrg\",\n" +
                    "        \"FIsTotalServiceOrCost\": false,\n" +
                    "        \"SubHeadEntity\": {\n" +
                    "            \"FSettleCurrId\": {\n" +
                    "                \"FNumber\": \"PRE001\"\n" +
                    "            },\n" +
                    "            \"FSettleOrgId\": {\n" +
                    "                \"FNumber\": \"100\"\n" +
                    "            },\n" +
                    "            \"FLocalCurrId\": {\n" +
                    "                \"FNumber\": \"PRE001\"\n" +
                    "            },\n" +
                    "            \"FExchangeTypeId\": {\n" +
                    "                \"FNumber\": \"HLTX01_SYS\"\n" +
                    "            },\n" +
                    "            \"FExchangeRate\": 1.0\n" +
                    "        },\n" +
                    "        \"FEntity\": [\n" +
                    "            {\n" +
                    "                \"FRowType\": \"Standard\",\n" +
                    "                \"FMaterialId\": {\n" +
                    "                    \"FNumber\": \"0000000000000002\"\n" +
                    "                },\n" +
                    "                \"FUnitID\": {\n" +
                    "                    \"FNumber\": \"Pcs\"\n" +
                    "                },\n" +
                    "                \"FRealQty\": 125.0,\n" +
                    "                \"FIsFree\": false,\n" +
                    "                \"FEntryTaxRate\": 13.00,\n" +
                    "                \"FReturnType\": {\n" +
                    "                    \"FNumber\": \"THLX01_SYS\"\n" +
                    "                },\n" +
                    "                \"FOwnerTypeId\": \"BD_OwnerOrg\",\n" +
                    "                \"FOwnerId\": {\n" +
                    "                    \"FNumber\": \"100\"\n" +
                    "                },\n" +
                    "                \"FStockId\": {\n" +
                    "                    \"FNumber\": \"CK001\"\n" +
                    "                },\n" +
                    "                \"FStockstatusId\": {\n" +
                    "                    \"FNumber\": \"KCZT01_SYS\"\n" +
                    "                },\n" +
                    "                \"FDeliveryDate\": \"2021-08-23 00:00:00\",\n" +
                    "                \"FNote\": \"退货备注\",\n" +
                    "                \"FSalUnitID\": {\n" +
                    "                    \"FNumber\": \"Pcs\"\n" +
                    "                },\n" +
                    "                \"FSalUnitQty\": 125.0,\n" +
                    "                \"FSalBaseQty\": 125.0,\n" +
                    "                \"FPriceBaseQty\": 125.0,\n" +
                    "                \"FIsOverLegalOrg\": false,\n" +
                    "                \"FARNOTJOINQTY\": 125.0,\n" +
                    "                \"FIsReturnCheck\": false,\n" +
                    "                \"FSettleBySon\": false\n" +
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
                    "    \"FormId\": \"SAL_RETURNSTOCK\",\n" +
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
