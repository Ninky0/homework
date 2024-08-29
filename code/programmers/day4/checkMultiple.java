package programmers.day4;

import java.util.Scanner;

public class checkMultiple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int n = sc.nextInt();

        class Solution {
            public int solution(int num, int n) {
                int answer = 0;
                if (num % n == 0) {
                    answer = 1;
                } else {
                    answer = 0;
                }
                return answer;
            }
        }

        Solution sol = new Solution();

        System.out.println(sol.solution(num, n));
    }
}
