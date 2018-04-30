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

public class NewUserUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtForename;
	private JTextField txtSurname;
	private char[] password;
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
	public NewUserUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Password");
		lblUsername.setBounds(23, 56, 59, 14);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("Username");
		label.setBounds(23, 24, 59, 14);
		contentPane.add(label);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(90, 21, 153, 20);
		contentPane.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(90, 53, 153, 20);
		contentPane.add(txtPassword);
		
		txtForename = new JTextField();
		txtForename.setColumns(10);
		txtForename.setBounds(90, 117, 153, 20);
		contentPane.add(txtForename);
		
		JLabel lblForename = new JLabel("Forename");
		lblForename.setBounds(23, 120, 59, 14);
		contentPane.add(lblForename);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(90, 149, 153, 20);
		contentPane.add(txtSurname);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(23, 152, 59, 14);
		
		contentPane.add(lblSurname);
		JComboBox cmbRole = new JComboBox();
		cmbRole.setMaximumRowCount(2);
		cmbRole.setBounds(90, 85, 153, 20);
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Admin", "User"}));
		contentPane.add(cmbRole);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(23, 88, 59, 14);
		contentPane.add(lblRole);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(90, 190, 89, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtUsername.getText();
				password = txtPassword.getPassword();
				passwordString = new String (password);
				roleString = cmbRole.getSelectedItem().toString();
				if (roleString.equals("Admin")) {
					admin=true;
				}
				else {
					admin=false;
				}
				forename = txtForename.getText();
				surname = txtSurname.getText();
			    try {
					boolean addUser = database.addUser(username, passwordString,
							admin, forename, surname);
					boolean addPreferences = database.addUserPreferences(username);
					System.out.println(addUser + " " + addPreferences);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnSubmit);
		
		
	}
}