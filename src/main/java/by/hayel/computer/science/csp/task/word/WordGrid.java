package by.hayel.computer.science.csp.task.word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WordGrid {
  private static final Random GENERATOR = new Random();
  private static final char ALPHABET_LENGTH = 26;
  private static final char FIRST_LETTER = 'A';

  int rows;
  int columns;
  char[][] grid;

  public WordGrid(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    grid = new char[rows][columns];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        var randomLetter = (char) (GENERATOR.nextInt(ALPHABET_LENGTH) + FIRST_LETTER);
        grid[row][col] = randomLetter;
      }
    }
  }

  public void mark(String word, List<GridLocation> locations) {
    for (int i = 0; i < word.length(); i++) {
      var location = locations.get(i);
      grid[location.getRow()][location.getColumn()] = word.charAt(i);
    }
  }

  public List<List<GridLocation>> generateDomain(String word) {
    var domain = new ArrayList<List<GridLocation>>();
    int length = word.length();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        if (col + length <= columns) {
          fillRight(domain, row, col, length);
          if (row + length <= rows) fillDiagonalRight(domain, row, col, length);
        }
        if (row + length <= rows) {
          fillDown(domain, row, col, length);
          if (col - length >= 0) fillDiagonalLeft(domain, row, col, length);
        }
      }
    }
    return domain;
  }

  private void fillRight(List<List<GridLocation>> domain, int row, int col, int length) {
    var locations = new ArrayList<GridLocation>();
    for (int column = col; column < (col + length); column++) {
      locations.add(new GridLocation(row, column));
    }
    domain.add(locations);
  }

  private void fillDiagonalRight(List<List<GridLocation>> domain, int row, int col, int length) {
    var locations = new ArrayList<GridLocation>();
    int tempRow = row;
    for (int column = col; column < (col + length); column++) {
      locations.add(new GridLocation(tempRow, column));
      tempRow++;
    }
    domain.add(locations);
  }

  private void fillDown(List<List<GridLocation>> domain, int row, int col, int length) {
    var locations = new ArrayList<GridLocation>();
    for (int r = row; r < (row + length); r++) {
      locations.add(new GridLocation(r, col));
    }
    domain.add(locations);
  }

  private void fillDiagonalLeft(List<List<GridLocation>> domain, int row, int col, int length) {
    var locations = new ArrayList<GridLocation>();
    int tempColumn = col;
    for(int r = row; r < (row + length); r++) {
      locations.add(new GridLocation(r, tempColumn));
      tempColumn--;
    }
    domain.add(locations);
  }

  @Override
  public String toString() {
    var builder = new StringBuilder();
    for (char[] row : grid) {
      builder.append(row);
      builder.append(System.lineSeparator());
    }
    return builder.toString();
  }

  @RequiredArgsConstructor
  @Getter
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  @EqualsAndHashCode
  public static class GridLocation {
    int row;
    int column;
  }
}
