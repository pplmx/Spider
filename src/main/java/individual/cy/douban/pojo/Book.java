package individual.cy.douban.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/21 8:54
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Data
@ToString
@AllArgsConstructor
public class Book {
    /**
     * 序号
     */
    private String id;
    /**
     * 书籍名称
     */
    private String name;
    /**
     * 书籍评分
     */
    private String score;
    /**
     * 评价人数
     */
    private String num;
    /**
     * 作者
     */
    private String author;
    /**
     * 作者
     */
    private String press;
    /**
     * 出版日期
     */
    private String date;
    /**
     * 价格
     */
    private String price;
}
