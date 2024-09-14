package programmers.day7;

import java.util.Arrays;
import java.util.Scanner;

public class countUp {
    public static void main(String[] args) {
        class Solution {
            public int[] solution(int start_num, int end_num) {

                int size = end_num - start_num + 1;

                int[] answer = new int[size];

                for(int i = start_num; i <= end_num; i++) {
                    answer[i - start_num] = i;
                }

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);

        int start_num = sc.nextInt();
        int end_num = sc.nextInt();

        System.out.println(Arrays.toString(sol.solution(start_num, end_num)));

    }
}
