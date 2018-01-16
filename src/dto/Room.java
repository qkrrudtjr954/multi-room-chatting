package dto;

import java.util.ArrayList;
import java.util.List;

public class Room {

    public String name;
    public List<Guest> guests;

    public Room(String name) {
        this.name = name;
        this.guests = new ArrayList<>();
    }

    public boolean addGuests(Guest guest){
        if(this.guests.add(guest)){
            return true;
        }else {
            return false;
        }
    }

    public boolean removeGuests(Guest guest){
        if(this.guests.remove(guest)){
            return true;
        }else {
            return false;
        }
    }
}
