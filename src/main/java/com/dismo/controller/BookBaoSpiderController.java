package com.dismo.controller;



import com.dismo.model.novel.NovelDetail;
import com.dismo.model.novel.NovelInfo;
import com.dismo.service.BookSpiderService;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yang Jiyu
 * 书包网爬虫程序,逐页爬并保存Mysql
 */
@Component
public class BookBaoSpiderController implements PageProcessor {

    URLCodec codec = new URLCodec();

    @Qualifier("bookDaoPipeline")
    @Autowired
    private PageModelPipeline bookDaoPipeline;

    /**
     * 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().setRetryTimes(10).setSleepTime(5000).setTimeOut(5000)
            .addCookie("UM_distinctid", "15f3dd37295e9-0d9a032de259758-12666d4a-1fa400-15f3dd37296171")
            .addCookie("CNZZDATA4769021", "cnzz_eid%3D1500105049-1508566602-null%26ntime%3D1508582803")
            .addCookie("bdshare_firstime", "1508570788716; ASPSESSIONIDAAQASQRC=EJDHABHBOGCAIDFMEGEDHJMF; ftcpvrich_fidx=3")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0");

    /**
     * 定义需要抓去固定页
     */
    public static String listUrl = "http://www\\.bookbao\\.cc/TXT/list2_[0-9]+\\.html";
    public static String dataUrl = "http://www\\.bookbao\\.cc/TXT/down_\\w+\\.html";
    public static String contentPage = "http://www\\.bookbao\\.cc/book\\.php\\?txt=/TXT/((%[0-9A-Fa-f]{2}){2})+\\.txt";
    public static String urlSuffix = "\\&yeshu=";
    public static String numberReg = "[0-9]+";


    public void startBookSpider() {
        site.setCharset("UTF-8");
        OOSpider.create(site,bookDaoPipeline, NovelInfo.class)
                .addUrl("http://www.bookbao.cc/TXT/list2_1.html")
                .thread(1).run();
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        String pre = "^" , end = "$",slash ="/",txt = ".txt";
        Integer maxListPageIndex = 0;
        //获取当前栏目最大页数,获取当前列表页中所有小说明细页;
        if(page.getUrl().regex(listUrl).match()){
            if(1 == findNum(page.getUrl().toString())){
                maxListPageIndex = Integer.parseInt(page.getHtml().$(".listl2 > dl:nth-child(3) > code:nth-child(5) > a:nth-child(10)").regex("(?<=>).*(?=</a>)").toString());
                page.addTargetRequests(page.getHtml().xpath("//div[@class='listl2']/ul/li/h5").links().all());
                page.addTargetRequest("http://www.bookbao.cc/TXT/list2_1.html");
                for(int i = 1; i<2 ;i++){
                    page.addTargetRequest("http://www.bookbao.cc/TXT/list2_"+i+".html");
                }
            }else{
                page.addTargetRequests(page.getHtml().xpath("//div[@class='listl2']/ul/li/h5").links().all());
            }
            //明细页
        } else if (page.getUrl().regex(dataUrl).match()) {
            NovelInfo book = new NovelInfo();
            //获取小说概要信息保存主表信息;
            book.setId(UUID.randomUUID().toString());
            book.setBook_title(page.getHtml().$(".pml1 >h1").regex("(?<=《)[^》]+(?=》)").toString());
            book.setBook_author(page.getHtml().xpath("//*[@id=\"xxlist\"]/li[5]/text()").toString());
            book.setBook_size(page.getHtml().xpath("//*[@id=\"xxlist\"]/li[4]/text()").toString());
            book.setBook_category(page.getHtml().xpath("//*[@id=\"xxlist\"]/li[2]/text()").toString());
            book.setBook_summary(page.getHtml().$(".con_text > li > span").toString());
            book.setUpdate_date(page.getHtml().xpath("//*[@id=\"xxlist\"]/li[7]/text()").toString());

            //获取内容明细页;添加下载列表;
            page.addTargetRequest(urlTransform(page.getHtml().$(".downlistbox > li:nth-child(1) > a:nth-child(1)").links().toString()));
            //获取下载内容页TXT,主表内容存储//获取对应图片,供使用;
        } else if (page.getUrl().regex(pre+contentPage+end).match()) {
            String endPageUrl = urlTransform(page.getHtml().xpath("/html/body/div/div").links().all().get(13).toString());
            Integer endPageIndex = findNum(endPageUrl);
            for (int i = 0; i <= endPageIndex; i++) {
                    page.addTargetRequest(urlTransform(page.getUrl() + "&yeshu=" + i));
            }
            //获取明细内容页;内容页存储;
        } else if (page.getUrl().regex(contentPage+urlSuffix+numberReg).match()) {
            //内容过滤,需要替换LOGO文件;
            NovelDetail bookDetail = new NovelDetail();
            String bookName = page.getHtml().xpath("/html/body/h1/text()").toString();
            String currentPageContents = page.getHtml().xpath("//div[@class='ddd']/text()").toString();

            if(0 == findNum(page.getUrl().toString())){
                bookDetail.setId(UUID.randomUUID().toString());
                bookDetail.setBookId("11");
                bookDetail.setBookContent(currentPageContents);
                bookDetail.setBookIndex(page.getUrl().toString());
            }

        } else {
            page.setSkip(true);
        }
    }

    /**n
     * @param url
     * @return
     * @throws Exception
     * 中文转换GB2312编码
     */
    public String urlTransform(String url) {
        String encodeUrl = "";
        String chinese = "";
        String regex = "([\u4e00-\u9fa5]+)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        while (matcher.find()) {
            chinese += matcher.group(0);
        }
        try {
            encodeUrl = url.replaceAll("[\u4e00-\u9fa5]+", codec.encode(chinese, "gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeUrl;
    }

    /**
     * @param url
     * @return
     * @throws Exception
     * 解决字符串中数字
     */
    public Integer findNum(String url) {
        Integer result = 0;
        String regEX = numberReg ;
        Matcher matcher = Pattern.compile(regEX).matcher(url);
        while(matcher.find()){
            result = Integer.parseInt(matcher.group());
        }
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final BookBaoSpiderController jobCrawler = applicationContext.getBean(BookBaoSpiderController.class);
        jobCrawler.startBookSpider();
    }

}