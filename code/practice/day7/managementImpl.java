package practice.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class managementImpl implements member {

    private int plan = 0;
    private int choice = 0;

    // 나중엔 이걸 <String, String[]> 으로 관리해보자.
    private Map<String, List<String>> memberMap = new HashMap<>();

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    @Override
    public void printPlan() {
        System.out.println("[요금제를 선택하세요.]");
        for (pricePlan plan : pricePlan.values()) {
            System.out.println("[" + (plan.ordinal() + 1) + "]" + plan.name() + " : " + plan.getPeopleNum() + "명 (현재 가입: " + plan.getMemberCnt() + "명)");
        }
        putPlan();
    }

    @Override
    public void putPlan() {
        Scanner sc = new Scanner(System.in);
        setPlan(sc.nextInt());
    }

    @Override
    public void printMenu() {
        System.out.println("[수행할 업무를 선택하세요]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]요금제변경 [8]초기화 [9]프로그램종료");
        putChoice();
    }

    @Override
    public void putChoice() {
        Scanner sc = new Scanner(System.in);
        setChoice(sc.nextInt());
    }

    @Override
    public void addMember() {
        Scanner sc = new Scanner(System.in);

        System.out.println("이름을 입력하세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력하세요.");
        String email = sc.nextLine();
        System.out.println("연락처를 입력하세요.");
        String phone = sc.nextLine();

        // 0 base 인덱스 (사용자 선택에서 1 빼주기)
        int select = getPlan() - 1;

        // .value() : 열거형의 모든 객체들을 배열로 리턴
        // 사용자가 선택한 플랜의 열거형 객체를 selectedPlan 이라고 하자.
        pricePlan selectedPlan = pricePlan.values()[select];

        // 선택한 플랜의 가입 여부를 판단해보자.
        if (selectedPlan.joinValidation()) {

            if (checkEmail(email)) {

                List<String> memberInfo = new ArrayList<>();
                memberInfo.add(name);
                memberInfo.add(phone);

                memberMap.put(email, memberInfo);

                selectedPlan.addCnt();

                System.out.println("회원 추가를 완료하였습니다.");
            } else {
                System.out.println("이미 존재하는 회원입니다.");
            }
        } else {
            System.out.println("선택한 요금제의 정원이 초과되었습니다. 다른 요금제를 선택하세요.");
        }
    }

    @Override
    public boolean checkEmail(String email) {
        return !memberMap.containsKey(email);
    }

    @Override
    public void findMemberByEmail() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이메일을 입력하세요.");
        String email = sc.nextLine();

        if (memberMap.containsKey(email)) {
            System.out.println("이메일: " + email);

            List<String> memberInfo = memberMap.get(email);
            System.out.println("이름: " + memberInfo.get(0));
            System.out.println("연락처: " + memberInfo.get(1));
        } else {
            System.out.println("해당 이메일로 등록된 회원이 없습니다.");
        }
    }

    @Override
    public void findMemberByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이름을 입력하세요.");
        String name = sc.nextLine();

        boolean flag = false;

        // memberMap 순회, 이름 비교
        for (Map.Entry<String, List<String>> entry : memberMap.entrySet()) {
            List<String> memberInfo = entry.getValue();
            String memberName = memberInfo.get(0);

            if (name.equals(memberName)) {
                flag = true;
                System.out.println("이름: " + memberName);
                System.out.println("이메일: " + entry.getKey());
                System.out.println("연락처: " + memberInfo.get(1));
            }
        }

        // 순회가 끝나고도 flag가 계속 false라면,
        if (!flag) {
            System.out.println("해당 이름으로 등록된 회원이 없습니다.");
        }
    }

    @Override
    public void printAll() {

    }


}
