package rafid;

import nahid.*;
import nabil.*;

import java.util.ArrayList;

public class Train {
  public int id;
  // Starting station
  public Station station;
  public String name;
  // The lists below are initialized
  public ArrayList<Coach> coaches = new ArrayList<Coach>();
  public ArrayList<ActionTime> actionTimes = new ArrayList<ActionTime>();

  public Train(int id, Station station, String name) {
    this.id = id;
    this.station = station;
    this.name = name;
  }

  public void findDistance(String station, int id) {
    // Print the distance from the given station in time
    // Example: Train X is 45 minutes away
  }

  public void showPassengers(int time, Station station) {
    // Find the passengers of a train for a given time and a station
  }

  public void showCoaches() {
    // Show the coaches of a train
  }
}
