/**
* This is the TownGraphManager class
*
*
* @author Fatima Mancia
*
*/
import java.util.*;

class TownGraphManager implements TownGraphManagerInterface {
  // private ArrayList<Town> towns;
  // private ArrayList<Road> roads;
    private Graph graph;
    TownGraphManager() {
      graph = new Graph();
      //towns = new ArrayList<Town>();
      //roads = new ArrayList<Road>;
    }
    /**
	 * addRoad adds roads
   * @param town1
   * @param town2
   * @param weight
   * @param roadName
	 * @return true if it is not empty. False, if it is
	 */
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
      Town sourceVertex = new Town(town1);
      Town destinationVertex = new Town(town2);
      Road r = graph.addEdge(sourceVertex, destinationVertex, weight, roadName);
      // System.out.println(roadName);
      if (r != null) {
        return true;
      }
      else {
        return false;
      }
    }
    /**
	 * getRoad gets road
   * @param town2
   * @param town1
	 * @return an empty space
	 */
    public String getRoad(String town1, String town2) {
        for (Road road : graph.edgeSet()) {
            if (road.getSource().getName().equals(town1) && road.getDestination().getName().equals(town2))
                return road.getName();
        }
        return "";
    }
    /**
	 * addTown adds town
   * @param v
   * @param 
	 * @return addVertex from Graph class
	 */
    public boolean addTown(String v) {
      Town town = new Town(v);
      return graph.addVertex(town);
    }
    /**
	 * getTown gets the town
   * @param name
   *
	 * @return town
	 */
    public Town getTown(String name) {
      Set<Town> set = graph.vertexSet();
      for (Town town : set) {
        if (name.equals(town.getName()))
          return town;
      }
      return null;
    }
    /**
	 * containsTown looks if it contains town
   * @param v
   * 
	 * @return containsVertex from the Graph class
	 */
    public boolean containsTown(String v) {
      Town town = new Town(v);
      return graph.containsVertex(town);
    }
    /**
	 * containsRoadConnection looks if there is a connection 
   * @param town1
   * @param town2
	 * @return containsEdge freom the Graph class
	 */
    public boolean containsRoadConnection(String town1, String town2) {
      Town sourceVertex = new Town(town1);
      Town destinationVertex = new Town(town2);
      return graph.containsEdge(sourceVertex, destinationVertex);
    }
    /**
	 * allRoads gets all roads
   * 
   * 
	 * @return list
	 */
    public ArrayList<String> allRoads() {
        ArrayList<String> list = new ArrayList<String>();
      for (Road r : graph.edgeSet())
        list.add(r.getName());
      return list;
    }
    /**
	 * deleteRoadConnection
   * @param town1
   * @param town2
   * @param road
	 * @return true
	 */
    public boolean deleteRoadConnection(String town1, String town2, String road) {
      Town sourceVertex = new Town(town1);
      Town destinationVertex = new Town(town2);
      Road r = graph.getEdge(sourceVertex, destinationVertex);
      Road rd = graph.removeEdge(sourceVertex, destinationVertex, r.getWeight(), road);
      if (rd != null) {
        return true;
      }
      else {
        return false;
      }
    }
    /**
	 * deleteTown deletes town
   * @param v
   * 
	 * @return removeVertex from the Graph class
	 */
    public boolean deleteTown(String v) {
      Town town = new Town(v);
      return graph.removeVertex(town);
    }
    /**
	 * allTowns gets all towns
   * 
   * 
	 * @return list
	 */
    public ArrayList<String> allTowns() {
      ArrayList<String> list = new ArrayList<>();
      for (Town r : graph.vertexSet())
        list.add(r.getName());
      return list;
    }
    /**
	 * getPath gets path
   * @param town1
   * @param town2
	 * @return g
	 */
    public ArrayList<String> getPath(String town1, String town2) {
      Town sourceVertex = new Town(town1);
      Town destinationVertex = new Town(town2);
      System.out.println("System path");
      ArrayList<String> g = graph.shortestPath(sourceVertex, destinationVertex);
      System.out.println("Size: " + g.size());
      return g;
  }
}
