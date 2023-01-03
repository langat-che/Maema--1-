import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ManagerPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPage frame = new ManagerPage();
					frame.setTitle("Manage the company");
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
	public ManagerPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		JButton btnNewButton = new JButton("View Employees");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerPage Mpage=new ManagerPage();
				EmployeeList Elist=new EmployeeList();
				Elist.setTitle("EmployeeList");
				Mpage.setVisible(false);
				Elist.setVisible(true);
				dispose();
				
			}
		});
		
		JButton assignments = new JButton("Assign Tasks");
		assignments.setBackground(Color.PINK);
		assignments.setForeground(Color.BLACK);
		assignments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiveTasks Eopen=new GiveTasks(1);
				ManagerPage Eclose=new ManagerPage();
				Eopen.setTitle("Give Tasks");
				Eopen.setVisible(true);
				Eclose.setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Home page");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login open=new Login();
				ManagerPage close =new ManagerPage();
				open.setTitle("Select Log in type");
				open.setVisible(true);
				close.setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerLogin Mclose=new ManagerLogin();
				ManagerPage Mopen=new ManagerPage();
				Mclose.setTitle("Log in as a manager");
				Mclose.setVisible(true);
				Mopen.setVisible(false);
				dispose();
				
			}
		});
		JButton btnEmployeeTasks = new JButton("Employee Tasks");
		btnEmployeeTasks.setBackground(Color.PINK);
		btnEmployeeTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerApproval Mclose=new ManagerApproval();
				ManagerPage Mopen=new ManagerPage();
				Mclose.setTitle("Employee Tasks");
				Mclose.setVisible(true);
				Mopen.setVisible(false);
				dispose();
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
					.addComponent(btnNewButton_2)
					.addGap(50))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(171)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEmployeeTasks)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(assignments)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(144)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(btnNewButton)
					.addGap(42)
					.addComponent(assignments)
					.addGap(32)
					.addComponent(btnEmployeeTasks)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
