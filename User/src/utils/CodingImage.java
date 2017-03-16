package utils;

import java.io.File;

public interface CodingImage {

	public String encode(File image);
	public File decode(String image,String filepath);
}
