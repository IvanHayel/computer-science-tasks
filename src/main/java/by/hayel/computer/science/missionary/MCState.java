package by.hayel.computer.science.missionary;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode
public class MCState {
  private static final int MAX = 3;

  int westMissionaries;
  int westCannibals;
  int eastMissionaries;
  int eastCannibals;
  boolean isBoatOnWest;

  public MCState(int missionaries, int cannibals, boolean isBoatOnWest) {
    westMissionaries = missionaries;
    westCannibals = cannibals;
    eastMissionaries = MAX - westMissionaries;
    eastCannibals = MAX - westCannibals;
    this.isBoatOnWest = isBoatOnWest;
  }

  public static int getMax() {
    return MAX;
  }

  public static List<MCState> successors(MCState state) {
    List<MCState> successStates = new ArrayList<>();
    var isBoatOnWest = state.isBoatOnWest();
    var wm = state.getWestMissionaries();
    var wc = state.getWestCannibals();
    var em = state.getEastMissionaries();
    var ec = state.getEastCannibals();
    if (isBoatOnWest) {
      if (wm > 1) successStates.add(new MCState(wm - 2, wc, false));
      if (wm > 0) successStates.add(new MCState(wm - 1, wc, false));
      if (wc > 1) successStates.add(new MCState(wm, wc - 2, false));
      if (wc > 0) successStates.add(new MCState(wm, wc - 1, false));
      if (wc > 0 && wm > 0) successStates.add(new MCState(wm - 1, wc - 1, false));
    } else {
      if (em > 1) successStates.add(new MCState(wm + 2, wc, true));
      if (em > 0) successStates.add(new MCState(wm + 1, wc, true));
      if (ec > 1) successStates.add(new MCState(wm, wc + 2, true));
      if (ec > 0) successStates.add(new MCState(wm, wc + 1, true));
      if (ec > 0 && em > 0) successStates.add(new MCState(wm + 1, wc + 1, true));
    }
    successStates.removeIf(Predicate.not(MCState::isLegal));
    return successStates;
  }

  public boolean goalTest() {
    return isLegal() && eastMissionaries == MAX && eastCannibals == MAX;
  }

  private boolean isLegal() {
    return (westMissionaries >= westCannibals || westMissionaries <= 0)
        && (eastMissionaries >= eastCannibals || eastMissionaries <= 0);
  }

  @Override
  public String toString() {
    return String.format(
        "On the west bank there are %d missionaries and %d cannibals.%n"
            + "On the east bank there are %d missionaries and %d cannibals.%n"
            + "The boat is on the %s bank.%n",
        westMissionaries,
        westCannibals,
        eastMissionaries,
        eastCannibals,
        isBoatOnWest ? "west" : "east");
  }
}
