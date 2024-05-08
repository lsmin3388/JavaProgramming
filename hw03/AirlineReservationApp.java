package hw3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 항공사 통합 예약 시스템
 * @date 2024-05-08
 * @author 이상민
 * @classNumber 2023012780
 */
public class AirlineReservationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SwissAirline swissAirline = new SwissAirline("Swiss Airline", scanner, 7, 10);
        TaiwanAirline taiwanAirline = new TaiwanAirline("Taiwan Airline", scanner, 6, 10);
        JejuAirline jejuAirline = new JejuAirline("Jeju Airline", scanner, 4, 10);

        while (true) {
            try {
                System.out.println("--------------------------------------------------------");
                System.out.println("        항공사 통합 예약 시스템을 방문해 주셔서 감사합니다.         ");
                System.out.println("--------------------------------------------------------");
                System.out.println("\t1. Swiss Airline 예약 시스템");
                System.out.println("\t2. Taiwan Airline 예약 시스템");
                System.out.println("\t3. Jeju Airline 예약 시스템");
                System.out.println("\t4. 전체 예약 현황 조회");
                System.out.println("\t0. 예약 시스템 종료");
                System.out.println("--------------------------------------------------------");
                System.out.print("메뉴를 선택해주세요: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요. (0~4)");
                    scanner.nextLine();
                    continue;
                }

                int menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1 -> swissAirline.menu();
                    case 2 -> taiwanAirline.menu();
                    case 3 -> jejuAirline.menu();
                    case 4 -> {
                        System.out.println("전체 예약 현황");
                        swissAirline.displaySeatPrefix("Swiss Airline");
                        taiwanAirline.displaySeatPrefix("Taiwan Airline");
                        jejuAirline.displaySeatPrefix("Jeju Airline");
                    }
                    case 0 -> {
                        System.out.println("항공사 통합 예약 시스템을 종료합니다.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요. (0~4)");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요. (0~4)");
                scanner.nextLine();
            }
        }
    }
}
