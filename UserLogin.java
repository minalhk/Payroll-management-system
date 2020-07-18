import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class UserLogin extends JFrame implements ActionListener{
	JLabel l1,l2,li,l;
	JTextField t1,t2;
	JButton b1,b2;
	ImageIcon im;
	UserLogin()
	{
		setLayout(null);
		l=new JLabel("PAYROLL MANAGEMENT SYSTEM");
		l.setBounds(150,10,750,50);
		add(l);
		im=new ImageIcon("Emp.png");
		li=new JLabel(im);
		li.setBounds(100,50,800,300);
		l1=new JLabel("USERNAME");
		l1.setBounds(400,400,120,30);
		add(l1);
		t1=new JTextField(20);
		t1.setBounds(510,400,120,30);
		add(t1);
		l2=new JLabel("PASSWORD");
		l2.setBounds(400,470,120,30);
		add(l2);
		t2=new JPasswordField(20);
		t2.setBounds(510,470,120,30);
		add(t2);
		b1=new JButton("LOGIN");
		b1.setBounds(410,510,100,30);
		b1.addActionListener(this);
		add(b1);
		b2=new JButton("CANCEL");
		b2.setBounds(520,510,100,30);
		b2.addActionListener(this);
		add(b2);
		add(li);
		setSize(1000,600);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton b=(JButton)ae.getSource();
		String s2,d2;
		int s1,d1=0,flg=0;
		if(b==b1)
		{
			try{
			s1=Integer.parseInt(t1.getText());
			s2=t2.getText();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://10.10.10.54:3306/t31348db","t31348","t31348");  
				System.out.println("Connection done");
				PreparedStatement ps=con.prepareStatement("select * from EmployeeLog");
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					d1=rs.getInt(1);
					System.out.println(d1);
					d2=rs.getString(2);
					System.out.println(d2);
					if(s1==d1 && s2.equals(d2)){
						flg=1;
						break;
					}
				}
			if(flg==1 && d1==0){
				JOptionPane.showMessageDialog(null,"Admin Login Successfull");
				setVisible(false);
				new Admin();
			}
			else if(flg==1){
				JOptionPane.showMessageDialog(null,"Employee Login Successfull");
				setVisible(false);
				new Emp(d1);				
			}
			else
				JOptionPane.showMessageDialog(null,"Username or Password Incorrect");
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null,""+e);
		}
		}
		if(b==b2)
		{
			t1.setText("");
			t2.setText("");
		//	setVisible(false);
		}
		
			
	}
	public static void main(String []a)
	{
		UserLogin u=new UserLogin();
	}
}
	 
