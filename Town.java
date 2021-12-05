/**
* This is the MorseCodeConverter class
*
*
* @author Fatima Mancia
*
*/
import java.util.*;
import java.util.LinkedList;
class Town implements Comparable<Town> {
  private String name;
  private HashMap<Town, Integer> adjTown;
  private LinkedList<Town> shortestPath;
  private Integer distance;
  
  Town(String name) {
    this.name = name;
    this.adjTown = new HashMap<Town, Integer>();
    this.shortestPath = new LinkedList<Town>();
    this.distance = Integer.MAX_VALUE;
  }
  
  Town(Town templateTown) {
    name = templateTown.name;
    adjTown = templateTown.adjTown;
    shortestPath = templateTown.shortestPath;
    distance = templateTown.distance;
  }
  /**
	 * getName gets the name
	 * @return name
	 */
  public String getName() {
    return name;
  }
  /**
	 * compareTo compares the towns
	 * @return a number
	 */
  public int compareTo(Town o) {
    if (name.compareToIgnoreCase(o.name) < 0) {
      return -1;
    }
    else if (name.compareToIgnoreCase(o.name) > 0) {
      return 1;
    }
    else {
      return 0;
    }
  }
  /**
	 * toString gets a string
	 * @return name
	 */
  public String toString() {
    return name;
  }
  /**
	 * hashCode gets the hashCode
	 * @return 0
	 */
  public int hashCode() {
    return 0;
  }
  /**
	 * equals compares the towns
	 * @return true if they are equals. False, if not
	 */
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    else {
      return false;
    }
  }
  /**
	 * addAdjTown adds the town adjacent
	 * @return put(t, x)
	 */
  public void addAdjTown(Town t, int x) {
    adjTown.put(t, x);
  }
  /**
	 * getShortestPath gets shortest path
	 * @return shortestPath
	 */
  public LinkedList<Town> getShortestPath() {
    return shortestPath;
  }
}