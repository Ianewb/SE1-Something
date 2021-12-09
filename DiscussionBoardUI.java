package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DiscussionBoardUI {
	public DiscussionBoardUI(){
		createAndShowGUI();
	}
	
	public void createAndShowGUI() {
		JFrame DisB = new JFrame();
        DisB.setSize(new Dimension(400, 800));
        DisB.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel();
        main.setPreferredSize(new Dimension(400, 800));

        JPanel Task = new JPanel();
        Task.setPreferredSize(new Dimension(400, 300));
        Task.setLayout(new BoxLayout(Task, BoxLayout.PAGE_AXIS));
        JTextField Title = new JTextField("Title");
        Title.setHorizontalAlignment(JTextField.CENTER);
        Title.setPreferredSize(new Dimension(200, 20));
        
        JButton accessabilityButton = new JButton("Change accessability");
        accessabilityButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(DisB,
					    "Accessability has been set to public.");
			}
        	
        });
        
        JTextArea Description = new JTextArea("Description");
        Description.setPreferredSize(new Dimension(350, 200));

        Task.add(Title, BorderLayout.NORTH);
        Task.add(accessabilityButton);
        Task.add(Description, BorderLayout.SOUTH);

        ChatUI c = new ChatUI();
        main.add(Task, BorderLayout.NORTH);
        main.add(c.getChatPanel(), BorderLayout.SOUTH);
        DisB.add(main);
        DisB.setVisible(true);
	}
	
	public static void main(String argv[]) {
		DiscussionBoardUI dui = new DiscussionBoardUI(); 
	}
}
