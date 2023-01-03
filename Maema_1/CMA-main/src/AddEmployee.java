import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AddEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField Ename;
	private JTextField Erole;
	private JTextField Ephone;
	private JTextField EID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee(0);
					frame.setVisible(true);
					//frame.setSize(550,370);
					frame.setBackground(Color.PINK);
					frame.setTitle("Edit Employee Details");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddEmployee(int i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,600, 370);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 12));
		
		Ename = new JTextField();
		Ename.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Role:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 12));
		
		Erole = new JTextField();
		Erole.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Phone Number:");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 12));
		
		Ephone = new JTextField();
		Ephone.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("EmployeeID:");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 12));
		
		EID = new JTextField();
		EID.setColumns(10);
		EID.setText(String.valueOf(i));
//		try{  
//			Class.forName("com.mysql.cj.jdbc.Driver");  
//			Connection con=DriverManager.getConnection(  
//			"jdbc:mysql://localhost/maema","root",""); 
//			PreparedStatement stmt=con.prepareStatement("select *from employeerecords where EmployeeID=?"); 
//			stmt.setInt(1,1);
//			ResultSet result=stmt.executeQuery();			
//			
//			if (result.next()) {
//				EID.setText(result.getString(1));
//				Ename.setText(result.getString(2));
//				Erole.setText(result.getString(3));
//				Ephone.setText(result.getString(4));
//				}
//		}catch(Exception S){ System.out.println(S);}   
//		
		JButton btnNewButton = new JButton("ADD Employee");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					PreparedStatement stmt=con.prepareStatement("insert into employeerecords values(?,?,?,?)"); 
					stmt.setString(1,EID.getText());
					stmt.setString(2, Ename.getText());
					stmt.setString(3,Erole.getText());
					stmt.setString(4,Ephone.getText());
					stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Employee added successfully");
                    
					con.close();  
						}catch(Exception S){ System.out.println(S);}   
			}
		});
		
		JButton btnNewButton_1 = new JButton("Fire Employee");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					PreparedStatement stmt=con.prepareStatement("select *from employeerecords where EmployeeID=?"); 
					stmt.setString(1,EID.getText());
					ResultSet result=stmt.executeQuery();
					if (result.next()) {
						PreparedStatement Dstmt=con.prepareStatement("delete from employeerecords where EmployeeID=?");
						Dstmt.setString(1, EID.getText());
						Dstmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Employee has been fired succesfully");
						}
					con.close();  
				}catch(Exception S){ System.out.println(S);}
			}
		});
		
		JButton btnNewButton_2 = new JButton("Update Records");
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					PreparedStatement stmt=con.prepareStatement("select *from employeerecords where EmployeeID=?"); 
					stmt.setString(1,EID.getText());
					ResultSet result=stmt.executeQuery();

					if (result.next()) {
						PreparedStatement dstmt=con.prepareStatement("UPDATE employeerecords SET Name='',Role='',PhoneNumber='' WHERE EmployeeID=? ");
						
						dstmt.setString(1,Ename.getText());
						dstmt.setString(2, Erole.getText());
						dstmt.setString(3, Ephone.getText());
						dstmt.setString(4,EID.getText());
						dstmt.executeUpdate();

						EID.setText("");
						Ename.setText("");
						Erole.setText("");
						Ephone.setText("");
						
						JOptionPane.showMessageDialog(null,"Employee records have been updated succesfully");
						}
				}catch(Exception S){ System.out.println(S);}   		
			}
		});
		
		JButton btnNewButton_3 = new JButton("Fetch Employee data");
		btnNewButton_3.setBackground(Color.PINK);
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					PreparedStatement stmt=con.prepareStatement("select *from employeerecords where EmployeeID=?"); 
					stmt.setString(1,EID.getText());
					ResultSet result=stmt.executeQuery();			
					
					if (result.next()) {
						EID.setText(result.getString(1));
						Ename.setText(result.getString(2));
						Erole.setText(result.getString(3));
						Ephone.setText(result.getString(4));
						}
				}catch(Exception S){ System.out.println(S);}   		
			}
		});
		
		JButton btnNewButton_4 = new JButton("Home page");
		btnNewButton_4.setBackground(Color.PINK);
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login open=new Login();
				AddEmployee close =new AddEmployee(0);
				open.setTitle("Select Log in type");
				open.setVisible(true);
				close.setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_5 = new JButton("Back");
		btnNewButton_5.setBackground(Color.PINK);
		btnNewButton_5.setForeground(Color.BLACK);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeList open=new EmployeeList();
				AddEmployee close =new AddEmployee(0);
				open.setTitle("Employees in the Firm");
				open.setVisible(true);
				close.setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_6 = new JButton("Assign task");
		btnNewButton_6.setBackground(Color.PINK);
		btnNewButton_6.setForeground(Color.BLACK);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pass=Integer.parseInt(EID.getText());
				AddEmployee close =new AddEmployee(0);
				GiveTasks open=new GiveTasks(pass);
				open.setTitle("Assign "+Ename.getText()+" a task");
				close.setVisible(false);
				open.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_4))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(Ephone)
									.addComponent(Erole, Alignment.LEADING)
									.addComponent(Ename, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
								.addComponent(EID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_4)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_5)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_3)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNewButton_6)
							.addComponent(btnNewButton_2)))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Ename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(Erole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(Ephone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_4)
								.addComponent(EID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addComponent(btnNewButton)
							.addGap(30)
							.addComponent(btnNewButton_1)
							.addGap(39)
							.addComponent(btnNewButton_3)
							.addGap(18)
							.addComponent(btnNewButton_2)
							.addGap(18)
							.addComponent(btnNewButton_6)))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_5)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
