package by.hayel.computer.science.csp.task.color;

import by.hayel.computer.science.csp.Constraint;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MapColoringConstraint extends Constraint<String, String> {
  String firstPlace;
  String secondPlace;

  public MapColoringConstraint(String firstPlace, String secondPlace) {
    super(List.of(firstPlace, secondPlace));
    this.firstPlace = firstPlace;
    this.secondPlace = secondPlace;
  }

  @Override
  public boolean satisfied(Map<String, String> assignment) {
    if (!assignment.containsKey(firstPlace) || !assignment.containsKey(secondPlace)) return true;
    return !assignment.get(firstPlace).equals(assignment.get(secondPlace));
  }
}
