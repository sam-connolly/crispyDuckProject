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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class ManagerMenu extends JFrame {

	private JFrame frame;
	private JTable tblActiveTasks;
	private JScrollPane scrollPane_1;
	private JTable tblToSignOff;
	private JScrollPane scrollPane_2;
	private JTable tblSignedOff;
	private JTextField txtSearch;
	private JTextField txtSearch_2;
	private JTextField txtSearch_1;
	private JComboBox<String> cmbFirstTable;
	private JComboBox<String> cmbLastTable;
	private JPanel panel;
	private JMenuBar menuBar;
	
	Database database = new Database();
	private ArrayList<Task> taskList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMenu window = new ManagerMenu();
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	 * Initialise the contents of the frame.
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
		
		DefaultTableModel model = new DefaultTableModel();
		cmbFirstTable = new JComboBox<String>();
		cmbFirstTable.setModel(new DefaultComboBoxModel<String>(new String[] {"All Uncompleted Tasks", "Assigned",
				"Pending Issues"}));
		GridBagConstraints gbc_cmbFirstTable = new GridBagConstraints();
		gbc_cmbFirstTable.insets = new Insets(0, 0, 5, 5);
		gbc_cmbFirstTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFirstTable.gridx = 1;
		gbc_cmbFirstTable.gridy = 1;
		frame.getContentPane().add(cmbFirstTable, gbc_cmbFirstTable);
		cmbFirstTable.addItemListener(new ItemListener()
		{
			//When the comboBox is changed, repopulate the table
			public void itemStateChanged(ItemEvent evt)
			{
				if (cmbFirstTable.getSelectedItem() == "All Uncompleted Tasks")
				{
					model.setRowCount(0);
					for (Task t : taskList)
					{
						if (t.getCompleted() == false)
						{
							model.addRow( new Object[] {t.getTaskName(), t.getLocation(), t.getCaretaker(),
							t.getTimeEstimateString(), t.getDateDue(), t.getPriority()});
						}
					}
					
				}
				else if (cmbFirstTable.getSelectedItem() == "Assigned")
				{
					model.setRowCount(0);
					for (Task t : taskList)
					{
						if (t.getCompleted() == false && t.getCaretaker() != "Not Assigned")
						{
							model.addRow( new Object[] {t.getTaskName(), t.getLocation(), t.getCaretaker(),
							t.getTimeEstimateString(), t.getDateDue(), t.getPriority()});
						}
					}
				}
				else
				{
					model.setRowCount(0);
					for (Task t : taskList)
					{
						if (t.getCompleted() == false && t.getIssue() == true)
						{
							model.addRow( new Object[] {t.getTaskName(), t.getLocation(), t.getCaretaker(),
							t.getTimeEstimateString(), t.getDateDue(), t.getPriority()});
						}
					}
				}
				}
			});
		
		cmbLastTable = new JComboBox<String>();
		cmbLastTable.setModel(new DefaultComboBoxModel<String>(new String[] {"All Completed", "Completed Today",
				"Completed This Week", "Completed This Month", "Completed This Year"}));
		GridBagConstraints gbc_cmbLastTable = new GridBagConstraints();
		gbc_cmbLastTable.insets = new Insets(0, 0, 5, 5);
		gbc_cmbLastTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbLastTable.gridx = 3;
		gbc_cmbLastTable.gridy = 1;
		frame.getContentPane().add(cmbLastTable, gbc_cmbLastTable);
		
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
		
		JScrollPane pnlActiveTasks = new JScrollPane();
		GridBagConstraints gbc_pnlActiveTasks = new GridBagConstraints();
		gbc_pnlActiveTasks.insets = new Insets(0, 0, 5, 5);
		gbc_pnlActiveTasks.fill = GridBagConstraints.BOTH;
		gbc_pnlActiveTasks.gridx = 1;
		gbc_pnlActiveTasks.gridy = 3;
		frame.getContentPane().add(pnlActiveTasks, gbc_pnlActiveTasks);
		
		tblActiveTasks = new JTable(model);

		model.addColumn("Name");
		model.addColumn("Location");
		model.addColumn("Caretaker");
		model.addColumn("Time Allowed");
		model.addColumn("Date Due");
		model.addColumn("Priority");
		
		TaskList activeTasks = database.getTasks();
		taskList = new ArrayList<Task>(activeTasks.getTaskList());
		
		for (Task t : taskList)
		{
			if (t.getCompleted() == false)
			{
				model.addRow( new Object[] {t.getTaskName(), t.getLocation(), t.getCaretaker(),
				t.getTimeEstimateString(), t.getDateDue(), t.getPriority()});
			}
		}
		pnlActiveTasks.setViewportView(tblActiveTasks);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 3;
		frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		DefaultTableModel model2 = new DefaultTableModel();
		tblToSignOff = new JTable(model2);
		
		model2.addColumn("Name");
		model2.addColumn("Location");
		model2.addColumn("Caretaker");
		model2.addColumn("Time Allowed");
		model2.addColumn("Date Due");
		
		for (Task t : taskList)
		{
			if (t.getCompleted() == true && t.getSignedOff() == false)
			{
				model2.addRow( new Object[] {t.getTaskName(), t.getLocation(), t.getCaretaker(),
				t.getTimeEstimateString(), t.getDateDue()});
			}
		}
		scrollPane_1.setViewportView(tblToSignOff);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 3;
		gbc_scrollPane_2.gridy = 3;
		frame.getContentPane().add(scrollPane_2, gbc_scrollPane_2);
		
		DefaultTableModel model3 = new DefaultTableModel();
		tblSignedOff = new JTable(model3);
		model3.addColumn("Name");
		model3.addColumn("Location");
		model3.addColumn("Caretaker");
		model3.addColumn("Date Signed Off");
		
		for (Task t : taskList)
		{
			if (t.getSignedOff() == true)
			{
				model3.addRow( new Object[] {t.getTaskName(), t.getLocation(), t.getCaretaker(),
				t.getSignedOffOn()});
			}
		}
		scrollPane_2.setViewportView(tblSignedOff);
		
		panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		frame.getContentPane().add(panel, gbc_panel);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(0, 0, 101, 23);
		btnAddTask.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				TaskCreationUI addTask = new TaskCreationUI();
				addTask.setVisible(true);
				dispose();
			}
		});
		panel.add(btnAddTask);
		
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
