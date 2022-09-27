package by.hayel.computer.science.csp.task.word;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import by.hayel.computer.science.csp.CSP;
import by.hayel.computer.science.csp.task.word.WordGrid.GridLocation;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class WordGridTest {
  /**
   * Method under test: {@link WordGrid#WordGrid(int, int)}
   */
  @Test
  void testConstructor() {
    WordGrid actualWordGrid = new WordGrid(1, 1);
    assertEquals(1, actualWordGrid.getColumns());
    assertEquals(1, actualWordGrid.getRows());
    assertEquals(1, actualWordGrid.getGrid().length);
    assertThrows(NegativeArraySizeException.class, () -> new WordGrid(-1, 1));
  }

  @Test
  void testSolution() {
    var grid = new WordGrid(9, 9);
    var words = List.of("IVAN", "JOE", "JOHN", "JANE");
    var domains = new HashMap<String, List<List<GridLocation>>>();
    words.forEach(word -> domains.put(word, grid.generateDomain(word)));
    var csp = new CSP<>(words, domains);
    csp.addConstraint(new WordSearchConstraint(words));
    var solution = csp.backtrackingSearch();
    if (solution == null) log.error("There is no solution for this task.");
    else {
      var generator = new Random();
      for(Entry<String, List<GridLocation>> item: solution.entrySet()) {
        var word = item.getKey();
        var locations = item.getValue();
        if(generator.nextBoolean()) Collections.reverse(locations);
        grid.mark(word, locations);
      }
      log.info("\n{}", grid);
    }
  }
}

