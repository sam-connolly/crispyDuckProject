import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaskCreationUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtTimeEstimate;
	private JTextField txtLocation;
	
	Database database = new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					TaskCreationUI frame = new TaskCreationUI();
					frame.setVisible(true);	
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public TaskCreationUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel pnlTopButtons = new JPanel();
		pnlTopButtons.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pnlTopButtons.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlTopButtons.setSize(new Dimension(5, 3));
		FlowLayout flowLayout = (FlowLayout) pnlTopButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(2);
		contentPane.add(pnlTopButtons);
		
		JButton btnBack = new JButton("Back");
		pnlTopButtons.add(btnBack);
		
		JPanel pnlMain = new JPanel();
		contentPane.add(pnlMain);
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
		
		JLabel lblTaskCreation = new JLabel("Task Creation");
		pnlMain.add(lblTaskCreation);
		lblTaskCreation.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTaskCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaskCreation.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JPanel pnlDataEntry = new JPanel();
		pnlMain.add(pnlDataEntry);
		pnlDataEntry.setLayout(new GridLayout(0, 2, 0, 18));
		
		JLabel lblTaskName = new JLabel("Task Name *");
		lblTaskName.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblTaskName);
		
		txtName = new JTextField();
		pnlDataEntry.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblDescription);
		
		JTextArea txtaDescription = new JTextArea();
		pnlDataEntry.add(txtaDescription);
		
		JLabel lblLocation = new JLabel("Location *");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblLocation);
		
		txtLocation = new JTextField();
		pnlDataEntry.add(txtLocation);
		txtLocation.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category *");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblCategory);
		
		ArrayList<String> categories = new ArrayList<String>();
		categories = database.getCategories();
		JComboBox cmbCategory = new JComboBox(categories.toArray());
		pnlDataEntry.add(cmbCategory);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblPriority);
		
		JComboBox cmbPriority = new JComboBox();
		cmbPriority.setModel(new DefaultComboBoxModel(new String[] {"1 (Highest)",
				"2", "3", "4", "5 (Lowest)"}));
		cmbPriority.setSelectedIndex(2);
		pnlDataEntry.add(cmbPriority);
		
		JLabel lblTimeEstimate = new JLabel("Time Estimate");
		lblTimeEstimate.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblTimeEstimate);
		
		txtTimeEstimate = new JTextField();
		txtTimeEstimate.setText("");
		pnlDataEntry.add(txtTimeEstimate);
		txtTimeEstimate.setColumns(10);
		
		JLabel lblCaretakers = new JLabel("Caretaker");
		lblCaretakers.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblCaretakers);
		
		ArrayList<String> caretakers = new ArrayList<String>();
		caretakers = database.getCaretakers();
		JComboBox cmbCaretakers = new JComboBox();
		for (int i = 0; i < caretakers.size(); i = i + 2)
		{
			cmbCaretakers.addItem(new KeyValue(caretakers.get(i+1), caretakers.get((i))));
		}
		pnlDataEntry.add(cmbCaretakers);
		//http://tech.chitgoks.com/2009/10/05/java-use-keyvalue-pair-for-jcombobox-like-htmls-select-tag/
		
		JLabel lblRepeating = new JLabel("Repeating (Leave empy if not a"
				+ " repeating task)");
		lblRepeating.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblRepeating);
		
		JPanel panel = new JPanel();
		pnlDataEntry.add(panel);
		
		JComboBox cmbRepeating1 = new JComboBox();
		panel.add(cmbRepeating1);
		
		JComboBox cmbRepeating2 = new JComboBox();
		panel.add(cmbRepeating2);
		
		JComboBox cmbRepeating3 = new JComboBox();
		panel.add(cmbRepeating3);
		
		JComboBox cmbRepeating4 = new JComboBox();
		panel.add(cmbRepeating4);
		
		JPanel pnlSubmission = new JPanel();
		contentPane.add(pnlSubmission);
		
		JButton btnCreateAndAssign = new JButton("Create and Assign");
		pnlSubmission.add(btnCreateAndAssign);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String insertSQL = "INSERT INTO Task (TaskName, TaskDesc,"
						+ " TaskCat, Priority, Location)"
						+ "VALUES (" + txtName.getText() + ", " 
						+ txtaDescription.getText() + ", "
						+ cmbCategory.getSelectedItem() + ", "
						+ cmbPriority.getSelectedItem() + ", "
						+ txtLocation.getText() + ");";
				
				Boolean created = database.createTask(insertSQL);
				
				if (created)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Task successfully created");
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(),
						    "Could not create task. Contact database administrator",
						    "Database error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pnlSubmission.add(btnCreate);
	}
}
