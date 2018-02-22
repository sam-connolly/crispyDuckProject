import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JToolBar;

public class MainMenu extends JFrame {
	private JTextField txtWelcomename;
	public MainMenu() {
		setMinimumSize(new Dimension(200, 400));
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		
		
		txtWelcomename = new JTextField();
	//	txtWelcomename.setHorizontalAlignment(SwingConstants.NORTH);
		panel.add(txtWelcomename);
		txtWelcomename.setText("Welcome %NAME%");
		txtWelcomename.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 20, 10));
		
		JButton btnNewButton = new JButton("View my Tasks ");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View my profile\r\n\t");
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
		
		JLabel lblWelcomename = new JLabel("Welcome %name%");
		panel_2.add(lblWelcomename);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Log out");
		
		panel_3.add(btnNewButton_2);
	}
	public static void main(String[] args) {
		MainMenu app = new MainMenu();
		app.setVisible(true);
	}
}