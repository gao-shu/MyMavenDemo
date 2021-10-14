import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * @Title: CustomerTest
 * @Description: 客户测试
 * @author: gaoshu
 * @date: 2021/8/11 14:50
 */
public class expressTest {


    public static void main(String[] args) throws Exception {
//        saveOrUpdate();
////        getOne();
        getList();
//        delete();

    }

    /**
     *
     * @throws Exception
     */
    private static void saveOrUpdate() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "BD_Customer";
//            String sContent = "{\n" +
//                    "    \"NeedUpDateFields\": [],\n" +
//                    "    \"NeedReturnFields\": [],\n" +
//                    "    \"IsDeleteEntry\": \"true\",\n" +
//                    "    \"SubSystemId\": \"\",\n" +
//                    "    \"IsVerifyBaseDataField\": \"false\",\n" +
//                    "    \"IsEntryBatchFill\": \"true\",\n" +
//                    "    \"ValidateFlag\": \"true\",\n" +
//                    "    \"NumberSearch\": \"true\",\n" +
//                    "    \"IsAutoAdjustField\": \"false\",\n" +
//                    "    \"InterationFlags\": \"\",\n" +
//                    "    \"Model\": {\n" +
//                    "        \"FCUSTID\": 0,\n" +
//                    "        \"FCreateOrgId\": {\n" +
//                    "            \"FNumber\": \"100\"\n" +
//                    "        },\n" +
//                    "        \"FUseOrgId\": {\n" +
//                    "            \"FNumber\": \"100\"\n" +
//                    "        },\n" +
//                    "        \"FName\": \"测试一个客户\",\n" +
//                    "        \"FCOUNTRY\": {\n" +
//                    "            \"FNumber\": \"China\"\n" +
//                    "        },\n" +
//                    "        \"FINVOICETITLE\": \"测试一个客户\",\n" +
//                    "        \"FIsGroup\": false,\n" +
//                    "        \"FIsDefPayer\": false,\n" +
//                    "        \"FCustTypeId\": {\n" +
//                    "            \"FNumber\": \"KHLB001_SYS\"\n" +
//                    "        },\n" +
//                    "        \"FTRADINGCURRID\": {\n" +
//                    "            \"FNumber\": \"PRE001\"\n" +
//                    "        },\n" +
//                    "        \"FSALDEPTID\": {\n" +
//                    "            \"FNumber\": \"BM000014\"\n" +
//                    "        },\n" +
//                    "        \"FSELLER\": {\n" +
//                    "            \"FNumber\": \"001_GW000022_1\"\n" +
//                    "        },\n" +
//                    "        \"FInvoiceType\": \"1\",\n" +
//                    "        \"FTaxType\": {\n" +
//                    "            \"FNumber\": \"SFL02_SYS\"\n" +
//                    "        },\n" +
//                    "        \"FPriority\": 1,\n" +
//                    "        \"FTaxRate\": {\n" +
//                    "            \"FNumber\": \"SL02_SYS\"\n" +
//                    "        },\n" +
//                    "        \"FISCREDITCHECK\": true,\n" +
//                    "        \"FIsTrade\": true,\n" +
//
//                    "        \"F_sdcc_Text2\": \"11\",\n" +
//                    "        \"FT_BD_CUSTOMEREXT\": {\n" +
//                    "            \"FEnableSL\": false,\n" +
//                    "            \"FPROVINCE\": {\n" +
//                    "                \"FNumber\": \"01\"\n" +
//                    "            },\n" +
//                    "            \"FALLOWJOINZHJ\": false\n" +
//                    "        },\n" +
//                    "        \"F_sdcc_shi\": {\n" +
//                    "            \"FNumber\": \"0101\"\n" +
//                    "        },\n" +
//                    "        \"F_sdcc_xianqu\": {\n" +
//                    "            \"FNumber\": \"010102\"\n" +
//                    "        }\n" +
//
//                    "    }\n" +
//                    "}";

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
                    "        \"FCUSTID\": 0,\n" +
                    "        \"FNumber\": \"2021-8\",\n" +
                    "        \"FCreateOrgId\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FUseOrgId\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FName\": \"zyp创建\",\n" +
                    "        \"FCOUNTRY\": {\n" +
                    "            \"FNumber\": \"China\"\n" +
                    "        },\n" +
                    "        \"FADDRESS\": \"郑州市郑州市\",\n" +
                    "        \"FTEL\": \"18599999999\",\n" +
                    "        \"FINVOICETITLE\": \"zyp创建\",\n" +
                    "        \"FIsGroup\": false,\n" +
                    "        \"FIsDefPayer\": false,\n" +
                    "        \"FCustTypeId\": {\n" +
                    "            \"FNumber\": \"KHLB001_SYS\"\n" +
                    "        },\n" +
                    "        \"FTRADINGCURRID\": {\n" +
                    "            \"FNumber\": \"PRE001\"\n" +
                    "        },\n" +
                    "        \"FDescription\": \"范围广泛\",\n" +
                    "        \"FInvoiceType\": \"1\",\n" +
                    "        \"FTaxType\": {\n" +
                    "            \"FNumber\": \"SFL02_SYS\"\n" +
                    "        },\n" +
                    "        \"FPriority\": 1,\n" +
                    "        \"FTaxRate\": {\n" +
                    "            \"FNumber\": \"SL02_SYS\"\n" +
                    "        },\n" +
                    "        \"FISCREDITCHECK\": true,\n" +
                    "        \"FIsTrade\": true,\n" +
                    "        \"F_sdcc_hkfs\": \"G\",\n" +
                    "        \"F_sdcc_khdj\": \"A\",\n" +
                    "        \"F_sdcc_Text\": \"测试行业\",\n" +
                    "        \"F_sdcc_Text1\": \"null\",\n" +
                    "        \"F_sdcc_Text2\": \"null\",\n" +
                    "        \"F_sdcc_Text3\": \"null\",\n" +
                    "   \"FT_BD_CUSTOMEREXT\": {\n" +
                    "            \"FEnableSL\": false,\n" +
                    "            \"FPROVINCE\": {\n" +
                    "                \"FNumber\": \"10\"\n" +
                    "            },\n" +
                    "            \"FALLOWJOINZHJ\": false\n" +
                    "        },\n" +
                    "        \"F_sdcc_shi\": {\n" +
                    "            \"FNumber\": \"1001\"\n" +
                    "        },\n" +
                    "        \"F_sdcc_xianqu\": {\n" +
                    "            \"FNumber\": \"100101\"\n" +
                    "        },\n" +
                    "        \"FSELLER\": {\n" +
                    "            \"FNumber\": \"001_GW000022_1\"\n" +
                    "        }    }\n" +
                    "}";
            String result = InvokeHelper.Save(sFormId, sContent);
//            JSON parse = JSONUtil.parse(result);
            JSONObject object = JSONUtil.parseObj(result);
            Boolean IsSuccess = object.getJSONObject("Result").getJSONObject("ResponseStatus").getBool("IsSuccess");
            Integer id = object.getJSONObject("Result").getInt("Id");
            System.out.println(IsSuccess);
            System.out.println(id);
            System.out.println("hola success");
        }
    }

    private static void getOne() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "BD_Customer";
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
                    "    \"FormId\": \"BD_Supplier\",\n" +
                    "    \"FieldKeys\": \"FNumber,FName,FForbidStatus,FGroup\",\n" +
                    "    \"FilterString\": \"\",\n" +
                    "    \"FilterString\": \"FForbidStatus = 'A' and FDocumentStatus = 'C'  " +
//                    "and FUseOrgId = '100098' " +
//                    "and FGroup = '102925'" +
                    "\",\n" +
                    "    \"OrderString\": \"\",\n" +
                    "    \"TopRowCount\": 0,\n" +
                    "    \"StartRow\": 0,\n" +
                    "    \"Limit\": 0\n" +
                    "}";
            String result = InvokeHelper.ViewList(sFormId, sContent);
            List<List> ts = JSONUtil.toList(JSONUtil.parseArray(result), List.class);
            ts.forEach(list -> System.out.println(list.get(0)+""+list.get(1)));
            System.out.println(ts);
            System.out.println("hola success");
        }
    }

    private static void delete() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "BD_Customer";
            String sContent = "{\n" +
                    "    \"CreateOrgId\": 0,\n" +
                    "    \"Numbers\": [\"CUST0040\"],\n" +
                    "    \"Ids\": \"\",\n" +
                    "    \"NetworkCtrl\": \"\"\n" +
                    "}";
            String result = InvokeHelper.Delete(sFormId, sContent);
            System.out.println(result);
            System.out.println("hola success");
        }
    }
}
