package by.hayel.computer.science.maze;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
public enum Cell {
  EMPTY(" "),
  BLOCKED("X"),
  START("S"),
  GOAL("G"),
  PATH("*");

  String code;
}
