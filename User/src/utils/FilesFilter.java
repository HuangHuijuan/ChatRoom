package utils;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class FilesFilter extends FileFilter {

	private String description = "default";
	private ArrayList<String> extension = new ArrayList<String>();
	
	public void addExtension(String e){
		if(!e.startsWith(".")){
			e = "." + e;
		}
		extension.add(e.toLowerCase());
	}
	
	public void setDescription(String d){
		description = d;
	}
	
	//accept方法设置的是能够在文件选择框里面选择的所有对象，因此一定要加上路径，因为你要点击进去选择路径里面的文件夹
	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		String name = f.getName().toLowerCase();
		if(f.isDirectory()){
			return true;
		}
		for(String extensions:extension){
			if(name.endsWith(extensions)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	
}
