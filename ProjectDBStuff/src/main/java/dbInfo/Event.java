package main.java.dbInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class Event {
    List<User> userList = new ArrayList<User>();
    Date resource;
    SimpleDateFormat sdf;
    int id, calendarID;

    Event(User u, Date d, int cID)
    {
        userList.add(u);
        resource = d;
        calendarID = cID;
    }

    public boolean checkConflicts(Event e)
    {
        boolean conflicts = false;
        if(e.resource == this.resource)
        {
            conflicts = true;
        }
        return conflicts;
    }

}
