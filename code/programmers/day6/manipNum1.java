package programmers.day6;

import java.util.Scanner;

public class manipNum1 {
    public static void main(String[] args) {
        class Solution {
            public int solution(int n, String control) {
                String[] con = control.split("");

                for (int i = 0; i < con.length; i++) {
                    if (con[i].equals("w")) {
                        n += 1;
                    } else if (con[i].equals("s")) {
                        n -= 1;
                    } else if (con[i].equals("d")) {
                        n += 10;
                    } else {
                        n -= 10;
                    }
                }
                return n;
            }
        }

        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String control = sc.nextLine();

        System.out.println(sol.solution(n, control));
    }
}
