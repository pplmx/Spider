package individual.cy.douban.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/21 8:33
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class Spider {
    public static String pickData(String url){
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            //设置代理
            /*HttpHost proxy = new HttpHost("218.192.175.84", 8080, "http");
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();*/
            HttpGet httpGet = new HttpGet(url);
            // 模拟浏览器
            /*httpGet.setConfig(config);*/
            httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
            httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Host", "www.cnvd.org.cn");
            httpGet.setHeader("referer", "http://www.cnvd.org.cn/");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            CloseableHttpResponse response = client.execute(httpGet);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
}
