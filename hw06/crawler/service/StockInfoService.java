package crawler.service;

/**
 * 주식 정보를 출력하는 서비스
 */
public interface StockInfoService {
    /**
     * 모든 주식 정보를 출력한다.
     */
    void printAllStockInfo();

    /**
     * 주식 이름으로 주식 정보를 검색한다.
     * @param stockName 주식 이름
     */
    void searchStockInfoByName(String stockName);

    /**
     * 주식 정보를 현재 가격을 기준으로 내림차순으로 출력한다.
     */
    void printStockInfoByCurrentPriceDesc();

    /**
     * 주식 정보를 현재 가격을 기준으로 오름차순으로 출력한다.
     */
    void printStockInfoByChangeRateAsc();
}
