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
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame {
	private static String username, passwordString;
	private static char[] password;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(110, 33, 139, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(110, 61, 139, 20);
		contentPane.add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(45, 36, 55, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 64, 55, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtUsername.getText();
				password = txtPassword.getPassword();
				passwordString = new String (password);
				Boolean valid = database.validateLogin(username, passwordString);
				if(valid) {
					JOptionPane.showMessageDialog(null, "Welcome, " + username, 
							"Success", JOptionPane.INFORMATION_MESSAGE);
					boolean isAdmin = database.checkRole(username);
					if (isAdmin) {
						ManagerMenu managerMenu = new ManagerMenu();
						managerMenu.setVisible(true);
					}
					else {
						EmployeeMenu employeeMenu = new EmployeeMenu();
						employeeMenu.setVisible(true);
					}
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Username and password do not match.", 
							"Login Failed", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(110, 111, 89, 23);
		contentPane.add(btnLogin);		
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static String getPassword() {
		return passwordString;
	}
}