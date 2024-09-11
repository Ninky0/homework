package practice.sql.notice;

public interface Notice {
    // 메뉴 출력
    void printMenu();
    // 사용자의 메뉴 선택
    void chooseMenu();

    // 가입
    void join();

    // id 중복 확인
    boolean ableUserId(String userid);

    // 새로운 id의 사용자를 db에 저장
    void insertUsr(String userid, String password, String name);

    // 로그인
    void signIn();

    // 가입된 사용자가 로그인을 하는지 확인
    void selectUsr(String userid, String password);

    // 글 목록
    void viewList();

    // 글 생성
    void createArticle();

    // 입력받은 내용으로 글 db에 저장
    void insertArticle(String content);

    // 글 수정
    void updateArticle();

    // 입력받은 내용으로 글 db에 update
    void reInsertArticle(int artno, String content);

    // 글 삭제
    void deleteArticle();

    // 글 작성자가 수정,삭제하려는지 - 접근확인
    boolean ableToAccess(int artno);

    // 로그아웃
    void signOut();

    // 회원탈퇴
    void withdrawUsr();
}
