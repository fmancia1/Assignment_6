/**
* This is the Graph class
*
*
* @author Fatima Mancia
*
*/
import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class Graph implements GraphInterface<Town, Road> {
  
  private ArrayList<Town> towns;
  private ArrayList<Road> roads;
  private HashMap<Town, Integer> distance;
  private HashMap<Town, Boolean> visited;
  private HashMap<Town, HashSet<Town>> routes;

  public Graph() {
    towns = new ArrayList<Town>();
    roads = new ArrayList<Road>();
    distance = new HashMap<Town, Integer>();
    visited = new HashMap<Town, Boolean>();
  }
  /**
	 * getEdge gets the road
   * @param sourceVertex
   * @param destinationVertex
	 * @return selection
	 */
  public Road getEdge(Town sourceVertex, Town destinationVertex) {
    Road selection = null;
    for (Road road : roads) {
      if (road.getSource().equals(sourceVertex) && road.getDestination().equals(destinationVertex)) {
        selection = road;
        break;
      }
    }
    return selection;
  }
   /**
	 * addEdge adds the road
   * @param sourceVertex
   * @param destinationVertex
	 * @return road
	 */
  public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
    Road road = new Road(sourceVertex, destinationVertex, weight, description);
    roads.add(road);
    sourceVertex.addAdjTown(destinationVertex, weight);
    destinationVertex.addAdjTown(sourceVertex, weight);
    return road;
  }
 /**
	 * addVertex adds town
   * @param v
   * 
	 * @return true if the town is there. If not false.
	 */
  public boolean addVertex(Town v) {
    return towns.add(v);
  }
   /**
	 * containsEdge looks if the program has the road
   * @param sourceVertex
   * @param destinationVertex
	 * @return true if the road is there. If not, false.
	 */
  public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
    for (Road road : roads) {
      if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
        return true;
      }
    }
    return false;
  }
   /**
	 * containsVertex looks if the program contains the town
   * @param v
   * 
	 * @return true if the town is there. If not, false
	 */
  public boolean containsVertex(Town v) {
    for (Town town : towns) {
      if (town.getName().compareToIgnoreCase(v.getName()) == 0) {
        return true;
      }
    }
    return false;
  }
    /**
	 * edgeSet gets the roads set
   * 
   * 
	 * @return the HashSet of roads
	 */
  public Set<Road> edgeSet() {
    return new HashSet<Road>(roads);
  }
    /**
	 * edgesOf gets the towns set 
   * @param vertex
   * 
	 * @return r
	 */
  public Set<Road> edgesOf(Town vertex) {
    Set<Road> r = new HashSet<Road>();
    for (Road road : this.roads) {
      if (road.contains(vertex)) {
        r.add(road);
      }
    } 
    return r;
  }
    /**
	 * removeEdge removes the roads
   * @param sourceVertex
   * @param destinationVertex
   * @param weight
   * @param description
	 * @return road
	 */
  public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
    Road road = new Road(sourceVertex, destinationVertex, weight, description);
    roads.remove(road);
    return road;
  }
    /**
	 * removeVertex removes the town
   * @param v
   *  
	 * @return the towns removed
	 */
  public boolean removeVertex(Town v) {
    return towns.remove(v);
  }
    /**
	 * vertexSet creates a set of towns
   *
   *
	 * @return the HashSet of towns
	 */
  public Set<Town> vertexSet() {
    return new HashSet<Town>(towns);
  }
    /**
	 * searchTown search for the town
   * @param source
   * @param target
   * @param routes
	 * @return found
	 */
  private boolean searchTown(Town source, Town target, HashMap<Town, HashSet<Town>> routes, ArrayList<Town> path) {
    boolean found = false;
    for (Town town2: routes.get(source)) {
      if (town2.equals(target)) {
        path.add(town2);
        found = true;
        break;
      }
      else if (routes.containsKey(town2)) {
        ArrayList<Town> new_path = new ArrayList<Town>();
        if (searchTown(town2, target, routes, new_path)) {
          path.add(town2);
          path.addAll(new_path);
          found = true;
          break;
        }
      }
    }
    return found;
  }
    /**
	 * shortestPath gets the list of shortest path
   * @param sourceVertex
   * @param destinationVertex
	 * @return ret
	 */
  public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
    ArrayList<String> ret = new ArrayList<String>();
    dijkstraShortestPath(sourceVertex);
    ArrayList<Town> path = new ArrayList<Town>();
    path.add(sourceVertex);
    searchTown(sourceVertex, destinationVertex, routes, path);
    for (int i = 0; i < path.size() - 1; i++) {
      Town town1 = path.get(i);
      Town town2 = path.get(i + 1);
      Road road = getEdge(town1, town2);
      String s = town1.getName() + " via " + 
           road.getName() + " to " + 
           town2.getName() + " " + 
           road.getWeight() + " mi";
      ret.add(s);
    }
    return ret;
  }
    /**
	 * minDist gets the minimum distance
   * 
   *
	 * @return nearestTown
	 */
  private Town minDist(HashMap<Town, Boolean> visited, HashMap<Town, Integer> distance) {
    // Look for the town with the minimum distance
    int min = Integer.MAX_VALUE;
    boolean found = false;
    Town nearestTown = null;
    for (Town town: towns) {
      if (!visited.get(town) && distance.get(town) < min) {
        min = distance.get(town);
        nearestTown = town;
      }
    }
    return nearestTown;
  }
  /**
	 * dijkstraShortestPath gets the shortestPath
   * @param sourceVertex
   * 
	 * 
	 */
  public void dijkstraShortestPath(Town sourceVertex) {
    /* routes save the shortest distances from one town to other 
       relative to sourceVertex
    */
    routes = new HashMap<Town, HashSet<Town>>();
    /* visited is initialized as false for each element
       distance is initialized as the greatest integer value
    */
    for (Town town: towns) {
      visited.put(town, false);
      distance.put(town, Integer.MAX_VALUE);
    }
    // Distance to thw sourceVertex is 0
    distance.put(sourceVertex, 0);
    for (int i = 0; i < towns.size() - 1; i++) {
      // Look for adjacent towns
      Town town1 = minDist(visited, distance);
      // They are now visited
      visited.put(town1, true);
      // Now compute the distance in relation to
      // sourceVertex
      for (Town town2: towns) {
        Road r = getEdge(town1, town2);
        if (r == null)
          // There is not route, so there is nothing to do
          continue;
        if (
          !visited.get(town2) &&  // Not visited yet
          distance.get(town1) != Integer.MAX_VALUE &&  
          distance.get(town1) + r.getWeight() < distance.get(town2)
        ) {
          // The distance from sourceVertex is computed
          distance.put(town2, distance.get(town1) + r.getWeight());
          // Routes from one town to other are saved
          if (!routes.containsKey(town1)) {
            routes.put(town1, new HashSet<Town>());
          }
          routes.get(town1).add(town2);
        }
      }
    }
    
    /*for (Town town: towns)
      System.out.println(town.getName() + " " + distance.get(town));
    for (Town town1: routes.keySet())
      for (Town town2: routes.get(town1))
        System.out.println(town1 + " " + town2);
   */ 
  }
}

