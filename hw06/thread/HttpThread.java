package thread;

import crawler.service.StockCrawlerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

/**
 * 주식 정보 업데이트를 위한 Http 스레드
 */
public class HttpThread extends Thread {
    private final StockCrawlerService stockCrawlerService;
    private final HttpCallBack httpCallBack;
    private final Long threadCycleMillis;
    private final CountDownLatch latch;

    private LocalDateTime lastUpdateDateTime;

    public HttpThread(StockCrawlerService stockCrawlerService, HttpCallBack httpCallBack, Long threadCycleMillis, CountDownLatch latch) {
        this.stockCrawlerService = stockCrawlerService;
        this.httpCallBack = httpCallBack;
        this.threadCycleMillis = threadCycleMillis;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                LocalDateTime localDateTime = LocalDateTime.now();
                stockCrawlerService.startCrawling();
                httpCallBack.callUpdate(localDateTime);
                this.lastUpdateDateTime = localDateTime;

                latch.countDown();

                synchronized (this) {
                    wait(threadCycleMillis);
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted");
        }
        System.out.println("HttpGetThread terminated!");
    }

    public String getLastUpdateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return lastUpdateDateTime.format(formatter);
    }
}
