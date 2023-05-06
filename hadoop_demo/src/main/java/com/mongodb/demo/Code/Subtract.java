package com.mongodb.demo.Code;

import java.util.ArrayList;
import java.util.List;

public class Subtract {
    public static void main(String[] args) {
            //int[] num = new int[]{3,3,5,2,2,1,0};
            //int[] num = new int[]{5,5,5,5};
        int[] num = new int[]{0,0};
            int cur = -1;
            boolean flag = true;
            List<Integer> list = new ArrayList<>();

            while(flag){
                cur = -1;
                for(int i = 0;i<num.length;i++){
                    if(num[i] == 0) continue;

                    if(cur == -1){
                        cur = num[i];  //模拟题的常数记录，一定要先处理，否则后面会有尾处理的麻烦。
                        list.add(cur);
                        flag = true;
                    }

                    if(cur > num[i]) break;

                    num[i] = num[i] - cur;
                }
                if(cur == -1) flag = false;
            }
            int res = 0;

            for(int n:list){
                res += n;
            }

            System.out.println(res);
    }
}
