import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskCreationUI extends JFrame 
{

	private JPanel contentPane;
	private JTextField txtTaskName;
	private JTextField txtLocation;
	private String username;
	
	Database database = new Database();

	 // Launch the application.
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					TaskCreationUI frame = new TaskCreationUI();
					// When the window is closed, it should actually close, not hide
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	 // Create the frame.
	public TaskCreationUI() 
	{
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
		btnBack.addActionListener(new ActionListener() 
		{
			//When this window is closed, dispose of it and open the ManagerMenu UI
			public void actionPerformed(ActionEvent e) 
			{
				ManagerMenu managerMenuNew;
				try 
				{
					managerMenuNew = new ManagerMenu();
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
		
		JLabel lblTaskCreation = new JLabel("Create Task");
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
		
		JLabel lblDateDue = new JLabel("(First) Date Due");
		lblDateDue.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblDateDue);
		
		JPanel pnlDateInput = new JPanel();
		pnlDataEntry.add(pnlDateInput);
		
		JComboBox<String> cmbDateDay = new JComboBox<String>();
		//Loop for adding all days of the month to the ComboBox. It's 31 because the default month is January
		for (int i = 1; i <= 31; i++)
		{
			//Before adding the number to the ComboBox, format it first
			cmbDateDay.addItem(formatDateDay(i));
		}
		pnlDateInput.add(cmbDateDay);
		
		JLabel lblDateSeperator1 = new JLabel("/");
		pnlDateInput.add(lblDateSeperator1);
		
		/*KeyValue is a class that stores two pieces of data, a key and value. This is used in ComboBoxes to store
		 * information that does not need to be shown in the ComboBox. In this case, the user sees only the name of the
		 * month, when there is also it's numerical value saved as well
		 */
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
		pnlDateInput.add(cmbDateMonth);
				
		JLabel lblDateSeperator2 = new JLabel("/");
		pnlDateInput.add(lblDateSeperator2);
		
		JComboBox<Integer> cmbDateYear = new JComboBox<Integer>();
		//Populate the ComboBox with years up to 3000
		for (int i = 2018; i <= 3000; i++)
		{
			cmbDateYear.addItem(i);
		}
		pnlDateInput.add(cmbDateYear);
		
		cmbDateMonth.addItemListener(new ItemListener()
		{
			//When the month is changed, repopulate the day ComboBox to have the correct number of days for that month
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
		
		//ArrayList to store all the categories returned from the database
		ArrayList<String> categories = new ArrayList<String>();
		//Call the getCategories method from the database and populate the ArrayList with it
		categories = database.getCategories();
		JComboBox<String> cmbCategory = new JComboBox<String>();
		cmbCategory.addItem("Select a category");
		//Loop through all returned categories
		for (int i = 0; i < categories.size(); i++)
		{
			//Add the category to the database
			cmbCategory.addItem(categories.get(i));
		}
		pnlDataEntry.add(cmbCategory);
		
		JLabel lblPriority = new JLabel("Priority (1 is highest. 5 is lowest)");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblPriority);
		
		JComboBox<Integer> cmbPriority = new JComboBox<Integer>();
		cmbPriority.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5}));
		cmbPriority.setSelectedIndex(2);
		pnlDataEntry.add(cmbPriority);
		
		JLabel lblTimeEstimate = new JLabel("Time Estimate *");
		lblTimeEstimate.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblTimeEstimate);
		
		JPanel timeEstimatePanel = new JPanel();
		pnlDataEntry.add(timeEstimatePanel);
		
		JComboBox<Integer> cmbHours = new JComboBox<Integer>();
		cmbHours.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
		14, 15}));
		timeEstimatePanel.add(cmbHours);
		
		JLabel lblHours = new JLabel("hours");
		timeEstimatePanel.add(lblHours);
		
		JComboBox<Integer> cmbMinutes = new JComboBox<Integer>();
		cmbMinutes.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
		55}));
		timeEstimatePanel.add(cmbMinutes);
		
		JLabel lblMinutes = new JLabel("minutes");
		timeEstimatePanel.add(lblMinutes);
				
		JLabel lblRepeating = new JLabel("");
		lblRepeating.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblRepeating);
		
		JPanel repeatingPanel = new JPanel();
		pnlDataEntry.add(repeatingPanel);
		
		JLabel lblRepeatEvery = new JLabel("Repeat every ");
		repeatingPanel.add(lblRepeatEvery);
		
		JComboBox<Integer> cmbRepeatingDays = new JComboBox<Integer>();
		repeatingPanel.add(cmbRepeatingDays);
		//Add 365 days to the ComboBox
		for (int i = 0; i <= 365; i++)
		{
			cmbRepeatingDays.addItem(i);
		}
		
		JLabel lblDays = new JLabel(" days (0 if not repeating)");
		repeatingPanel.add(lblDays);
		
		JPanel pnlSubmission = new JPanel();
		contentPane.add(pnlSubmission);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() 
		{
			//When the create button is pressed, add the entered data to the database if all required fields are filled
			public void actionPerformed(ActionEvent e) 
			{
				int hours = (Integer) cmbHours.getSelectedItem();		//The inputted hours
				int minutes = (Integer) cmbMinutes.getSelectedItem();	//The inputted minutes
				
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
					JOptionPane.showMessageDialog(new JFrame(),
						    "Could not format date input. Task may not be created. Contact support",
						    "Date conversion error",
						    JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
				Date currentDate = new Date();
				
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
				else if (sqlDate.before(currentDate))
				{
					//Dialog to tell the user to enter all required fields
					JOptionPane.showMessageDialog(new JFrame(),
						    "Due date entered is in the past. Please enter a date not in the past",
						    "Date entry error",
						    JOptionPane.WARNING_MESSAGE);
				}
				//If all required fields are entered, then continue
				else
				{	
					//Calculate the total minutes of the time entered
					int timeEstimateInMinutes = (hours * 60) + minutes;
					//SQL for creating a new database entry
					String insertSQL = "INSERT INTO Task (TaskName, TaskDesc,"
							+ " TaskCat, Priority, Repeating, TimeEstimate, Location, FirstAllocation)"
							+ "VALUES ('" 
							+ txtTaskName.getText() + "', '" 
							+ txtaDescription.getText() + "', '"
							+ cmbCategory.getSelectedItem() + "', '"
							+ cmbPriority.getSelectedItem() + "', '"
							+ cmbRepeatingDays.getSelectedItem() + "', '"
							+ timeEstimateInMinutes + "', '"
							+ txtLocation.getText() + "', #"
							+ sqlDate + "#);";
					
					/*Create the database field by calling createTask in the database class. Store whether it was a 
					success*/
					Boolean created = database.executeSQL(insertSQL);
					
					//If the data entry was a success, let the user know
					if (created)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Task successfully created");
						txtTaskName.setText("");
						txtaDescription.setText("");
						cmbCategory.setSelectedItem("Select a category");
						cmbPriority.setSelectedItem("3");
						cmbRepeatingDays.setSelectedItem(0);
						cmbHours.setSelectedItem(0);
						cmbMinutes.setSelectedItem(0);
						txtLocation.setText("");
					}
					//If data entry failed, tell the user and recommend they contact their database admin
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
