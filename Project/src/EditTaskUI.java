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
import java.text.ParseException;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
* UI form so the user can edit a task that's already in the database. Shows
* current details of the selected task/job, allowing the user to edit them, and
* update the databse with the new values.
*
* @author 	Sam Connolly 16006528
* @version	07/05/18
*/
public class EditTaskUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaskName;
	private JTextField txtLocation;

	
	Database database = new Database();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				int taskID = Integer.parseInt(args[0]);
				String taskOrJob = args[2];
				int jobID = Integer.parseInt(args[3]);
				try 
				{
					EditTaskUI frame = new EditTaskUI(taskID, args[1], taskOrJob, jobID);
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
	 * Closes the current frame, creates a new ManagerMenu and opens it
	 * 
	 * @param username	The username of the currently logged in user
	 */
	public void openManagerMenu(String username)
	{
		ManagerMenu managerMenuNew;
		try 
		{
			//Create a new ManagerMenu, passing the username to it
			managerMenuNew = new ManagerMenu(username);
			dispose();
		} 
		catch (ParseException | SQLException e1) 
		{
			//Alert the user that an error has occurred
			JOptionPane.showMessageDialog(new JFrame(),
				    "Could not open new window. Contact support",
				    "Window navigation error",
				    JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @param taskID	The ID of the task being edited
	 * @param username	The username of the currently logged in user
	 * @param taskOrJob Whether the user clicked on a task, or a job
	 * @param jobID		The ID of the job being edited
	 */
	public EditTaskUI(int taskID, String username, String taskOrJob, int jobID) 
	{
		//If no task is passed to the UI, alert the user and dispose of the window
		if (taskID == -1 && jobID == -1)
		{
			JOptionPane.showMessageDialog(new JFrame(),
				    "No task selected",
				    "Information pass error",
				    JOptionPane.WARNING_MESSAGE);
			dispose();
		}
		else
		{
			Task task = null;		//New task object to hold the details of the current task
			
			//If the selected row is a job, get the info for the matching job
			if (taskOrJob == "Job")
			{
				task = database.getJob(jobID);
			}
			//Otherwise, get the info on the task
			else
			{
				//Create a new task object of the task with the matching ID
				task = database.getTask(taskID);
			}
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 606, 758);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			
			//Top panel to separate the back button from the rest of the UI
			JPanel pnlTopButtons = new JPanel();
			pnlTopButtons.setAlignmentX(Component.RIGHT_ALIGNMENT);
			pnlTopButtons.setAlignmentY(Component.TOP_ALIGNMENT);
			pnlTopButtons.setSize(new Dimension(5, 3));
			FlowLayout flowLayout = (FlowLayout) pnlTopButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setHgap(2);
			contentPane.add(pnlTopButtons);
			
			//Button to go back to the ManagerMenu
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() 
			{
				//When this window is closed, dispose of it and open the ManagerMenu UI
				public void actionPerformed(ActionEvent e) 
				{
					//Close this window and open a new manager menu
					openManagerMenu(username);
				}
			});
			pnlTopButtons.add(btnBack);
			
			//Panel for holding the rest of the form
			JPanel pnlMain = new JPanel();
			contentPane.add(pnlMain);
			pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
			
			//Title of the form to remind the user what this form does
			JLabel lblEditTask = new JLabel("Edit Task");
			pnlMain.add(lblEditTask);
			lblEditTask.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblEditTask.setHorizontalAlignment(SwingConstants.CENTER);
			lblEditTask.setFont(new Font("Tahoma", Font.PLAIN, 28));
			
			//Panel to hold all data input components, and their labels
			JPanel pnlDataEntry = new JPanel();
			pnlMain.add(pnlDataEntry);
			pnlDataEntry.setLayout(new GridLayout(0, 2, 0, 18));
			
			//Label for task name entry field
			JLabel lblTaskName = new JLabel("Task Name *");
			lblTaskName.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblTaskName);
			
			//For entering the name of the task. Vital field, cannot be left blank
			txtTaskName = new JTextField();
			//Set the text to the current name of the task
			txtTaskName.setText(task.getTaskName());
			pnlDataEntry.add(txtTaskName);
			txtTaskName.setColumns(10);
			
			//Label for task description field
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblDescription);
			
			//Text are for entering a possibly quite long description. Not a vital field, so can be left empty
			JTextArea txtaDescription = new JTextArea();
			txtaDescription.setLineWrap(true);
			JScrollPane areaScrollPane = new JScrollPane(txtaDescription);
			txtaDescription.setText(task.getTaskDesc());
			areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			areaScrollPane.setPreferredSize(new Dimension(250, 250));
			pnlDataEntry.add(areaScrollPane);
			
			//Label for task location field
			JLabel lblLocation = new JLabel("Location *");
			lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblLocation);
			
			//For entering the location of the task. Vital field, cannot be left blank
			txtLocation = new JTextField();
			//Set the text to the current task location
			txtLocation.setText(task.getLocation());
			pnlDataEntry.add(txtLocation);
			txtLocation.setColumns(10);
			
			//Label for the due date entry panel
			JLabel lblDateDue = new JLabel("Date Due");
			lblDateDue.setHorizontalAlignment(SwingConstants.CENTER);
			
			//String to store the date for sub-stringing purposes
			String strFullDate = task.getDateDue();
			
			//Panel to hold all components for entering a date. The date input is made up of three comboBoxes
			JPanel pnlDateInput = new JPanel();
			
			String strDateYear = "";			//The year part of the date string
			String strDateMonthNum = "";		//The month part of the date string
			String strDateDays = "";			//The day part of the date string
			
			//If there is a due date for the job, get the different parts of the date from the string
			if (strFullDate != null)
			{
				strDateYear = strFullDate.substring(6,10);
				strDateMonthNum = strFullDate.substring(3,5);
				strDateDays = strFullDate.substring(0,2);
			}
			
			/*First comboBox for entering the day of the date. Since the default month is January, it initially has 31
			days*/
			JComboBox<String> cmbDateDay = new JComboBox<String>();
			cmbDateDay.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07",
					"08", "09", "10", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"
					, "26",	"27", "28",	"29", "30", "31"}));
			cmbDateDay.setSelectedItem(strDateDays);
			
			
			//Simple label to separate the date input comboBoxes
			JLabel lblDateSeperator1 = new JLabel("/");
			
			
			/*KeyValue is a class that stores two pieces of data, a key and value. This is used in ComboBoxes to store
			 * information that does not need to be shown in the ComboBox. In this case, the user sees only the name of
			 * the month, when there is also it's numerical value saved as well*/
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
			
			//If there is a due date, set the value of the month comboBox to the correct value
			if (strDateMonthNum != "")
			{
				//Set the selected month in the comboBox to the correct month according to the number 
				switch (strDateMonthNum)
				{
				case "01":
					cmbDateMonth.setSelectedIndex(0);
					break;
				case "02":
					cmbDateMonth.setSelectedIndex(1);
					break;
				case "03":
					cmbDateMonth.setSelectedIndex(2);
					break;
				case "04": 
					cmbDateMonth.setSelectedIndex(3);
					break;
				case "05":
					cmbDateMonth.setSelectedIndex(4);
					break;
				case "06":
					cmbDateMonth.setSelectedIndex(5);
					break;
				case "07":
					cmbDateMonth.setSelectedIndex(6);
					break;
				case "08":
					cmbDateMonth.setSelectedIndex(7);
					break;
				case "09":
					cmbDateMonth.setSelectedIndex(8);
					break;
				case "10":
					cmbDateMonth.setSelectedIndex(9);
					break;
				case "11":
					cmbDateMonth.setSelectedIndex(10);
					break;
				case "12":
					cmbDateMonth.setSelectedIndex(11);
					break;
				}
			}		
					
			//Simple label to separate the date input comboBoxes
			JLabel lblDateSeperator2 = new JLabel("/");
			
			//ComboBox for entering the year
			JComboBox<Integer> cmbDateYear = new JComboBox<Integer>();
			for (int i = 2018; i <= 3000; i++)
			{
				cmbDateYear.addItem(i);						//Add the year to the database
				
				String iterator = Integer.toString(i);		//String version of the year
				//If the string version of the year matches the year passed, set that year to the selected one
				if (iterator.equals(strDateYear))
				{
					cmbDateYear.setSelectedItem(i);
				}
			}		
			
			//Listener for if the month is changed
			cmbDateMonth.addItemListener(new ItemListener()
			{
				/*When the month is changed, repopulate the day ComboBox to have the correct number of days for that
				month*/
				public void itemStateChanged(ItemEvent evt)
				{
					KeyValue month =  (KeyValue) cmbDateMonth.getSelectedItem();	//The object from the ComboBox
					String monthKey = month.getKey();								//The key (name) of the month
					int year = (Integer) cmbDateYear.getSelectedItem();				//The selected year
					
					//If a new month has been selected, repopulate the days ComboBox
					if (evt.getStateChange() == ItemEvent.SELECTED)
					{
						//Empty the day ComboBox
						cmbDateDay.removeAllItems();
						
						//If the selected month only has 30 days, add 30 days to the days ComboBox
						if (monthKey == "September" || monthKey == "April" || monthKey =="June" ||
							monthKey =="November")
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
			
			//Listener for if the year has been changed
			cmbDateYear.addItemListener(new ItemListener()
			{
				/*If the year ComboBox is changed, check to see if the selected month is February, and if it's a leap
				year*/
				public void itemStateChanged(ItemEvent evt)
				{
					KeyValue month =  (KeyValue) cmbDateMonth.getSelectedItem();	//The object from the ComboBox
					String monthKey = month.getKey();								//The key (name) of the month
					int year = (Integer) cmbDateYear.getSelectedItem();				//The selected year
					
					//If a new month has been selected, repopulate the days ComboBox
					if (evt.getStateChange() == ItemEvent.SELECTED)
					{			
						/*If the new year is a leap year, and the selected month is February, put 29 days in the
						ComboBox*/
						if (monthKey == "February" && ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))))
						{
							//Clear the ComboBox
							cmbDateDay.removeAllItems();
							//Add 29 days to the ComboBox
							for (int i = 1; i <= 29; i++)
							{
								//Add the formatted version of the date
								cmbDateDay.addItem(formatDateDay(i));
							}
						}
					}
				}
			});
			
			//If the user is editing a job, add the date input and label components to the form
			if (taskOrJob.equals("Job"))
			{
				pnlDataEntry.add(lblDateDue);
				pnlDataEntry.add(pnlDateInput);
				pnlDateInput.add(cmbDateDay);
				pnlDateInput.add(lblDateSeperator1);
				pnlDateInput.add(cmbDateMonth);
				pnlDateInput.add(lblDateSeperator2);
				pnlDateInput.add(cmbDateYear);
			}
			
			
			//Label for the category input
			JLabel lblCategory = new JLabel("Category *");
			lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblCategory);
			
			//ArrayList to store all the categories returned from the database
			ArrayList<String> categories = new ArrayList<String>();
			//Call the getCategories method from the database and populate the ArrayList with it
			categories = database.getCategories();
			//ComboBox to store the categories
			JComboBox<String> cmbCategory = new JComboBox<String>();
			cmbCategory.addItem("Select a category");
			//Loop through all returned categories
			for (int i = 0; i < categories.size(); i++)
			{
				//Add the category to the comboBox
				cmbCategory.addItem(categories.get(i));
				
				//If the category matches the category of the passed task
				if (categories.get(i).equals(task.getTaskCat()))
				{
					//Set this to the selected category
					cmbCategory.setSelectedItem(categories.get(i));
				}
			}
			pnlDataEntry.add(cmbCategory);
			
			//Label for the priority input. Tells user which number indicates a higher priority
			JLabel lblPriority = new JLabel("Priority (1 is highest. 5 is lowest)");
			lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblPriority);
			
			//Priority input
			JComboBox<Integer> cmbPriority = new JComboBox<Integer>();
			cmbPriority.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5}));
			cmbPriority.setSelectedItem(Integer.parseInt(task.getPriority()));
			pnlDataEntry.add(cmbPriority);
			
			//Label for the time estimate input panel
			JLabel lblTimeEstimate = new JLabel("Time Estimate *");
			lblTimeEstimate.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblTimeEstimate);
			
			//Panel for holding the components for the input of a time estimation
			JPanel pnlTimeEstimate = new JPanel();
			pnlDataEntry.add(pnlTimeEstimate);
			
			//The full time estimate of the passed task
			Integer timeEstimateFull = task.getTimeEstimateInt();
			
			//ComboBox for holding the number of hours it will take to complete a task
			JComboBox<Integer> cmbHours = new JComboBox<Integer>();
			cmbHours.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
					13, 14, 15}));
			//Calculate how many hours are in the timeEstimate, and set this value to the selected item in the comboBox
			cmbHours.setSelectedItem((timeEstimateFull - (timeEstimateFull%60))/60);
			pnlTimeEstimate.add(cmbHours);
			
			//Label to indicate the preceding comboBox is for the input of hours
			JLabel lalHours = new JLabel("hours");
			pnlTimeEstimate.add(lalHours);
			
			//ComboBox for the input of how many minutes, on to of the hours, a task will take. Multiples of 5
			JComboBox<Integer> cmbMinutes = new JComboBox<Integer>();
			cmbMinutes.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 5, 10, 15, 20, 25, 30, 35, 40, 45,
					50, 55}));
			//Calculate the left over minutes in the time estimate passed, and set the comboBox to this value
			cmbMinutes.setSelectedItem(timeEstimateFull%60);
			pnlTimeEstimate.add(cmbMinutes);
			
			//Label to tell the user the preceding comboBox is for the input of minutes
			JLabel lblMinutes = new JLabel("minutes");
			pnlTimeEstimate.add(lblMinutes);
			

			//Label for the days to be completed in panel
			JLabel lblDaysToCompleteIn = new JLabel("Days to be completed in");
			lblDaysToCompleteIn.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblDaysToCompleteIn);
				
			//Panel to hold the time given input components
			JPanel pnlDaysToBeCompletedIn = new JPanel();
			pnlDataEntry.add(pnlDaysToBeCompletedIn);
				
			//ComboBox for inputting how many days are given for the task to be completed
			JComboBox<Integer> cmbDaysToBeCompletedIn = new JComboBox<Integer>();
			//Add 100 days to the comboBox
			for (int i = 1; i < 100; i++)
			{
				cmbDaysToBeCompletedIn.addItem(i);
			}
			pnlDaysToBeCompletedIn.add(cmbDaysToBeCompletedIn);
				
			//Label to tell the user the preceding comboBox is for the input of days
			JLabel lblCompletedDays = new JLabel(" days");
			pnlDaysToBeCompletedIn.add(lblCompletedDays);
			
			//Label for the days to be completed in panel
			JLabel lblRepeating = new JLabel("Repeating?");
			lblRepeating.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblRepeating);
			
			//Panel to hold the input and label for entering the number of days to pass before a task is repeated
			JPanel repeatingPanel = new JPanel();
			pnlDataEntry.add(repeatingPanel);
			
			/*The first label, so that when the user has entered a number of days, it forms a sentence, so it's easier for
			the user to understand*/
			JLabel lblRepeatEvery = new JLabel("Repeat every ");
			repeatingPanel.add(lblRepeatEvery);
			
			//ComboBox for entering the number of days to pass before a task is repeated
			JComboBox<Integer> cmbRepeatingDays = new JComboBox<Integer>();
			repeatingPanel.add(cmbRepeatingDays);
			//Add 365 days to the ComboBox
			for (int i = 0; i <= 365; i++)
			{
				//If the iterator is at the number of repeating days, make it the selected item
				if (i == task.getRepeating())
				{
					cmbRepeatingDays.setSelectedItem(i);
				}
				//Otherwise just add it to the comboBox
				else
				{
					cmbRepeatingDays.addItem(i);
				}
			}
			//Final label in the repeating panel. FInishes the sentence
			JLabel lblDays = new JLabel(" days (0 if not repeating)");
			repeatingPanel.add(lblDays);
			
			//Label for the caretakers comboBox
			JLabel lblCaretakers = new JLabel("Caretaker");
			lblCaretakers.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblCaretakers);
	 		
			//ComboBox for selecting a caretaker to complete the task
			JComboBox<String> cmbCaretakers = new JComboBox<String>();
			if (taskOrJob == "Task")
			{
				cmbCaretakers.addItem("Unallocated");
			}
			cmbCaretakers.setSelectedItem("Unallocated");
			
	 		//ArrayList to store users returned from the database
			ArrayList<String> users = new ArrayList<String>();
			//Populate ArrayList with contents of database
			users = database.getUsernames();
			
			//Loop through ArrayList adding items to ComboBox
			for (int i = 0; i < users.size(); i++)
			{
				cmbCaretakers.addItem(users.get(i));
				
				//If the current caretaker is the one already assigned the task
				if (users.get(i).equals(task.getCaretaker()))
				{
					//Make them the selected item
					cmbCaretakers.setSelectedItem(users.get(i));
				}
			}
			pnlDataEntry.add(cmbCaretakers);
			//http://tech.chitgoks.com/2009/10/05/java-use-keyvalue-pair-for-jcombobox-like-htmls-select-tag/
			
			
			//Label for the caretaker sign off checkBox
			JLabel lblCaretakerSignOff = new JLabel("Caretaker Sign Off? (Tick if yes)");
			lblCaretakerSignOff.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDataEntry.add(lblCaretakerSignOff);
			
			//CheckBox for setting if a caretaker can sign off the task
			JCheckBox chkBxCaretakerSignOff = new JCheckBox("");
			chkBxCaretakerSignOff.setHorizontalAlignment(SwingConstants.CENTER);
			System.out.println("CaretakerSignOff: " + task.getCaretakerSignOff());
			if (task.getCaretakerSignOff())
			{
				chkBxCaretakerSignOff.setSelected(true);
			}
			pnlDataEntry.add(chkBxCaretakerSignOff);
			
			//Panel to hold the submission button
			JPanel pnlSubmission = new JPanel();
			contentPane.add(pnlSubmission);
			
			//Button for creating the task. Checks that all required fields are entered, and triggers the SQL to execute
			JButton btnCreate = new JButton("Update");
			btnCreate.addActionListener(new ActionListener() 
			{
				//When the create button is pressed, add the entered data to the database if all required fields are filled
				public void actionPerformed(ActionEvent e) 
				{
					int hours = (Integer) cmbHours.getSelectedItem();		//The inputted hours
					int minutes = (Integer) cmbMinutes.getSelectedItem();	//The inputted minutes
					
					String nameText = txtTaskName.getText();				//String for the entered name
					String descriptionText = txtaDescription.getText();		//String for the entered description
					String locationText = txtLocation.getText();			//String for the entered location
					
					//Replace any single quotes in the strings with two single quotes, escaping them for the SQL
					nameText = nameText.replaceAll("'", "''");
					descriptionText = descriptionText.replaceAll("'", "''");
					locationText = locationText.replaceAll("'", "''");
					
					//If any of the required fields are not filled, alert the user and do not continue
					if (txtTaskName.getText().equals("") || txtLocation.getText().equals("") ||
						cmbCategory.getSelectedItem().equals("Select a category") || (hours == 0 && minutes == 0))
					{
						//Dialog to tell the user to enter all required fields
						JOptionPane.showMessageDialog(new JFrame(),
							    "Please fill in all fields marked with an *",
							    "Enter all info",
							    JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						//SQL version of the date
						java.sql.Date sqlDateDue = null;
						if (strFullDate != null)
						{
							//The KeyValue object from the month ComboBox
							KeyValue month = (KeyValue) cmbDateMonth.getSelectedItem();
							//The value (number) of this month
							String monthValue = month.getValue();
							
							//String value of the date
							String strDateDue = cmbDateDay.getSelectedItem() + "/" + monthValue + "/" +
							cmbDateYear.getSelectedItem();

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
						}
						
						
						Boolean caretakerSignOff = false;			//For if the task can be signed off by a caretaker
						//If the checkBox has been selected
						if (chkBxCaretakerSignOff.isSelected())
						{
							//Set caretakerSignOff to true
							caretakerSignOff = true;
						}
						
						//Calculate the total minutes of the time entered
						int timeEstimateInMinutes = (hours * 60) + minutes;
						//SQL for updating the task
						String updateSQL = "UPDATE Task SET "
								+ "TaskName = '" + nameText + 
								"', TaskDesc = '" + descriptionText + 
								"', TaskCat = '" + cmbCategory.getSelectedItem() + 
								"', Priority = '" + cmbPriority.getSelectedItem() +
								"', Repeating = '" + cmbRepeatingDays.getSelectedItem() + 
								"', TimeEstimate = '" + timeEstimateInMinutes +
								"', Location = '" + locationText +
								"', TimeGiven = '" + cmbDaysToBeCompletedIn.getSelectedItem() + 
								"', CaretakerSignOff = '" + caretakerSignOff +
								"' WHERE taskID = " + taskID;
	
						
						Boolean updatedTask = database.executeSQL(updateSQL);	//Boolean for if the task part updated
						
						//If the update was successful
						if (updatedTask)
						{
							//Tell the user
							JOptionPane.showMessageDialog(new JFrame(), "Task successfully edited");
						}
						//If not
						else
						{
							//Alert the user that the task wasn't edited
							JOptionPane.showMessageDialog(new JFrame(),
								    "Could not edit task. Contact database administrator",
								    "Database error",
								    JOptionPane.ERROR_MESSAGE);
						}
						
						//If the suer is editing a job
						if (taskOrJob == "Job")
						{
							//SQL for updating the job in the TaskList table
							String caretakerUsrnm = cmbCaretakers.getSelectedItem().toString();
							updateSQL = "UPDATE TaskList SET " +
									"Caretaker = '" + caretakerUsrnm + 
									"', DateDue = #" + sqlDateDue +
									"# WHERE jobID = " + jobID;
		
							//Boolean for if the SQL executed correctly
							Boolean updatedJob = database.executeSQL(updateSQL);
							
							//If it executed correctly, tell the user
							if (updatedJob)
							{
								JOptionPane.showMessageDialog(new JFrame(), "Job successfully edited");

							}
							//If not, alert them that the job was not edited
							else
							{
								JOptionPane.showMessageDialog(new JFrame(),
									    "Could not edit job. Contact database administrator",
									    "Database error",
									    JOptionPane.ERROR_MESSAGE);
							}
						}
						//Close this window and open a new manager menu
						openManagerMenu(username);
					}
				}
			});
			pnlSubmission.add(btnCreate);
		}
	}
}
