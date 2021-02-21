import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;
import java.util.Map;
/**
 * @author Yang
 * @since 1.8
 */

public class URLConnectionTest {
    public static void main(String[] args) {
        try {
//            uriTest();
            urlConnectionTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * uri类使用示例
     */
    public static void uriTest() {
        URI uri = URI.create("http://www.sina.com:80/category/?start=1");
        System.out.println(uri.getScheme());
        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getPath());
        System.out.println(uri.getQuery());
    }

    /**
     * urlConnection使用说明
     *
     * http请求
     * 1. get www.baidu.com http/1.1
     * 2. xxx:xxx
     *    xxx:xxx
     *
     * 3. 请求体
     *
     * http响应
     * 1. http/1.1 200 ok   状态码 2xx正常 3xx重定向 4xx客户端错误 5xx服务器错误
     * 2. xxx:xxx
     *    xxx:xxx
     *
     * 3. 响应体
     *
     * @throws IOException
     */
    public static void urlConnectionTest() throws IOException {
        URI uri = URI.create("https://www.baidu.com");
        HttpURLConnection httpURLConnection = (HttpURLConnection) uri.toURL().openConnection();
//      设置请求方式、请求头
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setUseCaches(true);

//        httpURLConnection.setDoOutput(true);  post方法用
//        连接
        httpURLConnection.connect();
        /* 用于post方法
        BufferedOutputStream outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        outputStream.write("hello".getBytes());
        outputStream.flush();
        outputStream.close();
        */
//        状态码、响应头
        System.out.println(httpURLConnection.getResponseCode());
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
//         关闭连接
        httpURLConnection.disconnect();
    }

}
