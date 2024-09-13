package programmers.day6;

import java.util.Scanner;

public class manipNum2 {
    public static void main(String[] args) {
        class Solution {
            public String solution(int[] numLog) {
                String answer = "";

                for (int i = 0; i < numLog.length - 1; i++) {
                    int man = numLog[i + 1] - numLog[i];

                    switch (man) {
                        case 1:
                            answer += "w";
                            break;
                        case -1:
                            answer += "s";
                            break;
                        case 10:
                            answer += "d";
                            break;
                        case -10:
                            answer += "a";
                            break;
                    }
                }
                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        int[] numLog = new int[n];

        for (int i = 0; i < n; i++) {
            numLog[i] = sc.nextInt();
        }

        System.out.println(sol.solution(numLog));

    }
}
