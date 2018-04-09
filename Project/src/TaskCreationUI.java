import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TaskCreationUI extends JFrame {

	/**
	 * 
	 */

	private JPanel contentPane;
	private JTextField txtTaskName;
	private JTextField txtLocation;
	
	Database database = new Database();
	private final Action action = new SwingAction();

	 // Launch the application.
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

	 // Create the frame.
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
		btnBack.setAction(action);
		pnlTopButtons.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
				{
					//ManagerMenu.setVisible(true);
				}
			});
		
		
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
		
		txtTaskName = new JTextField();
		pnlDataEntry.add(txtTaskName);
		txtTaskName.setColumns(10);
		
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
		JComboBox<String> cmbCategory = new JComboBox<String>();
		cmbCategory.addItem("Select a category");
		for (int i = 0; i < categories.size(); i++)
		{
			cmbCategory.addItem(categories.get(i));
		}
		pnlDataEntry.add(cmbCategory);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblPriority);
		
		JComboBox<String> cmbPriority = new JComboBox<String>();
		cmbPriority.setModel(new DefaultComboBoxModel<String>(new String[] {"1 (Highest)", "2", "3", "4", "5 (Lowest)"
				+ ""}));
		cmbPriority.setSelectedIndex(2);
		pnlDataEntry.add(cmbPriority);
		
		JLabel lblTimeEstimate = new JLabel("Time Estimate");
		lblTimeEstimate.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblTimeEstimate);
		
		JPanel timeEstimatePanel = new JPanel();
		pnlDataEntry.add(timeEstimatePanel);
		
		JComboBox<Integer> cmbHours = new JComboBox<Integer>();
		cmbHours.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15}));
		timeEstimatePanel.add(cmbHours);
		
		JLabel lalHours = new JLabel("hours");
		timeEstimatePanel.add(lalHours);
		
		JComboBox<Integer> cmbMinutes = new JComboBox<Integer>();
		cmbMinutes.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
				55}));
		timeEstimatePanel.add(cmbMinutes);
		
		JLabel lblMinutes = new JLabel("minutes");
		timeEstimatePanel.add(lblMinutes);
		
		//http://tech.chitgoks.com/2009/10/05/java-use-keyvalue-pair-for-jcombobox-like-htmls-select-tag/
		
		JLabel lblRepeating = new JLabel("");
		lblRepeating.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblRepeating);
		
		JPanel repeatingPanel = new JPanel();
		pnlDataEntry.add(repeatingPanel);
		
		JLabel lblRepeatEvery = new JLabel("Repeat every ");
		repeatingPanel.add(lblRepeatEvery);
		
		JComboBox<Integer> cmbRepeatingDays = new JComboBox<Integer>();
		repeatingPanel.add(cmbRepeatingDays);
		for (int i = 0; i <= 365; i++)
		{
			cmbRepeatingDays.addItem(i);
		}
		
		JLabel lblDays = new JLabel(" days (0 if not repeating)");
		repeatingPanel.add(lblDays);
		
		JPanel pnlSubmission = new JPanel();
		contentPane.add(pnlSubmission);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (txtTaskName.getText().equals("") || txtLocation.getText().equals("") ||
					cmbCategory.getSelectedItem().equals("Select a category"))
				{
					JOptionPane.showMessageDialog(new JFrame(),
						    "Please fill in all fields marked with an *",
						    "Enter all info",
						    JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					int hours = (Integer) cmbHours.getSelectedItem();
					int minutes = (Integer) cmbMinutes.getSelectedItem();
					
					int timeEstimateInMinutes = (hours * 60) + minutes;
					String insertSQL = "INSERT INTO Task (TaskName, TaskDesc,"
							+ " TaskCat, Priority, Repeating, TimeEstimate, Location)"
							+ "VALUES ('" + txtTaskName.getText() + "', '" 
							+ txtaDescription.getText() + "', '"
							+ cmbCategory.getSelectedItem() + "', '"
							+ cmbPriority.getSelectedItem() + "', '"
							+ cmbRepeatingDays.getSelectedItem() + "', '"
							+ timeEstimateInMinutes + "', '"
							+ txtLocation.getText() + "');";
					
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
			}
		});
		pnlSubmission.add(btnCreate);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
