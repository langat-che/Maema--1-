import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class EmployeeLogin extends JFrame {
	ResultSet result;
	private JPanel contentPane;
	private JTextField userID;
	private JPasswordField pass;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
					frame.setTitle("Log in as an employee");
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
	public EmployeeLogin() {
		Decoder decoder=Base64.getDecoder();
		Encoder encoder= Base64.getEncoder();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JLabel lblNewLabel = new JLabel("User ID :");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.BLACK);
		
		userID = new JTextField();
		userID.setColumns(10);
		
		pass = new JPasswordField();
		pass.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try{
						//Class.forName("com.mysql.jdbc.Driver");
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con= DriverManager.getConnection(  
						"jdbc:mysql://localhost/maema","root",""); 
						String Inputusername=userID.getText();
						String Inputpassword=pass.getText();
						String encode=new String(encoder.encodeToString(Inputpassword.getBytes()));
						if(Inputusername.isEmpty()||Inputpassword.toString().isEmpty())
						{
							JOptionPane.showMessageDialog(null,"Input Credentials");
							Inputusername=userID.getText();
							Inputpassword=pass.getText();
							encode=encoder.encodeToString(Inputpassword.getBytes());
						}
						
						PreparedStatement stmt=con.prepareStatement("select *from employeelogin where EmployeeID = ?");
						stmt.setString(1, Inputusername);
						//stmt.executeUpdate();
						result=stmt.executeQuery();
						if(result.next())
						{
							String dbpass=result.getString(2);
							if (dbpass.equals(encode))
							{
							EmployeeLogin Eclose=new EmployeeLogin();
							EmployeePage Eopen=new EmployeePage(Inputusername);
							Eclose.setVisible(false);
							JOptionPane.showMessageDialog(null,"Employee log in succesful");
							Eopen.setTitle("Welcome "+Inputusername);
							Eopen.setVisible(true);
							dispose();
							}
							else {
								JOptionPane.showMessageDialog(null,"Enter correct log in credentials");
							}
							}
					}
						//con.close();  
						catch(Exception S){ System.out.println(S);}    
						   
				}
					
		});
		
		btnNewButton_1 = new JButton("SIGN UP");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeLogin Mclose=new EmployeeLogin();
				EmployeeSignup Mopen=new EmployeeSignup();
				Mclose.setVisible(false);
				Mopen.setTitle("Sign up");
				Mopen.setVisible(true);
				dispose();
			}
				
		});
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeLogin Mclose=new EmployeeLogin();
				Login Mopen=new Login();
				Mclose.setVisible(false);
				Mopen.setTitle("Select log in type");
				Mopen.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pass)
						.addComponent(userID, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addGap(57)
					.addComponent(btnNewButton_2)
					.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(73)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(userID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(41)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_2)
							.addGap(79)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
