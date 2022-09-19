package by.hayel.computer.science.fibonacci;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class FibonacciSeries {
  private static final Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1));

  private static int last = 0;
  private static int next = 1;

  /**
   * ATTENTION: infinite recursion method
   * @throws java.lang.StackOverflowError
   */
  public static int calculateInfinityRecursion(int n) throws StackOverflowError {
    return calculateInfinityRecursion(n - 1) + calculateInfinityRecursion(n - 2);
  }

  public static int calculateBaseRecursion(int n) {
    if (n < 2) return n;
    return calculateBaseRecursion(n - 1) + calculateBaseRecursion(n - 2);
  }

  public static int calculateWithMemo(int n) {
    if (!memo.containsKey(n)) {
      memo.put(n, calculateWithMemo(n - 1) + calculateWithMemo(n - 2));
    }
    return memo.get(n);
  }

  public static int calculateWithIteration(int n) {
    int last = 0;
    int next = 1;
    for (int counter = 0; counter < n; counter++) {
      int temp = last;
      last = next;
      next = temp + next;
    }
    return last;
  }

  public static String getFibonacciSequence(int n) {
    StringJoiner joiner = new StringJoiner("; ");
    getFibonacciStream().limit(n).mapToObj(String::valueOf).forEachOrdered(joiner::add);
    return joiner.toString();
  }

  public static IntStream getFibonacciStream() {
    return IntStream.generate(
        () -> {
          int temp = last;
          last = next;
          next = temp + next;
          return temp;
        });
  }
}
