//폴더 오른쪽 마우스 - properties - java build path - Libraries - classpath - add external JARs 또는 폴더에서 jar파일 복사 후 이클립스 안에서 폴더에 오른쪽 마우스 - paste - add JARs..

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Haksa extends JFrame{

 JPanel panel;  // 메뉴별 화면이 출력되는 패널
 Haksa(){
  setTitle("학사관리시스템"); 
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 누르면 프로그램 종료
  
  JMenuBar bar=new JMenuBar();
  
  JMenu m_student=new JMenu("학생관리");//File메뉴
  bar.add(m_student);
  JMenu m_book=new JMenu("도서관리");//Edit메뉴
  bar.add(m_book);
  
  JMenuItem mi_list=new JMenuItem("학생정보");
  m_student.add(mi_list);
  
  mi_list.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e) {
    panel.removeAll(); //모든컴포넌트 삭제
    panel.revalidate(); //다시 활성화
    panel.repaint();    //다시 그리기
    panel.add(new Student()); //화면 생성.
    panel.setLayout(null);//레이아웃적용안함
    
   }});
   
  JMenuItem mi_bookRent=new JMenuItem("대출목록");
  m_book.add(mi_bookRent);
  
  mi_bookRent.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e) {
    panel.removeAll(); //모든컴포넌트 삭제
    panel.revalidate(); //다시 활성화
    panel.repaint();    //다시 그리기
    panel.add(new BookRent()); //화면 생성.
    panel.setLayout(null);//레이아웃적용안함
    
   }});
  
  panel=new JPanel();//panel생성
  add(panel);//프레임에 패널 추가
  
  setJMenuBar(bar);
  setSize(800,600);  
  setVisible(true);
 }
 public static void main(String[] args) {
  
  new Haksa();
 }

}
