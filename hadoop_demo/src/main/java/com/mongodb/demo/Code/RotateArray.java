package com.mongodb.demo.Code;

public class RotateArray {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int n = arr.length;

//        arr = halfRotate(arr);

        arr = secondaryRotate(mainRoate(nintyRotate(arr)));

        for(int[] cur:arr){

        }
    }

    private static int[][] nintyRotate(int[][] arr) {
        arr = halfRotate(arr);
        arr = mainRoate(arr);
        return arr;
    }

    private static int[][] secondaryRotate(int[][] arr) {
        int n = arr.length;
        for(int i = n-1;i>=0;i--){
            for(int j = n-1-i;j<n;j++){
                int tmp = arr[i][j];
                arr[i][j] = arr[n-j-1][n-i-1];
                arr[n-j-1][n-i-1] = tmp;
            }
        }
        return arr;
    }

    private static int[][] mainRoate(int[][] arr) {
        int n = arr.length;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<i;j++){
                int tmp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = tmp;
            }
        }
        return arr;
    }

    private static int[][] halfRotate(int[][] arr) {
        int n = arr.length;
        for(int i = 0;i<n/2;i++){
            for(int j = 0;j<n;j++){
                int tmp = arr[i][j];
                arr[i][j] = arr[n-i-1][j];
                arr[n-i-1][j] =  tmp;
            }
        }

        return arr;
    }
}
