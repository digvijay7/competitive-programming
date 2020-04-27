import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        Map<Long, List<Integer>> reverseIndex = new HashMap<>(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            reverseIndex.putIfAbsent(arr.get(i), new ArrayList<>(1000));
            reverseIndex.get(arr.get(i)).add(i);
        }
        long count = 0;
        for (int i = 0; i < arr.size(); i++) {            
            long ai = arr.get(i);
            if (ai % r != 0) {
                continue;
            }

            if (reverseIndex.containsKey(ai / r) && reverseIndex.containsKey(ai * r)) {
                int startJ = getStartIndexGreaterThan(reverseIndex.get(ai / r), i);
                List<Integer> akIndice = reverseIndex.get(ai * r);
                int startK = getStartIndexGreaterThan2(akIndice, i);
                count += ((long) startJ * (akIndice.size() - startK));
            }
        }
        return count;
    }

    private static int getStartIndexGreaterThan(List<Integer> indice, int x) {

        int i = Collections.binarySearch(indice, x);
        return i < 0 ? -(i + 1) : i;
    }

    private static int getStartIndexGreaterThan2(List<Integer> indice, int x) {

        int i = Collections.binarySearch(indice, x);
        return i < 0 ? -(i + 1) : i + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

