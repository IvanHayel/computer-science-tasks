package by.hayel.computer.science.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Maze {
  private static final String MAZE_BOUND = "|";

  int rows;
  int columns;
  MazeLocation start;
  MazeLocation goal;
  Cell[][] grid;

  public Maze(int rows, int columns, MazeLocation start, MazeLocation goal, double sparseness) {
    this.rows = rows;
    this.columns = columns;
    this.start = start;
    this.goal = goal;
    grid = new Cell[rows][columns];
    for (Cell[] row : grid) {
      Arrays.fill(row, Cell.EMPTY);
    }
    randomlyFill(sparseness);
    grid[start.getRow()][start.getColumn()] = Cell.START;
    grid[goal.getRow()][goal.getColumn()] = Cell.GOAL;
  }

  public Maze() {
    this(10, 10, new MazeLocation(0, 0), new MazeLocation(9, 9), 0.2);
  }

  private void randomlyFill(double sparseness) {
    for (int row = 0; row < rows; row++)
      for (int column = 0; column < columns; column++)
        if (Math.random() < sparseness) grid[row][column] = Cell.BLOCKED;
  }

  public boolean isGoalReached(MazeLocation mazeLocation) {
    return goal.equals(mazeLocation);
  }

  public List<MazeLocation> successors(MazeLocation ml) {
    List<MazeLocation> locations = new ArrayList<>();
    var row = ml.getRow();
    var column = ml.getColumn();
    if (row + 1 < rows && grid[row + 1][column] != Cell.BLOCKED)
      locations.add(new MazeLocation(row + 1, column));
    if (row - 1 >= 0 && grid[row - 1][column] != Cell.BLOCKED)
      locations.add(new MazeLocation(row - 1, column));
    if (column + 1 < columns && grid[row][column + 1] != Cell.BLOCKED)
      locations.add(new MazeLocation(row, column + 1));
    if (column - 1 >= 0 && grid[row][column - 1] != Cell.BLOCKED)
      locations.add(new MazeLocation(row, column - 1));
    return locations;
  }

  public void mark(List<MazeLocation> path) {
    for(MazeLocation ml : path) {
      grid[ml.getRow()][ml.getColumn()] = Cell.PATH;
    }
    grid[start.getRow()][start.getColumn()] = Cell.START;
    grid[goal.getRow()][goal.getColumn()] = Cell.GOAL;
  }

  public void clear(List<MazeLocation> path) {
    for(MazeLocation ml : path) {
      grid[ml.getRow()][ml.getColumn()] = Cell.EMPTY;
    }
    grid[start.getRow()][start.getColumn()] = Cell.START;
    grid[goal.getRow()][goal.getColumn()] = Cell.GOAL;
  }

  @Override
  public String toString() {
    var builder = new StringBuilder();
    for (Cell[] row : grid) {
      builder.append(MAZE_BOUND);
      for (Cell cell : row) {
        builder.append(cell.toString());
      }
      builder.append(MAZE_BOUND);
      builder.append(System.lineSeparator());
    }
    return builder.toString();
  }
}
