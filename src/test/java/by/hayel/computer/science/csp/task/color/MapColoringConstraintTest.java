package by.hayel.computer.science.csp.task.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import by.hayel.computer.science.csp.CSP;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MapColoringConstraintTest {
  private static final String WA = "Western Australia";
  private static final String NT = "Northern Territory";
  private static final String SA = "South Australia";
  private static final String Q = "Queensland";
  private static final String NSW = "New South Wales";
  private static final String V = "Victoria";
  private static final String T = "Tasmania";

  private static final String RED = "red";
  private static final String GREEN = "green";
  private static final String BLUE = "blue";
  private static final List<String> COLORS = List.of(RED, GREEN, BLUE);

  /** Method under test: {@link MapColoringConstraint#MapColoringConstraint(String, String)} */
  @Test
  void testConstructor() {
    MapColoringConstraint actualMapColoringConstraint =
        new MapColoringConstraint("First Place", "Second Place");
    assertEquals("First Place", actualMapColoringConstraint.getFirstPlace());
    assertEquals(2, actualMapColoringConstraint.getVariables().size());
    assertEquals("Second Place", actualMapColoringConstraint.getSecondPlace());
  }

  @Test
  void testSolution() {
    var variables = List.of(WA, NT, SA, Q, NSW, V, T);
    var domains = new HashMap<String, List<String>>();
    variables.forEach(v -> domains.put(v, COLORS));
    var csp = new CSP<>(variables, domains);
    csp.addConstraint(new MapColoringConstraint(WA, NT));
    csp.addConstraint(new MapColoringConstraint(WA, SA));
    csp.addConstraint(new MapColoringConstraint(SA, NT));
    csp.addConstraint(new MapColoringConstraint(Q, NT));
    csp.addConstraint(new MapColoringConstraint(Q, SA));
    csp.addConstraint(new MapColoringConstraint(Q, NSW));
    csp.addConstraint(new MapColoringConstraint(NSW, SA));
    csp.addConstraint(new MapColoringConstraint(V, SA));
    csp.addConstraint(new MapColoringConstraint(V, NSW));
    csp.addConstraint(new MapColoringConstraint(V, T));
    var solution = csp.backtrackingSearch();
    if (solution == null) log.error("There is no solution for this task.");
    else log.info("Solution: {}", solution);
  }
}
