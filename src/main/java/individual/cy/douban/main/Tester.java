package individual.cy.douban.main;

import individual.cy.douban.pojo.Book;
import individual.cy.douban.pojo.BookComparator;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/21 19:52
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class Tester {

    public static void main(String[] args) {
        Random random = new Random();
        int loop = 20;
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < loop; i++) {
            Integer score = random.nextInt(100);
            Integer num = random.nextInt(10000);
            Integer price = random.nextInt(500);
            Book book = new Book("", "cc", score.toString(), num.toString(), "cc", "mystic", "2017-12", price.toString());
            books.add(book);
        }
        for (Book book : books) {
            System.out.println("book = " + book);
        }
        System.out.println(" ==================================== ");
        books.sort(new BookComparator());
        for (Book book : books) {
            System.out.println("book = " + book);
        }
    }
    
}
