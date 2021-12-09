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

public class LoginUI {
	private static volatile boolean requestedLogin = false;
	private static volatile boolean isCreatingNew = false;
	private static volatile boolean clickedCreatingNew = false;
	private static volatile boolean closedCreatingNew = false;

	private static JTextField newUserText;
	private static JPasswordField newPassText;
	private static JTextField newEmailText;
	private static JTextField emailField;
	private static JPasswordField passwordField;
	
	private static JDialog d;
	
	private static JFrame frame;
	
	private static void createNewAccDialog() {
		d = new JDialog();
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		d.setTitle("Create Account");
		
		//Create panel to store objects
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
		//Create labels for creating new account
		JLabel newUser = new JLabel("Username:");
		JLabel newEmail = new JLabel("Email:");
		JLabel newPass = new JLabel("Password:");
		JLabel newPassConfirm = new JLabel("Confirm Password:");
		
		//Create text fields for information
		newUserText = new JTextField("Enter Username", 15);
		newEmailText = new JTextField("Enter Email", 15);
		newPassText = new JPasswordField("",15);
		final JPasswordField newPassConfText = new JPasswordField("",15);
		
		//Add text components to panel p (label + textField)
		p.add(newUser);
		newUser.setLabelFor(newUserText);
		p.add(newUserText);
		
		p.add(newEmail);
		newEmail.setLabelFor(newEmailText);
		p.add(newEmailText);
		
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
				} else {
					isCreatingNew = true;
				}
			}
	    	
	    });
		
	    cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				closedCreatingNew = true;
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
	
	private static void createAndShowGUI() {
        frame = new JFrame("Login");
	    
	    //Exit program when trying to exit window
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Set requested login to false by default
	    setRequestedLogin(false);
	    	    
	    //Create labels for login screen
	    JLabel email = new JLabel("Email:",SwingConstants.TRAILING);
	    JLabel password = new JLabel("Password:",SwingConstants.TRAILING);
	    
	    //Create text boxes for user to input information to
	    emailField = new JTextField("Enter email", 15);
	    passwordField = new JPasswordField("",15);
	    
	    //Create buttons for login and create new account
	    JButton loginButton = new JButton("Login");
	    JButton createNewButton = new JButton("Create New Account");
	    
	    //Create action listener for login button
	    loginButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(getEmailField().getText().length() > 1
        				&& getPasswordField().getPassword().length > 7
        				&& getEmailField().getText().contains("@")
        				&& getEmailField().getText().contains(".")) {
        			setRequestedLogin(true);
        		} else {
        			createLoginError();
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
                    	setClickedCreatingNew(true);
                    	createNewAccDialog();
                    }
                });
        	}
        });
	    
	    //Set text in boxes to blank
	    emailField.setText("");
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
	    userPanel.add(email, BorderLayout.LINE_START);
	    email.setLabelFor(emailField);
	    userPanel.add(emailField, BorderLayout.CENTER);
	    
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
	
	public static boolean isRequestedLogin() {
		return requestedLogin;
	}
	
	public static boolean isCreatingNew() {
		return isCreatingNew;
	}
	
	public static boolean isClickedCreatingNew() {
		return clickedCreatingNew;
	}
	
	public static boolean isClosedCreatingNew() {
		return closedCreatingNew;
	}
	
	public static JDialog getDialog() {
		return d;
	}

	public static void setClosedCreatingNew(boolean closedCreatingNew) {
		LoginUI.closedCreatingNew = closedCreatingNew;
	}
	
	public static void setCreatingNew(boolean creatingNew) {
		LoginUI.isCreatingNew = creatingNew;
	}

	public static void setRequestedLogin(boolean requestedLogin) {
		LoginUI.requestedLogin = requestedLogin;
	}
	
	public static void setClickedCreatingNew(boolean clickedCreatingNew) {
		LoginUI.clickedCreatingNew = clickedCreatingNew;
	}

	public static JTextField getNewUserText() {
		return newUserText;
	}

	public static void setNewUserText(JTextField newUserText) {
		LoginUI.newUserText = newUserText;
	}

	public static JPasswordField getNewPassText() {
		return newPassText;
	}

	public static void setNewPassText(JPasswordField newPassText) {
		LoginUI.newPassText = newPassText;
	}

	public static JTextField getNewEmailText() {
		return newEmailText;
	}

	public static void setNewEmailText(JTextField newEmailText) {
		LoginUI.newEmailText = newEmailText;
	}

	public static JTextField getEmailField() {
		return emailField;
	}

	public static void setEmailField(JTextField emailField) {
		LoginUI.emailField = emailField;
	}

	public static JPasswordField getPasswordField() {
		return passwordField;
	}

	public static void setPasswordField(JPasswordField passwordField) {
		LoginUI.passwordField = passwordField;
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static void createLoginError() {
		JOptionPane.showMessageDialog(frame,
			    "Invalid login attempt. Please try again.",
			    "Login error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public static void createExistingUserError(){
		JOptionPane.showMessageDialog(frame,
			    "Account with email already exists.",
			    "Error creating new account",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public static void createSuccessMsg() {
		JOptionPane.showMessageDialog(frame,
			    "Account successfully created");
	}

	public LoginUI(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Create login GUI
            	createAndShowGUI();
            }
        });
	}

	public void dispose() {
		frame.dispose();
	}
}
