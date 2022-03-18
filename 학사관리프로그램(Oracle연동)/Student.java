import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Student extends JPanel{
	JTextField tfId=null;  
	JTextField tfName=null;
	JTextField tfDepartment=null;
	JTextField tfAddress=null;
	JTextArea taList=null;
	JButton btnSave=null;  // insert -> Create
	JButton btnList=null;  // select -> Read 
	JButton btnModify=null;// update -> Update
	JButton btnRemove=null;// delete -> Delete
	JMenuItem menuItem1=null;//학생정보 메뉴
	
	JButton btnSearch = null; // 검색버튼
	
	DefaultTableModel model = null; // 테이블에 들어가는 데이터를 담당
	JTable table = null; // 테이블
	
	public Student() {
		
		this.setLayout(new FlowLayout());
		
		this.add(new JLabel("학번"));
		this.tfId=new JTextField(13);
		this.add(this.tfId);
		
		this.btnSearch = new JButton("Search");
		this.add(this.btnSearch);
		this.btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
					System.out.println("연결완료");
					
					Statement stmt=conn.createStatement();
					
					ResultSet rs=stmt.executeQuery("select * from student where id = '"+tfId.getText()+"'");
					
					model.setRowCount(0); //JTable초기화
					while(rs.next()) {

						String[] row=new String[4];//컬럼의 갯수가 4
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						row[3]=rs.getString("address");
						model.addRow(row);
						
						tfId.setText(rs.getString("id"));
						tfName.setText(rs.getString("name"));
						tfDepartment.setText(rs.getString("dept"));
						tfAddress.setText(rs.getString("address"));
					}
					rs.close();
					stmt.close();
					conn.close();
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		this.add(new JLabel("이름"));		
		this.tfName=new JTextField(20);
		this.add(this.tfName);
		
		this.add(new JLabel("학과"));
		this.tfDepartment=new JTextField(20);
		this.add(this.tfDepartment);
		
		this.add(new JLabel("주소"));
		this.tfAddress=new JTextField(20);
		this.add(this.tfAddress);
		
//		this.taList=new JTextArea(17,23);
//		this.add(new JScrollPane(this.taList));
		String colName[]={"학번","이름","학과","주소"};
		this.model=new DefaultTableModel(colName,0);
		this.table = new JTable(this.model);
		this.table.setPreferredScrollableViewportSize(new Dimension(250,300));//테이블 크기 250px X 300px
		this.table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				table=(JTable)e.getComponent();//클릭한 테이블 구하기
				model = (DefaultTableModel)table.getModel();
				
				String id = (String)model.getValueAt(table.getSelectedRow(), 0);//학번
				tfId.setText(id);

			    String name=(String)model.getValueAt(table.getSelectedRow(), 1);
			    tfName.setText(name);

			    String dept=(String)model.getValueAt(table.getSelectedRow(), 2);
			    tfDepartment.setText(dept);

			    String address=(String)model.getValueAt(table.getSelectedRow(), 3);
			    tfAddress.setText(address);
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}	
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		JScrollPane sp=new JScrollPane(this.table);
		
		this.add(sp);
		
		
		this.btnSave=new JButton("등록");
		this.add(this.btnSave);
		this.btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
					System.out.println("연결완료");
					
					Statement stmt=conn.createStatement();
					
					
					stmt.executeUpdate("insert into student values('"+tfId.getText()+"','"+tfName.getText()+"','"+tfDepartment.getText()+"','"+tfAddress.getText()+"')"); //영향을 받은 행의 수가 리턴됨
								
					ResultSet rs=stmt.executeQuery("select * from student");

					model.setRowCount(0); //JTable초기화
					while(rs.next()) {
						String[] row=new String[4];//컬럼의 갯수가 4
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						row[3]=rs.getString("address");
						model.addRow(row);
					}
					rs.close();
					stmt.close();
					conn.close();
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}});
		
		
		this.btnList = new JButton("목록");
		this.btnList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
					System.out.println("연결완료");
					
					Statement stmt=conn.createStatement();
					
					
					//stmt.executeUpdate("insert into student values('111111','홍길동','철학과')"); //영향을 받은 행의 수가 리턴됨
					
					//stmt.executeUpdate("update student set dept='전자공학' where id='1234567'");
					
					//stmt.executeUpdate("delete from student where id='1234567'");
					
					ResultSet rs=stmt.executeQuery("select * from student");
					model.setRowCount(0); //JTable초기화
					while(rs.next()) {
						String[] row=new String[4];//컬럼의 갯수가 4
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						row[3]=rs.getString("address");
						model.addRow(row);
						
						
					}
					rs.close();
					stmt.close();
					conn.close();
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
				
			}
			
		});
		this.add(this.btnList);
		this.btnModify = new JButton("수정");
		this.btnModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
					//Connection
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
					System.out.println("연결완료");
					
					Statement stmt=conn.createStatement();
					
					stmt.executeUpdate("update student set name='"+tfName.getText()+"',dept='"+tfDepartment.getText()+"', address = '"+tfAddress.getText()+"' where id='"+tfId.getText()+"'");
					
					ResultSet rs=stmt.executeQuery("select * from student");

					model.setRowCount(0); //JTable초기화
					while(rs.next()) {
						String[] row=new String[4];//컬럼의 갯수가 3
						row[0]=rs.getString("id");
						row[1]=rs.getString("name");
						row[2]=rs.getString("dept");
						row[3]=rs.getString("address");

						model.addRow(row);
					}
					rs.close();
					stmt.close();
					conn.close();
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
	
		this.add(this.btnModify);
		this.btnRemove = new JButton("삭제");
		this.btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
						//Connection
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","hong");// 연결
						System.out.println("연결완료");
						
						Statement stmt=conn.createStatement();
						
						stmt.executeUpdate("delete from student where id = '"+tfId.getText()+"'");
						
						ResultSet rs=stmt.executeQuery("select * from student");
						
						model.setRowCount(0); //JTable초기화
						while(rs.next()) {
							String[] row=new String[4];//컬럼의 갯수가 3
							row[0]=rs.getString("id");
							row[1]=rs.getString("name");
							row[2]=rs.getString("dept");	
							row[3]=rs.getString("address");

							model.addRow(row);
						}
						rs.close();
						stmt.close();
						conn.close();
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "삭제되었습니다", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		this.add(this.btnRemove);
		
		this.setSize(280, 550);
		this.setVisible(true);
	}
}
