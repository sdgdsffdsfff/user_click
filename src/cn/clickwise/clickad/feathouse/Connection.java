package cn.clickwise.clickad.feathouse;

/**
 * 连接kv 存储所需要的信息
 * @author lq
 *
 */
public class Connection {

	private String host;
	private int port;
	
	//cassandra 数据库
	private String keySpace;
	
	//cassandra 表名
	private String cfName;
	
	//cassandra 列名
	private String columnName;
	
	private String ardbHost;
	
	private int ardbPort;
	
	//ardb的数据库编号
	private int db;
	
   public Connection()
   {
	   
   }
	
	public Connection(String host,int port)
	{
		this.host=host;
		this.port=port;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	public String getKeySpace() {
		return keySpace;
	}

	public void setKeySpace(String keySpace) {
		this.keySpace = keySpace;
	}

	public String getCfName() {
		return cfName;
	}

	public void setCfName(String cfName) {
		this.cfName = cfName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getDb() {
		return db;
	}

	public void setDb(int db) {
		this.db = db;
	}

	public String getArdbHost() {
		return ardbHost;
	}

	public void setArdbHost(String ardbHost) {
		this.ardbHost = ardbHost;
	}

	public int getArdbPort() {
		return ardbPort;
	}

	public void setArdbPort(int ardbPort) {
		this.ardbPort = ardbPort;
	}
	
}
