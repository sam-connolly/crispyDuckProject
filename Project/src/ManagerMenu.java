import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dialog;
import javax.swing.JLabel;
import java.awt.Font;

public class ManagerMenu extends JFrame{

	private JFrame frame;
	private JTable tblActiveTasks;
	private JScrollPane pnlAllocated;
	private JTable tblAllocated;
	private JScrollPane pnlCompleted;
	private JTable tblSignedOff;
	private JComboBox<String> cmbFirstTable;
	private JComboBox<String> cmbLastTable;
	private JPanel panel;
	private JMenuBar menuBar;
	private TaskList allTasks = new TaskList();
	private Database database = new Database();
	private UserList allUsers = new UserList();
	private String username;
	private ArrayList<Task> taskList;
	private JTable tblUnallocated;
	private JTable tblCompleted;
	private JButton btnNewUser;
	private JButton btnLogout;
	private JComboBox inProgressComboBox;
	private JLabel lblUnallocated;
	private JLabel lblInProgress;
	private JLabel lblCompleted;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMenu window = new ManagerMenu(args[0]);
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
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public ManagerMenu(String username) throws ParseException, SQLException 
	{
		this.username=username;
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	private void initialize() throws ParseException, SQLException 
	{		
		allTasks = database.getTasks();
		allUsers = database.getUsers();
		
	
		frame = new JFrame();
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setBounds(100, 100, 873, 573);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 300, 300, 300, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 351, 35, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 100.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnAllocate = new JButton("Allocate Task");
		
		DefaultTableModel model = new DefaultTableModel();
		cmbFirstTable = new JComboBox<String>();
		cmbFirstTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbFirstTable.getSelectedItem() == "All Unallocated") {
					try {
						tblUnallocated.setModel(allTasks.getAllUnallocated("All Unallocated"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(cmbFirstTable.getSelectedItem() == "Due Allocation") {
					try {
						tblUnallocated.setModel(allTasks.getAllUnallocated("Due Allocation"));
						btnAllocate.setEnabled(true);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(cmbFirstTable.getSelectedItem() == "Not Due Allocation") {
					try {
						tblUnallocated.setModel(allTasks.getAllUnallocated("Not Due Allocation"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		lblUnallocated = new JLabel("Unallocated");
		lblUnallocated.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblUnallocated = new GridBagConstraints();
		gbc_lblUnallocated.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnallocated.gridx = 1;
		gbc_lblUnallocated.gridy = 0;
		frame.getContentPane().add(lblUnallocated, gbc_lblUnallocated);
		
		lblInProgress = new JLabel("In Progress");
		lblInProgress.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblInProgress = new GridBagConstraints();
		gbc_lblInProgress.insets = new Insets(0, 0, 5, 5);
		gbc_lblInProgress.gridx = 2;
		gbc_lblInProgress.gridy = 0;
		frame.getContentPane().add(lblInProgress, gbc_lblInProgress);
		
		lblCompleted = new JLabel("Completed");
		lblCompleted.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblCompleted = new GridBagConstraints();
		gbc_lblCompleted.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompleted.gridx = 3;
		gbc_lblCompleted.gridy = 0;
		frame.getContentPane().add(lblCompleted, gbc_lblCompleted);

		cmbFirstTable.setModel(new DefaultComboBoxModel(new String[] {"All Unallocated", "Due Allocation", "Not Due Allocation"}));
		GridBagConstraints gbc_cmbFirstTable = new GridBagConstraints();
		gbc_cmbFirstTable.insets = new Insets(0, 0, 5, 5);
		gbc_cmbFirstTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFirstTable.gridx = 1;
		gbc_cmbFirstTable.gridy = 1;
		frame.getContentPane().add(cmbFirstTable, gbc_cmbFirstTable);
		
		inProgressComboBox = allUsers.getUsersComboBox("in progress");
		inProgressComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inProgressComboBox.getSelectedItem() == "All Allocated") {
					try {
						tblAllocated.setModel(allTasks.getAllAllocated());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else  {
					tblAllocated.setModel(allTasks.getAllocatedToCaretaker(inProgressComboBox.getSelectedItem().toString()));
					btnAllocate.setEnabled(true);
				}
			}
		});
		GridBagConstraints gbc_inProgressComboBox = new GridBagConstraints();
		gbc_inProgressComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_inProgressComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_inProgressComboBox.gridx = 2;
		gbc_inProgressComboBox.gridy = 1;
		frame.getContentPane().add(inProgressComboBox, gbc_inProgressComboBox);
		
		cmbLastTable = allUsers.getUsersComboBox("completed");
		cmbLastTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbLastTable.getSelectedItem() == "All Completed") {
					try {
						tblCompleted.setModel(allTasks.getAllCompleted());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else  {
					tblCompleted.setModel(allTasks.getCompletedByCaretaker(cmbLastTable.getSelectedItem().toString()));
					btnAllocate.setEnabled(true);
				}
			}
		});
		GridBagConstraints gbc_cmbLastTable = new GridBagConstraints();
		gbc_cmbLastTable.insets = new Insets(0, 0, 5, 5);
		gbc_cmbLastTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbLastTable.gridx = 3;
		gbc_cmbLastTable.gridy = 1;
		frame.getContentPane().add(cmbLastTable, gbc_cmbLastTable);
		
		JScrollPane pnlUnallocated = new JScrollPane();
		GridBagConstraints gbc_pnlUnallocated = new GridBagConstraints();
		gbc_pnlUnallocated.insets = new Insets(0, 0, 5, 5);
		gbc_pnlUnallocated.fill = GridBagConstraints.BOTH;
		gbc_pnlUnallocated.gridx = 1;
		gbc_pnlUnallocated.gridy = 2;
		frame.getContentPane().add(pnlUnallocated, gbc_pnlUnallocated);
		
		tblUnallocated = new JTable(allTasks.getAllUnallocated("All Unallocated"));
		tblUnallocated.setAutoCreateRowSorter(true);
		pnlUnallocated.setViewportView(tblUnallocated);
		
		tblUnallocated.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e) 
				{
					if (e.getClickCount() == 2)
					{
						JTable target = (JTable)e.getSource();
						int row = target.getSelectedRow();
						int taskID = (Integer) tblUnallocated.getValueAt(row, 0);
						
						EditTaskUI editTask = new EditTaskUI(taskID, username, "Task", -1);
						editTask.setVisible(true);
						setVisible(false);
						frame.dispose();
					}
				}
			});
		
		pnlAllocated = new JScrollPane();
		
		GridBagConstraints gbc_pnlAllocated = new GridBagConstraints();
		gbc_pnlAllocated.insets = new Insets(0, 0, 5, 5);
		gbc_pnlAllocated.fill = GridBagConstraints.BOTH;
		gbc_pnlAllocated.gridx = 2;
		gbc_pnlAllocated.gridy = 2;
		frame.getContentPane().add(pnlAllocated, gbc_pnlAllocated);
		
		DefaultTableModel allocatedModel = allTasks.getAllAllocated();
		tblAllocated = new JTable(allocatedModel);
		tblAllocated.setAutoCreateRowSorter(true);
		pnlAllocated.setViewportView(tblAllocated);
		
		tblAllocated.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() == 2)
				{
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int jobID = (Integer) tblAllocated.getValueAt(row, 0);
					
					Task job = database.getJob(jobID);
					int taskID = job.getTaskID();
					EditTaskUI editTask = new EditTaskUI(taskID, username, "Job", jobID);
					editTask.setVisible(true);
					setVisible(false);
					frame.dispose();
				}
			}
		});
		
		pnlCompleted = new JScrollPane();
		GridBagConstraints gbc_pnlCompleted = new GridBagConstraints();
		gbc_pnlCompleted.insets = new Insets(0, 0, 5, 5);
		gbc_pnlCompleted.fill = GridBagConstraints.BOTH;
		gbc_pnlCompleted.gridx = 3;
		gbc_pnlCompleted.gridy = 2;
		frame.getContentPane().add(pnlCompleted, gbc_pnlCompleted);
		
		DefaultTableModel model3 = new DefaultTableModel();
		pnlCompleted.setViewportView(tblSignedOff);
		
		DefaultTableModel completedModel = allTasks.getAllCompleted();
		tblCompleted = new JTable(completedModel);
		tblCompleted.setAutoCreateRowSorter(true);
		pnlCompleted.setViewportView(tblCompleted);
		
		tblCompleted.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() == 2)
				{
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int jobID = (Integer) tblCompleted.getValueAt(row, 0);
					
					Task job = database.getJob(jobID);
					int taskID = job.getTaskID();
					EditTaskUI editTask = new EditTaskUI(taskID, username, "Job", jobID);
					editTask.setVisible(true);
					frame.dispose();
				}
			}
		});
		
		panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		frame.getContentPane().add(panel, gbc_panel);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(0, 0, 101, 23);
		btnAddTask.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				TaskCreationUI addTask = new TaskCreationUI(username);
				addTask.setVisible(true);
				setVisible(false);
				frame.dispose();
			}
		});
		panel.add(btnAddTask);
		
		btnAllocate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel toAllocateModel = new DefaultTableModel() {
				    @Override
				    public boolean isCellEditable(int row, int column) {
				       return false;
				    }
				};
				
				TableModel selectedModel = tblUnallocated.getModel();
				Object row[] = new Object[4];
				
				toAllocateModel.addColumn("Task ID"); 
				toAllocateModel.addColumn("Task Name"); 
				toAllocateModel.addColumn("Task Category");
				toAllocateModel.addColumn("Caretaker"); 
				
				int indexes[] = tblUnallocated.getSelectedRows();
				
				for(int i : indexes) {
					System.out.println(selectedModel.getValueAt(i, 2));
					if(selectedModel.getValueAt(i, 2).equals("DUE ALLOCATION")) {
						int taskID = Integer.parseInt( selectedModel.getValueAt(i, 0).toString() );
						System.out.println(taskID);
						String taskCat = allTasks.getFirstTaskWithTaskID(taskID).getTaskCat();
						row[0] = selectedModel.getValueAt(i, 0);
						row[1] = selectedModel.getValueAt(i, 1);
						row[2] = taskCat;
						row[3] = allUsers.findToAssign(taskCat, allTasks);
						
						toAllocateModel.addRow(row);
					}
				}
				
				try {
					AllocationMenu allocationMenu = new AllocationMenu(frame, username);
					allocationMenu.setToAllocateModel(toAllocateModel);
					allocationMenu.setVisible(true);
				} catch (ParseException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAllocate.setBounds(111, 0, 126, 23);
		panel.add(btnAllocate);
		
		JButton btnManageUser = new JButton("Manage User");
		btnManageUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateUserAdminUI updateUser = new UpdateUserAdminUI();
				updateUser.setVisible(true);
			}
		});
		btnManageUser.setBounds(247, 0, 126, 23);
		panel.add(btnManageUser);
		
		btnNewUser = new JButton("New User");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserUI newUser = new NewUserUI();
				newUser.setVisible(true);
			}
		});
		btnNewUser.setBounds(383, 0, 126, 23);
		panel.add(btnNewUser);
		
		JButton btnNewButton = new JButton("Sign Off Tasks");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel selectedModel = (DefaultTableModel) tblCompleted.getModel();
				int indexes[] = tblCompleted.getSelectedRows();
				Object[] row = new Object[5];
				
				for(int i : indexes) {
					int jobID = Integer.parseInt( selectedModel.getValueAt(i, 0).toString() );
					System.out.println(jobID);
					Task taskToSignOff = allTasks.getTaskWithJobID(jobID);
					if (taskToSignOff.getSignedOff() == false) {
						try {
							taskToSignOff.signOffTask();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						selectedModel.setValueAt("Yes", i, 3);
						selectedModel.setValueAt(taskToSignOff.getSignedOffOn(), i, 4);
					}
				}
				tblCompleted.setModel(selectedModel);
			}
		});
		btnNewButton.setBounds(519, 0, 126, 23);
		panel.add(btnNewButton);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI login = new LoginUI();
				login.setVisible(true);
				frame.dispose();
			}
		});
		menuBar.add(btnLogout);
		frame.setVisible(true);
	}
}