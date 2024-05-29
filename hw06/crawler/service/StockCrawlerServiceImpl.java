package crawler.service;

import crawler.entity.StockData;
import crawler.repository.StockRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

/**
 * 주식 정보를 크롤링하는 서비스
 */
public class StockCrawlerServiceImpl implements StockCrawlerService{
    private final StockRepository stockRepository;
    private final String urlToCrawl;
    private final String charset;

    public StockCrawlerServiceImpl(StockRepository stockRepository, String urlToCrawl, String charset) {
        this.stockRepository = stockRepository;
        this.urlToCrawl = urlToCrawl;
        this.charset = charset;
    }

    @Override
    public void startCrawling() {
        try {
            String content = fetchContentFromUrl();
            if (content != null) {
                parseHTML(content);
            }
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }

    /**
     * URL로부터 HTML 컨텐츠를 가져온다.
     * @return HTML 컨텐츠
     * @throws Exception 예외 발생 시
     */
    private String fetchContentFromUrl() throws Exception {
        URL url = new URL(urlToCrawl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            connection.disconnect();
            return content.toString();
        } else {
            System.err.println("GET 요청 실패: " + responseCode);
            return null;
        }
    }

    /**
     * HTML 컨텐츠를 파싱하여 주식 정보를 업데이트한다.
     * @param html HTML 컨텐츠
     */
    private void parseHTML(String html) {
        String tbodyStartTag = "<tbody>";
        String tbodyEndTag = "</tbody>";

        int tbodyStartIndex = html.indexOf(tbodyStartTag);
        int tbodyEndIndex = html.indexOf(tbodyEndTag);

        if (tbodyStartIndex != -1 && tbodyEndIndex != -1) {
            String tbodyContent = html.substring(tbodyStartIndex + tbodyStartTag.length(), tbodyEndIndex);
            String[] rows = tbodyContent.split("<tr");

            for (String row : rows) {
                if (row.contains("onmouseover") || row.contains("class=\"no\"")) {
                    StockData stockData = parseRow(row);
                    if (stockData != null) {
                        stockRepository.updateStockData(stockData.getStockName(), stockData);
                    }
                }
            }
        } else {
            System.err.println("HTML 파싱 실패: <tbody> 태그를 찾을 수 없습니다.");
        }
    }

    /**
     * HTML 행을 파싱하여 주식 정보를 반환한다.
     * @param row HTML 행
     * @return 주식 정보
     */
    private StockData parseRow(String row) {
        try {
            String[] columns = row.split("<td");
            String[] extractedValues = Arrays.stream(columns)
                    .skip(1)
                    .limit(7)
                    .map(this::extractValue)
                    .toArray(String[]::new);

            int index = Integer.parseInt(extractedValues[0]);
            if (index > 10) return null;

            String stockName = extractedValues[1];
            int currentPrice = Integer.parseInt(extractedValues[2]);
            String strStockChange = extractedValues[3].contains("상승") ? "상승" : columns[4].contains("하락") ? "하락" : "보합";
            int intStockChange = Integer.parseInt(extractedValues[3].replaceAll("[^0-9]", "").trim());
            double changeRate = Double.parseDouble(extractedValues[4].replace("%", ""));
            int faceValue = Integer.parseInt(extractedValues[5]);
            int capitalization = Integer.parseInt(extractedValues[6]);

            if (strStockChange.equals("하락")) intStockChange *= -1;

            return new StockData()
                    .index(index)
                    .stockName(stockName)
                    .currentPrice(currentPrice)
                    .strStockChange(strStockChange)
                    .intStockChange(intStockChange)
                    .changeRate(changeRate)
                    .faceValue(faceValue)
                    .capitalization(capitalization);
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 변환 오류: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("배열 인덱스 초과 오류: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("데이터 파싱 오류: " + e.getMessage());
        }
        return null;
    }

    /**
     * HTML 문자열에서 값을 추출한다.
     * @param htmlString HTML 문자열
     * @return 추출된 값
     */
    public String extractValue(String htmlString) {
        String regex = "<[^>]*>";
        String valueRegex = "[,%]";

        String result = htmlString.replaceAll(regex, "");
        if (result.contains(">") && !result.contains("<"))
            result = result.replaceAll(".*>", "");
        if (result.contains("<") && !result.contains(">"))
            result = result.replaceAll(".*<", "");

        result = result.replaceAll(valueRegex, "").trim();

        return result;
    }
}
