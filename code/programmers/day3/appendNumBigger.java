package programmers.day3;

import java.util.Scanner;

public class appendNumBigger {
    public static void main (String[] args) {
        class Solution {
            public int solution(int a, int b) {
                int answer = 0;

                //Integer.toString() 대신 String.valueof() 써도 됨
                String val1 = Integer.toString(a) + Integer.toString(b);
                String val2 = Integer.toString(b)+ Integer.toString(a);

                int ret1 = Integer.parseInt(val1);
                int ret2 = Integer.parseInt(val2);

                if(ret1 >= ret2) {
                    answer = ret1;
                }else answer = ret2;

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println(sol.solution(a, b));

    }
}
