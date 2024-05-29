package thread;

import crawler.service.StockInfoService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 주식 정보 출력을 위한 메뉴 스레드
 */
public class MenuThread extends Thread {
    private final HttpThread httpThread;
    private final StockInfoService stockInfoService;
    private final Scanner scanner;

    public MenuThread(HttpThread httpThread, StockInfoService stockInfoService, Scanner scanner) {
        this.httpThread = httpThread;
        this.stockInfoService = stockInfoService;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        int menu;
        while (true) {
            try {
                System.out.println();
                printBanner();
                System.out.println(" 1. 전체 주식 정보 출력");
                System.out.println(" 2. 종목명으로 검색");
                System.out.println(" 3. 현재가 기준 내림차순 출력");
                System.out.println(" 4. 등락률 기준 오름차순 출력");
                System.out.println(" 5. 프로그램 종료");
                System.out.println("----------------------------------------------------");
                System.out.print("==> ");

                menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1:
                        printBanner();
                        stockInfoService.printAllStockInfo();
                        break;
                    case 2:
                        System.out.println("Type a Stock Name: ");
                        String stockName = scanner.nextLine();

                        stockInfoService.searchStockInfoByName(stockName);
                        break;
                    case 3:
                        stockInfoService.printStockInfoByCurrentPriceDesc();
                        break;
                    case 4:
                        stockInfoService.printStockInfoByChangeRateAsc();
                        break;
                    case 5:
                        httpThread.interrupt();
                        scanner.close();
                        System.out.println("Exit Program");
                        return;
                    default:
                        System.out.println("Wrong menu. Type a menu again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong menu. Type a menu again!");
                scanner.nextLine();
            }
        }
    }

    public void printBanner() {
        System.out.println("----------------------------------------------------");
        System.out.println("          Top 10 Kospi Stock Information            ");
        System.out.printf("               Updated: %s                           \n", httpThread.getLastUpdateDate());
        System.out.println("----------------------------------------------------");
    }
}