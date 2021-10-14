import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

/**
 * @Title: test1
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/15 14:51
 */
public class test1 {
        @Test
        public void demo(){
            JSONObject jsonObject = JSONUtil.parseObj("{\"FormId\":\"PUR_PurchaseOrder\",\"TopRowCount\":0,\"Limit\":10,\"StartRow\":0,\"FilterString\":\"FMaterialId.FNumber='HG_TEST'\",\"OrderString\":\"FID ASC\",\"FieldKeys\":\"FID,FSupplierId,FMaterialId,FMaterialId.FNumber,FMaterialName\"}");
            System.out.println(jsonObject);
        }
}
