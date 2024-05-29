package config;

import crawler.repository.StockRepository;
import crawler.repository.StockRepositoryImpl;
import crawler.service.StockCrawlerService;
import crawler.service.StockCrawlerServiceImpl;
import crawler.service.StockInfoService;
import crawler.service.StockInfoServiceImpl;
import thread.HttpCallBack;
import thread.HttpCallBackImpl;
import thread.HttpThread;
import thread.MenuThread;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * 의존성 주입을 위한 설정 클래스
 */
public class DiContainerConfig {
    // 상수 선언부
    private static final String URL_TO_CRAWL = "https://finance.naver.com/sise/sise_market_sum.naver";
    private static final String CHARSET = "EUC-KR";
    private static final Long THREAD_CYCLE_MILLIS = 1000L * 60L;

    // 의존성 선언부
    private StockRepository stockRepository;
    private StockCrawlerService stockCrawlerService;
    private StockInfoService stockInfoService;
    private HttpCallBack httpCallBack;
    private CountDownLatch countDownLatch;
    private HttpThread httpThread;
    private MenuThread menuThread;
    private Scanner scanner;

    // 서비스 및 저장소 구성
    public StockRepository stockRepository() {
        if (stockRepository == null) {
            stockRepository = new StockRepositoryImpl();
        }
        return stockRepository;
    }

    public StockCrawlerService stockCrawlerService() {
        if (stockCrawlerService == null) {
            stockCrawlerService = new StockCrawlerServiceImpl(stockRepository(), URL_TO_CRAWL, CHARSET);
        }
        return stockCrawlerService;
    }

    public StockInfoService stockInfoService() {
        if (stockInfoService == null) {
            stockInfoService = new StockInfoServiceImpl(stockRepository());
        }
        return stockInfoService;
    }

    // 스레드 및 콜백 구성
    public HttpCallBack httpCallBack() {
        if (httpCallBack == null) {
            this.httpCallBack = new HttpCallBackImpl();
        }
        return httpCallBack;
    }

    public CountDownLatch countDownLatch() {
        if (countDownLatch == null) {
            countDownLatch = new CountDownLatch(1);
        }
        return countDownLatch;
    }

    public HttpThread httpThread() {
        if (httpThread == null) {
            httpThread = new HttpThread(stockCrawlerService(), httpCallBack(), THREAD_CYCLE_MILLIS, countDownLatch());
        }
        return httpThread;
    }

    public HttpThread httpThread(CountDownLatch latch) {
        this.countDownLatch = latch;
        if (httpThread == null) {
            httpThread = new HttpThread(stockCrawlerService(), httpCallBack(), THREAD_CYCLE_MILLIS, countDownLatch());
        }
        return httpThread;
    }


    public MenuThread menuThread() {
        if (menuThread == null) {
            menuThread = new MenuThread(httpThread(), stockInfoService(), scanner());
        }
        return menuThread;
    }

    // 유틸리티 구성
    public Scanner scanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
