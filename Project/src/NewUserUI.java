import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class NewUserUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtForename;
	private JTextField txtSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUserUI frame = new NewUserUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewUserUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Password");
		lblUsername.setBounds(20, 67, 59, 14);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("Username");
		label.setBounds(20, 39, 59, 14);
		contentPane.add(label);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(87, 36, 153, 20);
		contentPane.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(87, 64, 153, 20);
		contentPane.add(txtPassword);
		
		txtForename = new JTextField();
		txtForename.setColumns(10);
		txtForename.setBounds(87, 108, 153, 20);
		contentPane.add(txtForename);
		
		JLabel lblForename = new JLabel("Forename");
		lblForename.setBounds(20, 111, 59, 14);
		contentPane.add(lblForename);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(87, 136, 153, 20);
		contentPane.add(txtSurname);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(20, 139, 59, 14);
		contentPane.add(lblSurname);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(87, 189, 89, 23);
		contentPane.add(btnSubmit);
	}
}
