import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.junit.Test;

public class demo01 {

    @Test
    public void demo(){
        String strip = StrUtil.strip("[123]", "]");
        String strip2 = StrUtil.strip("[123]", "[", "]");
        String strip3 = StringUtil.strip("[123]", "[]");
        System.out.println(strip);
        System.out.println(strip2);
    }


    @Test
    public void demo1(){
        System.out.println(234);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
