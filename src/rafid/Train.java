package rafid;

import java.util.ArrayList;

public class Train {
    public int id;
    public String station;
    public String name;
    public ArrayList<Coach> coaches = new ArrayList<Coach>();

    public Train(int id, String station, String name) {
        this.id = id;
        this.station = station;
        this.name = name;
    }
//    public void findDistance(String station, int id){
//
//    }
    public void showPassengers(int id){
        System.out.println();
    }
    public void showCoaches(){
        System.out.println();
    }
}
