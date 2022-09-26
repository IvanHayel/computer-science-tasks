package by.hayel.computer.science.missionary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.hayel.computer.science.search.GenericSearch;
import by.hayel.computer.science.search.GenericSearch.Node;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MCStateTest {
  private static void displaySolution(List<MCState> path) {
    if (path.size() == 0) return;
    var oldState = path.get(0);
    log.info("\n{}", oldState);
    var states = path.subList(1, path.size());
    for (MCState currentState : states) {
      String message;
      if (currentState.isBoatOnWest()) {
        message =
            String.format(
                "%n%d missionaries and %d cannibals moved from the east bank to the west bank.%n",
                oldState.getEastMissionaries() - currentState.getEastMissionaries(),
                oldState.getEastCannibals() - currentState.getEastCannibals());
      } else {
        message =
            String.format(
                "%n%d missionaries and %d cannibals moved from the west bank to the east bank.%n",
                oldState.getWestMissionaries() - currentState.getWestMissionaries(),
                oldState.getWestCannibals() - currentState.getWestCannibals());
      }
      log.info(message);
      log.info("\n{}", currentState);
      oldState = currentState;
    }
  }

  /** Method under test: {@link MCState#MCState(int, int, boolean)} */
  @Test
  void testConstructor() {
    MCState actualMcState = new MCState(1, 1, true);
    assertEquals(2, actualMcState.getEastCannibals());
    assertTrue(actualMcState.isBoatOnWest());
    assertEquals(1, actualMcState.getWestMissionaries());
    assertEquals(1, actualMcState.getWestCannibals());
    assertEquals(2, actualMcState.getEastMissionaries());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link MCState#successors(MCState)} )}
   *   <li>{@link MCState#goalTest()}
   *   <li>{@link GenericSearch#breadthFirstSearch(Object, Predicate, Function)}
   *   <li>{@link GenericSearch#nodeToPath(Node)}
   * </ul>
   */
  @Test
  void testSolution() {
    var start = new MCState(MCState.getMax(), MCState.getMax(), true);
    var solution = GenericSearch.breadthFirstSearch(start, MCState::goalTest, MCState::successors);
    if (solution == null) {
      log.error("There is no solution found using breadth-first search!");
    } else {
      var path = GenericSearch.nodeToPath(solution);
      displaySolution(path);
    }
  }
}
