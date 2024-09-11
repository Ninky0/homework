package practice.sql.notice;

public class Start {

    public static void main(String[] args) {

        NoticeImpl noti = new NoticeImpl();

        while (true) {

            noti.printMenu();

            //  [1]회원가입 [2]로그인 [3]글목록 [4]글등록 [5]글수정 [6]글삭제
            //  [7]로그아웃 [8]회원탈퇴 [9]프로그램종료

            switch (noti.getMenu()) {
                case 1:
                    noti.join();
                    break;
                case 2:
                    noti.signIn();
                    break;
                case 3:
                    noti.viewList();
                    break;
                case 4:
                    noti.createArticle();
                    break;
                case 5:
                    noti.updateArticle();
                    break;
                case 6:
                    noti.deleteArticle();
                    break;
                case 7:
                    noti.signOut();
                    continue;
                case 8:
                    noti.withdrawUsr();
                    continue;
                case 9:
                    System.out.println("이용해주셔서 감사합니다.");    // 프로그램 진짜 종료
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }

            if(noti.getMenu() == 7 || noti.getMenu() == 8) {
                break; // 내부 루프 종료
            }

        }


    }
}
