package by.hayel.computer.science.hanoi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class HanoiTest {
  /**
   * Method under test: {@link Hanoi#Hanoi(int)}
   */
  @Test
  void testConstructor() {
    Hanoi actualHanoi = new Hanoi(20);
    assertEquals(20, actualHanoi.getDisksQuantity());
    assertTrue(actualHanoi.getTowerC().isEmpty());
    assertTrue(actualHanoi.getTowerB().isEmpty());
    Deque<Integer> towerA = actualHanoi.getTowerA();
    assertEquals(20, towerA.size());
  }

  /**
   * Method under test: {@link Hanoi#solve()}
   * ATTENTION: method has exponential growth,
   * which makes it unsuitable for a large number of disks
   */
  @Test
  void testSolve() {
    log.info("~~~ Towers of Hanoi test ~~~");
    Hanoi hanoi = new Hanoi(10);
    log.info("Tower A before solve: {}", hanoi.getTowerA());
    log.info("Tower B before solve: {}", hanoi.getTowerB());
    log.info("Tower C before solve: {}", hanoi.getTowerC());
    log.info("Solving process...");
    hanoi.solve();
    log.info("Tower A after solve: {}", hanoi.getTowerA());
    log.info("Tower B after solve: {}", hanoi.getTowerB());
    log.info("Tower C after solve: {}", hanoi.getTowerC());
  }
}

