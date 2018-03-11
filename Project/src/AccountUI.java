import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AccountUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPasswordNew;
	private JPasswordField txtPasswordOld;
	private JTextField txtFName;
	private JTextField txtSName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountUI frame = new AccountUI();
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
	public AccountUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 181, 242, 2);
		contentPane.add(separator);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(99, 32, 145, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(24, 35, 48, 14);
		contentPane.add(lblUsername);
		
		txtPasswordNew = new JPasswordField();
		txtPasswordNew.setBounds(99, 100, 145, 20);
		contentPane.add(txtPasswordNew);
		
		txtPasswordOld = new JPasswordField();
		txtPasswordOld.setBounds(99, 73, 145, 20);
		contentPane.add(txtPasswordOld);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setBounds(24, 76, 65, 14);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(24, 103, 71, 14);
		contentPane.add(lblNewPassword);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(75, 147, 119, 23);
		contentPane.add(btnChangePassword);
		
		txtFName = new JTextField();
		txtFName.setBounds(99, 212, 145, 20);
		contentPane.add(txtFName);
		txtFName.setColumns(10);
		
		txtSName = new JTextField();
		txtSName.setColumns(10);
		txtSName.setBounds(99, 241, 145, 20);
		contentPane.add(txtSName);
		
		JLabel lblForename = new JLabel("Forename");
		lblForename.setBounds(24, 215, 65, 14);
		contentPane.add(lblForename);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(24, 244, 65, 14);
		contentPane.add(lblSurname);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(75, 292, 119, 23);
		contentPane.add(btnDone);
	}
}
