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
import javax.swing.WindowConstants;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

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
	
	private String username;
	
	private Database database = new Database();
	private TaskList allTasks;
	private UserList allUsers;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField txtHours;
	private JLabel lblNewLabel_1;
	private JTextField txtMinutes;
	private JLabel lblNewLabel_2;
	private JScrollPane descriptionScrollPane;
	private JTextArea taskDescription;
	private JLabel lblToDo;
	private JLabel lblCompleted;

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
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setBounds(100, 100, 1169, 686);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 476, 0, 358, 0, 400, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 5, 5, 501, 60, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		lblToDo = new JLabel("To Do");
		lblToDo.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblToDo = new GridBagConstraints();
		gbc_lblToDo.insets = new Insets(0, 0, 5, 5);
		gbc_lblToDo.gridx = 1;
		gbc_lblToDo.gridy = 1;
		frame.getContentPane().add(lblToDo, gbc_lblToDo);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		lblNewLabel = new JLabel("Time taken to complete selected tasks");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel);
		
		lblCompleted = new JLabel("Completed");
		lblCompleted.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblCompleted = new GridBagConstraints();
		gbc_lblCompleted.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompleted.gridx = 5;
		gbc_lblCompleted.gridy = 1;
		frame.getContentPane().add(lblCompleted, gbc_lblCompleted);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 2;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtHours = new JTextField();
		txtHours.setText("0");
		panel.add(txtHours);
		txtHours.setColumns(3);
		
		lblNewLabel_1 = new JLabel("hours");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblNewLabel_1);
		
		txtMinutes = new JTextField();
		txtMinutes.setText("0");
		panel.add(txtMinutes);
		txtMinutes.setColumns(3);
		
		lblNewLabel_2 = new JLabel("minutes");
		panel.add(lblNewLabel_2);
		
		inProgressScrollPane = new JScrollPane();
		GridBagConstraints gbc_inProgressScrollPane = new GridBagConstraints();
		gbc_inProgressScrollPane.gridheight = 2;
		gbc_inProgressScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_inProgressScrollPane.fill = GridBagConstraints.BOTH;
		gbc_inProgressScrollPane.gridx = 1;
		gbc_inProgressScrollPane.gridy = 2;
		frame.getContentPane().add(inProgressScrollPane, gbc_inProgressScrollPane);
		
		tblInProgress = new JTable(allTasks.getAllocatedToCaretaker(username));
		tblInProgress.setAutoCreateRowSorter(true);
		
		tblInProgress.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() == 1)
				{
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int jobID = (Integer) tblInProgress.getValueAt(row, 0);
					
					taskDescription.setText(allTasks.getTaskWithJobID(jobID).getTaskDesc());
				}
			}
		});
		
		inProgressScrollPane.setViewportView(tblInProgress);
		
		completeButton = new JButton("Complete");
		completeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel selectedModel = (DefaultTableModel) tblInProgress.getModel();
				DefaultTableModel completedModel = (DefaultTableModel) tblCompleted.getModel();
				int indexes[] = tblInProgress.getSelectedRows();
				Object[] row = new Object[6];
				int index = 0;
				int hoursTaken = 0;
				int minutesTaken = 0;
				
				if(txtHours.getText().toString() == "" || txtHours.getText().toString() == null) {
					JOptionPane.showMessageDialog(new JFrame(),
					    "Please input time taken to complete these tasks",
					    "Invalid time taken",
					    JOptionPane.ERROR_MESSAGE);
				}
				else {
					hoursTaken = Integer.parseInt(txtHours.getText()) * 60;
				}
				if(txtMinutes.getText().toString() == ""  || txtHours.getText().toString() == null) {
					JOptionPane.showMessageDialog(new JFrame(),
					    "Please input time taken to complete these tasks",
					    "Invalid time taken",
					    JOptionPane.ERROR_MESSAGE);
				}
				else {
					minutesTaken = Integer.parseInt(txtMinutes.getText());
				}
				System.out.print(minutesTaken + hoursTaken);
				if ((hoursTaken + minutesTaken) == 0 ) {
					JOptionPane.showMessageDialog(new JFrame(),
						    "Please input time taken to complete these tasks",
						    "Invalid time taken",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if (minutesTaken > 60) {
					JOptionPane.showMessageDialog(new JFrame(),
						    "Please input a sensible value for minutes taken",
						    "Invalid time taken",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					for(int i : indexes) {
						int jobID = Integer.parseInt( selectedModel.getValueAt(i, 0).toString() );
						System.out.println(jobID);
						Task taskToComplete = allTasks.getTaskWithJobID(jobID);
						try {
							taskToComplete.completeTask(hoursTaken + minutesTaken);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						row[0] = taskToComplete.getJobID();
						row[1] = taskToComplete.getTaskName();
						row[2] = taskToComplete.getLocation();
						row[3] = taskToComplete.getFormattedTimeTaken();
						row[4] = taskToComplete.getSignedOff();
						row[5] = taskToComplete.getSignedOffOn();
						
						completedModel.addRow(row);
					}
					
					for (int i = 0; i < indexes.length; i++) {
						index = indexes[i] - i;
						selectedModel.removeRow(index);
					}
					tblCompleted.setModel(completedModel);
					tblInProgress.setModel(selectedModel);
				}
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
				int index = 0;
				
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
				}
				
				
				for (int i = 0; i < indexes.length; i++) {
					index = indexes[i] - i;
					selectedModel.removeRow(index);
				}
				tblInProgress.setModel(inProgressModel);
				tblCompleted.setModel(selectedModel);
			}
		});

		GridBagConstraints gbc_uncompleteButton = new GridBagConstraints();
		gbc_uncompleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_uncompleteButton.gridx = 4;
		gbc_uncompleteButton.gridy = 3;
		frame.getContentPane().add(uncompleteButton, gbc_uncompleteButton);
		
		completedScrollPane = new JScrollPane();
		GridBagConstraints gbc_completedScrollPane = new GridBagConstraints();
		gbc_completedScrollPane.gridheight = 3;
		gbc_completedScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_completedScrollPane.fill = GridBagConstraints.BOTH;
		gbc_completedScrollPane.gridx = 5;
		gbc_completedScrollPane.gridy = 2;
		frame.getContentPane().add(completedScrollPane, gbc_completedScrollPane);
		
		tblCompleted = new JTable(allTasks.getCompletedByCaretaker(username));
		tblCompleted.setAutoCreateRowSorter(true);
		
		completedScrollPane.setViewportView(tblCompleted);
		
		descriptionScrollPane = new JScrollPane();
		GridBagConstraints gbc_descriptionScrollPane = new GridBagConstraints();
		gbc_descriptionScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionScrollPane.fill = GridBagConstraints.BOTH;
		gbc_descriptionScrollPane.gridx = 1;
		gbc_descriptionScrollPane.gridy = 4;
		frame.getContentPane().add(descriptionScrollPane, gbc_descriptionScrollPane);
		
		taskDescription = new JTextArea();
		taskDescription.setWrapStyleWord(true);
		taskDescription.setLineWrap(true);
		descriptionScrollPane.setViewportView(taskDescription);
		
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
