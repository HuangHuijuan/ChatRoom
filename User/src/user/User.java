package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import utils.Base64CodingImage;
import utils.CodingImage;
import utils.DirectoryFilter;
import utils.EncodingImageIcon;
import utils.FilesFilter;

public class User {

	private String ip = "ws://127.0.0.1:8088/webchat/Servlet";
	private List<EncodingImageIcon> image_list = new ArrayList<EncodingImageIcon>();
	private static int count = 0;
	private static final String slash = File.separator;
	private static final long serialVersionUID = 5397236266401921234L;
	private static CodingImage coding_image = new Base64CodingImage();
	private WebSocketClient webSocketClient = null;
	
	private JFrame frame = new JFrame();
	
	private JLabel lable = new JLabel("test");
	private Icon icon1 = new ImageIcon();
	private JButton button1 = new JButton("连接",icon1);
	private Icon icon2 = new ImageIcon();
	private JButton button2 = new JButton("发送",icon2);
	private Icon icon3 = new ImageIcon();
	private JButton button3 = new JButton("发送图片",icon3);
	private Icon icon4 = new ImageIcon();
	private JButton button4 = new JButton("断开",icon4);
 
	//private JTextField message = new JTextField(40);
	private JTextPane message = new JTextPane();
	private JPanel left_area = new JPanel();
	private JTextArea right_area = new JTextArea(20,20);
	private JScrollPane left_scroll = new JScrollPane(left_area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane right_scroll = new JScrollPane(right_area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane message_scroll = new JScrollPane(message, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("文件");
	private JPopupMenu popupMenu = new JPopupMenu();
	private JFileChooser dfileChooser = new JFileChooser();
	private JFileChooser picfileChooser = new JFileChooser();
	private JFileChooser txtChooser = new JFileChooser();
	private DirectoryFilter dfilter = new DirectoryFilter();
	private FilesFilter picfilter = new FilesFilter();
	private FilesFilter txtfilter = new FilesFilter();

	public void initialize(){
		
		message.setPreferredSize(new Dimension(400, 100));
		message.setBorder(new EtchedBorder());
		message.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("shown");
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("resized");
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("delete");
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("hide");
			}
		});
		
		GridLayout g_layout = new GridLayout(1,4);
		g_layout.setHgap(10);
		JPanel bottom = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(g_layout);
		grid.add(button1);
		grid.add(button2);
		grid.add(button3);
		grid.add(button4);
		bottom.add(grid, BorderLayout.SOUTH);
		bottom.add(message_scroll, BorderLayout.NORTH);
		
		//left_area.setEditable(false);
		left_area.setPreferredSize(new Dimension(400,400));
		left_area.setBackground(Color.white);
		
		BoxLayout boxLayout = new BoxLayout(left_area, BoxLayout.Y_AXIS);
		left_area.setLayout(boxLayout);
		
		right_area.setEditable(false);
		
		JMenuItem reserve = new JMenuItem("保存文件");
		JMenuItem open = new JMenuItem("导入记录");
		file.add(reserve);
		file.add(open);
		menuBar.add(file);
		
		dfilter.setDescription("选择保存文件的目录");
		dfileChooser.setFileFilter(dfilter);
		dfileChooser.setAcceptAllFileFilterUsed(false);
		
		picfilter.setDescription("选择要发送的图片文件");
		picfilter.addExtension("jpg");
		picfilter.addExtension("png");
		picfileChooser.setFileFilter(picfilter);
		picfileChooser.setAcceptAllFileFilterUsed(false);
		
		txtfilter.setDescription("请选择聊天记录文件");
		txtfilter.addExtension("txt");
		txtChooser.setFileFilter(txtfilter);
		txtChooser.setAcceptAllFileFilterUsed(false);
		
		reserve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dfileChooser.setCurrentDirectory(new File("."));
				int result = dfileChooser.showSaveDialog(frame);
				if(result == JFileChooser.APPROVE_OPTION){
				}
			}
		});
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtChooser.setCurrentDirectory(new File("."));
				int result = txtChooser.showOpenDialog(frame);
				if(result == JFileChooser.APPROVE_OPTION){
					view(txtChooser.getSelectedFile());
				}
			}
		});
		
		
		JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
				true,left_scroll,bottom);
		JSplitPane whole = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				true, left, right_scroll);
		frame.add(whole);
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand() == "10"){
					System.out.println("ok");
				}
			}
		});
	
		ActionListener button_listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String action = e.getActionCommand();
				if(action.equals("连接")){
					try {
						connect_to_sever();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(action);
					return;
				}
				else if(action.equals("发送")){
					String me = message.getText();
					//System.out.println(me);
					System.out.println("is" + me);
					if(me == " " || me == null || me == ""){
						System.out.println("empty");
					}
					else{
						//me = "txt" + me;
						send_message(insert_image(me));
						System.out.println(insert_image(me));
						message.setText("");
						image_list.clear();
					}
					
					System.out.println(action);
					
					return;
				}
				else if(action.equals("发送图片")){
					if(webSocketClient != null &&webSocketClient.getReadyState() == 1){
						//使用相对路径
						picfileChooser.setCurrentDirectory(new File("." + slash +"PicFile"));
						int result = picfileChooser.showOpenDialog(frame);
						if(result == JFileChooser.APPROVE_OPTION){
							File image = picfileChooser.getSelectedFile();
							String coding_result = coding_image.encode(image);
							coding_result = "#pic#*pic*" + coding_result + "*end*#pic#";
							EncodingImageIcon imageIcon = new EncodingImageIcon(image.getAbsolutePath(),coding_result);
							image_list.add(imageIcon);
							message.insertIcon(imageIcon);
							//webSocketClient.send(coding_result);
							//System.out.println(image.toString());
						}
					}
					
					else{
						System.out.println("error");
					}

					return;
				}
				else if(action.equals("断开")){
					right_area.setText("");
					close_connection();
					System.out.println(action);
					return;
				}
			}
		};
		button1.addActionListener(button_listener);
		button2.addActionListener(button_listener);
		button3.addActionListener(button_listener);
		button4.addActionListener(button_listener);
		
		left_scroll.add(lable, Component.TOP_ALIGNMENT);
		
		frame.setJMenuBar(menuBar);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			//为了保证在程序退出的时候，即使websocketclient依旧处于连接状态，也可以将它关闭，避免 20005 invalid socket 错误
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(webSocketClient !=null && webSocketClient.getReadyState() == 1){
					webSocketClient.close();
				}
				//释放资源
				frame.dispose();
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
	
	private void connect_to_sever() throws URISyntaxException{
		if(webSocketClient != null){
			System.out.println("exist");
			System.out.println(webSocketClient.getReadyState());
		}
		else {
			System.out.println("in");
			webSocketClient = new WebSocketClient(new URI(ip),new Draft_17()) {

				//连接到webservlet后，websocketclient打开之后调用的方法
				@Override
				public void onOpen(ServerHandshake arg0) {
					// TODO Auto-generated method stub
					System.out.println("open");
					System.out.println(arg0.toString());
				}
				
				@Override
				public void onMessage(ByteBuffer bytes) {
					// TODO Auto-generated method stub
					System.out.println("ok1");
					String name = new String(bytes.array());
					right_area.setText(name + "\n");
				}

				//接收到消息后调用的方法
				@Override
				public void onMessage(String arg0) {
					// TODO Auto-generated method stub
					//System.out.println(arg0);
					Box line = Box.createHorizontalBox();
					String[] results = find(arg0, "#pic#");
					//System.out.println(results.length);
					for(int i=0;i<results.length;i++){
						System.out.println(results[i]);
						if(results[i] == ""){
							
						}
						else{
							if(i == 0){
								//int position = results[0].indexOf(" ", 10);
								//String before = results[i].substring(0, position);
								//String after = results[i].substring(position + 1);
								//line.add(new JLabel(before+ " " +after));
								line.add(new JLabel(results[i]));
							}
							else{
								//说明这是一个txt
								if(results[i].length() < 6|| !(results[i].substring(0, 5).equals("*pic*"))){
									line.add(new JLabel(results[i]));
									System.out.println("why");
								}
								else{
									int position = results[i].indexOf("*end*");
									coding_image.decode(results[i].substring(5,position), "." + slash + "reservation" + slash + count + ".png");
									line.add(new JLabel(new EncodingImageIcon("." + slash + "reservation" + slash + count +".png",results[i].substring(5,position))));
									count++;
								}
							}

						}
					}
					
					
					//String tag = arg0.substring(position + 1, position + 4);
					line.add(Box.createHorizontalGlue());
					left_area.add(line);
					left_area.validate();
					System.out.println(arg0);
					System.out.println("ok");
				}
				//发生错误的时候调用的方法
				@Override
				public void onError(Exception arg0) {
					// TODO Auto-generated method stub
					
				}
				
				//与webservlet断开的时候调用的方法
				@Override
				public void onClose(int arg0, String arg1, boolean arg2) {
					// TODO Auto-generated method stub
					
				}
			};
			webSocketClient.connect();
		}
	}
	
	private void send_message(String message){
		if(webSocketClient != null){
			webSocketClient.send(message);
		}
		else{
			System.out.println("no");
		}
	}
	
	private void close_connection(){
		if(webSocketClient != null){
			webSocketClient.close();
			System.out.println(webSocketClient.getReadyState());
			webSocketClient = null;
		}
		else{
			System.out.println("no");
		}
	}
	
	//将文本所有空格的地方设置为图片的编码
	private String insert_image(String text){
		List<Integer> position = new ArrayList<Integer>();
		int ini_position = 0;
		String result = "";
		
		do{
			ini_position = text.indexOf(" ", ini_position);
			if(ini_position != -1){
				position.add(ini_position);
			}
			ini_position += 1;
		}
		while(ini_position != 0);
		
		if(position.size() != image_list.size()){
			System.err.println("error occurs");
			System.exit(0);
		}
		if(position.size() != 0){
			for(int i=0,ini_p=0;i<position.size();i++){
				result += text.substring(ini_p, position.get(i)+1).replace(" ",image_list.get(i).toString());
				ini_p = position.get(i)+1;
				if(i == position.size() - 1){
					result += text.substring(ini_p);
				}
			}
			return result;
		}
		else return text;
	}
	
	private String[] find(String text,String delimiter){
		String[] results = text.split(delimiter);
		return results;
	}

	private void  view(File input){
		try {
			left_area.removeAll();
			BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			String result = "";
			while((result = b.readLine()) != null){
				Box line = Box.createHorizontalBox();
				//System.out.println("inn");
				//System.out.println(result);
					String[] results = find(result, "#pic#");
					for(int i=0;i<results.length;i++){
						System.out.println(results[i]);
						if(results[i] == ""){
							
						}
						else{
							if(i == 0){
								line.add(new JLabel(results[i]));
							}
							else{
								//说明这是一个txt
								if(results[i].length() < 6|| !(results[i].substring(0, 5).equals("*pic*"))){
									line.add(new JLabel(results[i]));
									System.out.println("why");
								}
								else{
									System.out.println("in");
									int position = results[i].indexOf("*end*");
									coding_image.decode(results[i].substring(5,position), "F:" + slash + "reservation" + slash + count + ".png");
									line.add(new JLabel(new EncodingImageIcon("F:" + slash + "reservation" + slash + count +".png",results[i].substring(5,position))));
									count++;
								}
							}

						}
					}
					line.add(Box.createHorizontalGlue());
					left_area.add(line);
					left_area.validate();
				}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		//System.out.println(System.getProperty("java.library.path"));
		try {
//			Properties props = new Properties();
//
//            props.setProperty("backgroundColor", "240 240 240");
//            props.setProperty("backgroundColorLight", "220 220 220");
//            props.setProperty("backgroundColorDark", "200 200 200");
//            props.setProperty("alterBackgroundColor", "180 180 180");
//
//            props.setProperty("frameColor", "164 164 164");
//            props.setProperty("gridColor", "196 196 196");
//
//            props.setProperty("disabledForegroundColor", "96 96 96");
//            props.setProperty("disabledBackgroundColor", "240 240 240");
//
//            props.setProperty("rolloverColor", "160 160 160");
//            props.setProperty("rolloverColorLight", "230 230 230");
//            props.setProperty("rolloverColorDark", "210 210 210");
//
//            props.setProperty("controlBackgroundColor", "248 248 248");
//            props.setProperty("controlShadowColor", "160 160 160");
//            props.setProperty("controlDarkShadowColor", "110 110 110");
//            props.setProperty("controlColorLight", "248 248 248");
//            props.setProperty("controlColorDark", " 210 210 210");
//
//            props.setProperty("buttonColorLight", "255 255 255");
//            props.setProperty("buttonColorDark", "230 230 230");
//
//            props.setProperty("menuBackgroundColor", "64 64 64");
//            props.setProperty("menuColorLight", "96 96 96");
//            props.setProperty("menuColorDark", "48 48 48");
//            props.setProperty("menuSelectionBackgroundColor", "48 48 48");
//            props.setProperty("toolbarBackgroundColor", "64 64 64");
//            props.setProperty("toolbarColorLight", "96 96 96");
//            props.setProperty("toolbarColorDark", "48 48 48");
//            props.setProperty("desktopColor", "220 220 220");

            // Set your theme
            //com.jtattoo.plaf.texture.TextureLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("org.jvnet.substance.SubstanceLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		new User().initialize();
	}
}
