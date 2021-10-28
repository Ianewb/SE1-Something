package calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddMember {
    public static void showAddMember() {
        JFrame email = new JFrame();
        Dimension d = new Dimension(300, 70);
        email.setPreferredSize(d);
        JPanel emailPanel = new JPanel();
        emailPanel.setPreferredSize(d);
        JLabel emailLbl =  new JLabel("Email");
        JTextField emailTF = new JTextField();
        emailTF.setText("example@example.com");
        JButton search = new JButton("Search");

        email.setTitle("Enter the Email");
        emailPanel.add(emailLbl);
        emailPanel.add(emailTF);
        emailPanel.add(search);
        email.add(emailPanel);

        // TODO: wait to be implement
//        search.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        email.pack();
        email.setVisible(true);
    }
}
