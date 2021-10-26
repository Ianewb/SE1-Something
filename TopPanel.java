package calendar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {

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

    public JPanel CreateTop() {
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

        AddNewMember.addActionListener(new AddMemberMethod());

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


    public class AddMemberMethod implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddMember addMember = new AddMember();
            addMember.showAddMember();
        }
    }


}
