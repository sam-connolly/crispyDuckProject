import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EditTaskUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaskName;
	private JTextField txtLocation;
	
	Database database = new Database();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Task task) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTaskUI frame = new EditTaskUI(task);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditTaskUI(Task task) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 720);
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
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//ManagerMenu menu = new ManagerMenu();
				//menu.setVisible(true);
				dispose();
			}
		});
		pnlTopButtons.add(btnBack);
		
		
		
		JPanel pnlMain = new JPanel();
		contentPane.add(pnlMain);
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
		
		JLabel lblTaskCreation = new JLabel("Edit Task");
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
		txtTaskName.setText(task.getTaskName());
		pnlDataEntry.add(txtTaskName);
		txtTaskName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblDescription);
		
		JTextArea txtaDescription = new JTextArea();
		txtaDescription.setText(task.getTaskDesc());
		pnlDataEntry.add(txtaDescription);
		
		JLabel lblLocation = new JLabel("Location *");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblLocation);
		
		txtLocation = new JTextField();
		txtLocation.setText(task.getLocation());
		pnlDataEntry.add(txtLocation);
		txtLocation.setColumns(10);
		
		JLabel lblDateDue = new JLabel("DateDue");
		lblDateDue.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblDateDue);
		
		JPanel pnlDateInput = new JPanel();
		pnlDataEntry.add(pnlDateInput);
		
		String strFullDate = task.getDateDue();
		String strDateYear = strFullDate.substring(0,3);
		String strDateMonthNum = strFullDate.substring(5,6);
		String strDateDays = strFullDate.substring(8,9);
		
		JComboBox<Integer> cmbDateDay = new JComboBox<Integer>();
		cmbDateDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31"}));
		cmbDateDay.setSelectedItem(strDateDays);
		pnlDateInput.add(cmbDateDay);
		
		JLabel lblDateSeperator1 = new JLabel("/");
		pnlDateInput.add(lblDateSeperator1);
		
		JComboBox<String> cmbDateMonth = new JComboBox<String>();
		cmbDateMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October", "November", "December"}));
		int month = Integer.parseInt(strDateMonthNum);
		switch (month)
		{
		case 1:
			cmbDateMonth.setSelectedItem("January");
			break;
		case 2:
			cmbDateMonth.setSelectedItem("February");
			break;
		case 3:
			cmbDateMonth.setSelectedItem("March");
			break;
		case 4: 
			cmbDateMonth.setSelectedItem("April");
			break;
		case 5:
			cmbDateMonth.setSelectedItem("May");
			break;
		case 6:
			cmbDateMonth.setSelectedItem("June");
			break;
		case 7:
			cmbDateMonth.setSelectedItem("July");
			break;
		case 8:
			cmbDateMonth.setSelectedItem("August");
			break;
		case 9:
			cmbDateMonth.setSelectedItem("September");
			break;
		case 10:
			cmbDateMonth.setSelectedItem("October");
			break;
		case 11:
			cmbDateMonth.setSelectedItem("November");
			break;
		case 12:
			cmbDateMonth.setSelectedItem("December");
			break;
		}
		pnlDateInput.add(cmbDateMonth);
				
		JLabel lblDateSeperator2 = new JLabel("/");
		pnlDateInput.add(lblDateSeperator2);
		
		JComboBox<Integer> cmbDateYear = new JComboBox<Integer>();
		for (int i = 2018; i <= 3000; i++)
		{
			String itterator = Integer.toString(i);
			if (itterator == strDateYear)
			{
				cmbDateYear.setSelectedItem(i);
			}
			else
			{
				cmbDateYear.addItem(i);
			}
		}
		pnlDateInput.add(cmbDateYear);
		
		cmbDateMonth.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent evt)
			{
				String month = (String) cmbDateMonth.getSelectedItem();
				int year = (Integer) cmbDateYear.getSelectedItem();
				
				if (evt.getStateChange() == ItemEvent.SELECTED)
				{
					cmbDateDay.removeAllItems();
					
					if (month == "September" || month == "April" || month =="June" || month =="November")
					{
						for (int i = 1; i <= 30; i++)
						{
							cmbDateDay.addItem(i);
						}
					}
					else if (cmbDateMonth.getSelectedItem() == "February")
					{
						if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
						{
							for (int i = 1; i <= 29; i++)
							{
								cmbDateDay.addItem(i);
							}
						}
						else
						{
							for (int i = 1; i <= 28; i++)
							{
								cmbDateDay.addItem(i);
							}
						}
					}
					else
					{
						for (int i = 0; i <=31; i++)
						{
							cmbDateDay.addItem(i);
						}
					}
				}
			}
		});
		
		cmbDateYear.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent evt)
			{
				String month = (String) cmbDateMonth.getSelectedItem();
				int year = (Integer) cmbDateYear.getSelectedItem();
				
				if (evt.getStateChange() == ItemEvent.SELECTED)
				{				
					if (month == "February" && ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))))
					{
						cmbDateDay.removeAllItems();
						for (int i = 1; i <= 29; i++)
						{
							cmbDateDay.addItem(i);
						}
					}
				}
			}
		});
		
		JLabel lblCategory = new JLabel("Category *");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblCategory);
		
		ArrayList<String> categories = new ArrayList<String>();
		categories = database.getCategories();
		JComboBox<String> cmbCategory = new JComboBox<String>();
		cmbCategory.addItem("Select a category");
		for (int i = 0; i < categories.size(); i++)
		{
			if (categories.get(i) == task.getTaskCat())
			{
				cmbCategory.setSelectedItem(categories.get(i));
			}
			else
			{
				cmbCategory.addItem(categories.get(i));
			}
		}
		pnlDataEntry.add(cmbCategory);
		
		JLabel lblPriority = new JLabel("Priority (1 is highest. 5 is lowest)");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblPriority);
		
		JComboBox<Integer> cmbPriority = new JComboBox<Integer>();
		cmbPriority.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5}));
		cmbPriority.setSelectedItem(task.getPriority());
		pnlDataEntry.add(cmbPriority);
		
		JLabel lblTimeEstimate = new JLabel("Time Estimate *");
		lblTimeEstimate.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblTimeEstimate);
		
		JPanel pnlTimeEstimate = new JPanel();
		pnlDataEntry.add(pnlTimeEstimate);
		
		Integer timeEstimateFull = task.getTimeEstimateInt();
		
		JComboBox<Integer> cmbHours = new JComboBox<Integer>();
		cmbHours.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15}));
		cmbHours.setSelectedItem((timeEstimateFull - (timeEstimateFull%60))/60);
		pnlTimeEstimate.add(cmbHours);
		
		JLabel lalHours = new JLabel("hours");
		pnlTimeEstimate.add(lalHours);
		
		JComboBox<Integer> cmbMinutes = new JComboBox<Integer>();
		cmbMinutes.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
				55}));
		cmbMinutes.setSelectedItem(timeEstimateFull%60);
		pnlTimeEstimate.add(cmbMinutes);
		
		JLabel lblMinutes = new JLabel("minutes");
		pnlTimeEstimate.add(lblMinutes);
				
		JLabel lblRepeating = new JLabel("Repeating?");
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
			if (i == task.getRepeating())
			{
				cmbRepeatingDays.setSelectedItem(i);
			}
			else
			{
				cmbRepeatingDays.addItem(i);
			}
		}
		
		JLabel lblDays = new JLabel(" days (0 if not repeating)");
		repeatingPanel.add(lblDays);
		
		JLabel lblCaretakers = new JLabel("Caretaker");
		lblCaretakers.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblCaretakers);
 		
 		ArrayList<String> caretakers = new ArrayList<String>();
 		caretakers = database.getCaretakers();
 		JComboBox<KeyValue> cmbCaretakers = new JComboBox<KeyValue>();
 		for (int i = 0; i < caretakers.size(); i = i + 2)
 		{
 			cmbCaretakers.addItem(new KeyValue(caretakers.get(i+1), caretakers.get((i))));
 		}
		pnlDataEntry.add(cmbCaretakers);
		//http://tech.chitgoks.com/2009/10/05/java-use-keyvalue-pair-for-jcombobox-like-htmls-select-tag/
		
		JPanel pnlSubmission = new JPanel();
		contentPane.add(pnlSubmission);
		
		JButton btnCreate = new JButton("Update");
		btnCreate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int hours = (Integer) cmbHours.getSelectedItem();
				int minutes = (Integer) cmbMinutes.getSelectedItem();
				
				if (txtTaskName.getText().equals("") || txtLocation.getText().equals("") ||
					cmbCategory.getSelectedItem().equals("Select a category") || (hours == 0 && minutes == 0))
				{
					JOptionPane.showMessageDialog(new JFrame(),
						    "Please fill in all fields marked with an *",
						    "Enter all info",
						    JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					//The KeyValue object from the month ComboBox
					KeyValue month = (KeyValue) cmbDateMonth.getSelectedItem();
					//The value (number) of this month
					String monthValue = month.getValue();
					
					//String value of the date
					String strDateDue = cmbDateDay.getSelectedItem() + "/" + monthValue + "/" +
					cmbDateYear.getSelectedItem();
					//SQL version of the date
					java.sql.Date sqlDate = null;
					try 
					{
						//Format the date using convertStringToSQLDate in the database class
						sqlDate = database.convertStringToSQLDate(strDateDue);
					} catch (ParseException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
					int timeEstimateInMinutes = (hours * 60) + minutes;
					String updateSQL = "UPDATE Task SET "
							+ "TaskName = " + txtTaskName.getText() + 
							", TaskDesc = " + txtaDescription.getText() + 
							", TaskCat = " + cmbCategory.getSelectedItem() + 
							", Priority = " + cmbPriority.getSelectedItem() +
							", Repeating = " + cmbRepeatingDays.getSelectedItem() + 
							", TimeEstimate = " + timeEstimateInMinutes +
							", Location = " + txtLocation.getText() +
							" WHERE taskID = " + task.getTaskID();

					
					Boolean created = database.executeSQL(updateSQL);
					
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
					
					String caretakerUsrnm = ((KeyValue) cmbCaretakers.getSelectedItem()).getKey();
					updateSQL = "UPDATE TaskList SET " +
							"Caretaker = " + caretakerUsrnm + 
							", DateDue = " + sqlDate +
							" WHERE jobID = " + task.getJobID();

					created = database.executeSQL(updateSQL);
					
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
}
