package nabil;

import rafid.*;
import nahid.*;

public class Ticket {
  public int id;
  public String passengerName, time;
  public Station from, to;
  public Train train;
  public Coach coach;

  public Ticket(int id, String passengerName, String time, Station from, Station to, Train train, Coach coach) {
    this.id = id;
    this.passengerName = passengerName;
    this.time = time;
    this.from = from;
    this.to = to;
    this.train = train;
    this.coach = coach;
  }

  public void display() {
    // Show details of the ticket
  }
}
