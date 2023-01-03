import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EmployeeList extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeList frame = new EmployeeList();
					frame.setTitle("Employees in the company");
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
	public EmployeeList() {
		String column[]={
				"EmployeeID", "Name", "Role", "PhoneNumber"};
		String data[][]=new String[10][4];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost/maema","root",""); 
			PreparedStatement stmt=con.prepareStatement("select *from employeerecords");
			ResultSet result=stmt.executeQuery();
			int i=1;
			data[0][0]="EmployeeID";
			data[0][1]="Name";
			data[0][2]="Role";
			data[0][3]="PhoneNumber";
			while(result.next())
			{
				int ID=result.getInt("employeeid");
				String name=result.getString("Name");
				String role=result.getString("role");
				String phone=result.getString("phonenumber");
				data[i][0]=ID+"";
				data[i][1]=name;
				data[i][2]=role;
				data[i][3]=phone;
		
				
				i++;
			}

            
			con.close();  
				}catch(Exception S){ System.out.println(S);} 
		DefaultTableModel model= new DefaultTableModel(data,column);
		table = new JTable(model);
		table.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 15));
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.BLACK);
		table.getColumnModel().getColumn(0).setPreferredWidth(127);
		table.getColumnModel().getColumn(1).setPreferredWidth(137);
		table.getColumnModel().getColumn(2).setPreferredWidth(122);
		table.getColumnModel().getColumn(3).setPreferredWidth(127);
		
		
	    table.addMouseListener(new MouseListener() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	            String IDselected = (String) table.getValueAt(table.getSelectedRow() ,0);
	            System.out.println(IDselected);
				EmployeeList Elist=new EmployeeList();
				AddEmployee Adde=new AddEmployee(Integer.parseInt(IDselected));
				Elist.setVisible(false);
				Adde.setTitle("Edit employee Details");
				Adde.setVisible(true);
				dispose();
	            
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
		JButton AddEmployee = new JButton("Edit Employee Records");
		AddEmployee.setBackground(Color.PINK);
		AddEmployee.setForeground(Color.BLACK);
		AddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeList Elist=new EmployeeList();
				AddEmployee Adde=new AddEmployee(0);
				Elist.setVisible(false);
				Adde.setTitle("Edit employee Details");
				Adde.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeList open=new EmployeeList();
				ManagerPage close =new ManagerPage();
				open.setTitle("Select Log in type");
				open.setVisible(false);
				close.setTitle("Welcome manager");
				close.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Home page");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login open=new Login();
				EmployeeList close =new EmployeeList();
				open.setTitle("Select Log in type");
				open.setVisible(true);
				close.setVisible(false);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(AddEmployee)
					.addGap(86)
					.addComponent(btnNewButton)
					.addGap(97)
					.addComponent(btnNewButton_1)
					.addGap(122))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddEmployee)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
