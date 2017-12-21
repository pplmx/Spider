package individual.cy.douban.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
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
    public static String pickData(String url) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
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

    /**
     * 使用本机ip进行获取数据
     * @param url
     * @return
     */
    public static String pick4data(String url) {
        //设置超时处理
        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
                setSocketTimeout(3000).build();
        HttpGet httpGet = new HttpGet(url);
        return grab(httpGet,config);
    }

    /**
     * 使用代理ip进行获取数据
     * @param url
     * @param ip
     * @param port
     * @return
     */
    public static String pick4data(String url, String ip, String port) {
        //设置代理访问和超时处理
        System.out.println("此时线程: " + Thread.currentThread().getName() + " 爬取所使用的代理为: "
                + ip + ":" + port);
        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
                setSocketTimeout(3000).build();
        HttpGet httpGet = new HttpGet(url);
        return grab(httpGet,config);
    }

    private static String grab(HttpGet httpGet, RequestConfig config){
        httpGet.setConfig(config);
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                "q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "www.xicidaili.com");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             //客户端执行httpGet方法，返回响应
             CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            //得到服务响应状态码
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, "utf-8");
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
}
