package thread;

import java.time.LocalDateTime;

/**
 * Http 콜백 인터페이스
 */
public interface HttpCallBack {
    /**
     * Http 콜백 메서드
     * @param localDateTime Http 요청 시간
     */
    void callUpdate(LocalDateTime localDateTime);
}
