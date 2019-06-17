package com.manbo.exercise;


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
        System.out.println(arithmetic("5 * (2 + 3) "));
    }

    private static Integer arithmetic(String expr) {
        // Input: “1 + 2 + 3” will return 6
        // Input: “(1 + 2) * 3” will return 9
        // Input: “(1 +) * 3” will raise an Exception
        // Please put your code here

        return calculate(expr).intValue();
    }


    /**
     * implement calculate by using recursive
     *
     * @throws NumberFormatException if expression is not a valid representation of a BigDecimal.
     **/
    private static BigDecimal calculate(String expression) {
        if (expression == null || "".equals(expression)) {
            throw new NumberFormatException("invalid arithmetic expression");
        }

        expression = expression.replaceAll(" ", "");

        // handle parenthesis
        int index = expression.lastIndexOf(OPEN_PARENTHESIS);
        if (index >= 0) {
            int rightIndex = expression.indexOf(CLOSE_PARENTHESIS, index);
            String left = expression.substring(0, index);
            String right = "";
            if (rightIndex + 1 < expression.length()) {
                right = expression.substring(rightIndex + 1);
            }

            BigDecimal middle = calculate(expression.substring(index + 1, rightIndex));
            return calculate(left + middle + right);
        }

        // operator = "+";
        index = expression.lastIndexOf(PLUS);
        if (index > 0) {
            BigDecimal left = calculate(expression.substring(0, index));
            BigDecimal right = calculate(expression.substring(index + 1));
            return left.add(right);
        }

        // operator = "-";
        index = expression.lastIndexOf(MINUS);
        if (index == 0) {
            // handle negative number
            BigDecimal result = calculate(expression.substring(index + 1));
            if (result.compareTo(new BigDecimal("0")) < 0) {
                return result.abs();
            } else {
                return result.negate();
            }
        } else if (index > 0) {
            BigDecimal left = calculate(expression.substring(0, index));
            BigDecimal right = calculate(expression.substring(index + 1));
            return left.subtract(right);
        }

        // operator = "*";
        index = expression.lastIndexOf(MULTIPLY);
        if (index > 0) {
            BigDecimal left = calculate(expression.substring(0, index));
            BigDecimal right = calculate(expression.substring(index + 1));
            return left.multiply(right);
        }

        // operator = "/";
        index = expression.lastIndexOf(DIVIDED);
        if (index > 0) {
            BigDecimal left = calculate(expression.substring(0, index));
            BigDecimal right = calculate(expression.substring(index + 1));
            return left.divide(right, BigDecimal.ROUND_DOWN);
        }

        BigDecimal num;
        try {
            num = new BigDecimal(expression);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("invalid arithmetic expression");
        }
        return num;
    }
}
