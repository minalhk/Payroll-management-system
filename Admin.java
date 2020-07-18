import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Admin extends JFrame implements ActionListener,KeyListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,le;
	JTextField t1,t2,t3,t4,t5,t6,t7,te;
	JButton b1,b2,b3,b4;

	Admin()
	{
		setLayout(new GridLayout(10,2,5,5));
		le=new JLabel("Search Employee to Update/Delete:");
		te=new JTextField();
		l1=new JLabel("Employee ID:");
		t1=new JTextField();
		te.addKeyListener(this);
		l2=new JLabel("Employee Name:");
		t2=new JTextField();
		l3=new JLabel("Salary:");
		t3=new JTextField();
		l4=new JLabel("Deduction:");
		t4=new JTextField();
		l5=new JLabel("OverTime:");
		t5=new JTextField();
		l6=new JLabel("Bonus:");
		t6=new JTextField();
		l7=new JLabel("Total Salary:");
		t7=new JTextField();
		b1=new JButton("ADD");
		b2=new JButton("UPDATE");
		b3=new JButton("DELETE");
		b4 =new JButton("CLOSE");
		add(le);add(te);
		add(l1);add(t1);
		add(l2);add(t2);
		add(l3);add(t3);
		add(l4);add(t4);
		add(l5);add(t5);
		add(l6);add(t6);
		add(l7);add(t7);
		add(b1);add(b2);
		add(b3);add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		setSize(400,400);
		setVisible(true);
	}
	public void keyPressed(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}
	public void keyReleased(KeyEvent ke)
	{
		
		Connection con;
		PreparedStatement ps1;
		ResultSet rs;
		int flg=0;
		try
		{
			int id,d,ot,t,sal;
			float bonus;
			String name;
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://10.10.10.54:3306/t31348db","t31348","t31348");  
			System.out.println("COnnection created");
			id=Integer.parseInt(te.getText());
			ps1=con.prepareStatement("select * from Employees where id=?");
			ps1.setInt(1,id);
			rs=ps1.executeQuery();
			if(rs.next()==false)
				flg=0;
			else
			{
				t1.setText(""+rs.getInt(1));
				t2.setText(""+rs.getString(2));
				t3.setText(""+rs.getInt(3));
				t4.setText(""+rs.getInt(4));
				t5.setText(""+rs.getInt(5));
				t6.setText(""+rs.getFloat(6));
				t7.setText(""+rs.getInt(7));
			}
			con.close();						
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e);
		}
		if(flg==0)
			System.out.println("No such Employee");
	}
	public void actionPerformed(ActionEvent e)
	{
		Connection con=null;
		PreparedStatement ps,ps1;
		ResultSet rs;
		try
		{
			int id,d,ot,t,sal;
			float bonus;
			String name;
			JButton b=(JButton)e.getSource();
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://10.10.10.54:3306/t31348db","t31348","t31348");  
			System.out.println("Connection done");
			
			if(b==b1)
			{
				id=Integer.parseInt(t1.getText());
				name=t2.getText();
				sal=Integer.parseInt(t3.getText());
				ps=con.prepareStatement("insert into Employees values(?,?,?,?,?,?,?)");
				ps.setInt(1,id);
				ps.setString(2,name);
				ps.setInt(3,sal);
				ps.setInt(4,0);
				ps.setInt(5,0);
				ps.setFloat(6,0);
				ps.setInt(7,sal);
				ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Added Successfully");
			}
			if(b==b2)
			{
				id=Integer.parseInt(t1.getText());
				ps=con.prepareStatement("select * from Employees where id=?");
				ps1=con.prepareStatement("update Employees set ename=?,salary=?,deduction=?,overtime=?,bonus=?,total=? where id=?");
				while(true)
				{
					if(id==0)
						break;
					ps.setInt(1,id);
					rs=ps.executeQuery();
					if(rs.next()==false)
					{
						JOptionPane.showMessageDialog(null,"Record not exists");
						break;
					}
					name=t2.getText();
					sal=Integer.parseInt(t3.getText());
					d=Integer.parseInt(t4.getText());
					ot=Integer.parseInt(t5.getText());
					bonus=Float.parseFloat(t6.getText());
					float f=(sal*(bonus/100));
					t=(sal-d)+(int)f;
					t7.setText(""+t);
					ps1.setString(1,name);
					ps1.setInt(2,sal);
					ps1.setInt(3,d);
					ps1.setInt(4,ot);
					ps1.setFloat(5,bonus);
					ps1.setInt(6,t);
					ps1.setInt(7,id);
					ps1.executeUpdate();
					break;
				}
			JOptionPane.showMessageDialog(null,"Updated Successfully");
			}
			if(b==b3)
			{
				id=Integer.parseInt(t1.getText());
				ps=con.prepareStatement("select * from Employees where id=?");
				ps1=con.prepareStatement("delete from Employees where id=?");
				id=Integer.parseInt(t1.getText());
				while(true)
				{
					if(id==0)
						break;
					ps.setInt(1,id);
					rs=ps.executeQuery();
					if(rs.next()==false)
					{
						JOptionPane.showMessageDialog(null,"No Data Found");
						break;
					}
					ps1.setInt(1,id);
					ps1.executeUpdate();
					break;
				}
				JOptionPane.showMessageDialog(null,"Deleted Successfully");
			}
			if(b==b4)
			{
				setVisible(false);
				new UserLogin();
			}
		con.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null,""+ex);
		}
		
	}
	
}
				
				
			
