package com.zjh.shiro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.zjh.base.BaseController;




/**
 * 登陆
 * @author Administrator
 *
 */
public class LoginShiro {
	static Logger logger = LoggerFactory.getLogger(LoginShiro.class);
	private static String jdbcUrl;
	private static String jdbcUserName;
	private static String jdbcPassword;
	
	static{
		InputStream inStream= null;
		try {
			//configPath 取的我WEB-INF 下面的路径
			//String configPath=Thread.currentThread().getContextClassLoader().getResource("/").getPath().replace("classes/","" );
			File f = new File(BaseController.class.getResource("/").getPath());
	        String rootPath = f.getPath().toString();
			FileInputStream in = new FileInputStream(rootPath+"/conf/jdbc.properties"); 
			Properties properties = new Properties();
			properties.load(in);
			jdbcUrl = properties.getProperty("jdbc.druid.url");
			jdbcUserName = properties.getProperty("jdbc.druid.username");
			jdbcPassword = properties.getProperty("jdbc.druid.password");
		} catch (Exception e) {
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
	
	public static boolean shiroLogin(String userName,String password){
		try {
			DruidDataSource dataSource = new DruidDataSource();
			dataSource.setUrl(jdbcUrl);
			dataSource.setUsername(jdbcUserName);
			dataSource.setPassword(jdbcPassword);
			JdbcRealm jdbcRealm = new JdbcRealm();
			jdbcRealm.setDataSource(dataSource);
			String sql = "select user_password from persons where user_nameen = ? ";
			jdbcRealm.setAuthenticationQuery(sql);
			DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
			defaultSecurityManager.setRealm(jdbcRealm);
			//主题提交认证请求
			SecurityUtils.setSecurityManager(defaultSecurityManager);
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			subject.login(token);
			return subject.isAuthenticated();
		} catch (Exception e) {
			logger.info("登陆失败,用户名或密码错误");
			return false;
		}
	}
	public static void main(String[] args) {
		
	}
}
