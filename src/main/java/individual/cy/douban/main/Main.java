package individual.cy.douban.main;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import individual.cy.douban.utils.Spider;
import individual.cy.douban.web.GrabDouban;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
        // 实现每一页一个线程获取数据
        // 获取总页数
        String html = Spider.pickData("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
        Document doc = Jsoup.parse(html);
        int totalPage = Integer.parseInt(doc.select("div.paginator > a").last().text());
        StringBuilder sb;
        GrabDouban douban = new GrabDouban();
        for (int i = 0; i < totalPage; i++) {
            sb = new StringBuilder("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start=");
            sb.append(i * 20);
            sb.append("&type=T");
            douban.setUrl(sb.toString());
            executorService.execute(douban);
            Thread.sleep(3000L);
        }
        executorService.shutdown();
        System.out.println("GrabDouban.books = " + GrabDouban.books);
        System.out.println("GrabDouban.books.size() = " + GrabDouban.books.size());
    }
}
