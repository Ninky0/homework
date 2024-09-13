package programmers.day6;

import java.util.Scanner;

public class last2Ele {
    public static void main(String[] args) {
        class Solution {
            public int[] solution(int[] num_list) {
                int size = num_list.length;

                int[] answer = new int[size + 1];

                for (int i = 0; i < size; i++) {
                    answer[i] = num_list[i];
                }

                // 이렇게 삼항연산자로 쓰는 방법도 있음.
                // answer[size] = (num_list[size-1] > num_list[size-2]) ? num_list[size-1] - num_list[size-2] : num_list[size-1] * 2;

                int ret = 0;

                if (num_list[size - 1] > num_list[size - 2]) {
                    ret = num_list[size - 1] - num_list[size - 2];
                } else {
                    ret = num_list[size - 1] * 2;
                }
                answer[size] = ret;

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
