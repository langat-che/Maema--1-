import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.Color;


public class EmployeeSignup extends JFrame {
	
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField userID;
	private javax.swing.JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeSignup frame = new EmployeeSignup();
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

	
    public EmployeeSignup() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
    	
    	Decoder decoder=Base64.getDecoder();
		Encoder encoder= Base64.getEncoder();
    	
        jButton1 = new javax.swing.JButton();
        jLabel1= new javax.swing.JLabel();
        jLabel2= new javax.swing.JLabel();
        userID = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Sign up");
        setBackground(new java.awt.Color(139, 139, 146));

        jButton1.setBackground(Color.PINK);
        jButton1.setText("Sign Up");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				try{
					String Inputusername=userID.getText();
					String Inputpassword=pass.getText();
					String encrypt=encoder.encodeToString(Inputpassword.toString().getBytes());
					String bytes=new String(decoder.decode(encrypt));
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					
					PreparedStatement stmt=con.prepareStatement("insert into employeelogin(EmployeeID,password) values(?,?)"); 
					stmt.setString(1, Inputusername);
					stmt.setString(2,encrypt);
					stmt.executeUpdate();
					if(Inputusername.isEmpty()||Inputpassword.toString().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Input Credentials");
						Inputusername=userID.getText();
						Inputpassword=pass.getText();
					}
					else {
					JOptionPane.showMessageDialog(null,"Employee added succesfully");
			        jLabel1.setText("Employee ID :");
			        jLabel2.setText("Password :");
					}
					con.close();  
						}catch(Exception S){ System.out.println(S);}   
			}
        });

        jLabel1.setText("Employee ID :");
        jLabel2.setText("Password :");

        JButton jButton2 = new JButton("Back");
        jButton2.setBackground(Color.PINK);
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeSignup Mclose=new EmployeeSignup();
				EmployeeLogin Mopen=new EmployeeLogin();
				Mclose.setVisible(false);
				Mopen.setTitle("Select log in type");
				Mopen.setVisible(true);
				dispose();
			}
		});
		

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addGap(46)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel1)
        						.addComponent(jLabel2))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(pass)
        						.addComponent(userID, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        					.addContainerGap(177, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jButton1)
        					.addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
        					.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
        					.addGap(62))))
		);
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addGap(66)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(userID,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(52)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLabel2)
        				.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1)
        				.addComponent(jButton2))
        			.addGap(34))
        );
        getContentPane().setLayout(layout);

        pack();
		}
}

   
