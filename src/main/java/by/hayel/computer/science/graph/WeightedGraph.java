package by.hayel.computer.science.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.IntConsumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeightedGraph<V> extends Graph<V, WeightedEdge> {
  public WeightedGraph(List<V> vertices) {
    super(vertices);
  }

  public static double totalWeight(List<WeightedEdge> path) {
    return path.stream().mapToDouble(WeightedEdge::getWeight).sum();
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

  public List<WeightedEdge> minimumSpanningTree(int start) {
    var result = new LinkedList<WeightedEdge>();
    if (start < 0 || start > (getVertexCount() - 1)) return result;
    var queue = new PriorityQueue<WeightedEdge>();
    var visited = new boolean[getVertexCount()];
    IntConsumer visit =
        index -> {
          visited[index] = true;
          edgesOf(index)
              .forEach(
                  edge -> {
                    if (!visited[edge.getTo()]) queue.offer(edge);
                  });
        };
    visit.accept(start);
    while (!queue.isEmpty()) {
      var edge = queue.poll();
      if (visited[edge.getTo()]) continue;
      result.add(edge);
      visit.accept(edge.getTo());
    }
    return result;
  }

  public void printWeightedPath(List<WeightedEdge> wp) {
    wp.forEach(
        edge ->
            log.info(
                "{} {}> {}", vertexAt(edge.getFrom()), edge.getWeight(), vertexAt(edge.getTo())));
    log.info("Total Weight: {}", totalWeight(wp));
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
