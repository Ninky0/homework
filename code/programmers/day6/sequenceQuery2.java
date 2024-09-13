package programmers.day6;

import java.util.Arrays;
import java.util.Scanner;

public class sequenceQuery2 {
    public static void main(String[] args) {
        class Solution {
            public int[] solution(int[] arr, int[][] queries) {
                int loopSize = queries.length;
                int[] answer = new int[loopSize];

                for (int i = 0; i < loopSize; i++) {
                    int small = queries[i][0];
                    int big = queries[i][1];
                    int least = queries[i][2];

                    answer[i] = apply(arr, small, big, least);
                }
                return answer;
            }

            public int apply(int[] arr, int small, int big, int least) {
                int min = Integer.MAX_VALUE;

                for (int i = small; i <= big; i++) {
                    if (arr[i] > least && arr[i] < min) {
                        min = arr[i];
                    }
                }

                return (min == Integer.MAX_VALUE) ? -1 : min;
            }
        }

        Solution solution = new Solution();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        sc.nextLine();
        int[][] queries = new int[m][3];
        for (int i = 0; i < m; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
            queries[i][2] = sc.nextInt();
        }

        String result = Arrays.toString(solution.solution(arr, queries));
        System.out.println(result);

    }
}
