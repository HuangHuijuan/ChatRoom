package servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import sun.misc.Service;
import utils.FileMessageService;
import utils.MessageService;

@SuppressWarnings("deprecation")
public class Servlet extends WebSocketServlet{

	private static MessageService file_service  = FileMessageService.getFileMessageService();
	private static final long serialVersionUID = 5297236266401928012L;
	private ArrayList<ServletMessageInbound> user_set = new ArrayList<ServletMessageInbound>();
	private int counter = 0;
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		return new ServletMessageInbound( "G" + ++counter + "");
	}
	
	private class ServletMessageInbound extends MessageInbound{

		private String name;
		WsOutbound user_outbound;
		public ServletMessageInbound(){
		}
		public ServletMessageInbound(String name){
			this.name = name;
		}
		@Override
		protected void onClose(int status) {
			// TODO Auto-generated method stub
			Date date = new Date();
			DateFormat formate = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
			System.out.println("close");
			file_service.finalize_file(name);
			user_set.remove(this);
			send_to_all(formate.format(date) + " " + name + " txt" + "has left--system");
			get_user_name();
		}

		@Override
		protected void onOpen(WsOutbound outbound) {
			// TODO Auto-generated method stub
			Date date = new Date();
			DateFormat formate = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
			CharBuffer cbuffer = CharBuffer.wrap(formate.format(date) + " " + name + " txt" + "Welcome--system");
			file_service.insert(cbuffer.toString() + "\r\n", name);
			user_outbound = outbound;
			send_to_all(formate.format(date) + " " + name + " txt" + "come in--system");
			user_set.add(this);
			get_user_name();
			
			try {
				user_outbound.writeTextMessage(cbuffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//当使用websocketclient.send(data[])方法的时候回调这个方法
		@Override
		protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
			// TODO Auto-generated method stub
			System.out.println("binary");
		}

		@Override
		protected void onTextMessage(CharBuffer arg0) throws IOException {
			// TODO Auto-generated method stub
			Date date = new Date();
			DateFormat formate = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
			String message = arg0.toString();
			message = formate.format(date) + " " + name + " " + message;
			send_to_all(message);
		}
		
	}
	
	private void send_to_all(String message){
		System.out.println(message);
		for(ServletMessageInbound user:user_set){
			//因为在writeTextMessage执行后会清空缓存区，所以要在循环中创建
			CharBuffer cbuffer = CharBuffer.wrap(message);
			try {
				file_service.insert(cbuffer.toString() + "\r\n", user.name);
				user.user_outbound.writeTextMessage(cbuffer);
				//user.user_outbound.writeBinaryMessage(ByteBuffer.wrap(new byte[]{'t','e','s','t'}));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean get_user_name(){		
		String names = "";
		for(ServletMessageInbound user_name:user_set){
			names = names + user_name.name + "\n";
			}
		for(ServletMessageInbound user:user_set){
			ByteBuffer bbuffer = ByteBuffer.wrap(names.getBytes());
			try {
				user.user_outbound.writeBinaryMessage(bbuffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
