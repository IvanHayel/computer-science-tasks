package by.hayel.computer.science.graph;

import java.util.Arrays;
import java.util.List;

public class WeightedGraph<V> extends Graph<V, WeightedEdge> {
  public WeightedGraph(List<V> vertices) {
    super(vertices);
  }

  public void addEdge(WeightedEdge edge) {
    var edges = getEdges();
    edges.get(edge.getFrom()).add(edge);
    edges.get(edge.getTo()).add(edge.reversed());
  }

  public void addEdge(int from, int to, double weight) {
    addEdge(new WeightedEdge(from, to, weight));
  }

  public void addEdge(V first, V second, double weight) {
    addEdge(indexOf(first), indexOf(second), weight);
  }

  @Override
  public String toString() {
    var builder = new StringBuilder();
    var count = getVertexCount();
    for (int counter = 0; counter < count; counter++) {
      builder.append(vertexAt(counter));
      builder.append(" --> ");
      builder.append(
          Arrays.toString(
              edgesOf(counter).stream()
                  .map(we -> String.format("(%s, %.1f)", vertexAt(we.getFrom()), we.getWeight()))
                  .toArray()));
      builder.append(System.lineSeparator());
    }
    return builder.toString();
  }
}
