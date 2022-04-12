package com.example.springboot0pa.jsoup;

import cn.hutool.core.convert.Convert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ZdmTest {

    @Test
    void contextLoads() throws IOException {
        //设置需要下载多少页
        int page=10;//先爬取10页的内容
        int result=0;
        for (int i = 1; i <= page; i++) {
//            result=pachong_page("http://www.dytt8.net/html/gndy/dyzz/list_23_" + i + ".html");
            System.out.println("第"+i+"页");
            result=pachong_page("https://faxian.smzdm.com/h2s0t0f0c0p"+ i);

        }
        System.out.println("爬取结束！一共爬取内容为:"+result*page+"条！");
    }

    public int pachong_page(String url) throws IOException {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();//模拟火狐浏览器
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取查询页商品列表
        Element list = doc.getElementById("feed-main-list");//查找table标签
        Elements table = list.getElementsByClass("J_feed_za");
        int result=table.size();
        System.out.println("商品数为:"+result);

        try {
            Thread.sleep(3000);//让线程操作不要太快 1秒一次 时间自己设置，主要是模拟人在点击
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Element tb : table) {

            // 查询所有的标题头 feed-ver-title
            String titel = tb.getElementsByClass("feed-ver-title").get(0).text();
            String price = tb.getElementsByClass("z-highlight").get(0).text();
            String descripe = tb.getElementsByClass("feed-ver-descripe").get(0).text();
            String zhi = tb.getElementsByClass("unvoted-wrap").get(0).select("span").first().text();
            String comment = tb.getElementsByClass("z-group-data").get(1).text();
            String bottom = tb.getElementsByClass("tag-bottom-right").get(0).text();

            // 获取所有评论在30以上的，且价格低于100的
            if (Convert.toInt(comment) < 30) {
                continue;
            }
            String[] strings = price.split("元");
            if (strings.length == 0) {
                continue;
            }
            if (Convert.toInt(strings[0]) > 100) {
                continue;
            }
            System.out.println(titel + "######" + Convert.toDouble(strings[0]) + "####" + Convert.toInt(comment) + "####" + bottom);

            //存入数据库
//            try {
//                Db.use().insert(
//                        Entity.create("movie")
//                                .set("type", "最新电影")
//                                .set("title", tr.get(1).select("a").text())
//                                .set("imagesUrl", imgUrl)
//                                .set("videoUrl", div1.select("td").text())
//                                .set("date", new Date())
//                                .set("describe", div1.select("p").text())
//                );
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }


        }
        return result;

    }
}
