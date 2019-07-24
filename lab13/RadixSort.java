import java.util.Arrays;
/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {

    private static int R = 256;

    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);

        int max = 0;
        for (String asc : asciis) {
            max = max > asc.length() ? max : asc.length();
        }

        int index = 0;
        while (index < max) {
            sorted = sortHelperLSD(sorted, index);
            index += 1;
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        int[] buckets = new int[R];
        String[] sorted = new String[asciis.length];

        for (String a : asciis) {
            int pos = (int) a.charAt(a.length() - index - 1);
            buckets[pos] += 1;
        }

        int k = 0;
        for (int i = 0; i < buckets.length; i += 1) {
            for (int j = 0; j < buckets[i]; j += 1, k += 1) {
                for (String a : asciis) {
                    int pos = (int) a.charAt(a.length() - index - 1);
                    while (pos == i) {
                        sorted[k] = a;
                        break;
                    }
                }
            }
        }
        return sorted;
    }



        public static void main (String[] args) {
            String[] testArr1 = { "he#", "ala", "suc", "suc", "ala",};
            String[] result1 = sort(testArr1);

            System.out.println(Arrays.toString(result1));

        }



    }



