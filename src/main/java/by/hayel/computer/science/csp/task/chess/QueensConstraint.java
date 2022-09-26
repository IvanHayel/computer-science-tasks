package by.hayel.computer.science.csp.task.chess;

import by.hayel.computer.science.csp.Constraint;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueensConstraint extends Constraint<Integer, Integer> {
  List<Integer> columns;

  public QueensConstraint(List<Integer> columns) {
    super(columns);
    this.columns = columns;
  }

  @Override
  public boolean satisfied(Map<Integer, Integer> assignment) {
    for (Entry<Integer, Integer> item : assignment.entrySet()) {
      int queenOfFirstColumn = item.getKey();
      int queenOfFirstRow = item.getValue();
      for (int queenOfSecondColumn = queenOfFirstColumn + 1;
          queenOfSecondColumn <= columns.size();
          queenOfSecondColumn++) {
        if(assignment.containsKey(queenOfSecondColumn)) {
          int queenOfSecondRow = assignment.get(queenOfSecondColumn);
          if (queenOfFirstRow == queenOfSecondRow) return false;
          if (Math.abs(queenOfFirstRow - queenOfSecondRow)
              == Math.abs(queenOfFirstColumn - queenOfSecondColumn)) return false;
        }
      }
    }
    return true;
  }
}
