/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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

public class Login {
	private static boolean validLogin = false;
	
	private static void createNewAccDialog() {
		final JDialog d = new JDialog();
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		d.setTitle("Create Account");
		
		//Create panel to store objects
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
		//Create labels for creating new account
		JLabel newUser = new JLabel("Username:");
		JLabel newPass = new JLabel("Password:");
		JLabel newPassConfirm = new JLabel("Confirm Password:");
		
		//Create text fields for information
		JTextField newUserText = new JTextField("Enter Username", 15);
		final JPasswordField newPassText = new JPasswordField("",15);
		final JPasswordField newPassConfText = new JPasswordField("",15);
		
		//Add text components to panel p (label + textField)
		p.add(newUser);
		newUser.setLabelFor(newUserText);
		p.add(newUserText);
		
		p.add(newPass);
		newPass.setLabelFor(newPassText);
		p.add(newPassText);
		
		p.add(newPassConfirm);
		newPassConfirm.setLabelFor(newPassConfText);
		p.add(newPassConfText);
		
		//Create buttons to add or cancel 
		JButton createAccButton = new JButton("Create");
		JButton cancelButton = new JButton("Cancel");
		
		//Add action listeners to buttons
	    createAccButton.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				//Confirm users passwords match
				if(!(newPassText.getPassword().length > 7)){ 
					JOptionPane.showMessageDialog(d,
							"Password should be more than 7 characters.",
							"Illegal Password",
							JOptionPane.WARNING_MESSAGE);
				} else if(!Arrays.equals(newPassText.getPassword(), newPassConfText.getPassword())){
					JOptionPane.showMessageDialog(d,
							"Passwords do not match.",
							"Password Mismatch Error",
							JOptionPane.WARNING_MESSAGE);
				} else if(false) {
				    //Dont add account if username already exists
				} else {
					JOptionPane.showMessageDialog(d,
							"Your account has been created.",
							"Account Created",
							JOptionPane.WARNING_MESSAGE);
					//Add the account
					d.dispose();
				}
			}
	    	
	    });
		
	    cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				d.dispose();
			}
	    	
	    });
	    
	    //Panel for organizing buttons
	    JPanel buttonPanel = new JPanel();
	    
	    //Add button components
	    buttonPanel.add(createAccButton ,BorderLayout.LINE_START);
	    buttonPanel.add(cancelButton, BorderLayout.LINE_END);
	    
	    //Create panel to organize panels
	    JPanel panelOrganizer = new JPanel();
	    panelOrganizer.setLayout(new BoxLayout(panelOrganizer, BoxLayout.PAGE_AXIS));
	    
	    //Add panels to panel organizer
	    panelOrganizer.add(p);
	    panelOrganizer.add(buttonPanel);
	    
	    d.add(panelOrganizer);
	    
	    //Display window
		d.pack();
		d.setVisible(true);
	}
	
	private static void createAndShowGUI(){
		final JFrame frame = new JFrame("Login");
	    
	    //Set validLogin to true for sake of demo
	    validLogin = true;
	    
	    //Exit program when trying to exit window
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	    
	    //Create labels for login screen
	    JLabel username = new JLabel("Username:",SwingConstants.TRAILING);
	    JLabel password = new JLabel("Password:",SwingConstants.TRAILING);
	    
	    //Create text boxes for user to input information to
	    JTextField usernameField = new JTextField("Enter Username", 15);
	    JPasswordField passwordField = new JPasswordField("",15);
	    
	    //Create buttons for login and create new account
	    JButton loginButton = new JButton("Login");
	    JButton createNewButton = new JButton("Create New Account");
	    
	    //Create action listener for login button
	    loginButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(validLogin) {
        			//provide access
	    			frame.dispose();
        		} else {
        			JOptionPane.showMessageDialog(frame,
	    				    "Invalid login. Please try again.",
	    				    "Login error",
	    				    JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
	    
	    //Create action listener for create new account button
	    createNewButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //Open window for creating a new account
                    	createNewAccDialog();
                    }
                });
        	}
        });
	    
	    //Set text in boxes to blank
	    usernameField.setText("");
	    passwordField.setText("");
	    
	    //Panel to organize text box orientations
	    JPanel panelOrganizer = new JPanel();
	    
	    //Panels for organizing login texts
	    JPanel userPanel = new JPanel();
	    JPanel passPanel = new JPanel();
	    
	    //Panel for organizing buttons
	    JPanel buttonPanel = new JPanel();
	    
	    //Set layout for panel organizer
	    panelOrganizer.setLayout(new BoxLayout(panelOrganizer, BoxLayout.PAGE_AXIS));
	    
	    //Add username components
	    userPanel.add(username, BorderLayout.LINE_START);
	    username.setLabelFor(usernameField);
	    userPanel.add(usernameField, BorderLayout.CENTER);
	    
	    //Add password components
	    passPanel.add(password, BorderLayout.LINE_START);
	    password.setLabelFor(passwordField);
	    passPanel.add(passwordField, BorderLayout.CENTER);
	    
	    //Add button components
	    buttonPanel.add(loginButton ,BorderLayout.LINE_START);
	    buttonPanel.add(createNewButton, BorderLayout.LINE_END);
	    
	    //Add panels to panel organizer
	    panelOrganizer.add(userPanel);
	    panelOrganizer.add(passPanel);
	    panelOrganizer.add(buttonPanel);
	    
	    frame.add(panelOrganizer);
	    
	    //Display window
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		//Create and show the login GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
