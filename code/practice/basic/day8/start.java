package practice.basic.day8;

import java.util.Scanner;

public class start {
    public static void main(String[] args) {
        accountImpl ac = new accountImpl();

        Scanner sc = new Scanner(System.in);
        String[] user = new String[2];

        System.out.println("성함을 입력해주세요.");
        String name = sc.nextLine();
        user[0] = name;

        ac.createAccount();

        user[1] = ac.getAccount();
        System.out.println("[이름] : " + user[0] + " [계좌번호] : " + user[1]);

        while (true) {
            ac.printMenu();

            switch (ac.getChoice()) {
                case 1:
                    ac.depositMoney();
                    break;
                case 2:
                    ac.withdrawMoney();
                    break;
                case 3:
                    System.out.println("현재 잔액: " + ac.getBalance() + "원");
                    break;
                case 4:
                    ac.displayHistory();
                    break;
                case 5:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }

        }

    }
}
