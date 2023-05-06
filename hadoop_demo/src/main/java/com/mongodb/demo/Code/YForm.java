package com.mongodb.demo.Code;

public class YForm {
    public static void main(String[] args) {
       // int[][] matrix = new int[][]{{2,0,0,0,2},{1,2,1,2,0},{0,1,2,1,0},{0,0,2,1,1},{1,1,2,1,1}};
        int[][] matrix = new int[][]{{1,0,2},{1,2,0},{0,2,0}};
        int[] recordY = new int[3];
        int[] other = new int[3];

        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                int cur = matrix[i][j];
                if(i == j && i <= matrix.length/2){
                    recordY[cur]++;
                }else if(i + j == matrix.length-1 && i <= matrix.length/2){ //注意这里是 i+j==length-1,要-1！
                    recordY[cur]++;
                }else if(i > matrix.length/2 && j == matrix[0].length/2){
                    recordY[cur]++;
                }else {
                    other[cur]++;
                }
            }
        }
        int maxOther = -1;
        int maxY = -1;
        int out = -1;
        int y = -1;
        int sum = 0;

        for(int i = 0;i<3;i++){
            if(maxOther < other[i]) {
                maxOther = other[i];
                out = i;
            }
        }

        for(int i = 0;i<3;i++){
            if(i == out) continue;

            if(maxY < recordY[i]){
                    maxY = recordY[i];
                    y = i;
            }
        }

        for(int i = 0;i<3;i++){
            if(i == out) continue;
            sum += other[i];
        }
        for(int i = 0;i<3;i++){
            if(i == out) continue;
            if(i == y) continue;
            sum += recordY[i];
        }

        System.out.println(sum);
    }
}
