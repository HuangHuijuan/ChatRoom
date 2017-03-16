package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class FileMessageService implements MessageService{

	private static Map<String, File> file_map = new Hashtable<String, File>();
	private static Map<String, BufferedWriter> out_map = new Hashtable<String, BufferedWriter>();
	private static FileMessageService fileservice = null;
	
	private FileMessageService(){
		
	}
	public static MessageService getFileMessageService() {
		// TODO Auto-generated method stub
		if(fileservice != null){
			return fileservice;
		}
		synchronized (FileMessageService.class) {
			if(fileservice == null){
				fileservice = new FileMessageService();
			}
			return fileservice;
		}
	}

	private boolean reserve(String message,String name) {
		// TODO Auto-generated method stub
		if(out_map.get(name)!=null){
			try {
				out_map.get(name).append(message);
				out_map.get(name).flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else{
			try {
				BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_map.get(name))));
				out_map.put(name, bwriter);
				bwriter.append(message);
				bwriter.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

	}

	public boolean getAll() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void initialFile(String name){
		String slash = File.separator;
		
		if(file_map.get(name) != null){
			if(!file_map.get(name).exists()){
				try {
					file_map.get(name).createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return;
		}
		else {
			File reservation = new File("." + slash + "reservation" + slash);
			if(!reservation.exists()){
				reservation.mkdir();
			}
			File reservation_file = new File(reservation, name + ".txt");
			file_map.put(name, reservation_file);
			try {
				reservation_file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
	}
	
	public void finalize_file(String name){
		file_map.remove(name);
		try {
			out_map.get(name).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out_map.remove(name);
	}
	public boolean insert(String message, String name) {
		// TODO Auto-generated method stub
		Thread file_thread = new FileOperationThread(name, message);
		file_thread.start();
		return false;
	}
	
	private class FileOperationThread extends Thread{

		String name;
		String message;
		public FileOperationThread(String name, String message) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.message = message;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			initialFile(name);
			reserve(message, name);
		}
		
	}
	

}
