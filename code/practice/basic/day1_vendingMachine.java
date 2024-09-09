package practice.basic;

import java.util.Scanner;

public class day1_vendingMachine {
    static final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;

    public static void printMenu(int totalMoney) {
        System.out.println("=========자판기==========");
        System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]-물-200원");
        System.out.println("[5]돈 투입 [6]잔돈 반환");
        System.out.println("잔액 : " + totalMoney + "원");
        System.out.println("=======================");
    }

    public static int getChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("원하는 메뉴를 선택하시오 ->");

        return sc.nextInt();
    }

    public static int getMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("돈을 투입하세요.");

        return sc.nextInt();
    }

    public static int calcMoney(int totalMoney, int menuPrice) {
        return totalMoney - menuPrice;
    }

    public static void printException() {
        System.out.println("투입 금액이 부족합니다.");
    }

    public static void lastSTEP(int result, String menuName) {

        if (result < 0) {
            printException();
        } else {
            System.out.println(menuName + " 이/가 나왔습니다.");
        }

    }

    public static void main(String[] args) {
        int totalMoney = 0; //고객이 투입한 돈
        int result = -1;

        int getMoney = getMoney();

        totalMoney += getMoney;

        while (true) {
            printMenu(totalMoney);
            int myChoice = getChoice();

            switch (myChoice) {
                case 1:
                    result = calcMoney(totalMoney, COKE);
                    lastSTEP(result, "콜라");

                    if (result >= 0) {
                        totalMoney = result;
                    }

                    //잔돈이 부족할 경우, 음료가 나오지 않고, 계산도 되지 않으므로
                    //totalMoney 값을 갱신해줄 필요가 없다.

                    break;

                case 2:
                    result = calcMoney(totalMoney, CIDER);
                    lastSTEP(result, "사이다");

                    if (result >= 0) {
                        totalMoney = result;
                    }
                    break;

                case 3:
                    result = calcMoney(totalMoney, FANTA);
                    lastSTEP(result, "환타");

                    if (result >= 0) {
                        totalMoney = result;
                    }
                    break;

                case 4:
                    result = calcMoney(totalMoney, WATER);
                    lastSTEP(result, "물");

                    if (result >= 0) {
                        totalMoney = result;
                    }
                    break;

                case 5:
                    int moreMoney = getMoney();
                    totalMoney += moreMoney;

                    break;

                case 6:
                    System.out.printf("잔돈 : %d 원이 반환되었습니다.", totalMoney);
                    return;
                default:
                    System.out.println("잘못누르셨습니다.");
            }
        }
    }
}

