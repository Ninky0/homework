package programmers.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class makeArray2 {
    public static void main(String[] args) {
        class Solution {
            public int[] solution(int l, int r) {
                ArrayList<Integer> list = new ArrayList<>();

                for (int i = l; i <= r; i++) {
                    String letter = i + "";
                    String[] check = letter.split("");

                    Boolean flag = true;

                    for (int j = 0; j < check.length; j++) {
                        if (!check[j].equals("0") && !check[j].equals("5")) {
                            flag = false;
                        }
                    }

                    if (flag) {
                        list.add(i);
                    }
                }

                if (list.isEmpty()) {
                    list.add(-1);
                }

                int[] answer = new int[list.size()];

                for (int i = 0; i < list.size(); i++) {
                    answer[i] = list.get(i);
                }

                return answer;
            }
        }

        Scanner sc = new Scanner(System.in);

        Solution sol = new Solution();

        int l = sc.nextInt();
        int r = sc.nextInt();

        String result = Arrays.toString(sol.solution(l, r));

        System.out.println(result);

    }
}
