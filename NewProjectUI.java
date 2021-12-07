package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewProjectUI extends JDialog {
	static boolean create = false;
	private String name = "";
	private static JPanel projectList;
	private static JFrame parentFrame;
	
	public String getName() {
		return name;
	}
	
    public NewProjectUI(JPanel owner,JFrame pFrame){
    	super(javax.swing.SwingUtilities.windowForComponent(owner));
    	parentFrame = pFrame;
    	projectList = owner;
    	createAndShowGUI();
    }
    
    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Login");
        
        //panel to hold components
        final JPanel compPanel = new JPanel();
        compPanel.setLayout(new BoxLayout(compPanel,BoxLayout.LINE_AXIS));
	    
	    //Exit program when trying to exit window
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	    
	    //Create labels for login screen
	    JLabel title = new JLabel("Enter Project Name",SwingConstants.CENTER);
	    
	    //Set title's alignment
	    title.setHorizontalAlignment(JLabel.CENTER);
	    
	    //Create text boxes for user to input information to
	    final JTextField projectField = new JTextField("", 30);
	    
	    //Create buttons for login and create new account
	    JButton cancelButton = new JButton("Cancel");
	    JButton createProjButton = new JButton("Create Project");
	    
	    //Create action listener for login button
	    createProjButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		compPanel.add(new JLabel(projectField.getText()));
        		
        		JButton openThisProj = new JButton("Open Project");
				openThisProj.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						CalendarUI c = new CalendarUI();
						c.CreateFrame();
						parentFrame.dispose();
					}
					
				});
				
				//Add rigid area to create space
				compPanel.add(Box.createRigidArea(new Dimension(10,0)));
				compPanel.add(openThisProj);
				
				//Add components to given panel
				projectList.add(compPanel);
				
				//Update UI of the menu to show new project
				parentFrame.revalidate();
		    	parentFrame.repaint();
        		frame.dispose();
        	}
        });
	    
	    //Create action listener for create new account button
	    cancelButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
	    
	    //Set text in boxes to blank
	    projectField.setText("");
	    
	    //Panel to organize text box orientations
	    JPanel panelOrganizer = new JPanel();
	    
	    //Panel for organizing buttons
	    JPanel buttonPanel = new JPanel();
	    
	    //Set layout for panel organizer
	    panelOrganizer.setLayout(new BorderLayout());
	    
	    //Add username components
	    panelOrganizer.add(title, BorderLayout.NORTH);
	    
	    //Add button components
	    buttonPanel.add(cancelButton ,BorderLayout.LINE_START);
	    buttonPanel.add(createProjButton, BorderLayout.LINE_END);
	    
	    //Add text field to panel organizer
	    panelOrganizer.add(projectField, BorderLayout.CENTER);
	    
	    //Add button panel to panel organizer
	    panelOrganizer.add(buttonPanel, BorderLayout.SOUTH);
	    
	    frame.add(panelOrganizer);
	    
	    //Display window
	    frame.pack();
	    frame.setVisible(true);
    }
}
