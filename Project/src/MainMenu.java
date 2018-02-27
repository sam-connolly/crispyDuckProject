import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenu extends JFrame {
	private JTextField txtWelcomename;
	private String name;
	public MainMenu() {
		setMinimumSize(new Dimension(200, 400));
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		
		
		txtWelcomename = new JTextField();
	//	txtWelcomename.setHorizontalAlignment(SwingConstants.NORTH);
		panel.add(txtWelcomename);
		txtWelcomename.setText("");
		txtWelcomename.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 20, 10));
		
		JButton btnNewButton = new JButton("View my Tasks ");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View my profile");
		panel_1.add(btnNewButton_1);
		
		JButton btnAddTaskm = new JButton("Add task (M)");
		btnAddTaskm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TaskCreationUI().setVisible(true);
			}
		});
		panel_1.add(btnAddTaskm);
		
		JButton btnAddUserm = new JButton("Add user (M)");
		panel_1.add(btnAddUserm);
		
		JButton btnEditTaskm = new JButton("Edit Task (M)");
		panel_1.add(btnEditTaskm);
		
		JButton btnEditUserm = new JButton("Edit User (M)");
		panel_1.add(btnEditUserm);
		
		JButton btnViewReportsm = new JButton("View reports (M)");
		btnViewReportsm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReportUI().setVisible(true);
			}
		});
		panel_1.add(btnViewReportsm);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		name = getEmpName(3);
		JLabel lblWelcomename = new JLabel("Welcome " + name);
		panel_2.add(lblWelcomename);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Log out");
		
		panel_3.add(btnNewButton_2);
	}
	// this will conn  to db
	private String getEmpName(int id) {
		Database db = new Database();
		Connection con = db.getConn();
		PreparedStatement sqlgetname;
		try {
			sqlgetname = con.prepareStatement("SELECT "
					+ "fName FROM User where ?");
			sqlgetname.setInt(1, id);
			ResultSet rs = sqlgetname.executeQuery();
			String name = rs.getString("fName");
			return name;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return name;
		
	}
	public static void main(String[] args) {
		MainMenu app = new MainMenu();
		app.setVisible(true);
	}
}