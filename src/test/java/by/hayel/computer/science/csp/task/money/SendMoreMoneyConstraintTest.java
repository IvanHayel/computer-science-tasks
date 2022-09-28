package by.hayel.computer.science.csp.task.money;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.hayel.computer.science.csp.CSP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SendMoreMoneyConstraintTest {
  /** Method under test: {@link SendMoreMoneyConstraint#SendMoreMoneyConstraint(List)} */
  @Test
  void testConstructor() {
    ArrayList<Character> characterList = new ArrayList<>();
    SendMoreMoneyConstraint actualSendMoreMoneyConstraint =
        new SendMoreMoneyConstraint(characterList);
    List<Character> letters = actualSendMoreMoneyConstraint.getLetters();
    assertSame(characterList, letters);
    assertTrue(letters.isEmpty());
    assertSame(letters, actualSendMoreMoneyConstraint.getVariables());
  }

  @Test
  void testSolution() {
    var letters = List.of('S', 'E', 'N', 'D', 'M', 'O', 'R', 'Y');
    var possibleDigits = new HashMap<Character, List<Integer>>();
    letters.forEach(letter -> possibleDigits.put(letter, List.of(0, 1, 2, 3, 4, 5, 7, 8, 9)));
    var csp = new CSP<>(letters, possibleDigits);
    csp.addConstraint(new SendMoreMoneyConstraint(letters));
    var solution = csp.backtrackingSearch();
    if (solution == null) log.error("There is no solution for this task.");
    else log.info("Solution: {}", solution);
  }
}
