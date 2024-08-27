package programmers.day3;

import java.util.Scanner;

public class compareTwoCalc {
    public static void main(String[] args) {
        class Solution {
            public int solution(int a, int b) {
                int answer = 0;

                String ret1 = Integer.toString(a)+Integer.toString(b);
                int val1 = Integer.parseInt(ret1);
                int val2 = 2*a*b;

                if(val1>=val2){
                    answer = val1;
                }else answer = val2;

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
