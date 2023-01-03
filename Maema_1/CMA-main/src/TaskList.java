import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class TaskList extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskList frame = new TaskList(0);
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
	public TaskList(int pid) {
		String column[]={
				"Assignments","Due date","Status","Manager Approval"};
		String data[][]=new String[10][5];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost/maema","root",""); 
			PreparedStatement stmt=con.prepareStatement("select assignment,Duedate,status,Approval from assignments where ID=?");
			stmt.setInt(1, pid);
			ResultSet result=stmt.executeQuery();
			int i=1;
			data[0][0]="Assignments";
		    data[0][1]="Due Date";
		    data[0][2]="Status";
		    data[0][3]="Approval";
			while(result.next())
			{
				String assign=result.getString(1);
				String duedate=result.getString(2);
				String status=result.getString(3);
				String approval=result.getString(4);
				data[i][0]=assign;
				data[i][1]=duedate;
				data[i][2]=status;
				data[i][3]=approval;
				i++;
			}
			con.close();  
		}catch(Exception S){ System.out.println(S);} 
       DefaultTableModel model= new DefaultTableModel(data,column);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerApproval Mclose=new ManagerApproval();
				TaskList Mopen=new TaskList(0);
				Mclose.setTitle("Log in as a manager");
				Mclose.setVisible(true);
				Mopen.setVisible(false);
				dispose();
			}
		});
		
		table = new JTable(model);
		table.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 15));
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseListener() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	            String IDselected = (String) table.getValueAt(table.getSelectedRow() ,0);
	            System.out.println(IDselected);
	            try{  
	    			Class.forName("com.mysql.cj.jdbc.Driver");  
	    			Connection con=DriverManager.getConnection(  
	    			"jdbc:mysql://localhost/maema","root",""); 
	    			PreparedStatement stmt=con.prepareStatement("update assignments set Approval=? where ID=? and assignment=? ");
	    			stmt.setInt(2, pid);
	    			stmt.setString(1,"Approved");
	    			stmt.setString(3, IDselected);
	    			stmt.executeUpdate();
	    			try{  
	    				Class.forName("com.mysql.cj.jdbc.Driver");  
	    				 con=DriverManager.getConnection(  
	    				"jdbc:mysql://localhost/maema","root",""); 
	    				 stmt=con.prepareStatement("select assignment,Duedate,status,Approval from assignments where ID=?");
	    				stmt.setInt(1, pid);
	    				ResultSet result=stmt.executeQuery();
	    				int i=1;
	    				data[0][0]="Assignments";
	    			    data[0][1]="Due Date";
	    			    data[0][2]="Status";
	    			    data[0][3]="Approval";
	    				while(result.next())
	    				{
	    					String assign=result.getString(1);
	    					String duedate=result.getString(2);
	    					String status=result.getString(3);
	    					String approval=result.getString(4);
	    					data[i][0]=assign;
	    					data[i][1]=duedate;
	    					data[i][2]=status;
	    					data[i][3]=approval;
	    					i++;
	    				}
	    				con.close();  
	    			}catch(Exception S){ System.out.println(S);} 
	    			JOptionPane.showMessageDialog(null,IDselected+" assignment"+"is Approved");
	    			con.close();  
	    		}catch(Exception S){ System.out.println(S);} 
	            DefaultTableModel model2= new DefaultTableModel(data,column);
	            table.setModel(model2);
	            
	        }
	        @Override
	        public void mouseExited(MouseEvent e) {
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        }
	    });
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(363, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton))
		);
		contentPane.setLayout(gl_contentPane);
	}
}