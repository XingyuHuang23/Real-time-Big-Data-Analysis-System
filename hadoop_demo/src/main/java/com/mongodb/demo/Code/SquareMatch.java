package com.mongodb.demo.Code;

import java.util.HashMap;
import java.util.Map;

public class SquareMatch {
    public static void main(String[] args) {
        int[][] board = new int[][]{{2, 4, 2, 4, 2},{4, 1, 4, 2, 2},{1, 4, 2, 5, 3}};
        char[][] pattern = new char[][]{{'4', 'c'},{'c', 'b'}};

        int[] records = new int[10]; //还得考虑到一个数字已经被一个char占据的情况。

        int w = pattern[0].length;
        int l = pattern.length;

       out2: for(int i = 0;i<board.length - l+1;i++){
           out:for(int j = 0;j<board[0].length-w+1;j++) {
                Map<Character,Integer> map = new HashMap<>();

                for(int n = 0;n<l;n++){
                    for(int m = 0;m<w;m++){
                        if(Character.isDigit(pattern[n][m])){
                            if(pattern[n][m] - '0' != board[i+n][j+m])  continue out;  //沃日，类型转换！！！

                        }else{
                            if(map.containsKey(pattern[n][m])){
                                if(map.get(pattern[n][m]) != board[i+n][j+m]) continue out;

                            }else{
                                if(records[board[i+n][j+m]] == 1) continue out; //还得考虑到一个数字已经被一个char占据的情况。

                                map.put(pattern[n][m],board[i+n][j+m]);
                                records[board[i+n][j+m]] = 1;
                            }
                        }
                    }
                }
                System.out.println(i+","+j);
                break out2;
            }
        }

    }
}
