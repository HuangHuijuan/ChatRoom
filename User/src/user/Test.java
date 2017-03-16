package user;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.UnsupportedLookAndFeelException;

import utils.EncodingImageIcon;

public class Test {


	private static final String slash = File.separator;
	private JFrame frame = new JFrame();
	private JPanel panel;
	private JTextPane pane;
	public static void main(String[] args) {

		String time = "1:12:13";
		System.out.println(time.matches("((((0?[0-9])|([1][0-9])|([2][0-4])):([0-5]?[0-9])((s)|(:([0-5]?[0-9])))))?$"));

		JLabel label = new JLabel("test");
		JTextPane t = new JTextPane();
		
//		Pattern p = Pattern.compile("[0-9][0-5]{1}[0-9][0-5]{1}[0-9][0-5]{1}");
//		Matcher m = p.matcher(time);
//		System.out.println(m.matches());
//		try {
//			System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
//			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
//		} catch (UnsupportedLookAndFeelException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		Test test = new Test();
//		test.ini();
//		String test = " hello";
//		System.out.println(test.substring(0, 2));
//		String[] r = "\\pic\\woshi\\pic\\liyu\\pic\\lei\\pic\\".split("#" + "pic" + "#");
//		System.out.println("\\pic\\woshi\\pic\\liyu\\pic\\lei\\pic\\");
//		System.out.println(r.length);
//		for(int i=0;i<r.length;i++){
//			System.out.println(r[i]);
//		}
//		System.out.println("*pic*nishi".substring(0, 5));
//		System.out.println(!"*pic*nishi".substring(0, 5).equals("*pic*"));
	}
	
	private void ini(){
		
		JLabel l = new JLabel(new EncodingImageIcon("F:" + slash + "reservation" + slash + 2 +".png","hello"));
		JLabel a = new JLabel("nimaowoyaoshilnizenmebuqusi");
		a.setSize(1,1);
		a.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		pane = new JTextPane();
		pane.setPreferredSize(new Dimension(400, 40));
		//pane.insertComponent(a);
		//pane.insertComponent(l);
		pane.insertComponent(l);
		pane.insertComponent(a);
//		System.out.println(pane.getText());
//		System.out.println(pane.getComponentCount());
//		System.out.println(pane.getText());
				Box b = Box.createHorizontalBox();
				b.add(new JLabel("nihao"));
				b.add(new JLabel(new ImageIcon("F:" + slash + "reservation" + slash + 1 +".png")));
				b.add(new JLabel(new ImageIcon("F:" + slash + "reservation" + slash + 2 +".png")));
				b.add(new JLabel("bug"));
				b.add(Box.createHorizontalGlue());
//		panel.add(pane);
//		panel.add(pane);
				panel.add(b);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	private String insert_image(String text){
		List<Integer> position = new ArrayList<Integer>();
		int ini_position = 0;
		String result = "";
		
		do{
			ini_position = text.indexOf("\\pic\\", ini_position);
			if(ini_position != -1){
				position.add(ini_position);
			}
			ini_position += 1;
		}
		while(ini_position != 0);
				
		for(int i=0,ini_p=0;i<position.size();i++){
			result += text.substring(ini_p, position.get(i)+5).replace("\\pic\\", "100");
			ini_p = position.get(i)+5;
		}
		return result;
	}
}
