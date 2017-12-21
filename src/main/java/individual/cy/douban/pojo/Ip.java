package individual.cy.douban.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/21 14:06
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Data
@ToString
public class Ip implements Serializable {
    /**
     * ip地址
     */
    private String address;
    /**
     * 端口
     */
    private String port;
    /**
     * 类型
     */
    private String type;
    /**
     * 速度
     */
    private String speed;
}
