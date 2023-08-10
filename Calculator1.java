import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator1 {
    private static final Map<String, Integer> romanToArabic = new HashMap<>();
    private static final Map<Integer, String> arabicToRoman = new HashMap<>();

    static {
        romanToArabic.put("I", 1);
        romanToArabic.put("II", 2);
        romanToArabic.put("III", 3);
        romanToArabic.put("IV", 4);
        romanToArabic.put("V", 5);
        romanToArabic.put("VI", 6);
        romanToArabic.put("VII", 7);
        romanToArabic.put("VIII", 8);
        romanToArabic.put("IX", 9);
        romanToArabic.put("X", 10);

        arabicToRoman.put(1, "I");
        arabicToRoman.put(2, "II");
        arabicToRoman.put(3, "III");
        arabicToRoman.put(4, "IV");
        arabicToRoman.put(5, "V");
        arabicToRoman.put(6, "VI");
        arabicToRoman.put(7, "VII");
        arabicToRoman.put(8, "VIII");
        arabicToRoman.put(9, "IX");
        arabicToRoman.put(10, "X");
        arabicToRoman.put(20, "XX");
        arabicToRoman.put(30, "XXX");
        arabicToRoman.put(40, "XL");
        arabicToRoman.put(50, "L");
        arabicToRoman.put(60, "LX");
        arabicToRoman.put(70, "LXX");
        arabicToRoman.put(80, "LXXX");
        arabicToRoman.put(90, "XC");
        arabicToRoman.put(100, "C");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input: ");
        String expression = scanner.nextLine();
        String[] tokens = expression.split("\\s+");

        if (tokens.length != 3) {
            System.out.println("Invalid expression!");
            return;
        }

        String firstOperand = tokens[0];
        String operator = tokens[1];
        String secondOperand = tokens[2];

        int firstValue;
        int secondValue;
        boolean isRoman = false;

        if (romanToArabic.containsKey(firstOperand)) {
            firstValue = romanToArabic.get(firstOperand);
            isRoman = true;
        } else {
            firstValue = Integer.parseInt(firstOperand);
            if (firstValue < 1 || firstValue > 10) {
                System.out.println("Invalid operand!");
                return;
            }
        }

        if (romanToArabic.containsKey(secondOperand)) {
            secondValue = romanToArabic.get(secondOperand);
            isRoman = true;
            if (!romanToArabic.containsKey(firstOperand)) {
                System.out.println("Invalid operand!");
                return;
            }
        } else {
            secondValue = Integer.parseInt(secondOperand);
            if (secondValue < 1 || secondValue > 10) {
                System.out.println("Invalid operand!");
                return;
            }
            if (romanToArabic.containsKey(firstOperand)) {
                System.out.println("Invalid operand!");
                return;
            }
        }

        int result = 0;
        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                result = firstValue / secondValue;
                break;
            default:
                System.out.println("Invalid operator!");
                return;
        }

        if (result < 0 || result > 100) {
            System.out.println("Result out of range!");
            return;
        }

        if (isRoman) {
            if (result == 0) {
                System.out.println("");
            } else if (result <= 10) {
                System.out.println(arabicToRoman.get(result));
            } else {
                int tens = result / 10 * 10;
                int ones = result % 10;
                String tensStr = tens == 0 ? "" : arabicToRoman.get(tens);
                String onesStr = ones == 0 ? "" : arabicToRoman.get(ones);
                System.out.println("Output:" + tensStr + onesStr);
            }
        } else {
            System.out.println("Output:" +result);
        }
    }
}
