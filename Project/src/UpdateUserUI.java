import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class UpdateUserUI extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtForename;
	private JTextField txtSurname;
	private String username, passwordString, roleString, forename, surname;
	private boolean admin;
	Database database = new Database();

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
	public UpdateUserUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Username");
		label.setBounds(23, 24, 59, 14);
		contentPane.add(label);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(90, 21, 153, 20);
		contentPane.add(txtUsername);
		//TODO: Update dynamically
		
		txtForename = new JTextField();
		txtForename.setColumns(10);
		txtForename.setBounds(90, 58, 153, 20);
		contentPane.add(txtForename);
		txtForename.setText(database.getForename(username));
		
		JLabel lblForename = new JLabel("Forename");
		lblForename.setBounds(23, 61, 59, 14);
		contentPane.add(lblForename);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(90, 89, 153, 20);
		contentPane.add(txtSurname);
		txtSurname.setText(database.getSurname(username));
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(23, 92, 59, 14);
		
		contentPane.add(lblSurname);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(61, 167, 142, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtUsername.getText();
				if (roleString.equals("Admin")) {
					admin=true;
				}
				else {
					admin=false;
				}
				forename = txtForename.getText();
				surname = txtSurname.getText();
			    try {
					boolean updateUser = database.updateUser(username,
							forename, surname);
					System.out.println(updateUser);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		contentPane.add(btnSubmit);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangePassword changePassword = new ChangePassword();
				changePassword.setVisible(true);
			}
		});
		
		btnChangePassword.setBounds(61, 133, 142, 23);
		contentPane.add(btnChangePassword);
	}
}
