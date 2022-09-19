package by.hayel.computer.science.fibonacci;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class FibonacciSeriesTest {
  /**
   * Method under test: {@link FibonacciSeries#calculateInfinityRecursion(int)}
   */
  @Test
  void testCalculateInfinityRecursion() {
    assertThrows(StackOverflowError.class,
        () -> FibonacciSeries.calculateInfinityRecursion(5));
  }

  /**
   * Method under test: {@link FibonacciSeries#calculateBaseRecursion(int)}
   */
  @Test
  void testCalculateBaseRecursion() {
    assertEquals(1, FibonacciSeries.calculateBaseRecursion(1));
    assertEquals(1, FibonacciSeries.calculateBaseRecursion(2));
    assertEquals(5, FibonacciSeries.calculateBaseRecursion(5));
    assertEquals(55, FibonacciSeries.calculateBaseRecursion(10));
    assertEquals(610, FibonacciSeries.calculateBaseRecursion(15));
    var start = System.nanoTime();
    assertEquals(102334155, FibonacciSeries.calculateBaseRecursion(40));
    var end = System.nanoTime();
    log.info("Base Recursion: finding the 40th took {} ms", (end - start) / 1_000_000);
  }

  /**
   * Method under test: {@link FibonacciSeries#calculateWithMemo(int)}
   */
  @Test
  void testCalculateWithMemo() {
    assertEquals(1, FibonacciSeries.calculateWithMemo(1));
    assertEquals(1, FibonacciSeries.calculateWithMemo(2));
    assertEquals(5, FibonacciSeries.calculateWithMemo(5));
    assertEquals(55, FibonacciSeries.calculateWithMemo(10));
    assertEquals(610, FibonacciSeries.calculateWithMemo(15));
    var start = System.nanoTime();
    assertEquals(102334155, FibonacciSeries.calculateWithMemo(40));
    var end = System.nanoTime();
    log.info("With memoization: finding the 40th took {} ms", (end - start) / 1_000_000);
  }

  /**
   * Method under test: {@link FibonacciSeries#calculateWithIteration(int)}
   */
  @Test
  void testCalculateWithIteration() {
    assertEquals(1, FibonacciSeries.calculateWithIteration(1));
    assertEquals(1, FibonacciSeries.calculateWithIteration(2));
    assertEquals(5, FibonacciSeries.calculateWithIteration(5));
    assertEquals(55, FibonacciSeries.calculateWithIteration(10));
    assertEquals(610, FibonacciSeries.calculateWithIteration(15));
    var start = System.nanoTime();
    assertEquals(102334155, FibonacciSeries.calculateWithIteration(40));
    var end = System.nanoTime();
    log.info("With iteration: finding the 40th took {} ms", (end - start) / 1_000_000);
  }

  /**
   * Method under test: {@link FibonacciSeries#getFibonacciSequence(int)}
   */
  @Test
  void testGetFibonacciSequence() {
    assertEquals("0", FibonacciSeries.getFibonacciSequence(1));
    log.info("Fibonacci sequence (40): {}", FibonacciSeries.getFibonacciSequence(40));
  }
}
