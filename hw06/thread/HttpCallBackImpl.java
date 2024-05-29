package thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Http 콜백 구현체
 */
public class HttpCallBackImpl implements HttpCallBack {
    @Override
    public void callUpdate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(formatter);
        System.out.println("Latest update date: " + formattedDate + "\n");
    }
}
