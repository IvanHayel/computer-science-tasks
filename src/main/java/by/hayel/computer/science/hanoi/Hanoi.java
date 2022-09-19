package by.hayel.computer.science.hanoi;

import java.util.Deque;
import java.util.LinkedList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Hanoi {
  int disksQuantity;
  Deque<Integer> towerA = new LinkedList<>();
  Deque<Integer> towerB = new LinkedList<>();
  Deque<Integer> towerC = new LinkedList<>();

  public Hanoi(int disksQuantity) {
    this.disksQuantity = disksQuantity;
    for (int i = 1; i <= disksQuantity; i++) {
      towerA.push(i);
    }
  }

  public void solve() {
    move(towerA, towerB, towerC, disksQuantity);
  }

  private void move(Deque<Integer> begin, Deque<Integer> end, Deque<Integer> temp, int n) {
    if (n == 1) {
      end.push(begin.pop());
    } else {
      move(begin, temp, end, n - 1);
      move(begin, end, temp, 1);
      move(temp, end, begin, n - 1);
    }
  }
}
