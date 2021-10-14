import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Title: test
 * @Description: 测试
 * @author: gaoshu
 * @date: 2021/7/14 11:23
 */
public class test {

    @Test
    public void demo1() throws UnsupportedEncodingException {
        String dbId = "60dbce6d0de376";// 数据中心ID
        String usserName = "demo";// 用户名称
        String appId = "hr";// 第三方系统应用Id
        String appSecret = "3c2ca0f150354a0c938e3bdf082d4984";// 第三方系统应用秘钥
        long currentTime = System.currentTimeMillis() / 1000;
        String timestamp = Long.toString(currentTime);
        String[] strArray = {dbId, usserName, appId, appSecret, timestamp};
        Arrays.sort(strArray);
        String combStr = null;
        for (int i = 0; i < strArray.length; i++) {
            if (combStr == null || combStr == "") {
                combStr = strArray[i];
            } else {
                combStr = combStr + strArray[i];
            }

        }
        byte[] strByte = combStr.getBytes("UTF-8");
        byte[] strSign = DigestUtils.sha(strByte);
        String sign = bytesToHexString(strSign);
        String urlPara = String.format("|%s|%s|%s|%s|%s|%s", dbId, usserName, appId, sign, timestamp, "2052");
        urlPara = java.net.URLEncoder.encode(urlPara, "UTF-8");
        String url = "http://localhost/K3Cloud/Silverlight/IndexSL.aspx?ud=" + urlPara;// Silverlight入口链接
        System.out.println(url);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
