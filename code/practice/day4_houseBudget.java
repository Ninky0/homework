package practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class day4_houseBudget {

    public static final int HISTORY_MAX_LENGTH = 10;
    public static String[][] histories = new String[12][HISTORY_MAX_LENGTH];
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static int[] historyIdx = new int[12]; // 각 월별로 인덱스를 따로 관리

    public static int getMonth() {
        System.out.println("========가계부========");
        System.out.println("작업하려는 월을 입력해주세요.( 1 ~ 12 )");

        // 범위 내의 값을 전달하기 위해.
        int month = getRightMonth();

        return month;
    }

    public static int getRightMonth() {
        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();

        // 범위 내의 값인지 판단하고, 아닐시 반복.
        if (month <= 12 && month >= 1) {
            return month - 1;// 0-based index로 사용
        } else {
            System.out.println("1부터 12까지 입력이 가능합니다. 다시 입력해주세요.");
            return getRightMonth();
        }
    }

    public static int printAndGetMenu() {
        System.out.println("수행할 업무를 선택해주세요");
        System.out.println("1.[내용추가] 2.[물품 내용 수정] 3.[물품 내용 삭제] 4.[해당 월 내용 전체 삭제] 5.[해당 월 내용 전체 조회] 6.[해당 월 작업 종료] 7.[프로그램 종료]");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void addItem(int month) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("물품 이름을 입력해주세요.");
        String name = scanner.nextLine();
        System.out.println("물품 가격을 입력해주세요.");
        String price = scanner.nextLine();

        String formattedDate = LocalDateTime.now().format(formatter);
        System.out.println("추가 일자 : " + formattedDate);

        String content = "물품이름 : " + name + "  물품가격 : " + price + "  추가일자 : " + formattedDate;

        addHistory(month, content);
    }

    public static void addHistory(int month, String content) {
        if (historyIdx[month] >= HISTORY_MAX_LENGTH) {
            System.out.println("초기 내역을 하나 지우고, 새로 추가하겠습니다.");

            for (int i = 1; i < HISTORY_MAX_LENGTH; i++) {
                histories[month][i - 1] = histories[month][i];
            }
            histories[month][HISTORY_MAX_LENGTH - 1] = content;
        } else {
            histories[month][historyIdx[month]++] = content;
        }
        System.out.println("내역 추가를 완료하였습니다.");
    }

    public static void updateItem(int month) {
        if (historyIdx[month] == 0) {
            System.out.println("내역이 없습니다.");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("수정하실 항목의 번호를 입력해주세요.");
            printAll(month);
            int idx = scanner.nextInt();

            // 엔터'\n'에 대한 처리. 사용자가 정수를 입력하면서 누른 엔터에 대한 처리를 하기 위함.
            // 없을시, 아래의, 'name'에 엔터가 입력되는꼴.
            scanner.nextLine();

            System.out.println(idx + "번 항목에 대한 수정을 진행하겠습니다.");
            System.out.println("수정하실 물품이름을 입력해주세요.");
            String name = scanner.nextLine();
            System.out.println("수정하실 물품가격을 입력해주세요.");
            String price = scanner.nextLine();

            String formattedDate = LocalDateTime.now().format(formatter);
            System.out.println("수정 일자 : " + formattedDate);

            String content = "물품이름 : " + name + "  물품가격 : " + price + "  추가일자 : " + formattedDate;

            updateHistory(month, content, idx - 1);
        }
    }

    public static void updateHistory(int month, String content, int idx) {
        histories[month][idx] = content;
        System.out.println("수정이 완료되었습니다.");
    }

    public static void deleteItem(int month) {
        if (historyIdx[month] == 0) {
            System.out.println("내역이 없습니다.");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("삭제하실 항목의 번호를 입력해주세요.");
            printAll(month);

            int idx = scanner.nextInt();
            deleteHistory(month, idx - 1);
        }
    }

    public static void deleteHistory(int month, int idx) {
        for (int i = idx + 1; i < historyIdx[month]; i++) {
            histories[month][i - 1] = histories[month][i];
        }
        histories[month][--historyIdx[month]] = null;
    }

    public static void deleteAll(int month) {
        if (historyIdx[month] == 0) {
            System.out.println("내역이 없습니다.");
        } else {
            for (int i = 0; i < historyIdx[month]; i++) {
                histories[month][i] = null;
            }
            historyIdx[month] = 0;
            System.out.println("전체 삭제되었습니다.");
        }
    }

    public static void printAll(int month) {
        if (historyIdx[month] == 0) {
            System.out.println("내역이 없습니다.");
        } else {
            for (int i = 0; i < historyIdx[month]; i++) {
                System.out.println((i + 1) + "  " + histories[month][i]);
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            int month = getMonth();
            printAll(month);

            while (true) {
                int myChoice = printAndGetMenu();
                switch (myChoice) {
                    case 1:
                        addItem(month);
                        break;
                    case 2:
                        updateItem(month);
                        break;
                    case 3:
                        deleteItem(month);
                        break;
                    case 4:
                        deleteAll(month);
                        break;
                    case 5:
                        printAll(month);
                        break;
                    case 6:
                        System.out.println("해당 월에 대한 작업을 종료합니다.");
                        break;
                    case 7:
                        System.out.println("프로그램 종료");
                        break;
                    default:
                        System.out.println("잘못입력하셨습니다.");
                        continue; // 잘못 입력한 경우 다시 메뉴를 출력
                }

                if (myChoice == 6) {
                    break; // 내부 루프 종료, 새로운 월 입력으로 돌아감
                } else if (myChoice == 7) {
                    return;
                }
            }
        }
    }
}
