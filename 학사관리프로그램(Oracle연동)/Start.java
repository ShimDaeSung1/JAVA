import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	
	
	private Image backGround=new ImageIcon(Start.class.getResource("아주대학교2.jpg")).getImage();
	public void paint(Graphics g) {//그리는 함수
		g.drawImage(backGround, 10, 17, this);//background를 그려줌
	}
	Start(){
		setLocation(720,300);
//		setLocationRelativeTo(null);
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

