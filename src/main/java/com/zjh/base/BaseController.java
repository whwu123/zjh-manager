package com.zjh.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/*分页行数*/
	protected Integer rows = 10;
	/*linux服务器路片的路径*/
	protected static String SMALL_PATH = "";
	protected static String COVER_PATH = "";
    protected static String REMOTE_PATH = "";
    
    protected static String NEWS_STATIC_PAGR = "";
    protected static String NEWS_STATIC_PAGR_TEMPLATE = "";
    protected static String HY_NEWS_STATIC_PAGR_TEMPLATE = "";
    protected static String YGFC_STATIC_PAGR_TEMPLATE = "";
	
	//protected static String SMALL_PATH = "/zjh/tomcat/apache-tomcat-7.0.57/webapps/zjhWeb/images/zjh/cover/small/";
	//protected static String COVER_PATH = "/zjh/tomcat/apache-tomcat-7.0.57/webapps/zjhWeb/images/zjh/cover/";
   // protected static String REMOTE_PATH = "/zjh/tomcat/apache-tomcat-7.0.57/webapps/zjhWeb/images/zjh/";
	static{
		InputStream inStream= null;
		try {
			// 获取类加载的根路径 E:\Workspaces\jxwoapg\jxwoapg\WebRoot\WEB-INF\classes
	        File f = new File(BaseController.class.getResource("/").getPath());
	        String rootPath = f.getPath().toString();
			FileInputStream in = new FileInputStream(rootPath+"/conf/filepath.properties"); 
			Properties properties = new Properties();
			properties.load(in);
			SMALL_PATH = properties.getProperty("small_path");
			COVER_PATH = properties.getProperty("cover_path");
			REMOTE_PATH = properties.getProperty("remoto_path");
			NEWS_STATIC_PAGR = properties.getProperty("news_static_path");
			NEWS_STATIC_PAGR_TEMPLATE = properties.getProperty("news_static_path_template");
			HY_NEWS_STATIC_PAGR_TEMPLATE = properties.getProperty("hy_news_static_path_template");
			YGFC_STATIC_PAGR_TEMPLATE = properties.getProperty("ygfc__static_path_template");
		} catch (Exception e) {
			System.out.println("获取文件 地址失败！！");
			e.printStackTrace();
		}finally{
			if(inStream!=null){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		}
	}
	public static void main(String[] args) {
		 
		System.out.println(COVER_PATH);
	}
}
