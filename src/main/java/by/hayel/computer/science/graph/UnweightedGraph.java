package by.hayel.computer.science.graph;

import java.util.List;

public class UnweightedGraph<V> extends Graph<V, Edge> {
  public UnweightedGraph(List<V> vertices) {
    super(vertices);
  }

  public void addEdge(Edge edge) {
    var edges = getEdges();
    edges.get(edge.getFrom()).add(edge);
    edges.get(edge.getTo()).add(edge.reversed());
  }

  public void addEdge(int from, int to) {
    addEdge(new Edge(from, to));
  }

  public void addEdge(V first, V second) {
    addEdge(new Edge(indexOf(first), indexOf(second)));
  }
}
