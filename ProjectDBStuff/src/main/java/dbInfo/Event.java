package main.java.dbInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class Event {
    List<User> userList = new ArrayList<User>();
    String description, name, location;
    Date resourceStart;
    Date resourceEnd;
    SimpleDateFormat sdf;
    int id, calendarID;

    Event(User u, Date d, int cID)
    {
        userList.add(u);
        resourceStart = d;
        calendarID = cID;
    }

    public boolean checkConflicts(Event e)
    {
        boolean conflicts = false;
        if(e.resourceStart == this.resourceStart)
        {
            conflicts = true;
        }
        return conflicts;
    }

}
