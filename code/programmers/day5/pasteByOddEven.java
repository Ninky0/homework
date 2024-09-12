package programmers.day5;

import java.util.Scanner;

public class pasteByOddEven {
    public static void main(String[] args) {

        class Solution {
            public int solution(int[] num_list) {
                int answer = 0;
                String odd = "";
                String even = "";

                for (int num : num_list) {
                    if (num % 2 == 1) {
                        odd += Integer.toString(num);
                    }else {
                        even += Integer.toString(num);
                    }
                }

                answer = Integer.parseInt(odd) + Integer.parseInt(even);

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);

        int[] num_list = new int[sc.nextInt()];

        for (int i = 0; i < num_list.length; i++) {
            num_list[i] = sc.nextInt();
        }

        sol.solution(num_list);
    }
}
