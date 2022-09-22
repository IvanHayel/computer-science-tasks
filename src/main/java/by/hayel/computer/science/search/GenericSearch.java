package by.hayel.computer.science.search;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public class GenericSearch {
  public static <T> Node<T> depthFirstSearch(
      T initial, Predicate<T> isGoalReached, Function<T, List<T>> successors) {
    Deque<Node<T>> frontier = new LinkedList<>();
    frontier.push(new Node<>(initial, null));
    Set<T> explored = new HashSet<>();
    explored.add(initial);
    while(!frontier.isEmpty()) {
      Node<T> currentNode = frontier.pop();
      T currentState = currentNode.getState();
      if(isGoalReached.test(currentState)) return currentNode;
      for(T child: successors.apply(currentState)) {
        if(explored.contains(child)) continue;
        explored.add(child);
        frontier.push(new Node<>(child, currentNode));
      }
    }
    return null;
  }

  public static <T> Node<T> breadthFirstSearch(
      T initial, Predicate<T> isGoalReached, Function<T, List<T>> successors) {
    Queue<Node<T>> frontier = new LinkedList<>();
    frontier.offer(new Node<>(initial, null));
    Set<T> explored = new HashSet<>();
    while (!frontier.isEmpty()) {
      Node<T> currentNode = frontier.poll();
      T currentState = currentNode.getState();
      if(isGoalReached.test(currentState)) return currentNode;
      for(T child: successors.apply(currentState)) {
        if(explored.contains(child)) continue;
        explored.add(child);
        frontier.offer(new Node<>(child, currentNode));
      }
    }
    return null;
  }

  public static <T> Node<T> aStarSearch(
      T initial, Predicate<T> isGoalReached, Function<T, List<T>> successors, ToDoubleFunction<T> heuristic) {
    Queue<Node<T>> frontier = new PriorityQueue<>();
    frontier.offer(new Node<>(initial, null, 0.0, heuristic.applyAsDouble(initial)));
    Map<T, Double> explored = new HashMap<>();
    explored.put(initial, 0.0);
    while (!frontier.isEmpty()) {
      Node<T> currentNode = frontier.poll();
      T currentState = currentNode.getState();
      if(isGoalReached.test(currentState)) return currentNode;
      for(T child: successors.apply(currentState)) {
        double newCost = currentNode.getCost() + 1;
        if(!explored.containsKey(child) || explored.get(child) > newCost) {
          explored.put(child, newCost);
          frontier.offer(new Node<>(child, currentNode, newCost, heuristic.applyAsDouble(child)));
        }
      }
    }
    return null;
  }

  public static <T> List<T> nodeToPath(Node<T> node) {
    List<T> path = new ArrayList<>();
    path.add(node.getState());
    while (node.getParent() != null) {
      node = node.getParent();
      path.add(0, node.getState());
    }
    return path;
  }

  public static <T extends Comparable<T>> boolean linearContains(List<T> list, T key) {
    return list.stream().anyMatch(item -> item.compareTo(key) == 0);
  }

  public static <T extends Comparable<T>> boolean binaryContains(List<T> list, T key) {
    int low = 0;
    int high = list.size() - 1;
    while (low <= high) {
      int middle = (low + high) >> 1;
      int comparison = list.get(middle).compareTo(key);
      if (comparison < 0) {
        low = middle + 1;
      } else if (comparison > 0) {
        high = middle - 1;
      } else {
        return true;
      }
    }
    return false;
  }

  @Getter
  @FieldDefaults(level = AccessLevel.PRIVATE)
  public static class Node<T> implements Comparable<Node<T>> {
    final T state;
    Node<T> parent;
    double cost;
    double heuristic;

    Node(T state, Node<T> parent) {
      this.state = state;
      this.parent = parent;
    }

    Node(T state, Node<T> parent, double cost, double heuristic) {
      this.state = state;
      this.parent = parent;
      this.cost = cost;
      this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node<T> other) {
      Double mine = getCost() + getHeuristic();
      Double theirs = other.getCost() + other.getHeuristic();
      return mine.compareTo(theirs);
    }
  }
}
