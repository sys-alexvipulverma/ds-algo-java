package io.github.sysalexvipulverma.util;

import io.github.sysalexvipulverma.exception.EmptyArrayException;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.function.LongToIntFunction;

@UtilityClass
public class IntegerArrayUtils {

    /*
     * Question from Let Us C
     *
     * Sum of an integer array
     * Time Complexity O(n)
     * */
    public static long sum(@NonNull int... array) {
        if (0 == array.length) {
            return 0;
        }

        long sum = 0;
        for (int element : array) {
            sum += element;
        }
        return sum;
    }

    public static double average(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        return ((double) sum(array)) / array.length;
    }

    /*
     * Question from Let Us C
     *
     * Copy the contents of one array into another in the reverse order.
     * Time Complexity O(n)
     * */
    public static int[] reversed(@NonNull int... array) {
        if (0 == array.length) {
            return new int[0];
        }

        int[] out = new int[array.length];
        for (int i = 0, j = array.length - 1; i < array.length; i++, j--) {
            out[i] = array[j];
        }
        return out;
    }

    public static int max(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        if (1 == array.length) {
            return array[0];
        }

        int max = Integer.MIN_VALUE;
        for (int element : array) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

    public static int min(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        if (1 == array.length) {
            return array[0];
        }

        int min = Integer.MAX_VALUE;
        for (int element : array) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }

    /*
     * Reversing an array without taking any other temporary array
     * Time Complexity O(n / 2)
     *
     * Asked in IG Group first coding round
     * */
    public static void reverseIterative(@NonNull int... array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /*
     * Reverse an array without taking any other array and use recursion
     * Asked in Publicis Sapient first coding round
     *
     * If input array is a = new int[] {1, 2, 3, 4}; then call this method as
     * reverseRecursive(a, 0, a.length - 1);
     * */
    public static void reverseRecursive(@NonNull int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;

        reverseRecursive(array, start + 1, end - 1);
    }

    public static long totalNumberOfElements(int[]... arrays) {
        long count = 0;

        for (int[] array : arrays) {
            if (null != array) {
                count += array.length;
            }
        }
        return count;
    }

    public static int[] merge(int[]... arrays) {
        int[] out = new int[(int) totalNumberOfElements(arrays)];

        int index = 0;

        for (int[] array : arrays) {
            if (null != array) {
                for (int element : array) {
                    out[index++] = element;
                }
            }
        }
        return out;
    }

    /*
     * The below implementations of addFirst(), addLast(), removeFirst() and removeLast()
     * will be helpful while creating an integer stack or queue (array based)
     * */
    public static int[] addFirst(int elementToAdd, @NonNull int... array) {
        if (0 == array.length) {
            return new int[]{elementToAdd};
        }

        int[] out = new int[array.length + 1];
        int index = 0;
        out[index++] = elementToAdd;

        while (index < out.length) {
            out[index] = array[index - 1];
            index++;
        }
        return out;
    }

    public static int[] addLast(int elementToAdd, @NonNull int... array) {
        if (0 == array.length) {
            return new int[]{elementToAdd};
        }

        int[] out = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            out[i] = array[i];
        }
        out[out.length - 1] = elementToAdd;
        return out;
    }

    public static int[] removeFirst(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        if (1 == array.length) {
            return new int[0];
        }

        int[] out = new int[array.length - 1];

        for (int index = 0; index < out.length; index++) {
            out[index] = array[index + 1];
        }

        return out;
    }

    public static int[] removeLast(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        if (1 == array.length) {
            return new int[0];
        }

        int[] out = new int[array.length - 1];

        for (int index = 0; index < out.length; index++) {
            out[index] = array[index];
        }

        return out;
    }

    public static int countFrequency(int query, @NonNull int... array) {
        int count = 0;

        for (int element : array) {
            if (query == element) {
                count++;
            }
        }

        return count;
    }

    public static boolean isSortedAscending(@NonNull int... array) {
        for (int index = 0; index < array.length - 1; index++) {
            if (array[index] > array[index + 1]) {
                return false;
            }
        }
        return true;
    }


    public static boolean isSortedDescending(@NonNull int... array) {
        for (int index = 0; index < array.length - 1; index++) {
            if (array[index] < array[index + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSortedInAnyOrder(@NonNull int... array) {
        return isSortedAscending(array) || isSortedDescending(array);
    }

    public static long product(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        long product = 1;

        for (int element : array) {
            if (0 == element) {
                return 0;
            }
            product *= element;
        }
        return product;
    }

    // TODO : add(index) and remove(index)

    // TODO : splitEvenOdd() method to split an array into two arrays one containing even numbers, the other odd

    // TODO : merge 2 sorted array into third sorted array. mergeSortedArrays(int[] a, int[] b)

    // TODO : Rotate an array

    // TODO : Check if a multidimensional array is a matrix

    // TODO : All the sorting algorithms

    // TODO : Frequency of each element

    // TODO : Check if an element is unique with O(n) complexity. Asked in Canopus Infosystem

    // TODO : Check if a multidimensional array is a square matrix

    // TODO : Add, subtract, multiply and divide 2 matrices. Return 3rd matrix as result

    // TODO : Find transpose of a matrix

    // TODO : Check if input element is part diagonals of square matrix

    public static boolean isMatrix(@NonNull int[]... twoDimensionalArray) {
        int length = twoDimensionalArray.length;

        for (int[] row : twoDimensionalArray) {
            if (null == row) {
                return false;
            }
            if (length != row.length) {
                return false;
            }
        }
        return true;
    }

    // TODO : Second max element. Asked in Publicis Sapient first coding round

    // TODO : Second max element using Stream API. Asked in Wipro first coding round

    public static void forEach(@NonNull IntConsumer consumer, @NonNull int... array) {
        for (int element : array) {
            consumer.accept(element);
        }
    }

    public static void mapInPlace(@NonNull LongToIntFunction mapper, @NonNull int... array) {
        for (int index = 0; index < array.length; index++) {
            array[index] = mapper.applyAsInt(array[index]);
        }
    }

    public static int[] mapped(@NonNull LongToIntFunction mapper, @NonNull int... array) {
        int[] out = new int[array.length];

        for (int index = 0; index < array.length; index++) {
            out[index] = mapper.applyAsInt(array[index]);
        }
        return out;
    }

    public static boolean contains(int query, @NonNull int... array) {
        for (int element : array) {
            if (element == query) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(int query, @NonNull int... array) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == query) {
                return index;
            }
        }
        return -1;
    }

    public static int lastIndexOf(int query, int... array) {
        for (int lastIndex = array.length - 1; lastIndex >= 0; lastIndex--) {
            if (array[lastIndex] == query) {
                return lastIndex;
            }
        }
        return -1;
    }


    /*
     * Question from Let Us C
     *
     * maximum and minimum elements in an integer array
     * Time Complexity O(n)
     * */
    public static int[] minAndMax(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        if (1 == array.length) {
            return new int[]{array[0], array[0]};
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int element : array) {
            if (element > max) {
                max = element;
            }
            if (element < min) {
                min = element;
            }
        }
        return new int[]{min, max};
    }

    /*
     * Print the element of an array to the console using a recursive method
     * Asked in DXC
     *
     * Call the below method as
     * printElements(0, array) if you want to print elements from 0 index.
     * */
    public static void printElements(int fromIndex, @NonNull int... array) {
        if (fromIndex >= array.length) {
            return;
        }

        System.out.println(array[fromIndex]);
        printElements(fromIndex + 1, array);
    }

    /*
     * Question from Let Us C, count frequency of each element
     *
     * Write now using java.util.Map<Integer, Integer> type of this implementation
     * but when I will design my own map, I will use it here
     * */
    public static Map<Integer, Integer> frequency(@NonNull int... array) {
        if (0 == array.length) {
            throw new EmptyArrayException();
        }

        if (1 == array.length) {
            return Map.of(array[0], 1);
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int element : array) {
            if (map.containsKey(element)) {
                map.put(element, map.get(element) + 1);
            } else {
                map.put(element, 1);
            }
        }

        return map;
    }
}
