import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

class LoginDialog extends JPanel{

	public LoginDialog() {
		setLayout(null);
		setSize(460,470);
		setVisible(true);
	}
	
}

public class Start extends JFrame {
	JScrollPane scrollPane;
	JButton button = null;
	Font f1;
	
	
	private Image backGround=new ImageIcon(Start.class.getResource("아주대학교.jpg")).getImage();
	public void paint(Graphics g) {//그리는 함수
		g.drawImage(backGround, 10, 17, this);//background를 그려줌
	}
	Start(){
		f1 = new Font("바탕",Font.PLAIN,15);
		setLayout(null);
		setTitle("아주대학교 학사관리 프로그램");
		
		
		button = new JButton("접속");
		button.setBounds(350,370, 70,30);
		add(button);

	    button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
				Login lg = new Login();
				lg.setVisible(true);
			}
			
		});
	    //프레임의 배경이 아주대 로고이므로 그 위에 버튼을 보이게 하려면
	    //패널을 올리고 그 위에 버튼을 붙여줘야함
		LoginDialog p = new LoginDialog();
		p.add(button);
		scrollPane = new JScrollPane(p);
        setContentPane(scrollPane);
        add(p);
		
		
	    this.setSize(460,470);
	    this.setVisible(true);
	
		
	}

	
	public static void main(String[] args) {
		new Start();

	}

}

// 1. 도서관리 메뉴에 대출현황 메뉴아이템을 만들어서 학과별 대출횟수를 집계해서
// 파이차트로 출력

// 2. 로그인 기능 추가. 첫 화면에서 모달다이얼로그 띄워서 로그인 후 사용

// 3. 첫화면에 인트로 이미지 넣기

// 4. 버튼 예쁘게 꾸미기
