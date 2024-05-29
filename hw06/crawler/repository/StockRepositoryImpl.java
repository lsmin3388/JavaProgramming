package crawler.repository;

import crawler.entity.StockData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 주식 정보를 저장하는 저장소
 */
public class StockRepositoryImpl implements StockRepository {
    HashMap<String, StockData> stockDataMap = new HashMap<>();

    @Override
    public void updateStockData(String stockName, StockData stockData) {
        stockDataMap.put(stockName, stockData);
    }

    @Override
    public List<StockData> findAllStockData() {
        List<StockData> stockDataList = new ArrayList<>(stockDataMap.values());
        stockDataList.sort(Comparator.comparingInt(StockData::getIndex));
        return stockDataList;
    }

    @Override
    public StockData findStockDataByStockName(String stockName) {
        return stockDataMap.getOrDefault(stockName, null);
    }
}
