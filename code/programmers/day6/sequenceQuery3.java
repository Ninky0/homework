package programmers.day6;

import java.util.Scanner;

public class sequenceQuery3 {
    public static void main(String[] args) {
        class Solution {
            public int[] solution(int[] arr, int[][] queries) {

                int loopSize = queries.length;

                int first = 0;
                int second = 0;

                int temp = 0;

                for(int i=0; i<loopSize; i++) {
                    first = queries[i][0];
                    second = queries[i][1];

                    temp = arr[first];
                    arr[first] = arr[second];
                    arr[second] = temp;
                }

                return arr;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[][] queries = new int[m][2];
        for (int i = 0; i < m; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        System.out.println(sol.solution(arr, queries));
    }
}
