package by.hayel.computer.science.csp.task.chess;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.hayel.computer.science.csp.CSP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class QueensConstraintTest {
  private static final List<Integer> CHESS_LINE = List.of(1, 2, 3, 4, 5, 6, 7, 8);

  /** Method under test: {@link QueensConstraint#QueensConstraint(List)} */
  @Test
  void testConstructor() {
    ArrayList<Integer> integerList = new ArrayList<>();
    QueensConstraint actualQueensConstraint = new QueensConstraint(integerList);
    List<Integer> columns = actualQueensConstraint.getColumns();
    assertSame(integerList, columns);
    assertTrue(columns.isEmpty());
    assertSame(columns, actualQueensConstraint.getVariables());
  }

  @Test
  void testSolution() {
    var columns = new ArrayList<>(CHESS_LINE);
    var rows = new HashMap<Integer, List<Integer>>();
    columns.forEach(column -> rows.put(column, new ArrayList<>(CHESS_LINE)));
    var csp = new CSP<>(columns, rows);
    csp.addConstraint(new QueensConstraint(columns));
    var solution = csp.backtrackingSearch();
    if (solution == null) log.error("There is no solution for this task.");
    else log.info("Solution: {}", solution);
  }
}
