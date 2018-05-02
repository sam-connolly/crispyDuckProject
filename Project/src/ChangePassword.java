import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePassword extends JDialog implements ActionListener {
	private String username, oldPasswordString, newPasswordString;
	private char[] oldPassword, newPassword;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	//Create Database object for connection to database
	Database database = new Database();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String user) {
		try {
			ChangePassword dialog = new ChangePassword(user);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChangePassword(String user) {
		setResizable(false);
		username = user;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Old Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(48, 75, 74, 14);
		contentPanel.add(lblNewLabel_1);

		txtOldPassword = new JPasswordField();
		txtOldPassword.setBounds(162, 71, 185, 22);
		contentPanel.add(txtOldPassword);
		txtOldPassword.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(48, 127, 74, 14);
		contentPanel.add(lblNewLabel_2);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(164, 123, 183, 22);
		contentPanel.add(txtNewPassword);
		txtNewPassword.setColumns(10);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setBackground(new Color(240, 240, 240));
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		//OK button pressed
		if (action.equals("OK")) {
			//Get values from user input
			oldPassword = txtOldPassword.getPassword();
			newPassword = txtNewPassword.getPassword();
			//Convert password inputs to Strings
			oldPasswordString = new String (oldPassword);
			newPasswordString = new String (newPassword);
			//Check old password is correct before allowing to change
			boolean valid = database.validateLogin(username, oldPasswordString);
			//If valid username password combo
			if(valid) {
				try {
					//Attempt update using new password
					boolean updated = database.updatePassword(username, newPasswordString);
					//If update successful
					if (updated) {
						JOptionPane.showMessageDialog(null, "Password Updated", 
								"Update Successful", JOptionPane.INFORMATION_MESSAGE);
						this.setVisible(false);
					}
					//If update failed
					else {
						JOptionPane.showMessageDialog(null, "Update Failed", 
								"Update Failed", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//If invalid username password combo
			else {
				JOptionPane.showMessageDialog(null, "Old password is incorrect", 
						"Update Failed", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		//Cancel button pressed
		if (action.equals("Cancel")) {			
			this.setVisible(false);	
		}
	}
}
