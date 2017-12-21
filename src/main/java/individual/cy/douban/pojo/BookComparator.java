package individual.cy.douban.pojo;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/21 20:06
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class BookComparator implements Comparator<Book> {
    /**
     *      降序: 优先评分,人数次之
     * @param book2
     * @param book1
     * @return
     */
    @Override
    public int compare(Book book2, Book book1) {
        String temp1 = book1.getScore();
        String temp2 = book2.getScore();
        if (temp1 == null || "".equals(temp1)) {
            temp1 = "0";
        }
        if (temp2 == null || "".equals(temp2)) {
            temp2 = "0";
        }
        // 评分排序优先
        double num1 = Double.parseDouble(temp1);
        double num2 = Double.parseDouble(temp2);
        int result = Double.compare(num1, num2);
        if (result == 0) {
            String temp3 = book1.getNum();
            String temp4 = book2.getNum();
            if (temp3 == null || "".equals(temp3)) {
                temp3 = "0";
            }
            if (temp4 == null || "".equals(temp4)) {
                temp4 = "0";
            }
            // 评分相同,则以评价人数排序
            double num3 = Double.parseDouble(temp3);
            double num4 = Double.parseDouble(temp4);
            result = Double.compare(num3, num4);
        }
        return result;
    }
}
