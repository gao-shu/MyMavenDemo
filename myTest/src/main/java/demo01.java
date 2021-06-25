import DemoEnum.DemoEnum;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

public class demo01 {

    @Test
    public void demo(){
        if (ReUtil.isMatch("^[0-9]+(\\.[0-9]{1,2})?$", "7.00")) {
            System.out.println(true);
        }
    }


    @Test
    public void demo1(){
        System.out.println(234);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    @Test
    public void demo2(){
        String s = StrUtil.join("' ,'", DemoEnum.parses());
        System.out.println(s);
    }
}
