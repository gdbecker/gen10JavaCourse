package gdb.bigdecimalcodealong;

import java.math.BigDecimal;

/**
 * @date Thursday December 19, 2019
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        BigDecimalMath myMath = new BigDecimalMath();
        
        //Recommended to use the String constructor for making all new BigDecimals
        BigDecimal op1 = new BigDecimal("10");
        BigDecimal op2 = new BigDecimal("6");
        
        System.out.println(myMath.Calculate(MathOperator.PLUS, op1, op2));
        System.out.println(myMath.Calculate(MathOperator.MINUS, op1, op2));
        System.out.println(myMath.Calculate(MathOperator.MULTIPLY, op1, op2));
        System.out.println(myMath.Calculate(MathOperator.DIVIDE, op1, op2));
    }
}
