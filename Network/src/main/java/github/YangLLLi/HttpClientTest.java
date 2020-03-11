package github.YangLLLi;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class HttpClientTest {
    public static void main(String[] args) {
        try {
            httpClientTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void httpClientTest() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println(Arrays.toString(httpResponse.getAllHeaders()));

        Scanner scanner = new Scanner(httpResponse.getEntity().getContent());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }


    }
}
