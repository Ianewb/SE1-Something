/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MenuUI {
	//TODO
	//Data member to hold selected project to open
	
    public MenuUI() {
    	createAndShowGUI();
    }
    
    private static void createAndShowGUI() {
    	final JFrame frame = new JFrame("Menu");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//Create panel to hold my calendar projects
    	final JPanel p = new JPanel();
    	
    	//Create new button to open project
    	JButton openProjButton = new JButton("Open Project");
    	
    	//Add action listener to open project
    	openProjButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CalendarUI c = new CalendarUI();
				c.CreateFrame();
				frame.dispose();
			}
    		
    	});
    	
    	//Set layout of panel
    	//p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
    	
    	//Create button for adding projects
    	JButton newProj = new JButton("Create Project");
    	
    	//panel to hold projects
    	final JPanel newProjPanel = new JPanel();
    	newProjPanel.setLayout(new BoxLayout(newProjPanel, BoxLayout.PAGE_AXIS));
		newProjPanel.add(Box.createHorizontalGlue());
    	
    	newProj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Create a dialog for new project name
				NewProjectUI myProj = new NewProjectUI(newProjPanel,frame);
			}
    	});
    	
    	//Add header components
    	JPanel headerPanel = new JPanel();
    	headerPanel.add(new JLabel("My Projects", SwingConstants.CENTER),BorderLayout.CENTER);
    	headerPanel.add(newProj,BorderLayout.EAST);
    	
    	//TODO
    	//Add each of my existing projects to panel
    	    	
    	//Set preferred size of the project panel
    	newProjPanel.setPreferredSize(new Dimension(400,400));
    	
    	//Create scroll pane to hold my projects
    	JScrollPane sp = new JScrollPane(newProjPanel);
    	
    	frame.add(headerPanel,BorderLayout.NORTH);
    	frame.add(sp);
    	frame.add(openProjButton,BorderLayout.SOUTH);
    	
    	frame.pack();
    	frame.setVisible(true);
    }
}
