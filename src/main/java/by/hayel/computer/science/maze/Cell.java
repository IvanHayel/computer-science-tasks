package by.hayel.computer.science.maze;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Cell {
  EMPTY(" "),
  BLOCKED("X"),
  START("S"),
  GOAL("G"),
  PATH("*");

  String code;

  @Override
  public String toString() {
    return code;
  }
}
