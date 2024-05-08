package hw3;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 항공사 예약 시스템 인터페이스
 */
public class ReservationSystem implements ReservationInterface {
    /**
     * 좌석 정보를 저장하는 2차원 배열
     */
    private int[][] seatData;

    /**
     * 입력을 받기 위한 Scanner 객체
     */
    private final Scanner scanner;

    /**
     * 항공사 이름
     */
    private final String airline;

    /**
     * 통로의 경계를 나타내는 문자 리스트
     */
    private List<Character> boundary;

    /**
     * 항공사 예약 시스템 생성자
     * @param airline 항공사 이름
     * @param scanner 입력을 받기 위한 Scanner 객체
     * @param row 행
     * @param col 열
     */
    public ReservationSystem(String airline, Scanner scanner, int row, int col) {
        this.airline = airline;
        this.scanner = scanner;
        makeSeats(row, col);
    }

    /**
     * 좌석 행과 열의 수를 입력받아서 전체 좌석 배열을 생성하는 메소드 (makeSeats)
     * @param row 좌석 행의 수
     * @param col 좌석 열의 수
     */
    @Override
    public void makeSeats(int row, int col) {
        seatData = new int[row][col];
    }

    /**
     * 좌석 예약 메소드 (reserveSeat)
     * @param seatName 좌석 이름: 1A, 1B 형태의 문자열
     * @return true: 예약 성공, false: 예약 실패
     */
    @Override
    public boolean reserveSeat(String seatName) {
        int[] seatArray = new int[2];
        if (!getSeatIndex(seatName, seatArray))
            return false;

        int row = seatArray[0];
        int col = seatArray[1];

        if (isReserved(row, col)) {
            System.out.println("[예약 실패] " + seatName + ": 이미 예약된 좌석입니다.");
            return false;
        }

        seatData[row][col] = 1;
        return true;
    }

    /**
     * 예약 취소 메소드 (cancelSeat)
     * @param seatName 좌석 이름: 1A, 1B 형태의 문자열
     * @return true: 예약 취소 성공, false: 예약 실패
     */
    @Override
    public boolean cancelSeat(String seatName) {
        int[] seatArray = new int[2];
        if (!getSeatIndex(seatName, seatArray))
            return false;

        int row = seatArray[0];
        int col = seatArray[1];

        if (!isReserved(row, col)) {
            System.out.println("[예약 취소 실패] " + seatName + ": 예약되지 않은 좌석입니다.");
            return false;
        }

        seatData[row][col] = 0;
        return true;
    }

    /**
     * 입력된 좌석의 유효성 검사 메소드 (getSeatIndex)
     * @param seatName 좌석 이름
     * @param seatArray <row, col>을 저장할 배열
     * @return true: 유효한 좌석, false: 유효하지 않은 좌석
     */
    @Override
    public boolean getSeatIndex(String seatName, int[] seatArray) {
        try {
            char ch = seatName.charAt(0);
            if (!Character.isDigit(ch)) {
                System.out.println(seatName + ": 숫자가 아닌 잘못된 좌석입니다.");
                return false;
            }

            int row = (seatData.length - 1 + 'A') - seatName.charAt(seatName.length() - 1);
            int col = Integer.parseInt(seatName.substring(0, seatName.length() - 1)) - 1;

            if (row < 0 || row >= seatData.length || col < 0 || col >= seatData[0].length) {
                System.out.println(seatName + ": 잘못된 좌석입니다.");
                return false;
            }

            seatArray[0] = row;
            seatArray[1] = col;
            return true;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println(seatName + ": 잘못된 좌석입니다.");
            return false;
        }
    }

    /**
     * 좌석 예약 현황 출력 (title, prefix)
     * @param airline 항공사 이름
     */
    public void displaySeatPrefix(String airline) {
        System.out.printf("[%s 예약 현황] 예약 좌석: %d / 총 좌석: %d\n", airline, getReservedSeatCount(), seatData.length * seatData[0].length);
    }

    /**
     * 예약 현황 화면 출력 메소드 (displaySeat)
     * @param airline 항공사 이름
     */
    @Override
    public void displaySeat(String airline) {
        System.out.println();
        displaySeatPrefix(airline);
        System.out.print("-----------------------------------------------------------\n");

        for (int i = 0; i < seatData.length; i++) {
            char row = (char) ('A' + seatData.length - i - 1);

            if (boundary != null && boundary.contains(row)) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("  앞                         통로                        뒤  ");
                System.out.println("-----------------------------------------------------------");
            }

            for (int j = 0; j < seatData[i].length; j++) {
                System.out.printf("[%2d%s] ", j + 1, row);
            }
            System.out.println();

            for (int j = 0; j < seatData[i].length; j++) {
                if (seatData[i][j] == 1) {
                    System.out.printf("%4s%-2s", "O", "");
                } else {
                    System.out.printf("%-6s", "");
                }
            }
            System.out.println();
        }

        System.out.print("-----------------------------------------------------------\n");
    }

    /**
     * 통로의 경계를 나타내는 문자 리스트를 설정하는 메소드
     * @param boundary 통로의 경계를 나타내는 문자 리스트
     */
    public void setBoundary(List<Character> boundary) {
        this.boundary = boundary;
    }

    /**
     * 좌석이 예약되어 있는지 확인하는 메소드
     * @param row 행
     * @param col 열
     * @return true: 예약된 좌석, false: 예약되지 않은 좌석
     */
    public boolean isReserved(int row, int col) {
        return seatData[row][col] == 1;
    }

    /**
     * 예약된 좌석의 수를 반환하는 메소드
     * @return 예약된 좌석의 수
     */
    public int getReservedSeatCount() {
        int count = 0;
        for (int[] seatDatum : seatData) {
            for (int j : seatDatum) {
                if (j == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 예약 시스템 메뉴
     */
    public void menu() {
        while (true) {
            System.out.println("-------------------------");
            System.out.println(airline);
            System.out.println("1. 좌석 예약");
            System.out.println("2. 예약 취소");
            System.out.println("3. 예약 현황 조회");
            System.out.println("4. 메인 메뉴로 돌아가기");
            System.out.println("-------------------------");
            System.out.print("메뉴를 선택해주세요: ");

            int menu;

            try {
                menu = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요. (1~4)");
                scanner.nextLine();
                continue;
            }

            switch (menu) {
                case 1 -> reserveMenu();
                case 2 -> cancelMenu();
                case 3 -> displaySeat(this.airline);
                case 4 -> {
                    System.out.println("메인 메뉴로 돌아갑니다.\n");
                    return;
                }
                default -> System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요. (1~4)");
            }
        }
    }

    /**
     * 좌석 예약 메뉴
     */
    private void reserveMenu() {
        System.out.print("예약할 좌석 이름을 입력하세요: ");
        String seatName = scanner.next();
        scanner.nextLine();

        if (reserveSeat(seatName)) {
            System.out.println("[예약 성공] " + seatName + "\n");
            displaySeat(this.airline);
        } else {
            System.out.println("[예약 실패] " + seatName + "\n");
        }
    }

    /**
     * 좌석 예약 취소 메뉴
     */
    private void cancelMenu() {
        System.out.print("좌석 이름을 입력해주세요: ");
        String seatName = scanner.next();
        scanner.nextLine();

        if (cancelSeat(seatName)) {
            System.out.println("[예약 취소 성공] " + seatName + "\n");
            displaySeat(this.airline);
        } else {
            System.out.println("[예약 취소 실패] " + seatName + "\n");
        }
    }
}
