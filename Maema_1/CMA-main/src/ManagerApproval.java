import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.ListSelectionModel;

public class ManagerApproval extends JFrame {
	
    private javax.swing.JButton jButton1;
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField Ename;
    private javax.swing.JTextField EID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerApproval frame = new ManagerApproval();
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
    public ManagerApproval() {
		getContentPane().setBackground(Color.LIGHT_GRAY);

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Ename = new javax.swing.JTextField();
        EID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manager Task Aproval");
        setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setText("Employee Name :");

        jLabel2.setText("Employee ID:");


        jButton1.setBackground(Color.PINK);
        jButton1.setText("Fetch Data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try{  
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost/maema","root",""); 
					PreparedStatement stmt=con.prepareStatement("select *from employeerecords where EmployeeID=?");
					stmt.setString(1,EID.getText());
					ResultSet result=stmt.executeQuery();
					if(result.next()) {
						EID.setText(result.getString(1));
						Ename.setText(result.getString(2));
					}
            	}catch(Exception S){ System.out.println(S);} 
            	
            	ManagerApproval Mclose=new ManagerApproval();
				TaskList Mopen=new TaskList(Integer.parseInt(EID.getText()));
				Mopen.setTitle("Employee Tasks");
				Mclose.setVisible(false);
				Mopen.setVisible(true);
				dispose();
            }
        });

        back.setBackground(Color.PINK);
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ManagerApproval Mclose=new ManagerApproval();
				ManagerPage Mopen=new ManagerPage();
				Mopen.setTitle("Manager Page");
				Mclose.setVisible(false);
				Mopen.setVisible(true);
				dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(jLabel1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jLabel2, Alignment.LEADING)
        				.addComponent(jButton1, Alignment.LEADING))
        			.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(EID)
        					.addGap(108))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(Ename, 143, 143, Short.MAX_VALUE)
        					.addGap(108))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(back)
        					.addGap(27))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(21)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(Ename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel2)
        				.addComponent(EID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(back)
        				.addComponent(jButton1))
        			.addGap(25))
        );
        getContentPane().setLayout(layout);

        pack();
    }
}
