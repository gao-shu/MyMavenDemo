package DemoEnum;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum DemoEnum {
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    A1(4, "A+"),
    A2(5, "A"),
    A3(6, "A-"),
    B1(7, "B+"),
    B2(8, "B"),
    B3(9, "B-"),
    C1(10, "C+"),
    C2(11, "C"),
    C3(12, "C-");

    private Integer code;//等级代码
    private String level;//等级

    DemoEnum(Integer code, String level) {
        this.code = code;
        this.level = level;
    }

    public static List<String> parses() {
        ArrayList<String> list = new ArrayList<>();
        for (DemoEnum demoEnum : DemoEnum.values()) {
            list.add(demoEnum.level);
        }
        return list;
    }


}
