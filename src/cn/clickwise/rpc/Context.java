package cn.clickwise.rpc;

public class Context {

	private String name;

	public Context(String name)
	{
	    this.name=name;	
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
