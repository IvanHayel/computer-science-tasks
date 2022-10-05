package by.hayel.computer.science.graph;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode(callSuper = false)
public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
  double weight;

  public WeightedEdge(int from, int to, double weight) {
    super(from, to);
    this.weight = weight;
  }

  @Override
  public WeightedEdge reversed() {
    return new WeightedEdge(getTo(), getFrom(), weight);
  }

  @Override
  public int compareTo(WeightedEdge other) {
    return Double.compare(weight, other.weight);
  }

  @Override
  public String toString() {
    return String.format("%d --%f--> %d", getFrom(), getWeight(), getTo());
  }
}
