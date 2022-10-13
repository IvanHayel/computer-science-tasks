package by.hayel.computer.science.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class WeightedGraphTest {
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
  private static final List<String> CITIES =
      List.of(
          SEATTLE,
          SAN_FRANCISCO,
          LOS_ANGELES,
          RIVERSIDE,
          PHOENIX,
          CHICAGO,
          BOSTON,
          NEW_YORK,
          ATLANTA,
          MIAMI,
          DALLAS,
          HOUSTON,
          DETROIT,
          PHILADELPHIA,
          WASHINGTON);

  /** Method under test: {@link WeightedGraph#WeightedGraph(List)} */
  @Test
  void testConstructor() {
    ArrayList<Object> objectList = new ArrayList<>();
    WeightedGraph<Object> actualWeightedGraph = new WeightedGraph<>(objectList);
    List<Object> vertices = actualWeightedGraph.getVertices();
    assertEquals(objectList, vertices);
    assertTrue(vertices.isEmpty());
    assertEquals(objectList, actualWeightedGraph.getEdges());
  }

  @Test
  void testGraph() {
    var cityGraph = new WeightedGraph<>(CITIES);
    cityGraph.addEdge(SEATTLE, CHICAGO, 1737);
    cityGraph.addEdge(SEATTLE, SAN_FRANCISCO, 678);
    cityGraph.addEdge(SAN_FRANCISCO, RIVERSIDE, 386);
    cityGraph.addEdge(SAN_FRANCISCO, LOS_ANGELES, 348);
    cityGraph.addEdge(LOS_ANGELES, RIVERSIDE, 50);
    cityGraph.addEdge(LOS_ANGELES, PHOENIX, 357);
    cityGraph.addEdge(RIVERSIDE, PHOENIX, 307);
    cityGraph.addEdge(RIVERSIDE, CHICAGO, 1704);
    cityGraph.addEdge(PHOENIX, DALLAS, 887);
    cityGraph.addEdge(PHOENIX, HOUSTON, 1015);
    cityGraph.addEdge(DALLAS, CHICAGO, 805);
    cityGraph.addEdge(DALLAS, ATLANTA, 721);
    cityGraph.addEdge(DALLAS, HOUSTON, 225);
    cityGraph.addEdge(HOUSTON, ATLANTA, 702);
    cityGraph.addEdge(HOUSTON, MIAMI, 968);
    cityGraph.addEdge(ATLANTA, CHICAGO, 588);
    cityGraph.addEdge(ATLANTA, WASHINGTON, 543);
    cityGraph.addEdge(ATLANTA, MIAMI, 604);
    cityGraph.addEdge(MIAMI, WASHINGTON, 923);
    cityGraph.addEdge(CHICAGO, DETROIT, 238);
    cityGraph.addEdge(DETROIT, BOSTON, 613);
    cityGraph.addEdge(DETROIT, WASHINGTON, 396);
    cityGraph.addEdge(DETROIT, NEW_YORK, 482);
    cityGraph.addEdge(BOSTON, NEW_YORK, 190);
    cityGraph.addEdge(NEW_YORK, PHILADELPHIA, 81);
    cityGraph.addEdge(PHILADELPHIA, WASHINGTON, 123);
    log.info("Graph:\n{}", cityGraph);
    var mst = cityGraph.minimumSpanningTree(0);
    cityGraph.printWeightedPath(mst);
    var dijkstraResult = cityGraph.dijkstra(LOS_ANGELES);
    var nameDistance = cityGraph.distanceArrayToDistanceMap(dijkstraResult.getDistances());
    log.info("Distances from Los Angeles:");
    nameDistance.forEach((name, distance) -> log.info(String.format("%s : %s", name, distance)));
    log.info(System.lineSeparator());
    log.info("Shortest path from Los Angeles to Boston:");
    var path =
        WeightedGraph.pathMapToPath(
            cityGraph.indexOf(LOS_ANGELES), cityGraph.indexOf(BOSTON), dijkstraResult.getPathMap());
    cityGraph.printWeightedPath(path);
  }
}
