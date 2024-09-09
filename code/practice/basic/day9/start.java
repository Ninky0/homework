package practice.basic.day9;

public class start {
    public static void main(String[] args) {

        accountBookImpl book = new accountBookImpl();

        while (true) {
            book.printMenu();

            switch (book.getMenu()) {
                case 1:
                    book.addItem();
                    break;
                case 2:
                    System.out.println("조회하려는 날짜(제목)를 입력해주세요.");
                    book.displayDetail();
                    break;
                case 3:
                    book.deleteAll();
                    break;
                case 4:
                    book.deleteItem();
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
