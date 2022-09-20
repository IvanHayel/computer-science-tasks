package by.hayel.computer.science.search;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class GenericSearchTest {
  private static final List<Integer> TEST_NUMBERS = List.of(1, 5, 15, 15, 15, 15, 3301);
  private static final List<Character> TEST_CHARACTERS = List.of('a', 'd', 'e', 'f', 'z');
  private static final List<String> TEST_STRINGS = List.of("john", "mark", "ronald", "sarah");

  /**
   * Method under test: {@link GenericSearch#linearContains(List, Comparable)}
   */
  @Test
  void testLinearContains() {
    assertTrue(GenericSearch.linearContains(TEST_NUMBERS, 5));
    assertFalse(GenericSearch.linearContains(TEST_NUMBERS, 22));
    assertTrue(GenericSearch.linearContains(TEST_CHARACTERS, 'a'));
    assertFalse(GenericSearch.linearContains(TEST_CHARACTERS, 'y'));
    assertTrue(GenericSearch.linearContains(TEST_STRINGS, "mark"));
    assertFalse(GenericSearch.linearContains(TEST_STRINGS, "sheila"));
  }

  /**
   * Method under test: {@link GenericSearch#binaryContains(List, Comparable)}
   */
  @Test
  void testBinaryContains() {
    assertTrue(GenericSearch.binaryContains(TEST_NUMBERS, 5));
    assertFalse(GenericSearch.binaryContains(TEST_NUMBERS, 22));
    assertTrue(GenericSearch.binaryContains(TEST_CHARACTERS, 'a'));
    assertFalse(GenericSearch.binaryContains(TEST_CHARACTERS, 'y'));
    assertTrue(GenericSearch.binaryContains(TEST_STRINGS, "mark"));
    assertFalse(GenericSearch.binaryContains(TEST_STRINGS, "sheila"));
  }
}

