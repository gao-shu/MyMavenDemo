import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.sun.xml.internal.ws.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class demo02 {
    public static void main(String[] args) throws IOException {
        // 读取文件
        Properties properties = new Properties();
        InputStream stream = Object.class.getResourceAsStream("/db.properties");
        properties.load(stream);
        Object username = properties.get("username");
        System.out.println(username.toString());
//        Props props = new Props("db.properties");

    }
}
