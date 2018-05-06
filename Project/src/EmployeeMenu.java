import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import java.awt.Color;

public class EmployeeMenu extends JFrame{

	private JFrame frame;
	private JScrollPane inProgressScrollPane;
	private JTable tblInProgress;
	private JScrollPane completedScrollPane;
	private JTable tblCompleted;
	private JButton completeButton;
	private JButton uncompleteButton;
	private JButton issueButon;
	private JMenuBar menuBar;
	private JButton btnLogout;
	private JButton btnAccount;
	private JComboBox inProgressSort;
	private JComboBox completedSort;
	private JTextField inProgressSearch;
	private JTextField completedSearch;
	
	private String username;
	
	private Database database = new Database();
	private TaskList allTasks;
	private UserList allUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMenu window = new EmployeeMenu(args[1]);
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
	public EmployeeMenu(String username) {
		this.username=username;
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		allTasks = database.getTasks();
		allUsers = database.getUsers();
		
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
		
		inProgressScrollPane = new JScrollPane();
		GridBagConstraints gbc_inProgressScrollPane = new GridBagConstraints();
		gbc_inProgressScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_inProgressScrollPane.fill = GridBagConstraints.BOTH;
		gbc_inProgressScrollPane.gridx = 1;
		gbc_inProgressScrollPane.gridy = 3;
		frame.getContentPane().add(inProgressScrollPane, gbc_inProgressScrollPane);
		
		tblInProgress = new JTable(allTasks.getAllocatedToCaretaker(username));
		
		inProgressScrollPane.setViewportView(tblInProgress);
		
		completeButton = new JButton("Complete");
		completeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel selectedModel = (DefaultTableModel) tblInProgress.getModel();
				DefaultTableModel completedModel = (DefaultTableModel) tblCompleted.getModel();
				int indexes[] = tblInProgress.getSelectedRows();
				Object[] row = new Object[5];
				
				for(int i : indexes) {
					int jobID = Integer.parseInt( selectedModel.getValueAt(i, 0).toString() );
					System.out.println(jobID);
					Task taskToComplete = allTasks.getTaskWithJobID(jobID);
					try {
						taskToComplete.completeTask();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					row[0] = taskToComplete.getJobID();
					row[1] = taskToComplete.getTaskName();
					row[2] = taskToComplete.getLocation();
					row[3] = taskToComplete.getSignedOff();
					row[4] = taskToComplete.getSignedOffOn();
					
					completedModel.addRow(row);
					tblCompleted.setModel(completedModel);
		
				}
				for(int i : indexes) {
					selectedModel.removeRow(i - 1);
				}
				tblInProgress.setModel(selectedModel);	
			}
		});
		GridBagConstraints gbc_completeButton = new GridBagConstraints();
		gbc_completeButton.insets = new Insets(0, 0, 5, 5);
		gbc_completeButton.gridx = 2;
		gbc_completeButton.gridy = 3;
		frame.getContentPane().add(completeButton, gbc_completeButton);
		
		uncompleteButton = new JButton("Uncomplete");
		uncompleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel selectedModel = (DefaultTableModel) tblCompleted.getModel();
				DefaultTableModel inProgressModel = (DefaultTableModel) tblInProgress.getModel();
				int indexes[] = tblCompleted.getSelectedRows();
				Object[] row = new Object[5];
				
				for(int i : indexes) {
					int jobID = Integer.parseInt( selectedModel.getValueAt(i, 0).toString() );
					System.out.println(jobID);
					Task taskToUncomplete = allTasks.getTaskWithJobID(jobID);
					//System.out.println(taskToComplete.getJobID());
					try {
						taskToUncomplete.uncompleteTask();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					row[0] = taskToUncomplete.getJobID();
					row[1] = taskToUncomplete.getTaskName();
					row[2] = taskToUncomplete.getLocation();
					row[3] = taskToUncomplete.getPriority();
					row[4] = taskToUncomplete.getDateDue();
					
					inProgressModel.addRow(row);
					tblInProgress.setModel(inProgressModel);
					
					selectedModel.removeRow(i);
					tblCompleted.setModel(selectedModel);
					
				}
			}
		});

		GridBagConstraints gbc_uncompleteButton = new GridBagConstraints();
		gbc_uncompleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_uncompleteButton.gridx = 4;
		gbc_uncompleteButton.gridy = 3;
		frame.getContentPane().add(uncompleteButton, gbc_uncompleteButton);
		
		completedScrollPane = new JScrollPane();
		GridBagConstraints gbc_completedScrollPane = new GridBagConstraints();
		gbc_completedScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_completedScrollPane.fill = GridBagConstraints.BOTH;
		gbc_completedScrollPane.gridx = 5;
		gbc_completedScrollPane.gridy = 3;
		frame.getContentPane().add(completedScrollPane, gbc_completedScrollPane);
		
		tblCompleted = new JTable(allTasks.getCompletedByCaretaker(username));
		
		completedScrollPane.setViewportView(tblCompleted);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		 btnLogout = new JButton("Logout");
		    btnLogout.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        frame.dispose();
		      }
		    });
		menuBar.add(btnLogout);
		 
		
		btnAccount = new JButton("Account");		 
	    btnAccount.addActionListener(new ActionListener() {	 
	      public void actionPerformed(ActionEvent e) {	 
	        UpdateUserUI updateUser = new UpdateUserUI(username);	 
	        updateUser.setVisible(true);	 
	      }	 
	    });
	    menuBar.add(btnAccount);
	    this.frame.setVisible(true);
	}
	
	public void setUser(String username) { 
		this.username = username;
		tblInProgress.setModel(allTasks.getAllocatedToCaretaker(" "));
	}
}
