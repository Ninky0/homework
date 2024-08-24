package programmers.day1;

public class specialLetter {
    public static void main(String[] args) {

        // '\이거' 이거처럼, 작은따옴표로 묶고, 역슬래쉬 뒤에 써준다.

        // 예제-> !@#$%^&*(\'"<>?:;
        System.out.print("!@#$%^&*(" + '\\'+'\''+'\"'+"<>?:;");

    }
}
