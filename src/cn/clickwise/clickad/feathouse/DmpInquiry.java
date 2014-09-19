package cn.clickwise.clickad.feathouse;

import java.io.File;

/**
 * dmp离线查询
 * @author zkyz
 */
public abstract class DmpInquiry {
	
	/**
	 * 从某地区dmp获取keyFile里所有用户的记录
	 * @param keyFile
	 * @return
	 */
	public abstract State fetchFromDmp(File keyFile,File recordFile,Dmp dmp);
	
	/**
	 * 从所有地区dmp获取所有用户的记录
	 * @param timeRange
	 * @return
	 */
	public abstract State fetchFromAllDmps(TimeRange timeRange);
	
	
	/**
	 * 用户记录从文件写入kv 存储
	 * @param recordFile
	 * @return
	 */
	public abstract State writeRecFile2DataStore(File recordFile);
	
	
	

}
