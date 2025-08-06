package io.github.sysalexvipulverma.util;

import io.github.sysalexvipulverma.exception.InvalidRangeException;
import io.github.sysalexvipulverma.exception.NegativeNumberException;
import lombok.experimental.UtilityClass;

import java.util.stream.IntStream;

@UtilityClass
public final class IntegerUtils {

    public static int abs(int input) {
        return input < 0 ? -input : input;
    }

    public static boolean isEven(int input) {
        return input % 2 == 0;
    }

    public static boolean isOdd(int input) {
        return input % 2 != 0;
    }

    /*
     * Question from Let Us C
     * Sum of all the digits of a number
     * */
    public static int sumOfDigits(int input) {
        input = abs(input);
        int sum = 0;

        while (input != 0) {
            sum += (input % 10);
            input /= 10;
        }
        return sum;
    }

    /*
     * Question from Let Us C
     * Count the number of digits
     * */
    public static int numberOfDigits(int input) {
        if (0 == input) {
            return 1;
        }

        input = abs(input);

        int count = 0;
        while (input != 0) {
            count++;
            input /= 10;
        }
        return count;
    }

    public static int maxDigit(int input) {
        input = abs(input);

        int max = 0;
        while (input != 0) {
            int digit = input % 10;

            if (9 == digit) {
                return 9;
            }

            if (digit > max) {
                max = digit;
            }
            input /= 10;
        }
        return max;
    }

    public static int minDigit(int input) {
        input = abs(input);

        int min = 9;
        while (input != 0) {
            int digit = input % 10;

            if (0 == digit) {
                return 0;
            }

            if (digit < min) {
                min = digit;
            }
            input /= 10;
        }
        return min;
    }

    public static long factorialRecursive(int input) {
        if (input < 0) {
            throw new NegativeNumberException();
        }

        if (input == 0 || input == 1) {
            return 1;
        }

        return input * factorialRecursive(input - 1);
    }

    public static long factorialIterative(int input) {
        if (input < 0) {
            throw new NegativeNumberException();
        }

        if (input == 0 || input == 1) {
            return 1;
        }

        long fact = 1;
        while (input >= 1) {
            fact *= input;
            input -= 1;
        }
        return fact;
    }

    public static String toNumberSystem(int input, int radix) {
        if (0 == input) {
            return Integer.toString(input);
        }

        if (radix <= 1 || radix > 36) {
            throw new NumberFormatException("invalid radix = " + radix);
        }

        StringBuilder builder = new StringBuilder();
        final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        while (input != 0) {
            int digit = input % radix;

            if (digit >= 10) {
                builder.append(alpha.charAt(digit - 10));
            } else {
                builder.append(digit);
            }
            input /= radix;
        }

        return builder.reverse().toString();
    }

    public static String toBinaryString(int input) {
        return toNumberSystem(input, 2);
    }

    public static String toOctalString(int input) {
        return toNumberSystem(input, 8);
    }

    public static String toHexString(int input) {
        return toNumberSystem(input, 16);
    }

    public static boolean isPrime(int input) {
        if (input < 2) {
            return false;
        }

        for (int counter = 2; counter < input; counter++) {
            if (input % counter == 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] digits(int input) {
        input = abs(input);
        int[] allDigits = new int[numberOfDigits(input)];

        for (int index = allDigits.length - 1; index >= 0; index--) {
            allDigits[index] = input % 10;
            input /= 10;
        }

        return allDigits;
    }

    public static boolean isPerfectNumber(int input) {
        if (input <= 1) {
            return false;
        }

        int sum = 1;
        for (int counter = 2; counter < input; counter++) {
            if (input % counter == 0) {
                sum += counter;
            }
        }
        return sum == input;
    }

    /*
     * Question from Let Us C
     * Check if input number is a strong number
     * */
    public static boolean isStrongNumber(int input) {
        if (input <= 0) {
            return false;
        }

        int copy = input;
        int newNumber = 0;

        while (copy != 0) {
            newNumber += (int) factorialRecursive(copy % 10);
            copy /= 10;
        }
        return newNumber == input;
    }

    /*
     * Harshad Number
     * A number divisible by the sum of its digits.
     * Example: 18 → 1+8 = 9, 18 is divisible by 9
     * */
    public static boolean isHarshadNumber(int input) {
        return input % sumOfDigits(input) == 0;
    }

    public static long productOfDigits(int input) {
        boolean wasNegative = input < 0;
        input = abs(input);

        long product = 1;
        while (input != 0) {
            int digit = input % 10;
            if (0 == digit) {
                return 0;
            }
            product *= digit;
            input /= 10;
        }

        if (wasNegative) {
            return -product;
        }
        return product;
    }

    /*
     * Spy Number
     * A number where the sum of its digits equals the product of its digits.
     * Example: 123 → 1+2+3 = 6, 1×2×3 = 6
     * */
    public static boolean isSpyNumber(int input) {
        return sumOfDigits(input) == productOfDigits(input);

    }

    public static int singleDigitSum(int input) {
        int sum = sumOfDigits(input);

        if (sum < 10) {
            return sum;
        }
        return singleDigitSum(sum);
    }

    public static boolean isHappyNumber(int input) {
        return 1 == singleDigitSum(input);
    }

    public static int count1(int input) {
        int count = 0;
        String binary = toBinaryString(input);
        for (char bit : binary.toCharArray()) {
            if ('1' == bit) {
                ++count;
            }
        }
        return count;
    }

    /*
     * Evil Number
     * A number with even number of 1s in its binary representation.
     * Example: 3 → binary: 11 → two 1s → Evil
     * */
    public static boolean isEvilNumber(int input) {
        return isEven(count1(input));
    }

    public static double pow(long mantissa, int exponent) {
        if (0 == exponent) {
            return 1;
        }

        if (1 == exponent) {
            return mantissa;
        }

        double product = 1D;
        for (int counter = 1; counter <= exponent; counter++) {
            product *= mantissa;
        }

        return exponent < 0 ? (1 / product) : product;
    }

    /*
     * Question from Let Us C
     * check if input number is an armstrong
     * 153 → 1³ + 5³ + 3³ = 1 + 125 + 27 = 153 ✅
     * */
    public static boolean isArmstrongNumber(int input) {
        if (input < 0) {
            return false;
        }

        if (input < 10) {
            return true;
        }

        int sum = 0;
        int copy = input;
        int numberOfDigits = numberOfDigits(input);

        while (copy != 0) {
            int digit = copy % 10;
            sum += (int) pow(digit, numberOfDigits);
            copy /= 10;
        }
        return input == sum;
    }

    /*
     * Check if a number is perfect square
     * For example 16 and 25 are perfect square because 4 * 4 = 16
     * and 5 * 5 = 25
     *
     * Asked in Microsoft
     * */
    public static boolean isPerfectSquare(int input) {
        if (input < 0) {
            return false;
        }

        for (int counter = 1; ; counter++) {
            int square = counter * counter;
            if (square == input) {
                return true;
            }

            if (square > input) {
                return false;
            }
        }
    }

    public static int[] allPrimes(int from, int upto) {
        if (from >= upto) {
            throw new InvalidRangeException();
        }

        return IntStream
                .iterate(from, x -> x <= upto, x -> x + 1)
                .filter(IntegerUtils::isPrime)
                .toArray();
    }

    // TODO : sqrt() implementation

    // TODO : Question form Let Us C, check if a number is part of fibonacci series

    // TODO : Question form Let Us C, reverse() digits

    // TODO : Question form Let Us C, palindrome number

    // TODO : shuffle digits

    // TODO : Kaprekar Number
    /*
     * A number where the square can be split into two parts that sum to the original number.
     * Example: 45 → 45² = 2025 → 20 + 25 = 45
     * */

    // TODO : Neon Number
    /*
     * Square of the number, and sum of the digits of the square equals the original number.
     * Example: 9 → 9² = 81, 8+1 = 9
     * */

    // TODO : Automorphic Number
    /*
     * A number whose square ends with the number itself.
     * Example: 76 → 76² = 5776
     * */

    // TODO : Question from Let Us C, sum of first and last digit

    // TODO : Question from Let Us C, armstrong numbers in given range
}
