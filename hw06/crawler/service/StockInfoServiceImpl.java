package crawler.service;

import crawler.entity.StockData;
import crawler.repository.StockRepository;

import java.util.Comparator;
import java.util.List;

/**
 * 주식 정보를 출력하는 서비스
 */
public class StockInfoServiceImpl implements StockInfoService {
    private final StockRepository stockRepository;

    public StockInfoServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void printAllStockInfo() {
        List<StockData> stockDataList = stockRepository.findAllStockData();

        for (StockData stockData: stockDataList) {
            System.out.println(stockData);
        }
    }

    @Override
    public void searchStockInfoByName(String stockName) {
        StockData stockData = stockRepository.findStockDataByStockName(stockName);
        if (stockData == null) {
            System.out.println("종목명에 " + stockName + "이(가) 없습니다.");
            return;
        }
        System.out.println(" N   종목명                     현재가      변화량      전일비      등락률       액면가     시가총액");
        System.out.println(stockData);
    }

    @Override
    public void printStockInfoByCurrentPriceDesc() {
        List<StockData> stockDataList = stockRepository.findAllStockData();
        stockDataList.sort(Comparator.comparing(StockData::getCurrentPrice).reversed());

        for (StockData stockData: stockDataList) {
            System.out.printf("%-20s %,10d\n", stockData.getStockName(), stockData.getCurrentPrice());
        }
    }

    @Override
    public void printStockInfoByChangeRateAsc() {
    List<StockData> stockDataList = stockRepository.findAllStockData();
        stockDataList.sort(Comparator.comparing(StockData::getChangeRate));

        for (StockData stockData: stockDataList) {
            System.out.printf("%-20s %,10.2f\n", stockData.getStockName(), stockData.getChangeRate());
        }
    }
}
