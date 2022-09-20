package by.hayel.computer.science.search;

import java.util.List;

public class GenericSearch {
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
}
