package crawler.entity;

/**
 * 주식 정보를 담는 클래스
 */
public class StockData {
    int index;
    String	stockName; // 종목명 (HashMap	key)
    int currentPrice; // 현재가
    String	strStockChange; // 전일비 (상승, 하락, 보합 등)
    int intStockChange; // 전일비 (하락이면 -숫자로 표시)
    double changeRate; // 등락률:	-1.15, +0.26
    int faceValue; // 액면가
    int capitalization; // 시가총액

    public StockData() { }

    public StockData index(int index) {
        this.index = index;
        return this;
    }

    public StockData stockName(String stockName) {
        this.stockName = stockName;
        return this;
    }

    public StockData currentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public StockData strStockChange(String strStockChange) {
        this.strStockChange = strStockChange;
        return this;
    }

    public StockData intStockChange(int intStockChange) {
        this.intStockChange = intStockChange;
        return this;
    }

    public StockData changeRate(double changeRate) {
        this.changeRate = changeRate;
        return this;
    }

    public StockData faceValue(int faceValue) {
        this.faceValue = faceValue;
        return this;
    }

    public StockData capitalization(int capitalization) {
        this.capitalization = capitalization;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public String getStockName() {
        return stockName;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public String getStrStockChange() {
        return strStockChange;
    }

    public int getIntStockChange() {
        return intStockChange;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getCapitalization() {
        return capitalization;
    }

    @Override
    public String toString() {
        return String.format("[%2d] %-20s %,10d %5s %,10d %,10.2f %,10d %,10d",
                index, stockName, currentPrice, strStockChange, intStockChange, changeRate, faceValue, capitalization);
    }
}
