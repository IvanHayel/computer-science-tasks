package by.hayel.computer.science.graph;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Edge {
  int from;
  int to;

  public Edge reversed() {
    return new Edge(to, from);
  }

  @Override
  public String toString() {
    return String.format("%d -> %d", from, to);
  }
}
