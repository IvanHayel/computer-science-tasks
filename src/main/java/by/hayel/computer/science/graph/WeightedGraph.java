package by.hayel.computer.science.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.IntConsumer;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeightedGraph<V> extends Graph<V, WeightedEdge> {
  public WeightedGraph(List<V> vertices) {
    super(vertices);
  }

  public static double totalWeight(List<WeightedEdge> path) {
    return path.stream().mapToDouble(WeightedEdge::getWeight).sum();
  }

  public static List<WeightedEdge> pathMapToPath(
      int start, int end, Map<Integer, WeightedEdge> pathMap) {
    if (pathMap.size() <= 0) return Collections.emptyList();
    var path = new LinkedList<WeightedEdge>();
    var edge = pathMap.get(end);
    path.add(edge);
    while (edge.getFrom() != start) {
      edge = pathMap.get(edge.getFrom());
      path.add(edge);
    }
    Collections.reverse(path);
    return path;
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

  public DijkstraResult dijkstra(V root) {
    int first = indexOf(root);
    double[] distances = new double[getVertexCount()];
    distances[first] = 0.0;
    boolean[] visited = new boolean[getVertexCount()];
    visited[first] = true;
    var pathMap = new HashMap<Integer, WeightedEdge>();
    var priorityQueue = new PriorityQueue<DijkstraNode>();
    priorityQueue.offer(new DijkstraNode(first, 0));
    while (!priorityQueue.isEmpty()) {
      int u = priorityQueue.poll().getVertex();
      double distU = distances[u];
      edgesOf(u)
          .forEach(
              edge -> {
                double distV = distances[edge.getTo()];
                double pathWeight = edge.getWeight() + distU;
                var to = edge.getTo();
                if (!visited[to] || distV > pathWeight) {
                  visited[to] = true;
                  distances[to] = pathWeight;
                  pathMap.put(to, edge);
                  priorityQueue.offer(new DijkstraNode(to, pathWeight));
                }
              });
    }
    return new DijkstraResult(distances, pathMap);
  }

  public Map<V, Double> distanceArrayToDistanceMap(double[] distances) {
    var distanceMap = new HashMap<V, Double>();
    for (int i = 0; i < distances.length; i++) {
      distanceMap.put(vertexAt(i), distances[i]);
    }
    return distanceMap;
  }

  @RequiredArgsConstructor
  @Getter
  @EqualsAndHashCode
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  public static final class DijkstraNode implements Comparable<DijkstraNode> {
    int vertex;
    double distance;

    @Override
    public int compareTo(DijkstraNode other) {
      Double mine = distance;
      Double theirs = other.getDistance();
      return mine.compareTo(theirs);
    }
  }

  @RequiredArgsConstructor
  @Getter
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  public static final class DijkstraResult {
    double[] distances;
    Map<Integer, WeightedEdge> pathMap;
  }
}
