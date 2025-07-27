package io.github.sysalexvipulverma.util;

import io.github.sysalexvipulverma.exception.EmptyArrayException;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IntegerArrayUtils {

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
     * Asked in Publicis Sapient coding round
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
}
