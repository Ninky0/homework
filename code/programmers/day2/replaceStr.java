package programmers.day2;

import java.util.Scanner;

public class replaceStr {

    public static void main(String[] args) {

        class Solution {
            public String solution(String my_string, String overwrite_string, int s) {
                String answer = "";

                int len1 = my_string.length();
                int len2 = overwrite_string.length();

                String str1 = my_string.substring(0, s);
                String str2 = my_string.substring(len2 + s, len1);

                answer = str1 + overwrite_string + str2;

                // replace() 연습용 코드
                // String sub = my_string.substring(s, len2);
                // String rep = sub.replace(sub, overwrite_string);
                // answer = my_string.substring(0, s) + rep + my_string.substring(len2 + s, len1);

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        String my_string = sc.nextLine();
        String overwrite_string = sc.nextLine();
        int s = sc.nextInt();

        System.out.println(sol.solution(my_string, overwrite_string, s));

    }
}
