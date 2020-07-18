import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Emp extends JFrame
{
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	int d;
	Emp(int d1)
	{
		d=d1;
		System.out.println("A:"+d);
		setLayout(new GridLayout(10,2,5,5));
		l1=new JLabel("Employee ID:");
		t1=new JTextField();
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
		add(l1);add(t1);
		add(l2);add(t2);
		add(l3);add(t3);
		add(l4);add(t4);
		add(l5);add(t5);
		add(l6);add(t6);
		add(l7);add(t7);
		setVisible(true);
		setSize(400,400);
		show(d);
	}
	void show(int id)
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver"):
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://10.10.10.54:3306/t31348db","t31348","t31348");
			PreparedStatement ps,ps1;
			ResultSet rs;
			while(true)
			{
				if(id==0)
					break;
				ps=con.prepareStatement("select * from Employees where id=?");
				ps.setInt(1,id);
				rs=ps.executeQuery();
				if(rs.next()==false)
				{
					JOptionPane.showMessageDialog(null,"No Data Found");
					break;
				}
				t1.setText(""+rs.getInt(1));
				t2.setText(""+rs.getString(2));
				t3.setText(""+rs.getInt(3));
				t4.setText(""+rs.getInt(4));
				t5.setText(""+rs.getInt(5));
				t6.setText(""+rs.getFloat(6));
				t7.setText(""+rs.getInt(7));
				break;
			}
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e);
		}
	}
}
			
