package hw3;

import java.util.List;
import java.util.Scanner;

/**
 * 스위스 항공사 예약 시스템
 */
public class SwissAirline extends ReservationSystem {
    /**
     * 스위스 항공사 예약 시스템 생성자
     * @param airline 항공사 이름
     * @param scanner 입력을 받기 위한 Scanner 객체
     * @param row 행
     * @param col 열
     */
    public SwissAirline(String airline, Scanner scanner, int row, int col) {
        super(airline, scanner, row, col);
    }

    /**
     * 스위스 항공사 실시간 좌석 출력
     * @param airline 항공사 이름
     */
    @Override
    public void displaySeat(String airline) {
        super.setBoundary(List.of('E', 'B'));
        super.displaySeat(airline);
    }
}
