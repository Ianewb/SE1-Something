package calendar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Calendar {
    public JFrame frame = new JFrame("Team Tasks");
    public JPanel forAll = new JPanel();
    public JPanel TopPanel = new JPanel();
    public JPanel MainCal = new JPanel();
    public JPanel forDateList = new JPanel();
    public ArrayList<JPanel> date = new ArrayList<>();

    public void CreateFrame() {
        frame.setSize(1200, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Top Panel
        TopPanel topPanel = new TopPanel();
        TopPanel = topPanel.CreateTop();

        // TODO: make it automatically
        // Set date panel
        JPanel blankDate1 = new DatePanel("");
        JPanel blankDate2 = new DatePanel("");
        JPanel blankDate3 = new DatePanel("");
        JPanel blankDate4 = new DatePanel("");
        JPanel blankDate5 = new DatePanel("");
        JPanel blankDate6 = new DatePanel("");
        JPanel blankDate7 = new DatePanel("");
        JPanel blankDate8 = new DatePanel("");
        JPanel blankDate9 = new DatePanel("");
        date.add(blankDate1);
        date.add(blankDate2);
        date.add(blankDate3);
        for (int i = 1; i < 32; i++) {
            JPanel newDate = new DatePanel(String.valueOf(i));
            date.add(newDate);
        }
        date.add(blankDate4);
        date.add(blankDate5);
        date.add(blankDate6);
        date.add(blankDate7);
        date.add(blankDate8);
        date.add(blankDate9);

        // Creat Date Panel
        for (int i = 0; i < 35; i++) {
            forDateList.add(date.get(i));
        }

        Dimension DforMainCal = new Dimension(700, 750);
        MainCal.setPreferredSize(DforMainCal);
        // TODO: Sometime is 5 x 8
        forDateList.setLayout(new GridLayout(5, 7));

        MainCal.add(DateList.createDateLbl());
        MainCal.add(forDateList);

        forAll.add(TopPanel);
        forAll.add(MainCal);

        frame.add(forAll);
        frame.setVisible(true);
    }
}
