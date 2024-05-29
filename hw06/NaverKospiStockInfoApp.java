import config.DiContainerConfig;
import thread.HttpThread;
import thread.MenuThread;

import java.util.concurrent.CountDownLatch;

/**
 * 네이버 코스피 주식 정보 애플리케이션
 * @date 2024-05-29
 * @author 이상민
 * @classNumber 2023012780
 */
public class NaverKospiStockInfoApp {
    public static void main(String[] args) {
        DiContainerConfig container = new DiContainerConfig();

        CountDownLatch latch = new CountDownLatch(1);
        HttpThread httpThread = container.httpThread(latch);

        httpThread.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for initial crawl: " + e.getMessage());
        }

        MenuThread menuThread = container.menuThread();
        menuThread.start();
    }
}