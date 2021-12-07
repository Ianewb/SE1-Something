package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
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
        JTextField Title = new JTextField("Title");
        Title.setHorizontalAlignment(JTextField.CENTER);

        Title.setPreferredSize(new Dimension(200, 20));
        JTextArea Description = new JTextArea("Description");
        Description.setPreferredSize(new Dimension(350, 200));

        Task.add(Title, BorderLayout.NORTH);
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
