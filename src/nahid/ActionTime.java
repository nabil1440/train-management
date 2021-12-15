package nahid;

import rafid.*;

import java.sql.*;

public class ActionTime {
  public String station, train, type, time;

  public ActionTime(String station, String train, String type, String time) {
    this.station = station;
    this.train = train;
    this.type = type;
    this.time = time;
  }

  public void displayDeparture() {
    System.out.println(this.train + " leaves " + this.station + " at " + this.time);
  }

  public void displayArrival() {
    System.out.println(this.train + " arrives at " + this.station + " at " + this.time);
  }

  public void createNew(Connection connection) {
    // Add new time to the DB
  }
}
