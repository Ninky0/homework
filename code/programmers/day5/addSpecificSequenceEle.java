package programmers.day5;

import java.util.Scanner;

public class addSpecificSequenceEle {
    public static void main(String[] args) {
        class Solution {

            // a, a+d, a+2d, a+3d ---
            public int solution(int a, int d, boolean[] included) {
                int answer = 0;

                int n = included.length;

                for(int i = 0; i<n; i++){
                    if(included[i]){
                        answer += (a+i*d);
                    }
                }

                return answer;
            }
        }

        Solution sol = new Solution();

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int d = scanner.nextInt();
        scanner.nextLine();
        boolean[] included = {true,false,false,true,true};

        System.out.println(sol.solution(a, d, included));
    }
}
