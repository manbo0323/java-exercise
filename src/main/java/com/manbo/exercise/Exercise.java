package com.manbo.exercise;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by manboyu.
 * Date: 2019/5/23
 * Time: 下午9:32
 */
public class Exercise {

    private final static String OPEN_PARENTHESIS = "(";
    private final static String CLOSE_PARENTHESIS = ")";
    private final static String PLUS = "+";
    private final static String MINUS = "-";
    private final static String MULTIPLY = "*";
    private final static String DIVIDED = "/";

    public static void main(String[] args) {
        System.out.println(arithmetic(args[0]));
    }

    private static Integer arithmetic(String expr) {
        // Input: “1 + 2 + 3” will return 6
        // Input: “(1 + 2) * 3” will return 9
        // Input: “(1 +) * 3” will raise an Exception
        // Please put your code here

        return cal(expr).intValue();
    }

    private static BigDecimal cal(String str) throws NumberFormatException {
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("invalid arithmetic expression");
        }

        str = StringUtils.deleteWhitespace(str);

        // handle parenthesis
        int index = str.lastIndexOf(OPEN_PARENTHESIS);
        if (index >= 0) {
            int rightIndex = str.indexOf(CLOSE_PARENTHESIS, index);


            String left = str.substring(0, index);
            String right = "";
            if (rightIndex + 1 < str.length()) {
                right = str.substring(rightIndex + 1);
            }

            BigDecimal middle = cal(str.substring(index + 1, rightIndex));
            return cal(left + middle + right);
        }

        // operator = "+";
        index = str.lastIndexOf(PLUS);
        if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.add(right);
        }

        // operator = "-";
        index = str.lastIndexOf(MINUS);
        if (index == 0) { // 負數處理
            BigDecimal result = cal(str.substring(index + 1));
            if (result.compareTo(new BigDecimal("0")) < 0) { // 小於0
                return result.abs(); // 絕對值
            } else {
                return result.negate(); // 相反數
            }
        } else if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.subtract(right);
        }

        // operator = "*";
        index = str.lastIndexOf(MULTIPLY);
        if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.multiply(right);
        }

        // operator = "/";
        index = str.lastIndexOf(DIVIDED);
        if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.divide(right, BigDecimal.ROUND_DOWN);
        }

        BigDecimal num;
        try {
            num = new BigDecimal(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("invalid arithmetic expression");
        }
        return num;
    }
}
