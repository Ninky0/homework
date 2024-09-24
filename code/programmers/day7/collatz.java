package programmers.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class collatz {
    public static void main(String[] args) {
        class Solution {
            public int[] solution(int n) {

                ArrayList<Integer> list = new ArrayList<>();
                list.add(n);

                while(n!=1){
                    if (n % 2 == 0) {
                        n = n / 2;
                    } else {
                        n = 3 * n + 1;
                    }
                    list.add(n);
                }

                int[] answer = new int[list.size()];
                for(int i=0; i<list.size(); i++){
                    answer[i] = list.get(i);
                }

                return answer;
            }
        }

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Solution solution = new Solution();
        int[] result = solution.solution(n);
        System.out.println(Arrays.toString(result));
    }
}
