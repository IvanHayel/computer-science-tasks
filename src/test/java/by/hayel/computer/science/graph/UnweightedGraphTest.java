package by.hayel.computer.science.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class UnweightedGraphTest {
  private static final String SEATTLE = "Seattle";
  private static final String SAN_FRANCISCO = "San Francisco";
  private static final String LOS_ANGELES = "Los Angeles";
  private static final String RIVERSIDE = "Riverside";
  private static final String PHOENIX = "Phoenix";
  private static final String CHICAGO = "Chicago";
  private static final String BOSTON = "Boston";
  private static final String NEW_YORK = "New York";
  private static final String ATLANTA = "Atlanta";
  private static final String MIAMI = "Miami";
  private static final String DALLAS = "Dallas";
  private static final String HOUSTON = "Houston";
  private static final String DETROIT = "Detroit";
  private static final String PHILADELPHIA = "Philadelphia";
  private static final String WASHINGTON = "Washington";
  private static final List<String> CITIES = List.of(
      SEATTLE, SAN_FRANCISCO, LOS_ANGELES,
      RIVERSIDE, PHOENIX, CHICAGO, BOSTON,
      NEW_YORK, ATLANTA, MIAMI, DALLAS, HOUSTON,
      DETROIT, PHILADELPHIA, WASHINGTON);

  /**
   * Method under test: {@link UnweightedGraph#UnweightedGraph(List)}
   */
  @Test
  void testConstructor() {
    ArrayList<Object> objectList = new ArrayList<>();
    UnweightedGraph<Object> actualUnweightedGraph = new UnweightedGraph<>(objectList);
    List<Object> vertices = actualUnweightedGraph.getVertices();
    assertEquals(objectList, vertices);
    assertTrue(vertices.isEmpty());
    assertEquals(objectList, actualUnweightedGraph.getEdges());
  }

  @Test
  void testGraph() {
    var cityGraph = new UnweightedGraph<>(CITIES);
    cityGraph.addEdge(SEATTLE, CHICAGO);
    cityGraph.addEdge(SEATTLE, SAN_FRANCISCO);
    cityGraph.addEdge(SAN_FRANCISCO, RIVERSIDE);
    cityGraph.addEdge(SAN_FRANCISCO, LOS_ANGELES);
    cityGraph.addEdge(LOS_ANGELES, RIVERSIDE);
    cityGraph.addEdge(LOS_ANGELES, PHOENIX);
    cityGraph.addEdge(RIVERSIDE, PHOENIX);
    cityGraph.addEdge(RIVERSIDE, CHICAGO);
    cityGraph.addEdge(PHOENIX, DALLAS);
    cityGraph.addEdge(PHOENIX, HOUSTON);
    cityGraph.addEdge(DALLAS, CHICAGO);
    cityGraph.addEdge(DALLAS, ATLANTA);
    cityGraph.addEdge(DALLAS, HOUSTON);
    cityGraph.addEdge(HOUSTON, ATLANTA);
    cityGraph.addEdge(HOUSTON, MIAMI);
    cityGraph.addEdge(ATLANTA, CHICAGO);
    cityGraph.addEdge(ATLANTA, WASHINGTON);
    cityGraph.addEdge(ATLANTA, MIAMI);
    cityGraph.addEdge(MIAMI, WASHINGTON);
    cityGraph.addEdge(CHICAGO, DETROIT);
    cityGraph.addEdge(DETROIT, BOSTON);
    cityGraph.addEdge(DETROIT, WASHINGTON);
    cityGraph.addEdge(DETROIT, NEW_YORK);
    cityGraph.addEdge(BOSTON, NEW_YORK);
    cityGraph.addEdge(NEW_YORK, PHILADELPHIA);
    cityGraph.addEdge(PHILADELPHIA, WASHINGTON);
    log.info("Graph:\n{}", cityGraph);
  }
}

