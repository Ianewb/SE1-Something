/*
 * Author: Jacob Senior
 */

package edu.baylor.ecs.csi3471.seniorjacob.CalendarProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Chat {
	private static JTextField textBox;
	private static JTextArea chatArea;
	private static JLabel message;
	private static JPanel chat = null;
	
	public Chat() {
		//Initialize panel
		chat = new JPanel();
		chat.setLayout(new BorderLayout());
		
		//Add header to chat panel
		chat.add(new JLabel("Chat",SwingConstants.CENTER),BorderLayout.NORTH);
		
		//Initialize components
		textBox = new JTextField("", 30);
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		message = new JLabel("Enter chat message:");
		
		//Create panel to store objects
		JPanel chatBarPanel = new JPanel();
		chatBarPanel.setLayout(new GridLayout(2,1));
		
		//Add chatbar components to chatbar panel
		chatBarPanel.add(message);
		chatBarPanel.add(textBox);
		
		//Add chatbar components to the bottom of the frame
		chat.add(chatBarPanel,BorderLayout.SOUTH);
		
		//Add a listener to check if enter key pressed
		textBox.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == KeyEvent.VK_ENTER) {
					addMessage(textBox.getText());
				}
			}
		});
		
		//Set margins for the chat area
		chatArea.setMargin(new Insets(7,7,7,7));
		
		//Create a scroll pane for the chat area
		JScrollPane sp = new JScrollPane(chatArea);
		chat.add(sp,BorderLayout.CENTER);
		
		//Display window
		//chat.setSize(400,300);
		//chat.setVisible(true);
	}
	
	private static void addMessage(String s) {
		s = s.trim();
		if(s.isEmpty()) {
			return;
		} else {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
			df.setTimeZone(c.getTimeZone());
			//Add name of user who entered the message above chat
			chatArea.append(df.format(c.getTime())+": "+textBox.getText()+"\n");
		}
		textBox.setText("");
	}
	
	public JPanel getChatPanel() {
		if(chat == null) {
			new NullPointerException("ERR: Chat panel was null");
		}
		return chat;
	}
	
	public static void main(String args[]) {
		Chat c = new Chat();
	}
}
