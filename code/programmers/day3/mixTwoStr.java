package programmers.day3;

import java.util.Scanner;

public class mixTwoStr {
    public static void main(String[] args) {

        class Solution {
            public String solution(String str1, String str2) {
                String answer = "";

                for(int i=0; i<str1.length(); i++) {

                    //answer += (""+str1.charAt(i)+str2.charAt(i));

                    String s1 = String.valueOf(str1.charAt(i));
                    String s2 = String.valueOf(str2.charAt(i));

                    answer += (s1 + s2);

                }

                return answer;

            }
        }

        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        Solution sol = new Solution();

        System.out.println(sol.solution(str1, str2));

    }
}
