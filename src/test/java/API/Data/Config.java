package API.Data;

import okhttp3.Headers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Config {

    @BeforeAll
    public static void start() {
        System.setProperty("https.protocols", "TLSv1.1");
    }

    @BeforeEach
    public void setUp() {}

    @AfterAll
    public static void finish() {}

    public static Headers getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "TestGun -> API");
        headers.put("api_key","ea0eb443-94f8-4fcd-a6d0-19970a7dfe34");
        return Headers.of(headers);
    }

    public static void requestDelay(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
            System.out.print("\nDelay for next request "+ms+"ms\n\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
