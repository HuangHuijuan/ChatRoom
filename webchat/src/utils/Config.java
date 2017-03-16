package utils;

public interface Config {
	public interface DBConfig{
		String url="jdbc:mysql://127.0.0.1:3306/test";
		String user="root";
		String psw="";
	}
	public interface MsgConfig{
		MsgFactoryType msgFactoryType=MsgFactoryType.FileType;
	}
	public interface WriteRate{
		int count=1;
	}
	enum MsgFactoryType{
		FileType,
		 DBtype
	}
}
