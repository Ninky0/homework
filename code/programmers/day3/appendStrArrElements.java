package programmers.day3;

import java.util.Scanner;

public class appendStrArrElements {
    public static void main(String[] args) {
        class Solution {
            public String solution(String[] arr) {
                String answer = "";

                for(int i = 0; i < arr.length; i++) {
                    answer += arr[i];
                }

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");

        System.out.println(sol.solution(arr));

    }
}
