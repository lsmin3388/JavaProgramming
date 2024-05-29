package crawler.repository;

import crawler.entity.StockData;

import java.util.List;

/**
 * 주식 정보를 저장하는 저장소
 */
public interface StockRepository {
    /**
     * 주식 정보를 저장한다.
     * @param stockName 주식 이름
     * @param stockData 주식 정보
     */
    void updateStockData(String stockName, StockData stockData);

    /**
     * 모든 주식 정보를 반환한다.
     * @return 모든 주식 정보
     */
    List<StockData> findAllStockData();

    /**
     * 주식 이름으로 주식 정보를 찾는다.
     * @param stockName 주식 이름
     * @return 주식 정보
     */
    StockData findStockDataByStockName(String stockName);
}
