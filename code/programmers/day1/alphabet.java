package programmers.day1;

import java.util.Scanner;

public class alphabet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();

        for (int i = 0; i < a.length(); i++) {
            int b = a.charAt(i);

            if(b>=97 && b<=122) {
                b -= 32;
            }else if(b>=65 && b<=90) {
                b += 32;
            }

            System.out.print((char)b);

        }

    }

}
