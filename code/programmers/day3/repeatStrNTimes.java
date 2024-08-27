package programmers.day3;

import java.util.Scanner;

public class repeatStrNTimes {
    public static void main(String[] args) {
        class Solution {
            public String solution(String my_string, int k) {
                String answer = "";

                for(int i = 0; i<k; i++){
                    answer += my_string;
                }

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);

        String my_string = sc.nextLine();
        int k = sc.nextInt();

        System.out.println(sol.solution(my_string, k));

    }
}
