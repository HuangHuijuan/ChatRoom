package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.java_websocket.util.Base64;

public class Base64CodingImage implements CodingImage {

	private static final String slash = File.separator;
	@Override
	public String encode(File image) {
		// TODO Auto-generated method stub
		String result = " ";
		byte[] codes = null;
		try {
			FileInputStream finput = new FileInputStream(image);
			codes = new byte[finput.available()];
			finput.read(codes);
			finput.close();
			result = Base64.encodeBytes(codes);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public File decode(String image, String filepath) {
		// TODO Auto-generated method stub
		File image_icon = new File(filepath);
		try {
			FileOutputStream foutput = new FileOutputStream(image_icon);
			byte[] result = Base64.decode(image);
			foutput.write(result);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image_icon;
	}

}
