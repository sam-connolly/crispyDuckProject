import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class AddTaskUI extends JDialog {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddTaskUI dialog = new AddTaskUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddTaskUI() {
		setResizable(false);
		setBounds(100, 100, 600, 416);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 343, 584, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		textField = new JTextField();
		textField.setBounds(92, 11, 150, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(92, 42, 150, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 574, 200);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel = new JLabel("Job:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(14, 14, 54, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Location:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(12, 45, 56, 14);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"HIGH", "MEDIUM ", "LOW"}));
		comboBox.setBounds(466, 11, 118, 20);
		getContentPane().add(comboBox);
		
		JLabel lblSignOffBy = new JLabel("Sign Off By:");
		lblSignOffBy.setHorizontalAlignment(SwingConstants.LEFT);
		lblSignOffBy.setBounds(10, 319, 66, 14);
		getContentPane().add(lblSignOffBy);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Manager", "Caretaker"}));
		comboBox_1.setBounds(92, 316, 118, 20);
		getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_2 = new JLabel("Urgency:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(390, 14, 66, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 70, 87, 14);
		getContentPane().add(lblDescription);
		
		JLabel lblNewLabel_3 = new JLabel("Every");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(260, 14, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(298, 11, 38, 20);
		getContentPane().add(spinner);
		
		JLabel lblDays = new JLabel("Days");
		lblDays.setBounds(346, 14, 46, 14);
		getContentPane().add(lblDays);
	}
}
