package programmers.day5;

import java.util.Scanner;

public class sumOrMultiply {
    public static void main(String[] args) {
        class Solution {
            public int solution(int[] num_list) {
                int answer = 0;

                int sum = 0;
                int mul = 1;

                for (int i = 0; i < num_list.length; i++) {
                    sum += num_list[i];
                    mul *= num_list[i];
                }

                if (sum * sum > mul) answer = 1;
                else if (sum * sum < mul) answer = 0;

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num_list = new int[n];
        for (int i = 0; i < n; i++) {
            num_list[i] = sc.nextInt();
        }

        System.out.println(sol.solution(num_list));
    }
}
