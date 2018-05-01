import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class UpdateUserUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtForename;
	private JTextField txtSurname;
	private String username = "sdean";
	Database database = new Database();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUserUI frame = new UpdateUserUI();
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(116, 190, 195, 23);
		contentPane.add(btnChangePassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(116, 224, 195, 23);
		contentPane.add(btnSave);
		
		txtUsername = new JTextField();
		txtUsername.setText(username);
		txtUsername.setEditable(false);
		txtUsername.setBounds(146, 33, 245, 28);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(42, 40, 105, 14);
		contentPane.add(lblUsername);
		
		txtForename = new JTextField();
		txtForename.setBounds(146, 73, 245, 28);
		contentPane.add(txtForename);
		txtForename.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(146, 112, 245, 28);
		contentPane.add(txtSurname);
		
		JLabel lblForename = new JLabel("Forename");
		lblForename.setBounds(42, 80, 105, 14);
		contentPane.add(lblForename);
		txtForename.setText(database.getForename(username));
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(42, 119, 105, 14);
		contentPane.add(lblSurname);
		txtSurname.setText(database.getSurname(username));
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangePassword changePassword = new ChangePassword(username);
				changePassword.setVisible(true);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String forename = txtForename.getText();
				String surname = txtSurname.getText();
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
	}
	
	public String getUsername() {
		return username;
	}
}
