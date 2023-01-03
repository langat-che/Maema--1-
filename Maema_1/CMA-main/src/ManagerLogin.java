 import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ManagerLogin extends JFrame {

	private JPanel contentPane;
	private JTextField Musername;
	private JPasswordField Mpass;
	private JButton btnNewButton;
//	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLogin frame = new ManagerLogin();
					frame.setTitle("ENTER YOUR MANAGER DETAILS");
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
	public ManagerLogin() {
		Decoder decoder=Base64.getDecoder();
		Encoder encoder= Base64.getEncoder();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.BLACK);
		
		Musername = new JTextField();
		Musername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.BLACK);
		
		Mpass = new JPasswordField();
		Mpass.setColumns(10);
		
		btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					String Inputusername=Musername.getText();
					String Inputpassword=Mpass.getText();
					String encode=new String(encoder.encodeToString(Inputpassword.getBytes()));
					if(Inputusername.isEmpty()||Inputpassword.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Input Credentials");
						Inputusername=Musername.getText();
						Inputpassword=Mpass.getText();
						encode=encoder.encodeToString(Inputpassword.getBytes());
					}
					else {
					PreparedStatement stmt=con.prepareStatement("select Mpassword from managerlogin where Mname=?");
					stmt.setString(1, Inputusername);
					//stmt.executeUpdate();
					ResultSet result=stmt.executeQuery();
					if(result.next())
					{
						String dbpass=result.getString(1);
						if (dbpass.equals(Inputpassword))
						{
						ManagerLogin Mclose=new ManagerLogin();
						ManagerPage Mopen=new ManagerPage();
						Mclose.setVisible(false);
						JOptionPane.showMessageDialog(null,"Manager log in succesful");
						Mopen.setTitle("Welcome "+Inputusername);
						Mopen.setVisible(true);
						dispose();
						}
						else {
							JOptionPane.showMessageDialog(null,"Manager Log in failed Enter correct log in credentials");
							
						}
							
					}
					}
					con.close();  
				}catch(Exception S){ System.out.println(S+"Error");}   
	}	
				
		});
		
//		btnNewButton_1 = new JButton("SIGN UP");
//		btnNewButton_1.setBackground(Color.PINK);
//		btnNewButton_1.setForeground(Color.BLACK);
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null,"To sign up as a manager kindly contact the administrator via maigualonah@gmail.com and wait for yor log in creadentials");}   
//		}
//		
//		);
		
		JButton Back = new JButton("Back");
		Back.setBackground(Color.PINK);
		Back.setForeground(Color.BLACK);
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerLogin Mclose=new ManagerLogin();
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(Mpass)
						.addComponent(Musername, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
					.addComponent(Back)
					.addGap(24))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
//					.addComponent(btnNewButton_1)
					.addContainerGap(207, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Musername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(Mpass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton))
//						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addComponent(Back)
					.addContainerGap(149, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
