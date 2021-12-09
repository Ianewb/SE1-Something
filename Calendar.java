/*
 * Author: Ricky
 * Editor: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;

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

        MainCal.setPreferredSize(new Dimension(700, 750));
        // TODO: Sometime is 5 x 8
        forDateList.setLayout(new GridLayout(5, 7));
        
        //Add chat on the side
        Chat c = new Chat();
        
        //Add center panel to hold calendar and chat
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(1100, 750));

        MainCal.add(createDateLbl());
        MainCal.add(forDateList);
        
        centerPanel.add(MainCal, BorderLayout.WEST);
//        JPanel chatP = new JPanel();
//        chatP.setPreferredSize(new Dimension(350, 750));
//        chatP.add(c.getChatPanel());
        centerPanel.add(c.getChatPanel(), BorderLayout.EAST);

        forAll.setPreferredSize(new Dimension(1100, 900));
        forAll.add(TopPanel, BorderLayout.NORTH);
        forAll.add(centerPanel, BorderLayout.SOUTH);

        JMenuBar menu = CreateMenu();
        frame.setJMenuBar(menu);
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

        //to_DB.setPreferredSize(new Dimension(80, 20));
    	JLabel date = new JLabel();
        date.setText(str);

        p.setPreferredSize(new Dimension(100, 140));

        p.add(date, BorderLayout.NORTH);
        // TODO: Get description from dicusion broad
        if (!str.equals("")) {
            JPanel for_button = new JPanel();
            for_button.setPreferredSize(new Dimension(85, 30));
            JButton to_DB = new JButton("");
            for_button.add(to_DB, BorderLayout.CENTER);
            p.add(for_button, BorderLayout.SOUTH);

            to_DB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createDiscussionBoard();
                }
            });
        }
        p.setBorder(new myBorder.RectBorder());
        return p;
    }

    public static JPanel CreateTop() {
        JPanel panel = new JPanel();
//        JPanel forLabel = new JPanel();
//        JPanel forMember = new JPanel();
        Dimension d = new Dimension(1100, 40);
        panel.setPreferredSize(d);
        panel.setBorder(new myBorder.RoundedBorder(20));
        panel.setOpaque(true);

        // TODO: for demonstration, the name may vary
        JLabel nameLabel = new JLabel("Something Group");

        // TODO: for demonstration, the member may vary
        JButton AddNewMember = new RoundButton("+");
        JButton AddNewEvent = new RoundButton("Event");
//        JButton member1 = new RoundButton("JW");
//        JButton member2 = new RoundButton("RZ");
//        JButton member3 = new RoundButton("WD");
//        JButton member4 = new RoundButton("JS");

        AddNewMember.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                showAddMember();
            }
        });
        AddNewEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAddEvent();
            }
        });

        panel.add(AddNewEvent);
//        forLabel.add(nameLabel);
//        forMember.add(member1);
//        forMember.add(member2);
//        forMember.add(member3);
//        forMember.add(member4);
//        forMember.add(AddNewMember);
//        panel.add(forLabel, BorderLayout.LINE_START);
//        panel.add(forMember, BorderLayout.LINE_END);
        panel.add(nameLabel);
//        panel.add(member1);
//        panel.add(member2);
//        panel.add(member3);
//        panel.add(member4);
        panel.add(AddNewMember);
        return panel;
    }

    public static JMenuBar CreateMenu() {
        JMenu menu = new JMenu("Menu");
        JMenu help = new JMenu("Help");
        JMenuBar mb = new JMenuBar();
        JMenuItem refresh = new JMenuItem("Refresh");
        JMenuItem blacklist = new JMenuItem("Blacklist");
        final JMenuItem sendNotify = new JMenuItem("Notify");
        JMenuItem notifyoff = new JMenuItem("Notify Off");

        JMenuItem contact = new JMenuItem("Contact");
        JMenuItem report = new JMenuItem("Report");
        contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();

                JOptionPane.showMessageDialog(frame,
                        "Please Contact Tomas_Cerny@baylor.edu",
                        "Need Help???",
                        JOptionPane.NO_OPTION);
            }
        });

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame("Report");
                JOptionPane pane = new JOptionPane();
                String str = pane.showInputDialog(frame,"Report Your Problem",
                        "Report", JOptionPane.OK_CANCEL_OPTION);
                System.out.println(str);
            }
        });

        // TODO: Implement
//        refresh.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

        blacklist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame email = new JFrame();
                Dimension d = new Dimension(300, 70);
                email.setPreferredSize(d);
                JPanel emailPanel = new JPanel();
                emailPanel.setPreferredSize(d);
                JLabel emailLbl =  new JLabel("Email");
                JTextField emailTF = new JTextField();
                emailTF.setText("example@example.com");
                JButton ban = new JButton("BAN!");

                email.setTitle("Enter the Email");
                emailPanel.add(emailLbl);
                emailPanel.add(emailTF);
                emailPanel.add(ban);
                email.add(emailPanel);

                // TODO: wait to be implement
//            ban.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
                ban.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        email.dispose();
                    }

                });

                email.pack();
                email.setVisible(true);
            }
        });

//        sendNotify.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        menu.add(refresh);
        menu.add(blacklist);
        menu.add(sendNotify);
        menu.add(notifyoff);
        mb.add(menu);
        help.add(contact);
        help.add(report);
        mb.add(help);
        return mb;
    }

    private static void createAndShowGUI() {
    	Login l = new Login();
    }

    public static void createDiscussionBoard() {
        JFrame DisB = new JFrame();
        DisB.setSize(new Dimension(400, 600));
        DisB.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        JPanel main = new JPanel();
//        main.setPreferredSize(new Dimension(400, 400));

        JPanel Task = new JPanel();
//        Task.setPreferredSize(new Dimension(400, 220));
        JTextField Title = new JTextField("Event Name");
        Title.setEditable(false);
        Title.setHorizontalAlignment(JTextField.CENTER);

        Title.setPreferredSize(new Dimension(350, 20));
        JTextArea Description = new JTextArea("Description");
        Description.setEditable(false);
        Description.setPreferredSize(new Dimension(350, 200));

        Task.add(Title, BorderLayout.NORTH);
        Task.add(Description, BorderLayout.SOUTH);

        Chat c = new Chat();
        DisB.add(Task);


//        JPanel chat_dis = new JPanel();
//        chat_dis.add(c.getChatPanel());
//        chat_dis.setMinimumSize(new Dimension(350, 500));
//        chat_dis.setSize(new Dimension(400, 500));
//        main.add(chat_dis);
//        main.setLayout(new GridLayout(2,1));
//        DisB.add(main);
        DisB.add(c.getChatPanel());
        DisB.setLayout(new GridLayout(2, 1));
        DisB.setVisible(true);
    }

    public static void createAddEvent() {
        JFrame Event = new JFrame();
        Event.setSize(new Dimension(400, 200));
        Event.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel eventlbl = new JLabel("Event: ");
        JLabel locatlbl = new JLabel("Location: ");
        JLabel descplbl = new JLabel("Description: ");
        JLabel startlbl = new JLabel("Start Time: ");
        JLabel endinlbl = new JLabel("End Time: ");

        JTextField event = new JTextField("Event Name", 20);
        JTextField locat = new JTextField("Location", 20);
        JTextArea descp = new JTextArea("Description");
        descp.setBorder(new myBorder.RectBorder());
        JTextField start = new JTextField("yyyy-MM-DD HH(0-23):mm", 20);
        JTextField end = new JTextField("yyyy-MM-DD HH(0-23):mm", 20);

        eventlbl.setLabelFor(event);
        locatlbl.setLabelFor(locat);
        descplbl.setLabelFor(descp);
        startlbl.setLabelFor(start);
        endinlbl.setLabelFor(end);

        JPanel layoutOg = new JPanel();
        layoutOg.setLayout(new SpringLayout());
        layoutOg.add(eventlbl);
        layoutOg.add(event);
        layoutOg.add(locatlbl);
        layoutOg.add(locat);
        layoutOg.add(descplbl);
        layoutOg.add(descp);
        layoutOg.add(startlbl);
        layoutOg.add(start);
        layoutOg.add(endinlbl);
        layoutOg.add(end);

//Lay out the panel.
        SpringUtilities.makeCompactGrid(layoutOg,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        JButton post = new JButton("POST");
        Event.add(layoutOg);
        Event.add(post, BorderLayout.SOUTH);

        //TODO: POST button
//        post.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        Event.setVisible(true);
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
