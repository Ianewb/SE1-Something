/*
 * Author: Ricky
 * Editor: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calendar {
    public JFrame frame = new JFrame("Team Tasks");
    public JPanel forAll = new JPanel();
    public JPanel TopPanel = new JPanel();
    public JPanel MainCal = new JPanel();
    public JPanel forDateList = new JPanel();
    public ArrayList<JPanel> date = new ArrayList<>();
    
    public static class RoundButton extends JButton {
        public RoundButton(String label) {
            super(label);
            setFocusable(false);
    /*
     These statements enlarge the button so that it
     becomes a circle rather than an oval.
    */
            Dimension size = new Dimension(25, 25);
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
    /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
    */
            setContentAreaFilled(false);
//            setBackground();
            setOpaque(false);
        }

        protected void paintBorder(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        }

        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.gray);
            } else {
                g.setColor(getBackground());
            }
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

            super.paintComponent(g);
        }

    }

    public void CreateFrame() {
        frame.setSize(1200, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Top Panel
        TopPanel = CreateTop();

        // TODO: make it automatically
        // Set date panel
        date.add(DatePanel(""));
        date.add(DatePanel(""));
        date.add(DatePanel(""));
        for (int i = 1; i < 32; i++) {
            date.add(DatePanel(String.valueOf(i)));
        }
        date.add(DatePanel(""));

        // Create Date Panel
        for (int i = 0; i < 35; i++) {
            forDateList.add(date.get(i));
        }

        Dimension DforMainCal = new Dimension(700, 750);
        MainCal.setPreferredSize(DforMainCal);
        // TODO: Sometime is 5 x 8
        forDateList.setLayout(new GridLayout(5, 7));
        
        //Add chat on the side
        Chat c = new Chat();
        
        //Add center panel to hold calendar and chat
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        MainCal.add(createDateLbl());
        MainCal.add(forDateList);
        
        centerPanel.add(MainCal,BorderLayout.CENTER);
        centerPanel.add(c.getChatPanel(),BorderLayout.EAST);

        forAll.add(TopPanel);
        forAll.add(centerPanel);

        frame.add(forAll);
        frame.setVisible(true);
    }
    
    public static void showAddMember() {
        final JFrame email = new JFrame();
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
//            search.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
        search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				email.dispose();
			}
        	
        });
        
        email.pack();
        email.setVisible(true);
        
    }
    
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
    
    public static JPanel DatePanel(String str) {
    	JPanel p = new JPanel();
    	JLabel date = new JLabel();
        date.setText(str);
        p.add(date, BorderLayout.NORTH);
        Dimension d = new Dimension(100, 140);
        p.setPreferredSize(d);
        p.setBorder(new myBorder.RectBorder());
        return p;
    }
    
    public static JPanel CreateTop() {
        JPanel panel = new JPanel();
//        JPanel forLabel = new JPanel();
//        JPanel forMember = new JPanel();
        Dimension d = new Dimension(1150, 40);
        panel.setPreferredSize(d);
        panel.setBorder(new myBorder.RoundedBorder(20));
        panel.setOpaque(true);

        // TODO: for demonstration, the name may vary
        JLabel nameLabel = new JLabel("Something Group");

        // TODO: for demonstration, the member may vary
        JButton AddNewMember = new RoundButton("+");
        JButton member1 = new RoundButton("JW");
        JButton member2 = new RoundButton("RZ");
        JButton member3 = new RoundButton("WD");
        JButton member4 = new RoundButton("JS");

        AddNewMember.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                showAddMember();
            }
        });

//        forLabel.add(nameLabel);
//        forMember.add(member1);
//        forMember.add(member2);
//        forMember.add(member3);
//        forMember.add(member4);
//        forMember.add(AddNewMember);
//        panel.add(forLabel, BorderLayout.LINE_START);
//        panel.add(forMember, BorderLayout.LINE_END);
        panel.add(nameLabel);
        panel.add(member1);
        panel.add(member2);
        panel.add(member3);
        panel.add(member4);
        panel.add(AddNewMember);
        return panel;
    }
    
    private static void createAndShowGUI() {
    	Login l = new Login();
    }
    
    public static void main(String args[]) {
		//Create and show the calendar GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
