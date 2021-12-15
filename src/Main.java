import nabil.*;
import rafid.*;
import nahid.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  static void clsSim() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }

  static void showStations(ArrayList<Station> stations, boolean enter) {
    for (int i = 0; i < stations.size(); i++) {
      stations.get(i).display();
    }

    if (enter) {
      System.out.println("Press enter to continue...");
      new Scanner(System.in).nextLine();
    }
  }

  static void showTrains(ArrayList<Train> trains, boolean enter) {
    for (int i = 0; i < trains.size(); i++) {
      trains.get(i).display();
    }

    if (enter) {
      System.out.println("Press enter to continue...");
      new Scanner(System.in).nextLine();
    }
  }

  public static void main(String[] args) {
    // Establish a DB connection
    Connection connection = null;
    try {
      // This line makes sure that we are using the PostgreSQL driver
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost:5432/test";
      connection = DriverManager.getConnection(url, "grass", "root");

      assert connection != null;
      System.out.println("DB connected!");
    } catch (Exception e) {
      System.out.println(e);
    }

    ArrayList<Station> stations = new ArrayList<Station>();
    ArrayList<Train> trains = new ArrayList<Train>();
    // Load stations from the DB
    Station.load(connection, stations);
    // Load trains and their coaches with it
    Train.load(connection, trains);

    boolean menu = true;

    while (menu) {
      clsSim();
      System.out.println("[1] - Show stations");
      System.out.println("[2] - Show trains and their capacity");
      System.out.println("[3] - Show schedule of a station");
      System.out.println("[4] - Show departure times of a train");
      System.out.println("[5] - Show arrival times of a train");
      System.out.println("[6] - Exit");

      int choice = new Scanner(System.in).nextInt();

      switch (choice) {
        case 1:
          showStations(stations, true);
          break;
        case 2:
          showTrains(trains, true);
          break;
        case 3:
          showStations(stations, false);
          System.out.print("Pick a station ID: ");
          int stationId = new Scanner(System.in).nextInt();
          for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).id == stationId) {
              stations.get(i).showTimeTable(connection);
            }
          }
          System.out.println("Press enter to continue...");
          new Scanner(System.in).nextLine();
          break;
        case 4:
          showTrains(trains, false);
          System.out.print("Pick a train ID: ");

          int trainId = new Scanner(System.in).nextInt();
          for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).id == trainId) {
              trains.get(i).showDepartureSchedule(connection);
            }
          }

          System.out.println("Press enter to continue...");
          new Scanner(System.in).nextLine();
          break;
        case 5:
          showTrains(trains, false);
          System.out.print("Pick a train ID: ");
          trainId = new Scanner(System.in).nextInt();

          for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).id == trainId) {
              trains.get(i).showArrivalSchedule(connection);
            }
          }

          System.out.println("Press enter to continue...");
          new Scanner(System.in).nextLine();
          break;
        case 6:
          clsSim();
          System.out.println("Closing application...");
          menu = false;
        default:
          // Do nothing
          break;
      }
    }
  }
}
