package crispyDuckProjectCode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginUI extends JFrame implements ActionListener{
	private JButton login;
	
	public static void main(String[] args) {
		LoginUI app = new LoginUI();
	}
	
	public LoginUI() {
		JFrame frame = new JFrame("login");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		
		JPanel pannel = new JPanel();
		
		frame.add(pannel);
		
		login = new JButton("login");
		pannel.add(login);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
	    Object src = evt.getSource();
	    
	    if (src == login) {
	    	// TODO write login code
			
		}
	
	}


}
