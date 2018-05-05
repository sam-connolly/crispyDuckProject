import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.util.ArrayList;
import javax.swing.JSeparator;

public class UpdateUserAdminUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtPassword;
	private JTextField txtForename;
	private JTextField txtSurname;
	private String password;
	private String username, passwordString, roleString, forename, surname;
	private boolean admin;
	Database database = new Database();
	private JTextField txtEfficiency;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUserAdminUI frame = new UpdateUserAdminUI();
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
	public UpdateUserAdminUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 337, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Combo box to allow Admin to select chosen user
		JComboBox<String> cmbUsername = new JComboBox<String>();
		cmbUsername.setSize(153, 22);
		cmbUsername.setLocation(139, 20);
		//Default item
		cmbUsername.addItem("Select a User");
		contentPane.add(cmbUsername);
		
		JLabel lblUsername = new JLabel("Password");
		lblUsername.setBounds(23, 56, 59, 14);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("Username");
		label.setBounds(23, 24, 59, 14);
		contentPane.add(label);
		
		//ArrayList to store users returned from the database
		ArrayList<String> users = new ArrayList<String>();
		//Populate ArrayList with contents of database
		users = database.getUsernames();
		//Loop through arraylist adding items to ComboBox
		for (int i = 0; i < users.size(); i++)
		{
			cmbUsername.addItem(users.get(i));
		}
		
		txtPassword = new JTextField();
		txtPassword.setBounds(139, 53, 153, 20);
		contentPane.add(txtPassword);
		
		txtForename = new JTextField();
		txtForename.setColumns(10);
		txtForename.setBounds(139, 117, 153, 20);
		contentPane.add(txtForename);
		
		JLabel lblForename = new JLabel("Forename");
		lblForename.setBounds(23, 120, 59, 14);
		contentPane.add(lblForename);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(139, 149, 153, 20);
		contentPane.add(txtSurname);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(23, 152, 59, 14);
		contentPane.add(lblSurname);
		
		//ComboBox to chose user role
		JComboBox<String> cmbRole = new JComboBox<String>();
		cmbRole.setMaximumRowCount(2);
		cmbRole.setBounds(139, 85, 153, 20);
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Admin", "User"}));
		contentPane.add(cmbRole);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(23, 88, 59, 14);
		contentPane.add(lblRole);

		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get username value
				username = cmbUsername.getSelectedItem().toString();
			    int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete "
			    		+ username + "?", "Confirm",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			    if (response == JOptionPane.YES_OPTION) {
			    	try {
						boolean deleteUser = database.deleteUser(username);
						System.out.println(deleteUser);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    } 
			}
		});
		btnDeleteUser.setBounds(103, 342, 116, 23);
		contentPane.add(btnDeleteUser);
		
		JComboBox<String> cmbCat = new JComboBox<String>();
		cmbCat.setBounds(139, 223, 151, 22);
		contentPane.add(cmbCat);
		ArrayList<String> categories = new ArrayList<String>();
		categories = database.getCategories();
		for (int i=0; i<categories.size(); i++)
		{
			cmbCat.addItem(categories.get(i));
		}
		
		JComboBox<String> cmbPrefLvl = new JComboBox<String>();
		cmbPrefLvl.setBounds(139, 256, 151, 20);
		contentPane.add(cmbPrefLvl);
		for (int i=0; i<11; i++)
		{
			cmbPrefLvl.addItem(Integer.toString(i));
		}
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(23, 227, 59, 14);
		contentPane.add(lblCategory);
		
		JLabel lblPreferenceLevel = new JLabel("Preference Level");
		lblPreferenceLevel.setBounds(23, 259, 59, 14);
		contentPane.add(lblPreferenceLevel);
		
		cmbUsername.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent evt)
			{
				String cat=cmbCat.getSelectedItem().toString();
				username = cmbUsername.getSelectedItem().toString();
				txtPassword.setText(database.getPassword(username));
				txtForename.setText(database.getForename(username));
				txtSurname.setText(database.getSurname(username));
				try {
					txtEfficiency.setText(String.valueOf(database.getEfficiency(username, cat)));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (database.checkRole(username)==true) {
					cmbRole.setSelectedItem("Admin");
				}
				else {
					cmbRole.setSelectedItem("User");
				}
				int preferenceLevel = 0;
				try {
					preferenceLevel = database.getPreferenceLevel(username, cat);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cmbPrefLvl.setSelectedItem(Integer.toString(preferenceLevel));
			}
		});
		
		cmbCat.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent evt)
			{
				String username = cmbUsername.getSelectedItem().toString();
				String cat=cmbCat.getSelectedItem().toString();
				System.out.println(username + cat);
				int preferenceLevel = 0;
				try {
					preferenceLevel = database.getPreferenceLevel(username, cat);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					txtEfficiency.setText(String.valueOf(database.getEfficiency(username, cat)));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cmbPrefLvl.setSelectedItem(Integer.toString(preferenceLevel));
			}
		});
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(103, 376, 116, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get values from textboxes
				username = cmbUsername.getSelectedItem().toString();
				roleString = cmbRole.getSelectedItem().toString();
				password = txtPassword.getText();
				forename = txtForename.getText();
				surname = txtSurname.getText();
				String cat = cmbCat.getSelectedItem().toString();
				int preference = Integer.parseInt(cmbPrefLvl.getSelectedItem().toString());
				//Convert password to String
				passwordString = new String (password);
				//Check if user is admin
				if (roleString.equals("Admin")) { //User is Admin
					admin=true;
				}
				else { //User is not Admin
					admin=false;
				}	
				//Attempt to update User 
			    try {
					boolean updateUser = database.updateUserAdmin(username, passwordString,
							admin, forename, surname);
					System.out.println(updateUser);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    try {
					boolean updatePreference = database.updateUserPreference(username, cat,
							preference);
					System.out.println(updatePreference);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnSubmit);
		
		JLabel lblEfficiency = new JLabel("Efficiency");
		lblEfficiency.setBounds(23, 290, 59, 14);
		contentPane.add(lblEfficiency);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 193, 301, 2);
		contentPane.add(separator);
		
		txtEfficiency = new JTextField();
		txtEfficiency.setEditable(false);
		txtEfficiency.setBounds(139, 287, 151, 20);
		contentPane.add(txtEfficiency);
	}
}
