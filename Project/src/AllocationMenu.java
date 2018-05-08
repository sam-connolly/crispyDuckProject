import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Insets;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Menu used to allocate tasks. Tasks selected for allocation are displayed in this window. Information on users is also displayed.
 */
public class AllocationMenu extends JDialog {
	
	private TaskList allTasks = new TaskList();
	private Database database = new Database();
	private UserList allUsers = new UserList();

	private final JPanel contentPanel = new JPanel();
	private JTable tblToAllocate;
	private JTable userInfoTable;
	


	/**
	 * Create the dialog.
	 * @param documentModal 
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public AllocationMenu(JFrame parentWindow, String username) throws ParseException, SQLException {
		super(parentWindow, "Allocation Menu", ModalityType.DOCUMENT_MODAL);
		
		// get list of users and tasks
		allTasks = database.getTasks();
		allUsers = database.getUsers();

		setBounds(100, 100, 532, 633);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{54, 296, 43, 135, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		
		JLabel lblSelectedName = new JLabel();
		// combo box with dynamic list of users
		JComboBox userCombo = allUsers.getUsersComboBox("allocation");
		
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			{
				JLabel lblAllocateTasks = new JLabel("Allocate Tasks");
				lblAllocateTasks.setVerticalAlignment(SwingConstants.BOTTOM);
				lblAllocateTasks.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel.add(lblAllocateTasks);
			}
		}
		{
			// area to view tasks to allocate
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				// table for tasks to allocate
				tblToAllocate = new JTable();
				scrollPane.setViewportView(tblToAllocate);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			{
				JLabel lblNewLabel = new JLabel("Assign to:");
				panel.add(lblNewLabel);
			}
			{
				// add a listener to the user combo box which changes the
				// contents of the bottom table to the selected user
				userCombo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							// set the user info table model to the one for the combo
							// box selected user
							userInfoTable.setModel(allUsers.getUserInfoModel
									(userCombo.getSelectedItem().toString(), allTasks));
							String fullName = allUsers.getUserCaretaker
									(userCombo.getSelectedItem().toString()).getFullName();
							lblSelectedName.setText(fullName);
						// alert user if there was an error
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(new JFrame(),
								    "Could not retrieve user information",
								    "Parse Exception", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(new JFrame(),
								    "Could not retrieve user information",
								    "SQL Exception", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					}
				});
				panel.add(userCombo);
			}
			{
				
				JButton btnOk = new JButton("OK");
				// set the caretaker to the chosen one for selected 
				// tasks in top table
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int indexes[] = tblToAllocate.getSelectedRows();
						
						for(int i : indexes) {
							tblToAllocate.setValueAt(userCombo.getSelectedItem()
									.toString(), i, 3);
						}
					}
				});
				panel.add(btnOk);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 3;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				lblSelectedName.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblSelectedName = new GridBagConstraints();
				gbc_lblSelectedName.insets = new Insets(0, 0, 5, 0);
				gbc_lblSelectedName.gridx = 0;
				gbc_lblSelectedName.gridy = 0;
				panel.add(lblSelectedName, gbc_lblSelectedName);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 1;
				panel.add(scrollPane, gbc_scrollPane);
				{
					userInfoTable = new JTable(allUsers.getUserInfoModel
							(userCombo.getSelectedItem().toString(), allTasks));
					String fullName = allUsers.getUserCaretaker
							(userCombo.getSelectedItem().toString()).getFullName();
					lblSelectedName.setText(fullName);
					scrollPane.setViewportView(userInfoTable);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					// pressing ok button closes the manager menu and opens a new one
					// so that the tables will be updated. Allocates all 
					// tasks that were imported
					public void actionPerformed(ActionEvent arg0) {
						for(int i = 0; i < tblToAllocate.getRowCount(); i++) {
							int taskID = Integer.parseInt( tblToAllocate.getValueAt(i, 0)
									.toString() );
							Task taskToAllocate = allTasks.getFirstTaskWithTaskID(taskID);
							try {
								taskToAllocate.assignToCaretaker
								(tblToAllocate.getValueAt(i, 3).toString(), allTasks);
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(new JFrame(),
									    "Could not allocate tasks",
									    "SQLException",
									    JOptionPane.ERROR_MESSAGE);
								e.printStackTrace();
							} catch (ParseException e) {
								JOptionPane.showMessageDialog(new JFrame(),
									    "Could not allocate tasks",
									    "ParseException",
									    JOptionPane.ERROR_MESSAGE);
								e.printStackTrace();
							}
						}
						// dispose old manager menu
						parentWindow.dispose();
						// try
						try {
							// try opening a new one
							ManagerMenu managerMenu = new ManagerMenu(username);
						} catch (ParseException | SQLException e) {
							JOptionPane.showMessageDialog(new JFrame(),
								    "Error refreshing Manager Menu, recommend restarting the program",
								    "ParseException",
								    JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				// dispose with no changes if cancel is pressed
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * function to set the model of the table 
	 * which holds tasks to be allocated
	 * @param toAllocateModel a model containing rows of tasks to be allocated
	 */
	public void setToAllocateModel(DefaultTableModel toAllocateModel) {
		tblToAllocate.setModel(toAllocateModel);
	}

}
