package by.hayel.computer.science.maze;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.hayel.computer.science.search.GenericSearch;
import by.hayel.computer.science.search.GenericSearch.Node;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MazeTest {
  /** Method under test: {@link Maze#toString()} */
  @Test
  void testToString() {
    var maze = new Maze();
    log.info("Default maze:\n{}", maze);
  }

  /** Method under test: {@link Maze#isGoalReached(MazeLocation)} */
  @Test
  void testIsGoalReached() {
    Maze maze = new Maze();
    assertFalse(maze.isGoalReached(new MazeLocation(1, 1)));
    assertTrue(maze.isGoalReached(new MazeLocation(9, 9)));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Maze#successors(MazeLocation)}
   *   <li>{@link Maze#mark(List)}
   *   <li>{@link Maze#clear(List)}
   *   <li>{@link GenericSearch#depthFirstSearch(Object, Predicate, Function)}
   *   <li>{@link GenericSearch#nodeToPath(Node)}
   * </ul>
   */
  @Test
  void testMazeWithDfs() {
    var maze = new Maze();
    log.info("Maze to test:\n{}", maze);
    var solution =
        GenericSearch.depthFirstSearch(maze.getStart(), maze::isGoalReached, maze::successors);
    if(solution == null) {
      log.error("There is no solution found using depth-first search!");
    } else {
      List<MazeLocation> path = GenericSearch.nodeToPath(solution);
      maze.mark(path);
      log.info("Solution:\n{}", maze);
      maze.clear(path);
      log.info("After clear:\n{}", maze);
    }
  }
}
