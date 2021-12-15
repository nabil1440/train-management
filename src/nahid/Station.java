package nahid;

import java.nio.channels.AcceptPendingException;
import java.sql.*;
import java.util.ArrayList;

public class Station {
  public int id;
  public String name;

  public Station(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void showTimeTable(Connection connection) {
    ArrayList<ActionTime> actionTimes = new ArrayList<ActionTime>();
    // find the departure time of each train
    try {
      assert connection != null;

      Statement timeSt = connection.createStatement();
      ResultSet timeRows = timeSt.executeQuery("select time, train_id, type from action_times where station_id=" + this.id + "and type='Departure';");

      while (timeRows.next()) {
        String time = timeRows.getString(1);
        int trainId = timeRows.getInt(2);
        String type = timeRows.getString(3);
        String trainName = null;

        Statement trainSt = connection.createStatement();
        ResultSet train = trainSt.executeQuery("select name from trains where id=" + trainId + " limit 1;");
        while (train.next()) {
          trainName = train.getString(1);
        }

        // Save them to the list
        actionTimes.add(new ActionTime(this.name, trainName, type, time));
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // Print the list
    for (int i = 0; i < actionTimes.size(); i++) {
      actionTimes.get(i).displayDeparture();
    }
  }

  public void display() {
    System.out.println("ID: " + this.id + " Name: " + this.name);
  }

  public static void load(Connection connection, ArrayList<Station> stations) {
    try {
      assert connection != null;
      Statement readStations = connection.createStatement();
      ResultSet dbStations = readStations.executeQuery("select * from stations");

      while (dbStations.next()) {
        int id = dbStations.getInt(1);
        String name = dbStations.getString(2);

        stations.add(new Station(id, name));
      }
    } catch (NullPointerException | SQLException e) {
      System.out.println(e);
    }
  }
}
