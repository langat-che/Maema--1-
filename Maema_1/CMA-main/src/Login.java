 import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setTitle("Log in type");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("MAEMA ASSOCIATES MANAGEMENT SYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.BLACK);
		JButton btnNewButton = new JButton("LOGIN AS MANAGER");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginframe=new Login();
				 ManagerLogin managerframe= new ManagerLogin();
				 loginframe.setVisible(false);
				 managerframe.setTitle("Login as a Manager");
				 managerframe.setVisible(true);
				 dispose();
			}
		});
		
		JButton btnLogInAs = new JButton("LOG IN AS AN EMPLOYEE");
		btnLogInAs.setBackground(Color.PINK);
		btnLogInAs.setForeground(Color.BLACK);
		btnLogInAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeLogin employeeloginframe= new EmployeeLogin();
				Login loginframe=new Login();
				employeeloginframe.setTitle("Log in as an Employee");
				loginframe.setVisible(false);
				employeeloginframe.setVisible(true);
				dispose();
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(110)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLogInAs, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(133)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(btnLogInAs, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
