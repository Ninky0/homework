package practice.sql;

import java.sql.*;
import java.util.Scanner;

public class A_join {
    public Connection connection() {
        String url = "jdbc:mysql://localhost:3306/java_basic";
        String user = "root";
        String password = "비밀이지렁이";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conn Success!");

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertData(String userid, String password, String name, int age, String phone) {
        String query = "INSERT INTO members (userid, password, name, age, phone) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, phone);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("회원가입 성공!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectOne(String userid, String password) {
        String query = "SELECT userid, name, age, phone FROM members WHERE userid = ? AND password = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");

                System.out.println("Welcome " + name + '\n' + "age: " + age + ", phone: " + phone);
            } else {
                System.out.println("Fail : 존재하지 않는 회원입니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMenu() {
        System.out.println("작업을 선택해주세요.");
        System.out.println("[1]회원가입  [2]로그인  [3]종료");
    }

    public void join() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("id를 입력해주세요");
        String userid = scanner.nextLine();

        System.out.println("pw를 입력해주세요");
        String password = scanner.nextLine();

        System.out.println("성함을 입력해주세요");
        String name = scanner.nextLine();

        System.out.println("나이를 입력해주세요");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("전화번호를 입력해주세요");
        String phone = scanner.nextLine();

        insertData(userid, password, name, age, phone);
    }

    public void signup(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("id를 입력해주세요");
        String userid = scanner.nextLine();

        System.out.println("pw를 입력해주세요");
        String password = scanner.nextLine();

        selectOne(userid, password);
    }

    public static void main(String[] args) {
        A_join aJoin = new A_join();

        while (true) {
            aJoin.printMenu();
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 1:
                    aJoin.join();
                    break;
                case 2:
                    aJoin.signup();
                    break;
                case 3:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
                default:
                    System.out.println("잘못입력하셨습니다.");
            }
        }

    }

}
