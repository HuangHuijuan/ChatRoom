package utils;


public interface MessageService {

	public boolean insert(String message,String name);
	public boolean getAll();
	public void finalize_file(String name);
}
