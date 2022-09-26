package by.hayel.computer.science.csp;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class Constraint<V, D> {
  List<V> variables;

  public abstract boolean satisfied(Map<V, D> assignment);
}
