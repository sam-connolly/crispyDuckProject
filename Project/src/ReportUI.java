import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Rectangle;

public class ReportUI extends JFrame{
	private JTable tableInProgress;
	public ReportUI() {

		setMinimumSize(new Dimension(200, 400));
		setLocationRelativeTo(null);
		
		JLabel lblViewReports = new JLabel("View Reports");
		lblViewReports.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblViewReports, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		tableInProgress = new JTable();
		String tblHeaderInPro[] = new String[] {"job No.", "Title", "assigined to"};
        DefaultTableModel mlInPro = new DefaultTableModel(tblHeaderInPro, 6);
        panel.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPaneIp = new JScrollPane();
        scrollPaneIp.setBounds(new Rectangle(0, 0, 240, 0));
        
        tableInProgress.setModel(mlInPro);
//		scrollPaneIp.add(tableInProgress, BorderLayout.NORTH);
		panel.add(scrollPaneIp, BorderLayout.WEST);
	}

}
