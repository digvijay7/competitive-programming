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

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
    	Map<Integer, Integer> numberFreq = new HashMap<>();
    	Map<Integer, Set<Integer>> freqNumbers = new HashMap<>();
    	List<Integer> output = new ArrayList<>(queries.size());
    	for (int i = 0; i < queries.size(); i++) {
    		int op = queries.get(i).get(0);
    		int num = queries.get(i).get(1);
    		if (op == 1) {
    			numberFreq.putIfAbsent(num, 0);
    			Set<Integer> numbers = freqNumbers.getOrDefault(numberFreq.get(num), Collections.emptySet());
    			numbers.remove(num);
    			numberFreq.put(num, numberFreq.get(num) + 1);
    			freqNumbers.putIfAbsent(numberFreq.get(num), new HashSet<>());
    			freqNumbers.get(numberFreq.get(num)).add(num);
    		} else if (op == 2 && numberFreq.containsKey(num) && numberFreq.get(num) > 0) {
    			Set<Integer> numbers = freqNumbers.get(numberFreq.get(num));
    			numbers.remove(num);
    			numberFreq.put(num, numberFreq.get(num) - 1);
    			freqNumbers.putIfAbsent(numberFreq.get(num), new HashSet<>());
    			freqNumbers.get(numberFreq.get(num)).add(num);
    		} else if (op == 3) {
    			if (freqNumbers.containsKey(num) && freqNumbers.get(num).size() > 0) {
    				output.add(1);
    				//System.out.println(1);
    			} else {
    				output.add(0);
    				//System.out.println(0);
    			}
    		}
    	}
    	return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

