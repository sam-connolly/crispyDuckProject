import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class UpdatePreferenceUI extends JFrame {

	private JPanel contentPane;
	Database database = new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePreferenceUI frame = new UpdatePreferenceUI();
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
	public UpdatePreferenceUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(97, 129, 99, 23);
		contentPane.add(btnSave);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(80, 33, 183, 23);
		contentPane.add(comboBox);
		ArrayList<String> categories = new ArrayList<String>();
		categories = database.getCategories();
		for (int i=0; i<categories.size(); i++)
		{
			comboBox.addItem(categories.get(i));
		}
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(23, 35, 74, 19);
		contentPane.add(lblCategory);
		
		JLabel lblPreferenceLevel = new JLabel("Preference Level");
		lblPreferenceLevel.setBounds(23, 79, 99, 19);
		contentPane.add(lblPreferenceLevel);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(122, 77, 141, 23);
		contentPane.add(comboBox_1);
		for (int i=0; i<11; i++)
		{
			comboBox_1.addItem(Integer.toString(i));
		}
		String username = "mBuglass";
		String cat=comboBox.getSelectedItem().toString();
		int preferenceLevel = 0;
		try {
			preferenceLevel = database.getPreferenceLevel(username, cat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox_1.setSelectedItem(Integer.toString(preferenceLevel));
		comboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent evt)
			{;
				String username = "mBuglass";
				String cat=comboBox.getSelectedItem().toString();
				int preferenceLevel = 0;
				try {
					preferenceLevel = database.getPreferenceLevel(username, cat);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				comboBox_1.setSelectedItem(Integer.toString(preferenceLevel));
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = "mBuglass";
				String cat=comboBox.getSelectedItem().toString();
				int preference = 0;
			    try {
						boolean updatePreference = database.updateUserPreference(username, cat,
								preference);
						System.out.println(updatePreference);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}	
		});
	}
}
