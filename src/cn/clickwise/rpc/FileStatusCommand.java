package cn.clickwise.rpc;

public class FileStatusCommand extends Command{

	private static final long serialVersionUID = 6490793333889299562L;
	
	private String name;
	
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	

}