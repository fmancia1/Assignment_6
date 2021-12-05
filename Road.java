/**
* This is the Road class
*
*
* @author Fatima Mancia
*
*/
class Road implements Comparable<Road> {
  private Town source;
  private Town destination;
  private int degrees;
  private String name;
  Road(Town source, Town destination, int degrees, String name) {
    this.source = source;
    this.destination = destination;
    this.degrees = degrees;
    this.name = name;
  }
  
  Road(Town source, Town destination, 
  String name) {
    this.source = source;
    this.destination = destination;
    this.degrees = 1;
    this.name = name;
  }
  public boolean contains(Town town) {
    return(source.compareTo(town) == 0 || destination.compareTo(town) == 0);
  }
  /**
	 * toString gets the string
	 * @return an empty space
	 */
  public String toString() {
    return "";
  }
  /**
	 * getName gets the name
	 * @return name
	 */
  public String getName() {
    return name;
  }
  /**
	 * getDestination gets the destination
	 * @return destination
	 */
  public Town getDestination() {
    return destination;
  }
  /**
	 * getSource gets the satart point
	 * @return source
	 */
  public Town getSource() {
    return source;
  }
  /**
	 * compareTo compares the road
	 * @return a number
	 */
  public int compareTo(Road o) {
    if (this.degrees < (o.degrees)) {
      return -1;
    }
    else if (this.degrees > (o.degrees)) {
      return 1;
    }
    else {
      return 0;
    }
  }
  /**
	 * getWeight gets the distance
	 * @return degrees
	 */
  public int getWeight() {
    return degrees;
  }
  /**
	 * equals compares the roads
	 * @return eq
	 */
  public boolean equals(Object r) {
    boolean eq = false;
    Road road = (Road) r;
    if (
      name.equals(road.getName()) &&
      source.equals(road.getSource()) &&
      destination.equals(road.getDestination()) &&
      degrees == road.getWeight()
    )
      eq = true;
    return eq;
  }
  
  public int hashCode() {
    return 0;
  }
}