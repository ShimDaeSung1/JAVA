import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewAdd extends JFrame {
	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfPhone;
	
	public NewAdd() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460,470);
		setLocation(720,300);
//		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblJoin = new JLabel("회원가입");
		Font f1 = new Font("돋움", Font.BOLD, 20); //궁서 바탕 돋움
		lblJoin.setFont(f1); 
		lblJoin.setBounds(159, 41, 101, 20);
		contentPane.add(lblJoin);

		JLabel lblUsername = new JLabel("password");
		lblUsername.setBounds(69, 163, 69, 20);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("username");
		label.setBounds(69, 113, 69, 20);
		contentPane.add(label);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(69, 210, 69, 20);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(69, 257, 69, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("phone");
		lblPhone.setBounds(69, 304, 69, 20);
		contentPane.add(lblPhone);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(159, 106, 186, 35);
		contentPane.add(tfUsername);
		
		tfPassword = new JPasswordField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(159, 156, 186, 35);
		contentPane.add(tfPassword);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(159, 203, 186, 35);
		contentPane.add(tfName);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(159, 250, 186, 35);
		contentPane.add(tfEmail);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(159, 297, 186, 35);
		contentPane.add(tfPhone);
		
		joinCompleteBtn = new JButton("회원가입완료");
		joinCompleteBtn.setBounds(206, 363, 139, 29);
		contentPane.add(joinCompleteBtn);
		
		setVisible(true);
		
		joinCompleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				dispose();
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
					System.out.println("연결완료");
					
					Statement stmt=conn.createStatement();
					
					
					stmt.executeUpdate("insert into member values('"+tfUsername.getText()+"','"+tfPassword.getText()+"','"+tfName.getText()+"','"+tfEmail.getText()+"','"+tfPhone.getText()+"')");
					
//					ResultSet rs=stmt.executeQuery("select pw from member where id = '"+id+"'");			
				
					stmt.close();
					conn.close();
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});





	}

	public static void main(String[] args) {
		new NewAdd();
		
	}

}
