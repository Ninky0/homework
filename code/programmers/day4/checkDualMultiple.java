package programmers.day4;

import java.util.Scanner;

public class checkDualMultiple {
    public static void main(String[] args) {



        class Solution {
            public int solution(int number, int n, int m) {
                int answer = 0;

                if(number%n==0 && number%m==0){
                    answer = 1;
                }else{
                    answer = 0;
                }

                return answer;
            }
        }

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Solution sol = new Solution();
        System.out.println(sol.solution(number, n, m));


    }

}
