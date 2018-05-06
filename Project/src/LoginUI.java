import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame {
	private static String username, passwordString;
	private static char[] password;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	//Create Database object for connection to database
	Database database = new Database();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(144, 33, 177, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(144, 61, 177, 20);
		contentPane.add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(45, 36, 89, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 64, 89, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get values from user input
				username = txtUsername.getText().trim();
				password = txtPassword.getPassword();
				//Convert password input to String
				passwordString = new String (password);
				passwordString.trim();
				//Check if username password combo matches
				Boolean valid = database.validateLogin(username, passwordString);
				//If login successful
				if(valid) {
					//Welcome message
					JOptionPane.showMessageDialog(null, "Welcome, " + username, 
							"Success", JOptionPane.INFORMATION_MESSAGE);
					//Checks user role
					boolean isAdmin = database.checkRole(username);
					//If user is Admin will open ManagerMenu UI 
					if (isAdmin) {
						try {
							ManagerMenu managerMenu = new ManagerMenu(username);
						} catch (ParseException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					//If user is NOT Admin will open EmployeeMenu UI
					else {
						EmployeeMenu employeeMenu = new EmployeeMenu(username);
					}
					//Close login window
					setVisible(false);
				}
				//If login failed
				else {
					//Error message informing user of incorrect login
					JOptionPane.showMessageDialog(null, "Username and password do not match.", 
							"Login Failed", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(144, 116, 89, 23);
		contentPane.add(btnLogin);		
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return passwordString;
	}
}