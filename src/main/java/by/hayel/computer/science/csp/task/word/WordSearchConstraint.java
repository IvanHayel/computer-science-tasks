package by.hayel.computer.science.csp.task.word;

import by.hayel.computer.science.csp.Constraint;
import by.hayel.computer.science.csp.task.word.WordGrid.GridLocation;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class WordSearchConstraint extends Constraint<String, List<GridLocation>> {
  public WordSearchConstraint(List<String> words) {
    super(words);
  }

  @Override
  public boolean satisfied(Map<String, List<GridLocation>> assignment) {
    var allLocations =
        assignment.values().stream().flatMap(Collection::stream).toList();
    var allLocationsSet = new HashSet<>(allLocations);
    return allLocationsSet.size() == allLocations.size();
  }
}
