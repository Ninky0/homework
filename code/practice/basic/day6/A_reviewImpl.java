package practice.basic.day6;

import java.util.*;

public class A_reviewImpl implements A_review {
    private Map<String, String> commentMap = new HashMap<>();
    private Map<String, Integer> likeMap = new HashMap<>();

    private int choice = 0;

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }


    @Override
    public void printMenu() {
        System.out.println("=======================");
        System.out.println("[1]댓글추가 [2]좋아요 [3]전체보기 [4]내용삭제 [5]종료");
        System.out.println("=======================");
    }

    @Override
    public void chooseMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("원하는 메뉴를 선택하시오 ->");

        int choice = sc.nextInt();
        setChoice(choice);
    }

    @Override
    public void addComment() {
        Scanner sc = new Scanner(System.in);

        System.out.println("사용자 ID를 입력하세요.");
        String userId = sc.nextLine();

        System.out.println("내용을 입력하세요.");
        String content = sc.nextLine();

        if (checkIDValid(userId) && checkContentValid(content)) {
            commentMap.put(userId, content);
            likeMap.put(userId, 0);
        }

    }

    @Override
    public boolean checkIDValid(String userId) {
        return !(userId == null || userId.trim().isEmpty());
    }

    @Override
    public boolean checkContentValid(String content) {
        return !(content == null || content.trim().isEmpty());
    }

    @Override
    public void printAll() {
        //Collections.sort()를 사용하기 위해 Map을 List 형태로 가져와야 한다.
        //Map.entrySet()을 이용하여 아래와 같이 Map의 Entry Set을 List 형태로 저장한다.

        // 좋아요 수(value)를 기준으로 내림차순 정렬한다.
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(likeMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 정렬된 결과를 출력한다.
        for (Map.Entry<String, Integer> entry : entryList) {
            String userId = entry.getKey();
            int likeCount = entry.getValue();
            String content = commentMap.get(userId);

            System.out.println("사용자 ID: " + userId + ", 내용: " + content + ", 좋아요: " + likeCount);
        }
    }

    @Override
    public void like() {
        printAll();
        System.out.println("좋아요할 리뷰의 사용자Id를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        String userId = sc.nextLine();

        if (likeMap.containsKey(userId)) {
            likeMap.put(userId, likeMap.get(userId) + 1); // 좋아요 수 증가
            System.out.println(userId + "님의 댓글에 좋아요를 눌렀습니다.");
        } else {
            System.out.println("해당 ID의 사용자를 찾을 수 없습니다.");
        }
    }

    @Override
    public void deleteComment() {
        printAll();
        System.out.println("삭제하실 댓글의 사용자 ID를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        String userId = sc.nextLine();
        commentMap.remove(userId);
        likeMap.remove(userId);
    }
}
