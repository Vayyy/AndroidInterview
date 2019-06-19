package com.jc.jctest.test;

import java.util.Scanner;

/**
 * author：jc on  2019-06-05 10:12
 * mail：chen.juncong@qq.com
 */
public class Test {

    public static void main(String[] args) {

        //String字符串的数字相加
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int result = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                result = result + Integer.parseInt(line.charAt(i) + "");
            }
        }
        System.out.print(result);
    }
}
