import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllocationMenu extends JDialog {
	
	private TaskList allTasks = new TaskList();
	private Database database = new Database();
	private UserList allUsers = new UserList();

	private final JPanel contentPanel = new JPanel();
	private JTable tblToAllocate;
	private JTable userInfoTable;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AllocationMenu dialog = new AllocationMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public AllocationMenu() throws ParseException, SQLException {
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
		JComboBox userCombo = allUsers.getUsersComboBox();
		
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
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
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
				userCombo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							userInfoTable.setModel(allUsers.getUserInfoModel(userCombo.getSelectedItem().toString(), allTasks));
							String fullName = allUsers.getUserCaretaker(userCombo.getSelectedItem().toString()).getFullName();
							lblSelectedName.setText(fullName);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				panel.add(userCombo);
			}
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int indexes[] = tblToAllocate.getSelectedRows();
						
						for(int i : indexes) {
							tblToAllocate.setValueAt(userCombo.getSelectedItem().toString(), i, 3);
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
					userInfoTable = new JTable(allUsers.getUserInfoModel(userCombo.getSelectedItem().toString(), allTasks));
					String fullName = allUsers.getUserCaretaker(userCombo.getSelectedItem().toString()).getFullName();
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
					public void actionPerformed(ActionEvent arg0) {
						for(int i = 0; i < tblToAllocate.getRowCount(); i++) {
							int taskID = Integer.parseInt( tblToAllocate.getValueAt(i, 0).toString() );
							Task taskToAllocate = allTasks.getFirstTaskWithTaskID(taskID);
							try {
								taskToAllocate.assignToCaretaker(tblToAllocate.getValueAt(i, 3).toString(), allTasks);
								dispose();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
	
	public void setToAllocateModel(DefaultTableModel toAllocateModel) {
		tblToAllocate.setModel(toAllocateModel);
	}

}
