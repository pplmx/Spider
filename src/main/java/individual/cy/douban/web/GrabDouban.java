package individual.cy.douban.web;

import individual.cy.douban.pojo.Book;
import individual.cy.douban.utils.Spider;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/21 9:29
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class GrabDouban implements Runnable {

    public static volatile List<Book> books = new Vector<>();

    @Getter
    @Setter
    private String url;


    @Override
    public void run() {
        System.out.println("url = " + url);
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        parse(url);
    }

    private void parse(String url) {
        System.out.println("books = " + books.size());
        String html = Spider.pickData(url);
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("ul.subject-list li.subject-item div.info");
        for (Element element : elements) {
            String name = element.select("h2 a").attr("title");
            String[] pub = element.select("div.pub").text().split("/");
            String price = pub[pub.length - 1];
            String date = pub[pub.length - 2];
            String press = pub[pub.length - 3];
            StringBuilder author = new StringBuilder();
            for (int i = 0; i < pub.length - 3; i++) {
                author.append(pub[i]);
            }
            String score = element.select("div.star span.rating_nums").text();
            String num = element.select("div.star span.pl").text();
            // 截取评价人数
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(num);
            num = m.replaceAll("").trim();
            Book book = new Book("", name, score, num, author.toString(), press, date, price);
            books.add(book);
        }
    }

    public static void main(String[] args) {
        // ?start=20&type=T
        // 这里是单线程执行的,结果正常返回,已打印输出
        GrabDouban gd = new GrabDouban();
        gd.parse("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
        System.out.println("GrabDouban.books = " + GrabDouban.books);
        // 获取总页数
//        String html = Spider.pickData("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
//        Document doc = Jsoup.parse(html);
//        int totalPage = Integer.parseInt(doc.select("div.paginator > a").last().text());
//        StringBuilder sb;
//        for (int i = 0; i < totalPage; i++) {
//            sb = new StringBuilder("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start=");
//            sb.append(i * 20);
//            sb.append("&type=T");
//        }
    }
}