import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

public class EmployeePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePage frame = new EmployeePage("User");
					frame.setTitle("WELCOME");
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
	public EmployeePage(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Employee Name");
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		
		JTextArea Ename = new JTextArea();
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID :");
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.BLACK);
		
		JTextArea ID = new JTextArea();
		ID.setColumns(10);

		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost/maema","root",""); 
		PreparedStatement stmt=con.prepareStatement("select *from employeerecords where Name=?");
		stmt.setString(1, user);
		ResultSet result;
		result=stmt.executeQuery();
		if(result.next())
		{
			Ename.setText(result.getString(2));
			ID.setText(result.getString(5));
			
		}
		con.close();  
		}catch(Exception S){ System.out.println(S);}
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeePage Eclose=new EmployeePage("");
				EmployeeLogin Eopen=new EmployeeLogin();
				Eclose.setVisible(false);
				Eopen.setTitle("Enter employee Log in credentials");
				Eopen.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("View task");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeePage Eclose=new EmployeePage("");
				EmployeeTasks Eopen=new EmployeeTasks(Integer.parseInt(ID.getText()));
				Eclose.setVisible(false);
				Eopen.setTitle(Ename.getText()+" Tasks");
				Eopen.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Home page");
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeePage Eclose=new EmployeePage("");
				Login Eopen=new Login();
				Eclose.setVisible(false);
				Eopen.setTitle("Select log in type");
				Eopen.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(ID)
						.addComponent(Ename, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_1)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton_2)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(Ename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(ID, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addGap(133)
							.addComponent(btnNewButton_2)))
					.addContainerGap())
		);
					
		contentPane.setLayout(gl_contentPane);
	}
}

