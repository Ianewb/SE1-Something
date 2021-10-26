package calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DateList {
    public static JPanel createDateLbl() {
        JPanel panel = new JPanel();
        Dimension d = new Dimension(95, 20);

        JPanel ForMonday = new JPanel();
        JPanel ForTuesday = new JPanel();
        JPanel ForWednesday = new JPanel();
        JPanel ForThursday = new JPanel();
        JPanel ForFriday = new JPanel();
        JPanel ForSaturday = new JPanel();
        JPanel ForSunday = new JPanel();

        JLabel Monday = new JLabel("Monday");
        JLabel Tuesday = new JLabel("Tuesday");
        JLabel Wednesday = new JLabel("Wednesday");
        JLabel Thursday = new JLabel("Thursday");
        JLabel Friday = new JLabel("Friday");
        JLabel Saturday = new JLabel("Saturday");
        JLabel Sunday = new JLabel("Sunday");

        ForMonday.setPreferredSize(d);
        ForTuesday.setPreferredSize(d);
        ForWednesday.setPreferredSize(d);
        ForThursday.setPreferredSize(d);
        ForFriday.setPreferredSize(d);
        ForSaturday.setPreferredSize(d);
        ForSunday.setPreferredSize(d);

        ForMonday.add(Monday);
        ForTuesday.add(Tuesday);
        ForWednesday.add(Wednesday);
        ForThursday.add(Thursday);
        ForFriday.add(Friday);
        ForSaturday.add(Saturday);
        ForSunday.add(Sunday);

        panel.add(ForSunday);
        panel.add(ForMonday);
        panel.add(ForTuesday);
        panel.add(ForWednesday);
        panel.add(ForThursday);
        panel.add(ForFriday);
        panel.add(ForSaturday);

        return panel;
    }
}
