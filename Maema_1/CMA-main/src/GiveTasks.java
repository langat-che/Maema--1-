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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GiveTasks extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private JTextField assignment;
	private JTextField Duedate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiveTasks frame = new GiveTasks(1);
					frame.setVisible(true);
					frame.setTitle("Assign");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GiveTasks(int p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		JLabel lblNewLabel = new JLabel("EmployeeID");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.BLACK);
		
		ID = new JTextField();
		ID.setColumns(10);
		ID.setText(String.valueOf(p));
		
		JLabel lblNewLabel_1 = new JLabel("Assignment");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
		
		assignment = new JTextField();
		assignment.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Due Date");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
		lblNewLabel_3.setForeground(Color.BLACK);
		
		Duedate = new JTextField();
		Duedate.setColumns(10);
		
		JButton btnNewButton = new JButton("Assign");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					PreparedStatement stmt=con.prepareStatement("insert into assignments(ID,assignment,Duedate) values(?,?,?)");
					stmt.setString(1,ID.getText());
					stmt.setString(2,assignment.getText());
					stmt.setString(3,Duedate.getText());
					stmt.executeUpdate();
					if(ID.getText()==null||assignment.getText()==null||Duedate.getText()==null)
					{			
						JOptionPane.showMessageDialog(null,"Please insert values into the fields");	
						
					}
					else {
					//stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Employee Assigned Successfully");
						ID.setText("");
						assignment.setText("");
						Duedate.setText("");
					}
					con.close(); 
					}
				 
				catch(Exception E)
				{
					System.out.println(E);
				}
				}
		});
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerPage close =new ManagerPage();
				GiveTasks open=new GiveTasks(1);
				open.setTitle(getTitle());
				close.setVisible(true);
				close.setTitle("Edit Employee records");
				open.setVisible(false);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(36))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Duedate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(assignment, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(assignment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(Duedate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
