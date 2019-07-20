package hw3.hash;

import java.util.List;
import java.util.Arrays;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];
        int oomagesSize = oomages.size();
        for(int i = 0; i < M; i++){
            buckets[i] = 0;
        }
        for(int i = 0; i < oomagesSize; i++){
            int hashCode = oomages.get(i).hashCode();
            int bucketNum = (hashCode & 0x7FFFFFFF) % M;
            buckets[bucketNum] += 1;//if oomages have same bucketNum, they should put in same bucket.
        }
        Arrays.sort(buckets);

        return (buckets[0] > (oomagesSize / 50.0)) && (buckets[M-1] < (oomagesSize / 2.5));

    }
}
