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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Date;

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
	private int taskID = -1;
	
	Database database = new Database();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				int taskID = Integer.parseInt(args[0]);
				try 
				{
					EditTaskUI frame = new EditTaskUI(taskID, args[1]);
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
	 * Method for formatting the day part of the dates, to ensure all days have 2 digits
	 * @param day The day of the month
	 * @return the formatted, 2 digit day of the month
	 */
	public String formatDateDay (int day)
	{
		String formattedDay;
		
		//If the day would normally have only 1 digit, add a zero to the beginning
		if (day < 10)
		{
			formattedDay = "0" + day;
		}
		else
		{
			formattedDay = "" + day;
		}
		
		//Return the formatted day
		return formattedDay;
	}

	/**
	 * Create the frame.
	 */
	public EditTaskUI(int taskID, String username) 
	{
		if (taskID == -1)
		{
			JOptionPane.showMessageDialog(new JFrame(),
				    "No task selected",
				    "Information pass error",
				    JOptionPane.WARNING_MESSAGE);
			dispose();
		}
		else
		{
			Task task = database.getTask(taskID);
			
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
				//When this window is closed, dispose of it and open the ManagerMenu UI
				public void actionPerformed(ActionEvent e) 
				{
					ManagerMenu managerMenuNew;
					try 
					{
						managerMenuNew = new ManagerMenu(username);
						managerMenuNew.setVisible(true);
						dispose();
					} 
					catch (ParseException | SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			System.out.println(strFullDate);
			/*String strDateYear = strFullDate.substring(0,3);
			String strDateMonthNum = strFullDate.substring(5,6);
			String strDateDays = strFullDate.substring(8,9);*/
			
			JComboBox<String> cmbDateDay = new JComboBox<String>();
			cmbDateDay.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
					"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
					"29", "30", "31"}));
			//cmbDateDay.setSelectedItem(strDateDays);
			pnlDateInput.add(cmbDateDay);
			
			JLabel lblDateSeperator1 = new JLabel("/");
			pnlDateInput.add(lblDateSeperator1);
			
			JComboBox<KeyValue> cmbDateMonth = new JComboBox<KeyValue>();
			cmbDateMonth.addItem(new KeyValue("January", "01"));
			cmbDateMonth.addItem(new KeyValue("February", "02"));
			cmbDateMonth.addItem(new KeyValue("March", "03"));
			cmbDateMonth.addItem(new KeyValue("April", "04"));
			cmbDateMonth.addItem(new KeyValue("May", "05"));
			cmbDateMonth.addItem(new KeyValue("June", "06"));
			cmbDateMonth.addItem(new KeyValue("July", "07"));
			cmbDateMonth.addItem(new KeyValue("August", "08"));
			cmbDateMonth.addItem(new KeyValue("September", "09"));
			cmbDateMonth.addItem(new KeyValue("October", "10"));
			cmbDateMonth.addItem(new KeyValue("November", "11"));
			cmbDateMonth.addItem(new KeyValue("December", "12"));
			/*int month = Integer.parseInt(strDateMonthNum);
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
			}*/
			pnlDateInput.add(cmbDateMonth);
					
			JLabel lblDateSeperator2 = new JLabel("/");
			pnlDateInput.add(lblDateSeperator2);
			
			JComboBox<Integer> cmbDateYear = new JComboBox<Integer>();
			for (int i = 2018; i <= 3000; i++)
			{
				/*String itterator = Integer.toString(i);
				if (itterator == strDateYear)
				{
					cmbDateYear.setSelectedItem(i);
				}
				else
				{*/
					cmbDateYear.addItem(i);
				//}
			}
			pnlDateInput.add(cmbDateYear);
			
			cmbDateMonth.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent evt)
				{
					KeyValue month =  (KeyValue) cmbDateMonth.getSelectedItem();	//The object from the ComboBox
					String monthKey = month.getKey();								//The key (name) of the month
					int year = (Integer) cmbDateYear.getSelectedItem();
					
					//If a new month has been selected, repopulate the days ComboBox
					if (evt.getStateChange() == ItemEvent.SELECTED)
					{
						//Empty the day ComboBox
						cmbDateDay.removeAllItems();
						
						//If the selected month only has 30 days, add 30 days to the days ComboBox
						if (monthKey == "September" || monthKey == "April" || monthKey =="June" ||	monthKey =="November")
						{
							for (int i = 1; i <= 30; i++)
							{
								//Add the formatted version of the date
								cmbDateDay.addItem(formatDateDay(i));
							}
						}
						//Perform further checks if the selected month is February
						else if (monthKey == "February")
						{
							//This algorithm return true if the entered year is a leap year
							if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
							{
								//If the year is a leap year, add 29 days to the ComboBox
								for (int i = 1; i <= 29; i++)
								{
									//Add the formatted version of the date
									cmbDateDay.addItem(formatDateDay(i));
								}
							}
							//If it isn't a leap year, add only 28 days
							else
							{
								//Add 28 days to the ComboBox
								for (int i = 1; i <= 28; i++)
								{
									//Add the formatted version of the date
									cmbDateDay.addItem(formatDateDay(i));
								}
							}
						}
						//If none of the above conditions are true, add 31 days to the ComboBox
						else
						{
							//Add 31 days to the ComboBox
							for (int i = 1; i <=31; i++)
							{
								//Add the formatted version of the date
								cmbDateDay.addItem(formatDateDay(i));
							}
						}
					}
				}
			});
			
			cmbDateYear.addItemListener(new ItemListener()
			{
				//If the year ComboBox is changed, check to see if the selected month is February, and if it's a leap year
				public void itemStateChanged(ItemEvent evt)
				{
					KeyValue month =  (KeyValue) cmbDateMonth.getSelectedItem();	//The object from the ComboBox
					String monthKey = month.getKey();								//The key (name) of the month
					int year = (Integer) cmbDateYear.getSelectedItem();				//The selected year
					
					//If a new month has been selected, repopulate the days ComboBox
					if (evt.getStateChange() == ItemEvent.SELECTED)
					{			
						//If the new year is a leap year, and the selected month is February, put 29 days in the ComboBox
						if (monthKey == "February" && ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))))
						{
							//Clear the ComboBox
							cmbDateDay.removeAllItems();
							//Add 29 days to the ComboBox
							for (int i = 1; i <= 29; i++)
							{
								//Add teh formatted version of the date
								cmbDateDay.addItem(formatDateDay(i));
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
				cmbCategory.addItem(categories.get(i));
				
				if (categories.get(i).equals(task.getTaskCat()))
				{
					cmbCategory.setSelectedItem(categories.get(i));
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
	 		
			JComboBox<String> cmbCaretakers = new JComboBox<String>();
			cmbCaretakers.addItem("Unallocated");
			cmbCaretakers.setSelectedItem("Unallocated");
	 		//ArrayList to store users returned from the database
			ArrayList<String> users = new ArrayList<String>();
			//Populate ArrayList with contents of database
			users = database.getUsernames();
			//Loop through arraylist adding items to ComboBox
			for (int i = 0; i < users.size(); i++)
			{
				cmbCaretakers.addItem(users.get(i));
				
				if (users.get(i).equals(task.getCaretaker()))
				{
					cmbCaretakers.setSelectedItem(users.get(i));
				}
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
						java.sql.Date sqlDateDue = null;
						
						DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
						Date currentDate = new Date();
						
						try 
						{
							//Format the date using convertStringToSQLDate in the database class
							sqlDateDue = database.convertStringToSQLDate(strDateDue);
						} 
						catch (ParseException e1) 
						{
							JOptionPane.showMessageDialog(new JFrame(),
								    "Could not format date input. Task may not be created. Contact support",
								    "Date conversion error",
								    JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}	
						
						int timeEstimateInMinutes = (hours * 60) + minutes;
						String updateSQL = "UPDATE Task SET "
								+ "TaskName = '" + txtTaskName.getText() + 
								"', TaskDesc = '" + txtaDescription.getText() + 
								"', TaskCat = '" + cmbCategory.getSelectedItem() + 
								"', Priority = '" + cmbPriority.getSelectedItem() +
								"', Repeating = '" + cmbRepeatingDays.getSelectedItem() + 
								"', TimeEstimate = '" + timeEstimateInMinutes +
								"', Location = '" + txtLocation.getText() +
								"' WHERE taskID = " + taskID;
	
						
						Boolean created = database.executeSQL(updateSQL);
						
						if (created)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Task successfully edited");
						}
						else
						{
							JOptionPane.showMessageDialog(new JFrame(),
								    "Could not edit task. Contact database administrator",
								    "Database error",
								    JOptionPane.ERROR_MESSAGE);
						}
						
						int jobID = -1;
						
						if (task != null)
						{
							jobID = task.getJobID();
						}
						String caretakerUsrnm = cmbCaretakers.getSelectedItem().toString();
						updateSQL = "UPDATE TaskList SET " +
								"Caretaker = '" + caretakerUsrnm + 
								"', DateDue = #" + sqlDateDue +
								"# WHERE jobID = " + jobID;
	
						created = database.executeSQL(updateSQL);
						
						if (created)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Job successfully edited");
						}
						else
						{
							JOptionPane.showMessageDialog(new JFrame(),
								    "Could not edit job. Contact database administrator",
								    "Database error",
								    JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			pnlSubmission.add(btnCreate);
		}
	}
	
	public void setTaskID(int taskID)
	{
		this.taskID = taskID;
	}
}
