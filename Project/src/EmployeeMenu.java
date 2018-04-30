import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import java.awt.Color;

public class EmployeeMenu extends JFrame{

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable inProgress;
	private JScrollPane scrollPane_1;
	private JTable completed;
	private JButton completeButton;
	private JButton uncompleteButton;
	private JButton issueButon;
	private JMenuBar menuBar;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JComboBox inProgressSort;
	private JComboBox completedSort;
	private JTextField inProgressSearch;
	private JTextField completedSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMenu window = new EmployeeMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Database database = new Database();

		TaskList allActiveTasks = new TaskList();
		
		allActiveTasks = database.getActiveTasks();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1169, 686);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 400, 0, 0, 0, 400, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 6, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		inProgressSort = new JComboBox();
		GridBagConstraints gbc_inProgressSort = new GridBagConstraints();
		gbc_inProgressSort.insets = new Insets(0, 0, 5, 5);
		gbc_inProgressSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_inProgressSort.gridx = 1;
		gbc_inProgressSort.gridy = 1;
		frame.getContentPane().add(inProgressSort, gbc_inProgressSort);
		
		completedSort = new JComboBox();
		GridBagConstraints gbc_completedSort = new GridBagConstraints();
		gbc_completedSort.insets = new Insets(0, 0, 5, 5);
		gbc_completedSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_completedSort.gridx = 5;
		gbc_completedSort.gridy = 1;
		frame.getContentPane().add(completedSort, gbc_completedSort);
		
		inProgressSearch = new JTextField();
		inProgressSearch.setForeground(Color.LIGHT_GRAY);
		inProgressSearch.setText("Search");
		GridBagConstraints gbc_inProgressSearch = new GridBagConstraints();
		gbc_inProgressSearch.insets = new Insets(0, 0, 5, 5);
		gbc_inProgressSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_inProgressSearch.gridx = 1;
		gbc_inProgressSearch.gridy = 2;
		frame.getContentPane().add(inProgressSearch, gbc_inProgressSearch);
		inProgressSearch.setColumns(10);
		
		completedSearch = new JTextField();
		completedSearch.setForeground(Color.LIGHT_GRAY);
		completedSearch.setText("Search");
		GridBagConstraints gbc_completedSearch = new GridBagConstraints();
		gbc_completedSearch.insets = new Insets(0, 0, 5, 5);
		gbc_completedSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_completedSearch.gridx = 5;
		gbc_completedSearch.gridy = 2;
		frame.getContentPane().add(completedSearch, gbc_completedSearch);
		completedSearch.setColumns(10);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		String[] columnNames = {"Caretaker", "Date Issued", "Date Due"};

		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		
		for (int i = 0; i < allActiveTasks.getSize(); i++) {
			ActiveTask taskToDisplay = allActiveTasks.retrieveActiveTask(i);
			
			Vector row = new Vector();
			row.add(taskToDisplay.getCaretaker());
			row.add(taskToDisplay.getDateIssued());
			row.add(taskToDisplay.getDateDue());
			
			model.addRow(row);
			
		}
		
		inProgress = new JTable(model);
		
		scrollPane.setViewportView(inProgress);
		
		completeButton = new JButton("Complete");
		GridBagConstraints gbc_completeButton = new GridBagConstraints();
		gbc_completeButton.insets = new Insets(0, 0, 5, 5);
		gbc_completeButton.gridx = 2;
		gbc_completeButton.gridy = 3;
		frame.getContentPane().add(completeButton, gbc_completeButton);
		
		uncompleteButton = new JButton("Uncomplete");
		GridBagConstraints gbc_uncompleteButton = new GridBagConstraints();
		gbc_uncompleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_uncompleteButton.gridx = 4;
		gbc_uncompleteButton.gridy = 3;
		frame.getContentPane().add(uncompleteButton, gbc_uncompleteButton);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 5;
		gbc_scrollPane_1.gridy = 3;
		frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		completed = new JTable();
		completed.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(completed);
		
		issueButon = new JButton("Issue");
		GridBagConstraints gbc_issueButon = new GridBagConstraints();
		gbc_issueButon.insets = new Insets(0, 0, 5, 5);
		gbc_issueButon.gridx = 3;
		gbc_issueButon.gridy = 4;
		frame.getContentPane().add(issueButon, gbc_issueButon);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		btnNewButton_3 = new JButton("Logout");
		menuBar.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Account");
		menuBar.add(btnNewButton_4);
	}
}
