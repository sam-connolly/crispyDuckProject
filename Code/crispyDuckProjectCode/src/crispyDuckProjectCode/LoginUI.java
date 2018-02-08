package crispyDuckProjectCode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginUI extends JFrame implements ActionListener{
	private JButton test;
	
	public static void main(String[] args) {
		LoginUI app = new LoginUI();
		app.setVisible(true);
	}
	
	public LoginUI() {
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 250, 300);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		test = new JButton("login");
		test.addActionListener(this);
		main.add(test);
		
		main.add(buttons);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	
	}


}
