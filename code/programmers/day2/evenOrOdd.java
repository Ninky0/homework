package programmers.day2;

import java.util.Scanner;

public class evenOrOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String result = "";

        if(n%2==0){
            result = "even";
        }else{
            result = "odd";
        }

        System.out.println(n + " is " + result);

    }
}
