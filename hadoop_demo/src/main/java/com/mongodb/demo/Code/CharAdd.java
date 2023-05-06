package com.mongodb.demo.Code;

import java.util.HashSet;
import java.util.Set;

public class CharAdd {
    public static void main(String[] args) {
        String str = "CodeSignalZ";

        Set<Character> set = new HashSet<>();
        set.add('a');set.add('e');set.add('i');set.add('o');set.add('u');set.add('A');set.add('E');set.add('I');set.add('O');set.add('U');
        StringBuffer sb = new StringBuffer();

        for(char cur:str.toCharArray()){
            if(cur == 'Z'){  //保证闭合
                sb.append('B');

            }else if(cur == 'z'){ //保证闭合
                sb.append('b');

            }else if(set.contains(cur)){
                sb.append(cur);
            }else{
                cur+=1;//需要用 +=1 来修改， 直接传cur+1不行。
                while(set.contains(cur)){
                    cur+=1;
                }
                sb.append(cur);
            }

        }
        System.out.println(sb.toString());
    }
}
