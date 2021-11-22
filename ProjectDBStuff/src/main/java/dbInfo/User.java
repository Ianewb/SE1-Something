package main.java.dbInfo;

import java.util.ArrayList;
import java.util.List;

public class User {
    UserDTO toData;
    String username;
    String email;
    String password;

    List<Integer> calendarIDs = new ArrayList<Integer>();

    public User(){}

    public User(String u, String e, String p){
        username = u;
        email = e;
        password = p;
    }

    public void createNewLogin()
    {
        toData.save(this);
    }

}
