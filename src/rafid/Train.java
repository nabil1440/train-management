package rafid;

import nahid.*;
import nabil.*;

import java.sql.*;

import java.util.ArrayList;

public class Train {
  public int id;
  public String name;
  public int capacity = 0;
  // The lists below are initialized
  public ArrayList<Coach> coaches = new ArrayList<Coach>();
  public ArrayList<ActionTime> actionTimes = new ArrayList<ActionTime>();

  public Train(int id, String name, int capacity) {
    this.id = id;
    this.name = name;
    this.capacity = capacity;
  }

  public void display() {
    System.out.println("ID: " + this.id + " Train: " + this.name + ", Capacity: " + this.capacity);
  }

  public static void load(Connection connection, ArrayList<Train> trains) {
    try {
      assert connection != null;

      Statement st = connection.createStatement();
      ResultSet dbTrains = st.executeQuery("select * from trains;");

      while (dbTrains.next()) {
        int id = dbTrains.getInt(1);
        String name = dbTrains.getString(2);
        int capacity = findCapacity(connection, id);
        trains.add(new Train(id, name, capacity));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void showPassengers(Station from) {
    // Find the passengers of a train from a station
  }

  public static int findCapacity(Connection connection, int trainId) {
    // Get the coaches from the DB
    int sum = 0;
    try {
      assert connection != null;
      Statement statement = connection.createStatement();
      ResultSet coaches = statement.executeQuery("select capacity from coaches where train_id = " + trainId + ";");

      while (coaches.next()) {
        sum += coaches.getInt(1);
      }

      return sum;
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }

  public void showDepartureSchedule(Connection connection) {
    ArrayList<ActionTime> actionTimes = new ArrayList<ActionTime>();

    // Show departure schedule
    try {
      assert connection != null;

      Statement st = connection.createStatement();
      ResultSet departs = st.executeQuery("select * from action_times where type='Departure' and train_id=" + this.id + ";");

      while (departs.next()) {
        String time = departs.getString(2);
        int stationId = departs.getInt(3);
        String stationName = null;

        Statement stationStatement = connection.createStatement();
        ResultSet station = stationStatement.executeQuery("select name from stations where id=" + stationId + " limit 1;");

        while (station.next()) {
          stationName = station.getString(1);
        }
        // Save the time and the station name in a list
        actionTimes.add(new ActionTime(stationName, this.name, "Departure", time));
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // Print the list
    for (int i = 0; i < actionTimes.size(); i++) {
      actionTimes.get(i).displayDeparture();
    }
  }

  public void showArrivalSchedule(Connection connection) {
    // Show arrival schedule
    ArrayList<ActionTime> actionTimes = new ArrayList<ActionTime>();

    // Show departure schedule
    try {
      assert connection != null;

      Statement st = connection.createStatement();
      ResultSet arrives = st.executeQuery("select * from action_times where type='Arrival' and train_id=" + this.id + ";");

      while (arrives.next()) {
        String time = arrives.getString(2);
        int stationId = arrives.getInt(3);
        String stationName = null;

        Statement stationStatement = connection.createStatement();
        ResultSet station = stationStatement.executeQuery("select name from stations where id=" + stationId + " limit 1;");

        while (station.next()) {
          stationName = station.getString(1);
        }
        // Save the time and the station name in a list
        actionTimes.add(new ActionTime(stationName, this.name, "Arrival", time));
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // Print the list
    for (int i = 0; i < actionTimes.size(); i++) {
      actionTimes.get(i).displayArrival();
    }
  }
}
