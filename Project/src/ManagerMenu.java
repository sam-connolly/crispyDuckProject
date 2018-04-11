import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class ManagerMenu extends JFrame {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JScrollPane scrollPane_2;
	private JTable table_2;
	private JTextField txtSearch;
	private JTextField txtSearch_2;
	private JTextField txtSearch_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JPanel panel;
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMenu window = new ManagerMenu();
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
	public ManagerMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 573);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 300, 300, 300, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 351, 35, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 100.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All Tasks", "Assigned", "Awaiting Sign Off", "All Completed", "Completed Today", "Completed This Week", "Completed This Month", "Completed This Year", "Pending Issues"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"All Tasks", "Assigned", "Awaiting Sign Off", "All Completed", "Completed Today", "Completed This Week", "Completed This Month", "Completed This Year", "Pending Issues"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 1;
		frame.getContentPane().add(comboBox_1, gbc_comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"All Tasks", "Assigned", "Awaiting Sign Off", "All Completed", "Completed Today", "Completed This Week", "Completed This Month", "Completed This Year", "Pending Issues"}));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 1;
		frame.getContentPane().add(comboBox_2, gbc_comboBox_2);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(Color.LIGHT_GRAY);
		txtSearch.setText("Search");
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 2;
		frame.getContentPane().add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		txtSearch_2 = new JTextField();
		txtSearch_2.setText("Search");
		txtSearch_2.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_txtSearch_2 = new GridBagConstraints();
		gbc_txtSearch_2.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch_2.gridx = 2;
		gbc_txtSearch_2.gridy = 2;
		frame.getContentPane().add(txtSearch_2, gbc_txtSearch_2);
		txtSearch_2.setColumns(10);
		
		txtSearch_1 = new JTextField();
		txtSearch_1.setText("Search");
		txtSearch_1.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_txtSearch_1 = new GridBagConstraints();
		gbc_txtSearch_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch_1.gridx = 3;
		gbc_txtSearch_1.gridy = 2;
		frame.getContentPane().add(txtSearch_1, gbc_txtSearch_1);
		txtSearch_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Issued", "Job", "Employee", "Urgency", "More"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(83);
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 3;
		frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
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
		scrollPane_1.setViewportView(table_1);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 3;
		gbc_scrollPane_2.gridy = 3;
		frame.getContentPane().add(scrollPane_2, gbc_scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
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
		scrollPane_2.setViewportView(table_2);
		
		panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		frame.getContentPane().add(panel, gbc_panel);
		
		JButton btnNewButton = new JButton("Add Task");
		btnNewButton.setBounds(0, 0, 101, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Print Tasks");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(109, 0, 101, 23);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 4;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		JButton btnNewButton_2 = new JButton("Sign Off");
		btnNewButton_2.setBounds(0, 0, 99, 23);
		panel_1.add(btnNewButton_2);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
	}
}
