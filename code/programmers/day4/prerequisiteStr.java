package programmers.day4;

import java.util.Scanner;

public class prerequisiteStr {

    public static void main(String[] args) {


        class Solution {
            public int solution(String ineq, String eq, int n, int m) {
                int answer = 0;

                if(ineq.equals("<")){
                    if(eq.equals("=")){
                        answer = (n<=m) ? 1 : 0;
                    }else{
                        answer = (n<m) ? 1 : 0;
                    }
                }else{
                    if(eq.equals("=")){
                        answer = (n>=m) ? 1 : 0;
                    }else{
                        answer = (n>m) ? 1 : 0;
                    }
                }
                return answer;
            }
        }

        Solution solution = new Solution();

        Scanner sc = new Scanner(System.in);
        String ineq = sc.nextLine();
        String eq = sc.nextLine();

        int n = sc.nextInt();
        int m = sc.nextInt();

        System.out.println(solution.solution(ineq, eq, n, m));

    }
}
