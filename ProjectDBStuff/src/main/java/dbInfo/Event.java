package main.java.dbInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class Event {
    List<User> userList = new ArrayList<User>();
    String description, name, location;
    Date resourceStart;
    Date resourceEnd;
    SimpleDateFormat sdf;
    int calendarID;

    Event(User u, Date s, Date e, int cID)
    {
        userList.add(u);
        resourceStart = s;
        resourceEnd = e;
        calendarID = cID;
    }

    public boolean checkConflicts(Event e)
    {
        boolean conflicts = false;
        if(e.resourceStart == this.resourceStart || e.resourceEnd == this.resourceEnd)
        {
            conflicts = true;
        }
        return conflicts;
    }

    public void addUser(User u){
        userList.add(u);
    }

}
