package rafid;

import nabil.*;

import java.util.ArrayList;

public class Coach {
  public int id;
  public Train train;
  public String name;
  // The bellow lists are initialized by default
  public ArrayList<Ticket> passengers = new ArrayList<Ticket>();

  public Coach() {

  }

  public void showPassengers() {
    // Show the passengers of a coach
  }
}
