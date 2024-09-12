package programmers.day5;

import java.util.Scanner;

public class changeCodeByMode {
    public static void main(String[] args) {
        class Solution {
            public String solution(String code) {
                String answer = "";
                int mode = 0;

                for (int i = 0; i < code.length(); i++) {
                    if (code.charAt(i) == '1') {
                        mode = 1 - mode;
                    }
                    else if (i % 2 == mode) {
                        answer += code.charAt(i);
                    }
                }
                return "".equals(answer) ? "EMPTY" : answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        String code = sc.nextLine();

        System.out.println(sol.solution(code));

    }
}
