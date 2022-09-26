package by.hayel.computer.science.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.hayel.computer.science.search.GenericSearch;
import by.hayel.computer.science.search.GenericSearch.Node;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
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

  /** Method under test: {@link Maze#euclideanDistance(MazeLocation)} */
  @Test
  void testEuclideanDistance() {
    Maze maze = new Maze();
    assertEquals(11.313708498984761d, maze.euclideanDistance(new MazeLocation(1, 1)));
  }

  /** Method under test: {@link Maze#manhattanDistance(MazeLocation)} */
  @Test
  void testManhattanDistance() {
    Maze maze = new Maze();
    assertEquals(16.0d, maze.manhattanDistance(new MazeLocation(1, 1)));
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
    if (solution == null) {
      log.error("There is no solution found using depth-first search!");
    } else {
      List<MazeLocation> path = GenericSearch.nodeToPath(solution);
      maze.mark(path);
      log.info("DFS Solution:\n{}", maze);
      maze.clear(path);
      log.info("After clear:\n{}", maze);
    }
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Maze#successors(MazeLocation)}
   *   <li>{@link Maze#mark(List)}
   *   <li>{@link Maze#clear(List)}
   *   <li>{@link GenericSearch#breadthFirstSearch(Object, Predicate, Function)} (Object, Predicate,
   *       Function)}
   *   <li>{@link GenericSearch#nodeToPath(Node)}
   * </ul>
   */
  @Test
  void testMazeWithBfs() {
    var maze = new Maze();
    log.info("Maze to test:\n{}", maze);
    var solution =
        GenericSearch.breadthFirstSearch(maze.getStart(), maze::isGoalReached, maze::successors);
    if (solution == null) {
      log.error("There is no solution found using breadth-first search!");
    } else {
      List<MazeLocation> path = GenericSearch.nodeToPath(solution);
      maze.mark(path);
      log.info("BFS Solution:\n{}", maze);
      maze.clear(path);
      log.info("After clear:\n{}", maze);
    }
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Maze#successors(MazeLocation)}
   *   <li>{@link Maze#mark(List)}
   *   <li>{@link Maze#clear(List)}
   *   <li>{@link GenericSearch#aStarSearch(Object, Predicate, Function, ToDoubleFunction)}
   *   <li>{@link GenericSearch#nodeToPath(Node)}
   * </ul>
   */
  @Test
  void testMazeWithAStar() {
    var maze = new Maze();
    log.info("Maze to test:\n{}", maze);
    var solution =
        GenericSearch.aStarSearch(
            maze.getStart(), maze::isGoalReached, maze::successors, maze::manhattanDistance);
    if (solution == null) {
      log.error("There is no solution found using A* search!");
    } else {
      List<MazeLocation> path = GenericSearch.nodeToPath(solution);
      maze.mark(path);
      log.info("A* Solution:\n{}", maze);
      maze.clear(path);
      log.info("After clear:\n{}", maze);
    }
  }

  @Test
  void testMazeWithAll() {
    var maze = new Maze();
    log.info("Maze to test:\n{}", maze);
    GenericSearch.enableStateCounter();
    var start = System.nanoTime();
    var dfsSolution =
        GenericSearch.depthFirstSearch(maze.getStart(), maze::isGoalReached, maze::successors);
    var end = System.nanoTime();
    var dfsStates = GenericSearch.getStateCounterValue();
    var dfsTime = (end - start) / 1_000_000;
    GenericSearch.enableStateCounter();
    start = System.nanoTime();
    var bfsSolution =
        GenericSearch.breadthFirstSearch(maze.getStart(), maze::isGoalReached, maze::successors);
    end = System.nanoTime();
    var bfsStates = GenericSearch.getStateCounterValue();
    var bfsTime = (end - start) / 1_000_000;
    GenericSearch.enableStateCounter();
    start = System.nanoTime();
    var aStarSolution =
        GenericSearch.aStarSearch(
            maze.getStart(), maze::isGoalReached, maze::successors, maze::manhattanDistance);
    end = System.nanoTime();
    var aStarStates = GenericSearch.getStateCounterValue();
    var aStarTime = (end - start) / 1_000_000;
    showSolution(maze, dfsSolution, "DFS", dfsTime);
    showSolution(maze, bfsSolution, "BFS", bfsTime);
    showSolution(maze, aStarSolution, "A*", aStarTime);
    log.info(
        String.format(
            "DFS - %d states, BFS - %d states, A* - %d states", dfsStates, bfsStates, aStarStates));
  }

  private void showSolution(
      Maze maze, GenericSearch.Node<MazeLocation> solution, String algorithm, long time) {
    if (solution == null) {
      log.error(String.format("There is no solution found using %s!", algorithm));
    } else {
      List<MazeLocation> path = GenericSearch.nodeToPath(solution);
      maze.mark(path);
      log.info(String.format("%s took %d ms:\n%s", algorithm, time, maze));
      maze.clear(path);
    }
  }
}
