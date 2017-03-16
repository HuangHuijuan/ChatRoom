package utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DirectoryFilter extends FileFilter{

	private String description = "default";
	
	public void setDescription(String d){
		description = d;
	}
	
	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		if(f.isDirectory()){
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	
}
