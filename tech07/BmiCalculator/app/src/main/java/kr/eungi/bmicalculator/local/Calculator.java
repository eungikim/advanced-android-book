package kr.eungi.bmicalculator.local;

/**
 * Created by shoma2da on 2015/12/21.
 */
public class Calculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand: expression.split("\\+"))
            sum += Integer.parseInt(summand);
        return sum;
    }
}

