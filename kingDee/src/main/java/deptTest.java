/**
 * @Title: UserTest
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/15 15:15
 */
public class deptTest {


    static String dbId = "60dbce6d0de376";
    static String uid = "demo";
    static String pwd = "666666";
    static int lang = 2052;



    public static void main(String[] args) throws Exception {
//        saveOrUpdate();
//        getOne();
        getList();
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
                    "    \"FormId\": \"BD_Department\",\n" +
                    "    \"FieldKeys\": \"FParentID,FDEPTID,FName,FUseOrgId,FForbidStatus,FUseOrgId,FUseOrgId.Fnumber\",\n" +
                    "    \"FilterString\": \"FDEPTID = '16593'\",\n" +
                    "    \"OrderString\": \"\",\n" +
                    "    \"TopRowCount\": 0,\n" +
                    "    \"StartRow\": 0,\n" +
                    "    \"Limit\": 0\n" +
                    "}";
            InvokeHelper.ViewList(sFormId, sContent);
            System.out.println("hola success");
        }
    }
}
