package nahid;

public class Station {
  public int id;
  public String name;
  public Station next;
  public Station prev;

  public void traverseForward() {
    if (this.next != null) {
      System.out.println("Name: " + this.name + "id: " + this.id);
    }
  }

  public void traverseBackward() {
    if (this.prev != null) {
      System.out.println("Name: " + this.name + "id: " + this.id);
    }
  }

  public void showArrivalTimes() {
    // find the arrival time of each train
  }

  public void showDepartureTimes() {
    // find the departure time of each train
  }
}
