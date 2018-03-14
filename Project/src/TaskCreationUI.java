import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class TaskCreationUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtTimeEstimate;
	private JTextField txtLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskCreationUI frame = new TaskCreationUI();
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
	public TaskCreationUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 552);
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
		
		JButton btnPlaceholder1 = new JButton("Placeholder");
		pnlTopButtons.add(btnPlaceholder1);
		
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
		
		JComboBox cmbCategory = new JComboBox();
		pnlDataEntry.add(cmbCategory);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblPriority);
		
		JComboBox cmbPriority = new JComboBox();
		pnlDataEntry.add(cmbPriority);
		
		JLabel lblTimeEstimate = new JLabel("Time Estimate");
		lblTimeEstimate.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblTimeEstimate);
		
		txtTimeEstimate = new JTextField();
		txtTimeEstimate.setText("");
		pnlDataEntry.add(txtTimeEstimate);
		txtTimeEstimate.setColumns(10);
		
		JLabel lblCaretaker = new JLabel("Caretaker");
		lblCaretaker.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDataEntry.add(lblCaretaker);
		
		JComboBox cmbCaretaker = new JComboBox();
		pnlDataEntry.add(cmbCaretaker);
		
		JLabel lblRepeating = new JLabel("Repeating (Leave empy if not a repeating task)");
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
		
		JButton btnCreateAndIssue = new JButton("Create and Assign");
		pnlSubmission.add(btnCreateAndIssue);
		
		JButton btnCreate = new JButton("Create");
		pnlSubmission.add(btnCreate);
	}

}
