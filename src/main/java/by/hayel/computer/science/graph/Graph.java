package by.hayel.computer.science.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class Graph<V, E extends Edge> {
  List<V> vertices = new ArrayList<>();
  List<List<E>> edges = new ArrayList<>();

  public Graph(List<V> vertices) {
    this.vertices.addAll(vertices);
    vertices.forEach(vertex -> edges.add(new ArrayList<>()));
  }

  public int getVertexCount() {
    return vertices.size();
  }

  public int getEdgeCount() {
    return edges.stream().mapToInt(List::size).sum();
  }

  public int addVertex(V vertex) {
    vertices.add(vertex);
    edges.add(new ArrayList<>());
    return getVertexCount() - 1;
  }

  public V vertexAt(int index) {
    return vertices.get(index);
  }

  public int indexOf(V vertex) {
    return vertices.indexOf(vertex);
  }

  public List<V> neighborsOf(int index) {
    return edges.get(index).stream().map(edge -> vertexAt(edge.getTo())).toList();
  }

  public List<V> neighborsOf(V vertex) {
    return neighborsOf(indexOf(vertex));
  }

  public List<E> edgesOf(int index) {
    return edges.get(index);
  }

  public List<E> edgesOf(V vertex) {
    return edgesOf(indexOf(vertex));
  }

  @Override
  public String toString() {
    var builder = new StringBuilder();
    var count = getVertexCount();
    for (int index = 0; index < count; index++) {
      builder.append(vertexAt(index));
      builder.append(" -> ");
      builder.append(Arrays.toString(neighborsOf(index).toArray()));
      builder.append(System.lineSeparator());
    }
    return builder.toString();
  }
}
