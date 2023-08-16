import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continueCalculation = null;
        do {
            System.out.println("Input:");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                System.out.println("Неверный формат ввода.");
                continue;
            }

            int num1 = convertToArabic(parts[0]);
            int num2 = convertToArabic(parts[2]);
            char operation = parts[1].charAt(0);
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                System.out.println("Числа должны быть в диапазоне от 1 до 10.");
                continue;
            }

            boolean isNum1Roman = isRoman(parts[0]);
            boolean isNum2Roman = isRoman(parts[2]);
            if (isNum1Roman != isNum2Roman) {
                System.out.println("Оба числа должны быть либо арабскими, либо римскими.");
                continue;
            }

            int result = calculate(num1, num2, operation, isNum1Roman && isNum2Roman);
            if (isNum1Roman && isNum2Roman) {
                System.out.println(convertToRoman(result));
            } else {
                System.out.println("Output:" +result);
            }

            System.out.println("continue? (y/n)");
            continueCalculation = scanner.nextLine();
        } while (continueCalculation.equalsIgnoreCase("y"));
    }


    private static int calculate(int num1, int num2, char operation, boolean isRoman) {
        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                if (isRoman && result < 0) {
                    throw new IllegalArgumentException("Результат не может быть отрицательным для римских чисел.");
                }
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    throw new IllegalArgumentException("Деление на ноль недопустимо.");
                }
                break;
            default:
                throw new IllegalArgumentException("Недопустимая операция: " + operation);
        }
        return result;
    }




    private static boolean isRoman(String str) {
        return str.matches("[IVXLCDM]+");
    }

    private static int convertToArabic(String str) {
        if (isRoman(str)) {
            switch (str) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        }
        return Integer.parseInt(str);
    }

    private static String convertToRoman(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Римские числа не могут быть отрицательными");
        }
        StringBuilder result = new StringBuilder();
        int[] arabicNumbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumbers = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabicNumbers.length; i++) {
            while (number >= arabicNumbers[i]) {
                number -= arabicNumbers[i];
                result.append(romanNumbers[i]);
            }
        }
        return result.toString();

    }
}
