import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
Font f1;
	
Login(){
		
		f1 = new Font("바탕",Font.PLAIN,15);
		
		JLabel lb1 = new JLabel();            
		lb1.setBounds(20,200, 300,50);  
		lb1.setFont(f1);
   
		JLabel lb2=new JLabel("Username:");    
		lb2.setBounds(20,50, 80,30);
		lb2.setFont(f1);
		
		JTextField tf1 = new JTextField();  
		tf1.setBounds(100,50, 150,30);
		tf1.setFont(f1);
		
		JLabel lb3=new JLabel("Password:");    
		lb3.setBounds(20,100, 80,30);
		lb3.setFont(f1);
		
		JPasswordField pw1 = new JPasswordField();   
		pw1.setBounds(100,100,150,30);
		pw1.setFont(f1);
		
		JButton bt1 = new JButton("Login");    
		bt1.setBounds(40,150, 100,30);    
		bt1.setFont(f1);
		bt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				String id = tf1.getText().trim();
				String pw = pw1.getText().trim();
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
					System.out.println("연결완료");
					
					Statement stmt=conn.createStatement();
					
					
//					stmt.executeUpdate("update student set name='"+tfName.getText()+"',dept='"+tfDepartment.getText()+"', address = '"+tfAddress.getText()+"' where id='"+tfId.getText()+"'");
					
					ResultSet rs=stmt.executeQuery("select pw from member where id = '"+id+"'");

					if(rs.next()) {
						if(rs.getString(1).contentEquals(pw)) {
							JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
						
						}else {
							JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
						}
						
					}
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력하셔야 됩니다.","아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
					
					rs.close();
					stmt.close();
					conn.close();
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			
			}
		});
		
		
		JButton bt2 = new JButton("회원가입");
		bt2.setBounds(160, 150, 100, 30);
		bt2.setFont(f1);
		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new NewAdd();
			}
			
		});
		
		bt1.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {       
				String data = "Username " + tf1.getText();  
				data += ", Password: "   
						+ new String(pw1.getPassword());   
				lb1.setText(data);          
             }  
          });   
 
		add(lb1);add(pw1);
		add(lb2);add(tf1);
		add(lb3);add(bt1);
		add(bt2);
		setTitle("Password EX");
		setSize(320,320);
		setLayout(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Login();

	}

}
