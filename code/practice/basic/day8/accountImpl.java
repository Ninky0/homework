package practice.basic.day8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class accountImpl implements account{

    private int choice;
    private int balance;
    private String accountNum;
    private List<String[]> list;

    public accountImpl() {
        this.choice = 0;
        this.balance = 0;
        this.accountNum = "";
        this.list = new ArrayList<>();
    }

    public int getChoice(){
        return choice;
    }

    public void setChoice(int choice){
        this.choice = choice;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public String getAccount(){
        return accountNum;
    }

    public void setAccount(String accountNum){
        this.accountNum = accountNum;
    }


    @Override
    public void createAccount(){
        int[] nums = new int[5];

        nums[0] = (int) (Math.random() * 9) + 1;

        for (int i = 1; i < nums.length; i++) {
            int temp;
            boolean isDuplicate;

            do {
                isDuplicate = false;
                temp = (int) (Math.random() * 10);

                for (int j = 0; j < i; j++) {
                    if (nums[j] == temp) {
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);

            nums[i] = temp;
        }

        for (int num : nums) {
            accountNum += num;
        }

        setAccount(accountNum);
    }

    @Override
    public void printMenu() {
        System.out.println("[메뉴를 선택하세요.]");
        System.out.println("[1]입금 [2]출금 [3]현재 금액 조회 [4]내역 조회 [5]종료");
        putChoice();
    }

    @Override
    public void putChoice(){
        Scanner sc = new Scanner(System.in);
        setChoice(sc.nextInt());
    }

    @Override
    public void depositMoney(){
        Scanner sc = new Scanner(System.in);
        System.out.println("[입금] 입금하실 금액을 입력해주세요.");
        int amount = sc.nextInt();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = LocalDateTime.now().format(formatter);

        String[] deposit = new String[3];
        deposit[0] = "[입금]";
        deposit[1] = Integer.toString(amount);
        deposit[2] = formattedDate;

        System.out.println("입금액 : " + deposit[1] + "원");
        System.out.println("입금일자 : " + deposit[2]);

        balance += amount;
        setBalance(balance);
        addHistory(deposit);
    }

    @Override
    public void withdrawMoney(){
        Scanner sc = new Scanner(System.in);
        System.out.println("[출금] 출금하실 금액을 입력해주세요.");
        int amount = sc.nextInt();

        if (amount > balance) {
            System.out.println("출금 가능한 금액이 부족합니다.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = LocalDateTime.now().format(formatter);

        String[] withdraw = new String[3];
        withdraw[0] = "[출금]";
        withdraw[1] = Integer.toString(amount);
        withdraw[2] = formattedDate;

        System.out.println("출금액 : " + withdraw[1] + "원");
        System.out.println("출금일자 : " + withdraw[2]);

        balance -= amount;
        setBalance(balance);
        addHistory(withdraw);
    }

    public void addHistory(String[] newList){
        list.add(newList);
    }

    @Override
    public void displayHistory(){
        if (list.isEmpty()) {
            System.out.println("내역이 없습니다.");
        } else {
            // 리스트를 역순으로 정렬(최신순 조회)
            Collections.reverse(list);

            for (String[] element : list) {
                System.out.println(element[0] + " 금액: " + element[1] + "원, 날짜: " + element[2]);
            }
            // 리스트를 원래대로 돌려놓기.
            Collections.reverse(list);
        }
    }

}
