package com.icss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbInfo {
	private static DbInfo info;                 //µ¥Àý
	private String driver;
	private String url;
	private String uname;
	private String pwd;	
	

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUname() {
		return uname;
	}

	public String getPwd() {
		return pwd;
	}

	private DbInfo() {
		
	}
	
	static {
		InputStream fis = null;
		try {
			String fname = DbInfo.class.getResource("/").getPath() +"db.properties";
			Properties prop = new Properties();			
			fis = new FileInputStream(new File(fname));;
			prop.load(fis);
			info = new DbInfo();
			info.driver = prop.getProperty("driver");
			info.url = prop.getProperty("url");
			info.uname = prop.getProperty("username");
			info.pwd = prop.getProperty("password");	
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
		}finally{
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					Log.logger.error(e.getMessage());
				}
			}			
		}
	}
	
	public static DbInfo instance(){		
		return info;
	}

}
