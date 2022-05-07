package com.ceair.shoppingservice.common.util.address;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.neo.base.util.TreeUtils;
import com.neo.common.enums.AreaEnum;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 收货地址智能解析
 * 依赖第三方库：
 * <pre>
 * <dependency>
 *      <groupId>cn.hutool</groupId>
 *      <artifactId>hutool-all</artifactId>
 *      <version>5.5.4</version>
 * </dependency>
 * <dependency>
 *      <groupId>com.alibaba</groupId>
 *      <artifactId>fastjson</artifactId>
 *      <version>1.2.75</version>
 * </dependency>
 * <dependency>
 *      <groupId>com.google.guava</groupId>
 *      <artifactId>guava</artifactId>
 *      <version>30.1.1-jre</version>
 * </dependency>
 * <dependency>
 *      <groupId>org.projectlombok</groupId>
 *      <artifactId>lombok</artifactId>
 *      <version>1.18.18</version>
 * <scope>provided</scope>
 * </dependency>
 * <dependency>
 *      <groupId>commons-lang</groupId>
 *      <artifactId>commons-lang</artifactId>
 *      <version>2.6</version>
 * </dependency>
 * </pre>
 *
 * @author Neo
 * @since 2021/3/25 14:44
 */
@Slf4j
public class AddressUtil {

    /**
     * 自定义去除关键字，可自行添加
     */
    public static List<String> EXCLUDE_KEYS = Lists.newArrayList("详细地址", "收货地址", "收件地址", "地址", "所在地区", "地区",
            "姓名", "收货人", "收件人", "联系人", "收", "邮编",
            "联系电话", "联系电話", "电话", "电話", "联系人手机号码", "手机号码", "手机号",
            "自治区直辖县级行政区划", "省直辖县级行政区划");

    /**
     * 特殊符号正则
     */
    public static final String SPECIAL_SYMBOL_REGEX = "[`~!@#$^&*()=|{}':;',\\[\\]\\.<>/?~！@#￥……&*（）——|{}【】‘；：”“’。，、？]";
    /**
     * 手机号正则
     */
    public static final Pattern MOBILE_PATTERN = Pattern.compile("(86-[1][3-9][0-9]{9})|(86[1][3-9][0-9]{9})|([1][3-9][0-9]{9})");
    /**
     * 电话号码正则
     */
    public static final Pattern PHONE_PATTERN = Pattern.compile("(([0-9]{3,4}-)[0-9]{7,8})|([0-9]{12})|([0-9]{11})|([0-9]{10})|([0-9]{9})|([0-9]{8})|([0-9]{7})");
    /**
     * 邮编正则
     */
    public static final Pattern ZIP_CODE_PATTERN = Pattern.compile("([0-9]{6})");

    /**
     * 省市区县数据文件路径（根据实际情况调整）
     */
    public static final String FILE_PATH = "/address-parse/china-area.json";

    public static final String EMPTY = "", BLANK = " ";

    public static List<AreaTree> PROVINCE_LIST, CITY_LIST, AREA_LIST;


    static {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<String> lines = FileUtil.readUtf8Lines(AddressParse.class.getResource(FILE_PATH));
        String file = String.join(EMPTY, lines);

        List<AreaTree> areas = JSON.parseArray(file, AreaTree.class);
        Iterator<AreaTree> iterator = areas.iterator();
        while (iterator.hasNext()) {
            AreaTree next = iterator.next();
            if (AreaEnum.CITY.getCode().equals(next.getLevel()) || AreaEnum.AREA.getCode().equals(next.getLevel())) {
                if (StringUtils.length(next.getName()) <= 2) {
                    iterator.remove();
                }
                if (StringUtils.equals(next.getName(), "市辖区")) {
                    iterator.remove();
                }
            }
        }

        areas = TreeUtils.buildPath(areas, o -> Objects.equals(o.getParentCode(), 0L));
        Map<Integer, List<AreaTree>> areaMapping = areas.stream().collect(Collectors.groupingBy(AreaTree::getLevel));

        PROVINCE_LIST = areaMapping.get(AreaEnum.PROVINCE.getCode());
        CITY_LIST = areaMapping.get(AreaEnum.CITY.getCode());
        AREA_LIST = areaMapping.get(AreaEnum.AREA.getCode());
        log.info("地址解析器初始化耗时：{} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }


    /**
     * 解析主入口
     *
     * @author Neo
     * @since 2021/3/25 14:44
     */
    public static List<ParseResult> parse(String address) {
        if (StringUtils.isBlank(address)) {
            return Collections.EMPTY_LIST;
        }

        // 地址清洗
        address = cleanAddress(address);

        // 提取手机号
        String mobile = parseByPattern(MOBILE_PATTERN, address);
        address = StringUtils.replace(address, mobile, BLANK);

        // 提取电话号码
        String phone = parseByPattern(PHONE_PATTERN, address);
        address = StringUtils.replace(address, phone, BLANK);

        // 提取邮编
        String zipCode = parseByPattern(ZIP_CODE_PATTERN, address);
        address = StringUtils.replace(address, zipCode, BLANK);

        // 提取名字
        Pair<String, String> nameInfo = parseName(EMPTY, address);
        address = nameInfo.getValue();

        List<ParseResult> results = parseArea(address);


        for (ParseResult r : results) {
            r.setMobile(mobile);
            r.setPhone(phone);
            r.setZipCode(StringUtils.isBlank(r.getZipCode()) ? zipCode : r.getZipCode());
            r.setName(StringUtils.isBlank(r.getName()) ? nameInfo.getKey() : r.getName());
        }

        if (CollectionUtils.isEmpty(results)) {
            nameInfo = parseName(EMPTY, address);
            results.add(ParseResult.builder().name(nameInfo.getKey()).address(nameInfo.getValue()).build());
        }

        return results;
    }


    public static List<ParseResult> parseArea(String address) {
        List<ParseResult> results = new ArrayList<>();
        if (StringUtils.isBlank(address)) {
            return results;
        }

        // 清除两个以上的空格
        address = address.replaceAll(" {2,}", BLANK);

        // 正向解析
        results.addAll(parseByProvince(address));

        // 通过城市逆向解析
        results.addAll(parseByCity(address));

        //通过地区逆向解析
        results.addAll(parseByArea(address));


        return results;
    }

    /**
     * 通过地区逆向解析
     *
     * @author Neo
     * @since 2021/3/25 9:29
     */
    public static List<ParseResult> parseByArea(String addressBase) {
        List<ParseResult> results = new ArrayList<>();
        ParseResult result;
        String address = addressBase;
        for (AreaTree area : AREA_LIST) {
            if (StringUtils.length(area.getName()) < 2) {
                continue;
            }
            MatchResult match = match(area, address);
            if (!match.isMatch()) {
                continue;
            }

            result = new ParseResult();
            result.setProvince(area.getParent().getParent().getName());
            result.setCity(area.getParent().getName());
            result.setArea(area.getName());
            result.setZipCode(area.getZipCode());
            result.setType(AreaEnum.AREA);

            // 左侧排除省份城市名剩下的内容识别为姓名
            String leftAddress = StringUtils.left(address, match.getIndex());
            MatchResult provinceMatch = null, cityMatch = null;
            if (StringUtils.isNotBlank(leftAddress)) {
                provinceMatch = match(area.getParent().getParent(), leftAddress);
                leftAddress = provinceMatch.isMatch() ? StringUtils.remove(leftAddress, provinceMatch.getMatchName()) : leftAddress;
            }

            if (StringUtils.isNotBlank(leftAddress)) {
                cityMatch = match(area.getParent(), leftAddress);
                leftAddress = cityMatch.isMatch() ? StringUtils.remove(leftAddress, cityMatch.getMatchName()) : leftAddress;
            }

            if (StringUtils.isNotBlank(leftAddress)) {
                result.setName(StringUtils.trim(leftAddress));
            }

            // 出现同省地区匹配错误处理，广东省惠来县惠城镇 如不经处理匹配到 广东省惠州市惠城区
            if (Objects.nonNull(provinceMatch) && Objects.nonNull(cityMatch)
                    && StringUtils.isNotBlank(provinceMatch.getMatchName()) && StringUtils.isNotBlank(cityMatch.getMatchName())) {
                List<ParseResult> tempResult = parseByArea(StringUtils.left(address, match.getIndex()));
                if (CollectionUtils.isNotEmpty(tempResult)) {
                    result = ParseResult.assign(result, CollectionUtil.getFirst(tempResult.iterator()));
                    address = StringUtils.right(address, match.getIndex());


                    if (StringUtils.isBlank(result.getArea())) {
                        address = parseAreaByCity(area.getParent(), result, address);
                    }
                }
            }

            if (StringUtils.isNotBlank(result.getProvince()) && StringUtils.isNotBlank(result.getCity()) && StringUtils.isNotBlank(result.getArea())) {
                address = StringUtils.substring(address, match.getIndex() + match.getMatchNameLength());
                result.setDetail(StringUtils.trim(address));
                results.add(result);
                break;
            }
        }

        return results;
    }


    /**
     * 通过城市逆向解析
     *
     * @author Neo
     * @since 2021/3/25 9:19
     */
    public static List<ParseResult> parseByCity(String addressBase) {
        List<ParseResult> results = new ArrayList<>();
        ParseResult result;
        String address = addressBase;
        for (AreaTree city : CITY_LIST) {
            // 排除重庆市下的 500200:县
            if (StringUtils.length(city.getName()) < 2) {
                continue;
            }
            MatchResult match = match(city, address);
            if (!match.isMatch()) {
                continue;
            }

            result = new ParseResult();
            result.setProvince(city.getParent().getName());
            result.setCity(city.getName());
            result.setZipCode(city.getZipCode());
            result.setType(AreaEnum.CITY);

            // 将城市左侧的部分排除省份后剩下的内容识别为姓名
            String leftAddress = StringUtils.left(address, match.getIndex());
            if (StringUtils.isNotBlank(leftAddress)) {
                if (StringUtils.contains(leftAddress, city.getParent().getName())) {
                    leftAddress = StringUtils.remove(leftAddress, city.getParent().getName());
                } else {
                    leftAddress = StringUtils.remove(leftAddress, city.getParent().getShortName());
                }
                if (StringUtils.isNotBlank(leftAddress)) {
                    result.setName(StringUtils.trim(leftAddress));
                }
            }
            address = StringUtils.substring(address, match.getIndex() + match.getMatchNameLength());
            address = parseAreaByCity(city, result, address);

            result.setDetail(StringUtils.trim(address));

            if (StringUtils.isNotBlank(result.getProvince()) && StringUtils.isNotBlank(result.getCity())) {
                results.add(result);
            }

        }
        return results;
    }


    /**
     * 解析省份
     *
     * @author Neo
     * @since 2021/3/24 16:55
     */
    public static List<ParseResult> parseByProvince(String addressBase) {
        List<ParseResult> results = new ArrayList<>();
        ParseResult result;
        String address = addressBase;

        for (AreaTree province : PROVINCE_LIST) {
            result = new ParseResult();
            MatchResult match = match(province, address);

            if (match.isMatch()) {
                result.setProvince(province.getName());
                result.setZipCode(province.getZipCode());
                result.setType(AreaEnum.PROVINCE);

                address = StringUtils.remove(address, match.getMatchName());
            }


            // 如果省份不是第一位 在省份之前的字段识别为名称
            if (match.getIndex() > 0) {
                result.setName(StringUtils.trim(StringUtils.substring(address, 0, match.getIndex())));
                address = StringUtils.remove(address, result.getName());
            }

            if (StringUtils.isNotBlank(result.getProvince())) {
                address = parseCityByProvince(province, result, address);
            }

            if (StringUtils.isNotBlank(result.getProvince())) {
                address = parseAreaByProvince(province, result, address);
            }

            if (StringUtils.isNotBlank(result.getZipCode())) {
                result.setDetail(StringUtils.trim(address));
            }

            if (StringUtils.isNotBlank(result.getProvince())) {
                results.add(result);
            }
        }

        return results;
    }

    /**
     * 解析地区通过省份
     *
     * @author Neo
     * @since 2021/3/24 16:59
     */
    public static String parseAreaByProvince(AreaTree province, ParseResult result, String address) {
        for (AreaTree city : province.getChildren()) {
            for (AreaTree area : city.getChildren()) {
                MatchResult match = match(area, address);
                if (!match.isMatch() || match.getIndex() > 5) {
                    continue;
                }

                result.setCity(city.getName());
                result.setArea(area.getName());
                result.setZipCode(area.getZipCode());

                address = StringUtils.substring(address, match.getIndex() + match.getMatchNameLength());
            }
        }
        return address;
    }


    /**
     * 解析城市通过省份
     *
     * @author Neo
     * @since 2021/3/24 16:54
     */
    public static String parseCityByProvince(AreaTree province, ParseResult result, String address) {
        for (AreaTree city : province.getChildren()) {
            MatchResult match = match(city, address);
            if (!match.isMatch()) {
                continue;
            }

            result.setCity(city.getName());
            result.setZipCode(city.getZipCode());

            address = StringUtils.remove(address, match.getMatchName());
            address = parseAreaByCity(city, result, address);
        }

        return address;
    }


    /**
     * 提取地区通过城市
     *
     * @author Neo
     * @since 2021/3/24 16:49
     */
    public static String parseAreaByCity(AreaTree city, ParseResult result, String address) {
        for (AreaTree area : city.getChildren()) {
            MatchResult match = match(area, address);
            if (!match.isMatch()) {
                continue;
            }

            result.setArea(match.getMatchName());
            result.setZipCode(area.getZipCode());

            address = StringUtils.remove(address, match.getMatchName());
        }
        return address;
    }


    /**
     * 1. 地址清洗
     *
     * @author Neo
     * @since 2021/3/24 15:44
     */
    public static String cleanAddress(String address) {
        address = address.replaceAll("\\r\\n", BLANK)
                .replaceAll("\\n", BLANK)
                .replaceAll("\\t", BLANK)
                .replaceAll(" {2,}", BLANK)
                .replaceAll("(\\d{3})-(\\d{4})-(\\d{4})", "$1$2$3")
                .replaceAll("(\\d{3}) (\\d{4}) (\\d{4})", "$1$2$3")
        ;


        for (String search : EXCLUDE_KEYS) {
            address = address.replaceAll(search, BLANK);
        }

        address = address.replaceAll(SPECIAL_SYMBOL_REGEX, BLANK);

        return address;
    }


    /**
     * 通过正则解析数据
     *
     * @author Neo
     * @since 2021/3/24 15:45
     */
    public static String parseByPattern(Pattern pattern, String address) {
        if (Objects.isNull(pattern) || StringUtils.isBlank(address)) {
            return EMPTY;
        }
        Matcher matcher = pattern.matcher(address);
        return matcher.find() ? matcher.group(0) : EMPTY;
    }


    /**
     * 解析收货人姓名
     *
     * @author Neo
     * @since 2021/3/24 15:45
     */
    public static Pair<String, String> parseName(String name, String address) {
        if (StringUtils.isNotBlank(name)) {
            return new Pair<>(name, address);
        }


        List<String> items = Splitter.on(BLANK).trimResults().omitEmptyStrings().splitToList(address);
        if (CollectionUtils.size(items) < 2) {
            return new Pair<>(name, address);
        }
        String parseName = items.get(0);
        for (String item : items) {
            if (length(parseName) > length(item)) {
                parseName = item;
            }
        }

        String finalParseName = parseName;
        address = items.stream().filter(i -> !StringUtils.equals(i, finalParseName)).collect(Collectors.joining(BLANK));

        return new Pair<>(parseName, address);
    }

    /**
     * 统计字符串长度
     * 汉字算两位，英文一位
     *
     * @author Neo
     * @since 2021/3/24 15:40
     */
    public static int length(String str) {
        int result = 0;
        if (Objects.isNull(str) || EMPTY.equals(str)) {
            return result;
        }

        for (char c : str.toCharArray()) {
            result += c >= 0x0391 && c <= 0xFFE5 ? 2 : c <= 0x00FF ? 1 : 0;
        }
        return result;
    }


    /**
     * 地区节点匹配
     *
     * @author Neo
     * @since 2021/3/24 15:56
     */
    public static MatchResult match(AreaTree area, String address) {
        int index = StringUtils.indexOf(address, area.getName());
        boolean matchShort = false;

        if (index == -1) {
            index = StringUtils.indexOf(address, area.getShortName());
            matchShort = index > -1;
        }
        String matchName = index > -1 ? matchShort ? area.getShortName() : area.getName() : EMPTY;
        return new MatchResult(matchShort, matchName, index);
    }


    public static void main(String[] args) {
        ArrayList<String> address = Lists.newArrayList(
                "太阳鲜鲜 盐田区山海四季城F栋17A，13111111111",
                "盐田区山海四季城F栋2f，13111111111 太阳鲜鲜",
                "谢先生，深圳市龙岗区南湾街道尚峰花园4C2231 13111111111",
                "测试 江西九江市湖口县武山镇 15912344321",
                "盐田区山海四季城D栋17A\n周敏 13111111111",
                "广东省深圳市盐田区东海三街山海四季城F4E，李侯明，13111111111",
                "深圳市盐田区，大梅沙万科东海岸221栋，周女士13111111111",
                "收货人: 杨燕艳\n手机号码: 13111111111\n所在地区: 广东省深圳市龙岗区龙岗街道\n详细地址: 格水村三巷十号三楼",
                "地址:深圳市盐田区山海四季城A栋32D\n张欢 13111111111",
                "地址：深圳市龙华新区樟坑一区通博花园181栋\n收件人：于生生\n电话：13111111111",
                "所在地区: 湖南省株洲市醴陵市白兔潭镇\n详细地址: 金牛居委会金牛路5号国超\n刘娇 131 1111 1111",
                "江西南昌市青山湖区广兰大道418号东华理工大学核工系南区9栋1112室 131 1111 1111 孙轶念",
                "湖北黄石市牧羊湖水机路华瑞南岸星城一栋一单元2202。\n\n刘月红13111111111",
                "盐田区北山道山海四季城F20D 韩先生 13111111111",
                "深圳市盐田区盐田街道东海三街8号山海四季花园  曾候丽  13111111111",
                "深圳市罗湖区凤凰路中山花园1栋582室，刘蓝琴，电話13111111111",
                "袁月青13111111111四川省成都市高新西区百叶路1号电子科技大学成都学院计算机(分院)",
                "盐田区山海四季城F栋1B，卢燕13111111111",
                "地址：广东省佛山市顺德区乐从天佑城E座2005室；\n联系人：熊翠花\n联系电话：13111111111；",
                "深圳市南山区南光路龙坤居2栋D座714，收件人：张珍云，电话：13111111111",
                "深圳市盐田区万科东海岸21-102，收件人：叶侠，电话：131 1111 1111",
                "地址：深圳市 南山区 南商路碧海天家园A89B 联系电话：13111111111 黄发猜",
                "都匀市水岸绿洲小区2栋二单元2033  李玉 13111111111",
                "贵州省都匀市。甘塘镇绿茵湖村一组2号  刘雪莉     13111111111",
                "湛江市廉江市车板镇人才市场，0755-22107333.曹建林 邮编：713200",
                "广东省清远市 清城区洲心街道，金茂家园一栋一楼100号商铺   13111111111 聂小姐",
                "江苏省 苏州市 吴江区 干将东路678号江苏大厦11楼 215000 徐天宇 13911111111",
                "王小梅13911111111湖南省郴州市桂东县桂东县清泉镇",
                "莫席辉13911111111广西壮族自治区柳州市柳北区跃进路42号4栋20楼",
                "韩仁伟,13911111111,安徽省安庆市太湖县新仓镇，塔山村，前进组",
                "北京市北京市东城区建设路紫薇花园 13311111111 何晓旭",
                "13311111111 上海市黄浦区 建设路 紫薇花园  何晓旭",
                "王晓光 重庆市 垫江县 太平镇，13311111111",
                "瓦丽丽，13311111111，甘肃省 兰州市 城关区 东岗街道向阳街道",
                "刘海江13311111111河南省省直辖县级行政区划济源市沁园路丹尼斯",
                "13311111111 广东省 东莞市 中堂镇潢涌大坦村二街四巷1号",
                "韩丽丽 13311111111 广东省 东莞市 望牛墩镇赤滘村南昌南路53号",
                "蔡丽凤,13311111111,江苏省盐城市其它区神州路御景湾7#1104",
                "张彤，13311111111，黑龙江省 大兴安岭地区 加格达奇区 铁路南小区29号楼4单元5658sf",
                "黄梅, 13311111111, 江西省 抚州市 临川区 上顿渡镇江西省抚州市临川区上顿渡镇老公安局",
                "何花菊，86-13311111111，辽宁省 盘锦市 盘山县 东郭镇 辽宁省盘锦市盘山县东郭镇 ，000000",
                "雁平 86-13311111111 广东省 江门市 恩平市 牛江镇 岭南娟姑水果店",
                "北京 北京市 顺义区 胜利街道宜宾南区2-2-401  李俊南 18210997754"
        );
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (String s : address) {
            System.out.println("===============" + s + "===============");
            parse(s).forEach(ParseResult::format);
        }
        System.out.println("耗时:" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParseResult {
        private String name;

        private String province;
        private String city;
        private String area;
        private String detail;

        private String zipCode;

        private String mobile;
        private String phone;

        private AreaEnum type;
        private String address;


        public static ParseResult assign(ParseResult target, ParseResult source) {
            if (Objects.isNull(target) && Objects.nonNull(source)) {
                return source;
            }
            if (Objects.nonNull(target) && Objects.isNull(source)) {
                return target;
            }

            target.setName(StringUtils.isBlank(source.getName()) ? target.getName() : source.getName());

            target.setProvince(StringUtils.isBlank(source.getProvince()) ? target.getProvince() : source.getProvince());
            target.setCity(StringUtils.isBlank(source.getCity()) ? target.getCity() : source.getCity());
            target.setArea(StringUtils.isBlank(source.getArea()) ? target.getArea() : source.getArea());
            target.setDetail(StringUtils.isBlank(source.getDetail()) ? target.getDetail() : source.getDetail());

            target.setZipCode(StringUtils.isBlank(source.getZipCode()) ? target.getZipCode() : source.getZipCode());

            target.setMobile(StringUtils.isBlank(source.getMobile()) ? target.getMobile() : source.getMobile());
            target.setPhone(StringUtils.isBlank(source.getPhone()) ? target.getPhone() : source.getPhone());

            target.setType(Objects.nonNull(source.getType()) ? target.getType() : source.getType());
            target.setAddress(StringUtils.isBlank(source.getAddress()) ? target.getAddress() : source.getAddress());

            return target;
        }

        public void format() {
            System.out.printf("姓名：%s，电话：%s，手机：%s，省：%s，市：%s，区：%s，详细地址：%s，类型：%s\r\n",
                    this.getName(), this.getPhone(), this.getMobile(), this.getProvince(), this.getCity(), this.getArea(), this.getDetail(), this.getType());
        }


        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MatchResult {
            private boolean match;
            private boolean matchShort;
            private String matchName;
            private int matchNameLength;
            private int index;

            public MatchResult(boolean matchShort, String matchName, int index) {
                this.matchShort = matchShort;
                this.matchName = matchName;
                this.index = index;
            }


            public boolean isMatch() {
                return this.index > -1;
            }

            public int getMatchNameLength() {
                return StringUtils.length(this.matchName);
            }
        }
    }

