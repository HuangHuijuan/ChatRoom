package utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

public class EncodingImageIcon extends ImageIcon{

	private String encoding;

	public EncodingImageIcon(String encoding) {
		super();
		this.encoding = encoding;
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(byte[] imageData, String description,String encoding) {
		super(imageData, description);
		this.encoding = encoding;
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(byte[] imageData,String encoding) {
		super(imageData);
		this.encoding = encoding;
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(Image image, String description,String encoding) {
		super(image, description);
		this.encoding = encoding;
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(Image image,String encoding) {
		super(image);
		this.encoding = encoding;
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(String filename, String description,String encoding) {
		super(filename, description);
		this.encoding = encoding;
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(String filename,String encoding) {
		super(filename);
		this.encoding = encoding;
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(URL location, String description) {
		super(location, description);
		// TODO Auto-generated constructor stub
	}

	public EncodingImageIcon(URL location) {
		super(location);
		// TODO Auto-generated constructor stub
	}

	public String getEncoding() {
		return encoding;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return encoding;
	}
}