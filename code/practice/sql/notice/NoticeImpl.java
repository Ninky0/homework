package practice.sql.notice;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class NoticeImpl implements Notice {

    private int menu;
    private int userno;

    NoticeImpl() {
        this.menu = 0;
        this.userno = -1;
    }

    public int getMenu() {
        return this.menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getUserno() {
        return this.userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public Connection connection() {
        String url = "jdbc:mysql://localhost:3306/notice_database";
        String user = "root";
        String password = "비밀이지롱가링";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printMenu() {
        System.out.println("작업을 선택해주세요.");
        System.out.println("[1]회원가입 [2]로그인 [3]글목록 [4]글등록 [5]글수정 [6]글삭제");
        System.out.println("[7]로그아웃 [8]회원탈퇴 [9]프로그램종료");
        chooseMenu();
    }

    @Override
    public void chooseMenu() {
        Scanner sc = new Scanner(System.in);
        setMenu(sc.nextInt());
    }

    @Override
    public void join() {
        Scanner sc = new Scanner(System.in);
        System.out.println("id를 입력해주세요");
        String userid = sc.nextLine();

        if (ableUserId(userid)){
            System.out.println("pw를 입력해주세요");
            String password = sc.nextLine();

            System.out.println("성함을 입력해주세요");
            String name = sc.nextLine();

            insertUsr(userid, password, name);
        } else {
            join(); // 가능한 user id를 입력할때까지 재귀
        }
    }

    @Override
    public boolean ableUserId(String userid) {
        String query = "SELECT userid FROM usr WHERE userid = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Fail: 이미 존재하는 id입니다.");
                return false;
            }
            else {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUsr(String userid, String password, String name) {
        String query = "INSERT INTO usr (userid, password, name) VALUES (?, ?, ?)";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("회원가입 성공!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void signIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("id를 입력해주세요");
        String userid = sc.nextLine();

        System.out.println("pw를 입력해주세요");
        String password = sc.nextLine();

        selectUsr(userid, password);
    }

    @Override
    public void selectUsr(String userid, String password) {
        String query = "SELECT userno, userid, name FROM usr WHERE userid = ? AND password = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userno = resultSet.getInt("userno");
                String name = resultSet.getString("name");

                System.out.println(name + "님, 반갑습니다!");
                setUserno(userno); //로그인한 사용자의 userno 저장
            } else {
                System.out.println("Fail : 존재하지 않는 회원입니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewList() {
        String query = "SELECT * FROM article";

        try (
                Connection conn = connection();
                Statement statement = conn.createStatement(); // Statement 사용
        ) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int artno = resultSet.getInt("artno");
                int userno = resultSet.getInt("userno");
                String content = resultSet.getString("content");
                Timestamp createAt = resultSet.getTimestamp("create_at");
                Timestamp updateAt = resultSet.getTimestamp("update_at");

                System.out.printf("글 번호: %d, 작성자 번호: %d, 내용: %s, 생성일: %s, 수정일: %s\n",
                        artno, userno, content, createAt != null ? createAt.toString() : "NULL",
                        updateAt != null ? updateAt.toString() : "NULL");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createArticle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("글 내용을 입력해주세요.");
        String content = sc.nextLine();

        insertArticle(content);
    }

    @Override
    public void insertArticle(String content) {
        String query = "INSERT INTO article (userno, content, create_at) VALUES (?, ?, ?)";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            int userno = getUserno(); // 현재 로그인한 사용자의 userno 가져오기
            LocalDateTime create_at = LocalDateTime.now();

            Timestamp timestamp = Timestamp.valueOf(create_at);

            preparedStatement.setInt(1, userno);
            preparedStatement.setString(2, content);
            preparedStatement.setTimestamp(3, timestamp);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("글 등록 성공!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateArticle() {
        Scanner sc = new Scanner(System.in);
        viewList();

        System.out.println("수정하려는 글의 artno를 입력해주세요.");
        int artno = sc.nextInt();
        sc.nextLine(); // 개행문자 제거

        if (ableToAccess(artno)) {
            System.out.println("새로운 글 내용을 입력해주세요.");
            String content = sc.nextLine();

            reInsertArticle(artno, content);
        } else {
            System.out.println("수정 권한이 없습니다.");
        }
    }

    @Override
    public void reInsertArticle(int artno, String content) {
        String query = "UPDATE article SET content = ?, update_at = ? WHERE artno = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            LocalDateTime update_at = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(update_at);

            preparedStatement.setString(1, content);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, artno);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("글 수정 성공!");
            } else {
                System.out.println("해당 글이 존재하지 않습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteArticle() {
        Scanner sc = new Scanner(System.in);

        viewList();

        System.out.println("삭제하려는 글의 artno를 입력해주세요.");
        int artno = sc.nextInt();
        sc.nextLine();  // 개행문자 제거

        if (ableToAccess(artno)) {
            String query = "DELETE FROM article WHERE artno = ?";

            try (
                    Connection conn = connection();
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
            ) {
                preparedStatement.setInt(1, artno);
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    System.out.println("글이 성공적으로 삭제되었습니다.");
                } else {
                    System.out.println("해당 글이 존재하지 않습니다.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("삭제 권한이 없습니다.");
        }
    }

    @Override
    public boolean ableToAccess(int artno) {
        String query = "SELECT userno FROM article WHERE artno = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, artno);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int articleUserno = resultSet.getInt("userno");
                return getUserno() == articleUserno; // 로그인한 사용자의 userno와 글 작성자의 userno 비교
            } else {
                System.out.println("해당 글이 존재하지 않습니다.");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void signOut() {
        System.out.println("로그아웃 처리 되었습니다.");
        setUserno(-1);
    }

    @Override
    public void withdrawUsr() {
        String query = "DELETE FROM usr WHERE userno = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, getUserno());
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("탈퇴 처리 되었습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setUserno(-1);
    }

    @Override
    public void deleteArticleToo() {
        System.out.println("탈퇴처리 전, 작성했던 모든 글이 삭제됩니다.");

        String query = "DELETE FROM article WHERE userno = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, getUserno());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("글 삭제 완료. 탈퇴처리하겠습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}