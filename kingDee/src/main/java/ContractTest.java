import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * @Title: ContractTest
 * @Description: 销售单测试
 * @author: gaoshu
 * @date: 2021/8/11 14:50
 */
public class ContractTest {


    public static void main(String[] args) throws Exception {
        saveOrUpdate();
//        getOne();
//        getList();
    }

    /**
     *
     * @throws Exception
     */
    private static void saveOrUpdate() throws Exception {
        if (InvokeHelper.Login()) {
            String sFormId = "SAL_SaleOrder";
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
                    "            \"FNUMBER\": \"XSDD01_SYS\"\n" +
                    "        },\n" +
                    "        \"FBillNo\": \"666555\"," +
                    "        \"FDate\": \""+ DateUtil.beginOfDay(DateUtil.date())+"\",\n" +
                    "        \"FSaleOrgId\": {\n" +
                    "            \"FNumber\": \"100\"\n" +
                    "        },\n" +
                    "        \"FCustId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FReceiveId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FSalerId\": {\n" +
                    "            \"FNumber\": \"123456_GW000001_1\"\n" +
                    "        },\n" +
                    "        \"FSettleId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FChargeId\": {\n" +
                    "            \"FNumber\": \"2021-2\"\n" +
                    "        },\n" +
                    "        \"FISINIT\": false,\n" +
                    "        \"FIsMobile\": false,\n" +
                    "        \"F_sdccMFFs\": {\n" +
                    "            \"FNumber\": \"02\"\n" +
                    "        },\n" +
                    "        \"F_sdccMFFshi\": {\n" +
                    "            \"FNumber\": \"0202\"\n" +
                    "        },\n" +
                    "        \"F_sdccMFFxian\": {\n" +
                    "            \"FNumber\": \"020202\"\n" +
                    "        },\n" +
                    "        \"F_sdcc_mff_xxdz\": \"这个地址\",\n" +
                    "        \"F_sdcc_mff_lxr\": \"张三\",\n" +
                    "        \"F_sdcc_mff_lxdh\": \"13200000000\"," +
                    "        \"F_sdcc_mff_gylx\": \"工艺路线：\",\n" +
                    "        \"F_sdcc_mff_skqk\": \"A\",\n" +
                    "        \"F_sdcc_dtzfh\": false,\n" +
                    "        \"F_sdcc_dzcp\": null,\n" +
                    "        \"F_sdcc_zdwlgs\": false,\n" +
                    "        \"F_sdcc_fhfs\": \"客户自提\",\n" +
                    "        \"F_sdcc_ypfkd\": false,\n" +
                    "        \"F_abc_BillFrom\": \"A\",\n" +
                    "        \"FSaleOrderFinance\": {\n" +
                    "            \"FSettleCurrId\": {\n" +
                    "                \"FNumber\": \"PRE001\"\n" +
                    "            },\n" +
                    "            \"FIsPriceExcludeTax\": true,\n" +
                    "            \"FIsIncludedTax\": true,\n" +
                    "            \"FExchangeTypeId\": {\n" +
                    "                \"FNumber\": \"HLTX01_SYS\"\n" +
                    "            },\n" +
                    "            \"FOverOrgTransDirect\": false\n" +
                    "        },\n" +
                    "        \"FSaleOrderEntry\": [\n" +
                    "{\n" +
                    "                \"FRowType\":\"Standard\",\n" +
                    "                \"FMaterialId\":{\n" +
                    "                    \"FNumber\":\"0000000000000002\"\n" +
                    "                },\n" +
                    "                \"FUnitID\":{\n" +
                    "                    \"FNumber\":\"m\"\n" +
                    "                },\n" +
                    "                \"FQty\":1.00,\n" +
                    "                \"FPriceUnitId\":{\n" +
                    "                    \"FNumber\":\"m\"\n" +
                    "                },\n" +
                    "                \"FPrice\":133.00,\n" +
                    "                \"FTaxPrice\":133.00,\n" +
                    "                \"FIsFree\":false,\n" +
                    "                \"FDeliveryDate\":\"2021-08-19 16:51:19\",\n" +
                    "                \"FStockOrgId\":{\n" +
                    "                    \"FNumber\":\"\"\n" +
                    "                },\n" +
                    "                \"FSettleOrgIds\":{\n" +
                    "                    \"FNumber\":\"100\"\n" +
                    "                },\n" +
                    "                \"FSupplyOrgId\":{\n" +
                    "                    \"FNumber\":\"\"\n" +
                    "                },\n" +
                    "                \"FOwnerTypeId\":\"BD_OwnerOrg\",\n" +
                    "                \"FOwnerId\":{\n" +
                    "                    \"FNumber\":\"\"\n" +
                    "                },\n" +
                    "                \"FReserveType\":\"1\",\n" +
                    "                \"FPriceBaseQty\":1.00,\n" +
                    "                \"FStockUnitID\":{\n" +
                    "                    \"FNumber\":\"m\"\n" +
                    "                },\n" +
                    "                \"FStockQty\":1.00,\n" +
                    "                \"FStockBaseQty\":1.00,\n" +
                    "                \"FOUTLMTUNIT\":\"SAL\",\n" +
                    "                \"FOutLmtUnitID\":{\n" +
                    "                    \"FNumber\":\"m\"\n" +
                    "                },\n" +
                    "                \"FISMRP\":false,\n" +
                    "                \"F_sdcc_bzQty\":0.00,\n" +
                    "                \"F_sdcc_AllWeight\":0.00,\n" +
                    "                \"FOrderEntryPlan\":[\n" +
                    "                    {\n" +
                    "                        \"FPlanDate\":\"2021-08-19 16:51:19\",\n" +
                    "                        \"FPlanQty\":1.00\n" +
                    "                    }\n" +
                    "                ]\n" +
                    "            }        ],\n" +
                    "        \"FSaleOrderPlan\": [\n" +
                    "            {\n" +
                    "                \"FNeedRecAdvance\": false,\n" +
                    "                \"FRecAdvanceRate\": 0,\n" +
                    "                \"FRecAdvanceAmount\": 133.00,\n" +
                    "                \"FIsOutStockByRecamount\": false\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";

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
//                    "        \"FID\": 0,\n" +
//                    "        \"FBillTypeID\": {\n" +
//                    "            \"FNUMBER\": \"XSDD01_SYS\"\n" +
//                    "        },\n" +
//                    "        \"FDate\": \"2021-08-19 00:00:00\",\n" +
//                    "        \"FSaleOrgId\": {\n" +
//                    "            \"FNumber\": \"100\"\n" +
//                    "        },\n" +
//                    "        \"FCustId\": {\n" +
//                    "            \"FNumber\": \"2021-2\"\n" +
//                    "        },\n" +
//                    "        \"FReceiveId\": {\n" +
//                    "            \"FNumber\": \"2021-2\"\n" +
//                    "        },\n" +
//                    "        \"FSalerId\": {\n" +
//                    "            \"FNumber\": \"123456_GW000001_1\"\n" +
//                    "            \"FNumber\": \"123456_GW000001_1\"\n" +
////                    "            \"FNumber\": \"001_GW000022_1\"\n" +
//                    "        },\n" +
//                    "        \"FSettleId\": {\n" +
//                    "            \"FNumber\": \"2021-2\"\n" +
//                    "        },\n" +
//                    "        \"FChargeId\": {\n" +
//                    "            \"FNumber\": \"2021-2\"\n" +
//                    "        },\n" +
//                    "        \"FISINIT\": false,\n" +
//                    "        \"FIsMobile\": false,\n" +
//                    "        \"F_sdcc_mff_gylx\": \"工艺路线：\",\n" +
//                    "        \"F_sdcc_mff_skqk\": \"A\",\n" +
//                    "        \"F_sdcc_dtzfh\": false,\n" +
//                    "        \"F_sdcc_dzcp\": null,\n" +
//                    "        \"F_sdcc_zdwlgs\": false,\n" +
//                    "        \"F_sdcc_fhfs\": \"客户自提\",\n" +
//                    "        \"F_sdcc_ypfkd\": false,\n" +
//                    "        \"F_abc_BillFrom\": \"A\",\n" +
//                    "        \"FSaleOrderFinance\": {\n" +
//                    "            \"FSettleCurrId\": {\n" +
//                    "                \"FNumber\": \"PRE001\"\n" +
//                    "            },\n" +
//                    "            \"FIsPriceExcludeTax\": true,\n" +
//                    "            \"FIsIncludedTax\": true,\n" +
//                    "            \"FExchangeTypeId\": {\n" +
//                    "                \"FNumber\": \"HLTX01_SYS\"\n" +
//                    "            },\n" +
//                    "            \"FOverOrgTransDirect\": false\n" +
//                    "        },\n" +
//                    "        \"FSaleOrderEntry\": [\n" +
//                    "{\n" +
//                    "                \"FRowType\":\"Standard\",\n" +
//                    "                \"FMaterialId\":{\n" +
//                    "                    \"FNumber\":\"0000000000000002\"\n" +
//                    "                },\n" +
//                    "                \"FUnitID\":{\n" +
//                    "                    \"FNumber\":\"m\"\n" +
//                    "                },\n" +
//                    "                \"FQty\":1.00,\n" +
//                    "                \"FPriceUnitId\":{\n" +
//                    "                    \"FNumber\":\"m\"\n" +
//                    "                },\n" +
//                    "                \"FPrice\":133.00,\n" +
//                    "                \"FTaxPrice\":133.00,\n" +
//                    "                \"FIsFree\":false,\n" +
//                    "                \"FDeliveryDate\":\"2021-08-19 14:03:27\",\n" +
//                    "                \"FStockOrgId\":{\n" +
//                    "                    \"FNumber\":\"\"\n" +
//                    "                },\n" +
//                    "                \"FSettleOrgIds\":{\n" +
//                    "                    \"FNumber\":\"100\"\n" +
//                    "                },\n" +
//                    "                \"FSupplyOrgId\":{\n" +
//                    "                    \"FNumber\":\"\"\n" +
//                    "                },\n" +
//                    "                \"FOwnerTypeId\":\"BD_OwnerOrg\",\n" +
//                    "                \"FOwnerId\":{\n" +
//                    "                    \"FNumber\":\"\"\n" +
//                    "                },\n" +
//                    "                \"FReserveType\":\"1\",\n" +
//                    "                \"FPriceBaseQty\":1.00,\n" +
//                    "                \"FStockUnitID\":{\n" +
//                    "                    \"FNumber\":\"m\"\n" +
//                    "                },\n" +
//                    "                \"FStockQty\":1.00,\n" +
//                    "                \"FStockBaseQty\":1.00,\n" +
//                    "                \"FOUTLMTUNIT\":\"SAL\",\n" +
//                    "                \"FOutLmtUnitID\":{\n" +
//                    "                    \"FNumber\":\"m\"\n" +
//                    "                },\n" +
//                    "                \"FISMRP\":false,\n" +
//                    "                \"F_sdcc_bzQty\":0.00,\n" +
//                    "                \"F_sdcc_AllWeight\":0.00,\n" +
//                    "                \"FOrderEntryPlan\":[\n" +
//                    "                    {\n" +
//                    "                        \"FPlanDate\":\"2021-08-19 14:03:27\",\n" +
//                    "                        \"FPlanQty\":1.00\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }        ],\n" +
//                    "        \"FSaleOrderPlan\": [\n" +
//                    "            {\n" +
//                    "                \"FNeedRecAdvance\": false,\n" +
//                    "                \"FRecAdvanceRate\": 0,\n" +
//                    "                \"FRecAdvanceAmount\": 133.00,\n" +
//                    "                \"FIsOutStockByRecamount\": false\n" +
//                    "            }\n" +
//                    "        ]\n" +
//                    "    }\n" +
//                    "}";
            String result = InvokeHelper.Save(sFormId, sContent);
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
                    "    \"FormId\": \"SAL_SaleOrder\",\n" +
                    "    \"FieldKeys\": \"FBillNo\",\n" +
                    "    \"FilterString\": \"\",\n" +
//                    "    \"FilterString\": \"FNumber = 'CUST0005'\",\n" +
                    "    \"OrderString\": \"\",\n" +
                    "    \"TopRowCount\": 0,\n" +
                    "    \"StartRow\": 0,\n" +
                    "    \"Limit\": 0\n" +
                    "}";
            String result = InvokeHelper.ViewList(sFormId, sContent);
            List<List> ts = JSONUtil.toList(JSONUtil.parseArray(result), List.class);
//            ts.forEach(list -> System.out.println(list.get(0)+""+list.get(1)));
            System.out.println(ts);
            System.out.println("hola success");
        }
    }
}
