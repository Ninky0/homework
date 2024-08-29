package programmers.day4;

import java.util.Scanner;

public class flagDiffCalc {
    public static void main(String[] args) {

        class Solution {
            public int solution(int a, int b, boolean flag) {
                int answer = 0;

                answer = (flag?(a+b):(a-b));

                return answer;
            }
        }

        Solution solution = new Solution();
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        boolean flag = sc.nextBoolean();    // true, false

        System.out.println(solution.solution(a, b, flag));

    }
}
