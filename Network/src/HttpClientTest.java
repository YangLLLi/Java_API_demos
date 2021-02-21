/**
 * @author Yang
 * @since 11
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {
    public static void main(String[] args) {
        try {
            httpClientTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void httpClientTest() throws IOException {
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com"))
                .GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpHeaders headers = response.headers();
            String body = response.body();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
