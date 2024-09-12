package programmers.day5;

import java.util.Scanner;

public class diceGame2 {
    public static void main(String[] args) {
        class Solution {
            public int solution(int a, int b, int c) {
                int answer = 0;

                if (a != b && a != c && b != c) {
                    answer = a + b + c;
                } else if (a == b && a == c && b == c) {
                    answer = (a + b + c) * (a * a + b * b + c * c) * (a * a * a + b * b * b + c * c * c);
                } else {
                    answer = (a + b + c) * (a * a + b * b + c * c);
                }

                return answer;
            }
        }

        Solution sol = new Solution();
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println(sol.solution(a, b, c));

    }
}
