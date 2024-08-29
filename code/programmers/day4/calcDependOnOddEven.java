package programmers.day4;

import java.util.Scanner;

public class calcDependOnOddEven {
    public static void main(String[] args) {

        class Solution {
            public int solution(int n) {
                int answer = 0;

                //홀수
                if(n%2 ==1){
                    for(int i = n; i >= 0;i-=2){
                        answer+=i;
                    }
                }else if(n%2 == 0){
                    for(int i = n; i>=0; i-=2){
                        answer += i*i;
                    }
                }

                return answer;
            }
        }

        Solution solution = new Solution();

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        System.out.println(solution.solution(n));
    }
}
