package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Input: ");

        Scanner reader = new Scanner(System.in);
        String InputData = reader.nextLine();
        reader.close();

        String[] myArray = InputData.split(" ");

        if (myArray.length != 3) {
            System.err.println("Incorrect format");
            System.exit(0);
        }

        String[] romeArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] oper = {"+", "-", "*", "/"};
        ArrayList<String> rA = new ArrayList<>(Arrays.asList(romeArray));
        ArrayList<String> aA = new ArrayList<>(Arrays.asList(arabArray));
        ArrayList<String> operation = new ArrayList<>(Arrays.asList(oper));

        boolean a = aA.contains(myArray[0]);
        boolean b = aA.contains(myArray[2]);
        boolean c = rA.contains(myArray[0]);
        boolean d = rA.contains(myArray[2]);
        boolean e = operation.contains(myArray[1]);
        if(e == false){
            throw new Exception("Неверный оператор");
        }

        if (a == true && b == true){
            int num1 = Integer.parseInt(myArray[0]);
            int num2 = Integer.parseInt(myArray[2]);
            char op = myArray[1].charAt(0);

            // вызов класса
            Solution solution1 = new Solution();
            int result = solution1.calc(num1, num2, op);
            System.out.println("Output: " + result);

        } else if (c == true && d == true)
        {
            char op = myArray[1].charAt(0);

            RomanToArabic romanToArabic1 = new RomanToArabic();
            int result1 = romanToArabic1.romanToArabic(myArray[0]);
            int result2 = romanToArabic1.romanToArabic(myArray[2]);
            Solution solution1 = new Solution();
            int result3 = solution1.calc(result1, result2, op);

            ArabicToRoman arabicToRoman1 = new ArabicToRoman();
            String result = arabicToRoman1.arabicToRoman(result3);

            System.out.println("Input: " + result );

        } else{
            System.err.println("Попробуй еще раз");
            System.exit(0);
        }
    }
}
class Solution{
    int ans;
    int calc(int num1, int num2, char op){

        switch (op) {
            case ('+'): ans = num1 + num2;
                break;
            case ('-'): ans = num1 - num2;
                break;
            case ('*'):  ans = num1 * num2;
                break;
            case ('/'): ans = num1 / num2;
                break;
        }
        return ans;
    }
}
class RomanToArabic {
    public static  int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }
        return result;
    }
}
class ArabicToRoman {
    public static String arabicToRoman(int number) {
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}