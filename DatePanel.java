package calendar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DatePanel extends JPanel {
    JLabel date = new JLabel();

    public DatePanel(String str) {
        date.setText(str);
        add(date, BorderLayout.NORTH);
        Dimension d = new Dimension(100, 140);
        setPreferredSize(d);
        setBorder(new myBorder.RectBorder());
    }



}
