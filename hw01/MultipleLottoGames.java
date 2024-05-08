import java.util.Arrays;
import java.util.Scanner;

/**
 * 중복을 제거한 로또 번호 생성 및 당첨 번호 비교 프로그램
 * @date 2024-04-03
 * @author 이상민
 * @classNumber 2023012780
 */
public class MultipleLottoGames {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int lottoNum;

        do {
            System.out.print("구입할 Lotto 게임 수를 입력하세요 (1~5): ");
            lottoNum = input.nextInt();
        } while (lottoNum < 1 || lottoNum > 5);

        int[][] randomLotto = generateRandomLotto(lottoNum);
        int[] correctLotto = generateCorrectLotto();

        // 로또 번호 생성 및 결과 출력
        printRandomLotto(randomLotto);

        // 당첨 번호 출력
        printCorrectLotto(correctLotto);

        // 로또 결과 출력
        printMatchResults(randomLotto, correctLotto);

        System.out.println("--------------------------------------------");
        System.out.println("Lotto 게임 종료");

        input.close();
    }

    /**
     * 중복을 제거한 로또 번호 생성
     * @param lottoNum 생성할 로또 번호 개수
     * @return 중복을 제거한 로또 번호 2차원 배열
     */
    private static int[][] generateRandomLotto(int lottoNum) {
        int[][] randomLotto = new int[lottoNum][6];

        for (int i = 0; i < lottoNum; i++) {
            for (int j = 0; j < 6; j++) {
                int randomNum;
                boolean isExist;

                do {
                    randomNum = (int) (Math.random() * 45) + 1;
                    isExist = false;
                    for (int k = 0; k < j; k++) {
                        if (randomLotto[i][k] == randomNum) {
                            isExist = true;
                            break;
                        }
                    }
                } while (isExist);

                randomLotto[i][j] = randomNum;
            }
            Arrays.sort(randomLotto[i]);
        }

        return randomLotto;
    }

    /**
     * 당첨 번호 생성
     * @return 당첨 번호 1차원 배열
     */
    private static int[] generateCorrectLotto() {
        int[] correctLotto = new int[6];

        for (int i = 0; i < 6; i++) {
            int lottoNum;
            boolean isExist;

            do {
                lottoNum = (int) (Math.random() * 45) + 1;
                isExist = false;
                for (int num : correctLotto) {
                    if (num == lottoNum) {
                        isExist = true;
                        break;
                    }
                }
            } while (isExist);

            correctLotto[i] = lottoNum;
        }
        Arrays.sort(correctLotto);

        return correctLotto;
    }

    /**
     * 자동 생성된 로또 번호 출력
     * @param randomLotto 자동 생성된 로또 번호 2차원 배열
     */
    private static void printRandomLotto(int[][] randomLotto) {
        System.out.println("--------------------------------------------");
        System.out.println("자동 생성 번호: ");
        for (int i = 0; i < randomLotto.length; i++) {
            System.out.printf("[%2d]", i + 1);
            for (int j = 0; j < 6; j++) {
                System.out.printf("%3d ", randomLotto[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 당첨 번호 출력
     * @param correctLotto 당첨 번호 1차원 배열
     */
    private static void printCorrectLotto(int[] correctLotto) {
        System.out.println("--------------------------------------------");
        System.out.print("당첨 번호: ");
        for (int num : correctLotto) {
            System.out.printf("%3d", num);
        }
        System.out.println();
    }

    /**
     * 로또 결과 출력
     * @param randomLotto 자동 생성된 로또 번호 2차원 배열
     * @param correctLotto 당첨 번호 1차원 배열
     */
    private static void printMatchResults(int[][] randomLotto, int[] correctLotto) {
        for (int i = 0; i < randomLotto.length; i++) {
            System.out.println("--------------------------------------------");
            System.out.printf("Result[%d]: ", i + 1);

            for (int j = 0; j < 6; j++) {
                System.out.printf("%3d ", randomLotto[i][j]);
            }
            System.out.print("\n           ");

            for (int j = 0; j < 6; j++) {
                boolean matched = false;
                for (int num : correctLotto) {
                    if (randomLotto[i][j] == num) {
                        matched = true;
                        break;
                    }
                }
                System.out.print(matched ? "  O " : "  X ");
            }
            System.out.println();
        }
    }
}