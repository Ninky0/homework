package practice;

import java.util.Scanner;

public class day2_memberManagement {
    static int totalCnt = 0;
    static int totalMemberCnt = 0;

    // 요금제
    public static int printPricePlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[요금제를 선택하세요.]");
        System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명");

        return sc.nextInt();
    }

    public static int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[수행할 업무를 선택하세요]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");

        return sc.nextInt();
    }

    public static void printException1() {
        System.out.println("회원이 꽉 찼습니다.");
    }

    // 회원이 존재하면 true, 새로운 회원이면 false 반환
    public static boolean checkEmail(String[][] members, String email) {

        for (int i = 0; i < members.length; i++) {
            if (email.equals(members[i][1])) {
                return true;
            }
        }
        return false;
    }

    public static int findUserIndexbyEmail(String[][] members, String email) {
        int index = -1;
        for (int i = 0; i < members.length; i++) {
            if (email.equals(members[i][1])) {
                index = i;
            }
        }
        return index;
    }

    public static void addMember(String[][] members) {
        if (totalMemberCnt == totalCnt) {
            printException1();
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("이름을 입력하세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력하세요.");
        String email = sc.nextLine();
        System.out.println("연락처를 입력하세요.");
        String phone = sc.nextLine();

        boolean exist = checkEmail(members, email);

        if (exist) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        // members에 넣어주세요.
        members[totalMemberCnt][0] = name;
        members[totalMemberCnt][1] = email;
        members[totalMemberCnt][2] = phone;

        totalMemberCnt++;

    }

    public static void selectEmail(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이메일을 입력하세요.");
        String email = sc.nextLine();

        int index = findUserIndexbyEmail(members, email);
        if (index != -1) {
            System.out.printf("[이름]" + members[index][0]);
            System.out.printf("[이메일]" + members[index][1]);
            System.out.printf("[연락처]" + members[index][2]);
        } else{
            System.out.println("찾으시는 정보가 없습니다.");
        }

    }

    public static void selectName(String[][] members) {
        System.out.println("[조회] 이름을 입력하세요.");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        boolean flag = false;

        for (String[] member : members) {
            if (name.equals(member[0])) {
                System.out.println("[이름]" + member[0] + "[이메일]" + member[1] + "[연락처]" + member[2]);
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("찾으시는 정보가 없습니다.");
        }

    }

    public static void selectAll(String[][] members) {
        for (int i = 0; i < totalMemberCnt; i++) {
            System.out.println((i+1)+"[이름]"+members[i][0]+"[이메일]"+members[i][1]+"[연락처]"+members[i][2]);
        }
    }

    public static void updateMember(String[][] members) {
        System.out.println("[수정] 변경하려는 기존 이메일을 입력하세요.");

        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();

        int index = findUserIndexbyEmail(members, email);
        if (index != -1) {
            System.out.println("변경할 이름을 입력하세요.");
            members[index][0] = sc.nextLine();

            System.out.println("변경할 이메일을 입력하세요.");
            members[index][1] = sc.nextLine();

            System.out.println("변경할 연락처를 입력하세요.");
            members[index][2] = sc.nextLine();

            System.out.println("수정이 완료되었습니다.");
        }else {
            System.out.println("찾으시는 회원이 없습니다.");
        }

    }

    public static void deleteMember(String[][] members) {

        System.out.println("[삭제] 삭제하려는 회원의 이메일을 입력하세요.");

        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();

        int index = findUserIndexbyEmail(members, email);

        if (index != -1) {

            for(int i = index; i < totalMemberCnt-1; i++) {
                members[i][0]=members[i+1][0];
                members[i][1]=members[i+1][1];
                members[i][2]=members[i+1][2];
            }

            members[totalMemberCnt-1][0] = null;
            members[totalMemberCnt-1][1] = null;
            members[totalMemberCnt-1][2] = null;

            totalMemberCnt--;

            System.out.println("삭제가 완료되었습니다.");
        }else {
            System.out.println("찾으시는 회원이 없습니다.");
        }

    }

    public static void main(String[] args) {

        int pricePlanNum = printPricePlan();
        String[][] members = new String[pricePlanNum * 10][3];
        totalCnt = pricePlanNum * 10;

        while (true) {

            int menuNum = printMenu();
            switch (menuNum) {
                case 1:
                    addMember(members);
                    break;
                case 2:
                    selectEmail(members);
                    break;
                case 3:
                    selectName(members);
                    break;
                case 4:
                    selectAll(members);
                    break;
                case 5:
                    updateMember(members);
                    break;
                case 6:
                    deleteMember(members);
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
            }
        }

    }
}
