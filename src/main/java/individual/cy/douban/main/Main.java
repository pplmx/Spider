package individual.cy.douban.main;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import individual.cy.douban.pojo.Book;
import individual.cy.douban.pojo.BookComparator;
import individual.cy.douban.utils.ExportExcel;
import individual.cy.douban.utils.Spider;
import individual.cy.douban.web.GrabDouban;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/21 11:23
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class Main {
    /**
     * 创建线程池
     */
    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        List<Book> books = new Vector<>();
        // 实现每一页一个线程获取数据
        // 获取总页数
        String html = Spider.pickData("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
        /*String html = Spider.pick4data("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B","220.249.185.178","9999");*/
        Document doc = Jsoup.parse(html);
        int totalPage = Integer.parseInt(doc.select("div.paginator > a").last().text());
        StringBuilder sb;
        for (int i = 0; i < totalPage; i++) {
            GrabDouban douban = new GrabDouban(books);
            sb = new StringBuilder("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start=");
            sb.append(i * 20);
            sb.append("&type=T");
            douban.setUrl(sb.toString());
            executorService.execute(douban);
            // 防止爬取速度太快,IP被封
            Thread.sleep(3000L);
        }
        executorService.shutdown();
        // 排序
        books.sort(new BookComparator());
        // 添加编号
        List<Book> noBooks = new ArrayList<>();
        Integer no = 0;
        for (Book book : books) {
            book.setId((++no).toString());
            noBooks.add(book);
        }
        // 截取前40个
        noBooks = noBooks.subList(0,40);
        Map<String, String> title = new LinkedHashMap<>(16);
        title.put("id", "序号");
        title.put("name", "书名");
        title.put("score", "评分");
        title.put("num", "评价人数");
        title.put("author", "作者");
        title.put("press", "出版社");
        title.put("date", "出版日期");
        title.put("price", "价格");
        String sheet = "豆瓣编程书籍排行";
        ExportExcel.excelExport(noBooks, title, sheet);
    }
}
