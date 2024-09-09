package practice.basic.day6;


public class A_start {

    public static void main(String[] args) {
        A_reviewImpl rv = new A_reviewImpl();

        while (true) {
            rv.printMenu();
            rv.chooseMenu();

            int choice = rv.getChoice();

            switch (choice) {

                // 리뷰 추가
                case 1:
                    rv.addComment();
                    break;

                // 좋아요
                case 2:
                    rv.like();
                    break;

                // 전체 조회
                case 3:
                    rv.printAll();
                    break;

                // 리뷰 삭제
                case 4:
                    rv.deleteComment();
                    break;

                // 종료
                case 5:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;

                default:
                    System.out.println("잘못누르셨습니다.");
            }
        }

    }
}
