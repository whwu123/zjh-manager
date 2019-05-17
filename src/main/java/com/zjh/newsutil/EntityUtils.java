package com.zjh.newsutil;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
	public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;
        
    /**
     * 连接数据库
     * @return
     */
    public static Connection getConnection(){
        String url = "jdbc:mysql://127.0.0.1:3306/zjhweb?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String user = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
        return connection;
    }
    /**
     * 关闭资源
     */
    public static void close(){
        try{
            if(connection != null){
               connection.close();
               connection = null;
            }
            if(preparedStatement != null){
                preparedStatement.close();
                preparedStatement = null;
            }
            if(resultSet!= null){
                resultSet.close();
                resultSet = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    /**
     * 将结果集转换成实体对象集合
     * @param res 结果集
     * @param c 实体对象映射类
     * @return
    * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List Populate(ResultSet rs,Class cc) throws Exception{
        
        //结果集 中列的名称和类型的信息
        ResultSetMetaData rsm = rs.getMetaData();
        int colNumber = rsm.getColumnCount();
        List list = new ArrayList();
        Field[] fields = cc.getDeclaredFields();
        
        //遍历每条记录
        while(rs.next()){
            //实例化对象
            Object obj = cc.newInstance();
            //取出每一个字段进行赋值
            for(int i=1;i<=colNumber;i++){
                Object value = rs.getObject(i);
                //匹配实体类中对应的属性
                for(int j = 0;j<fields.length;j++){
                    Field f = fields[j];
                  //  System.out.println(f.getType());
                    if (f.getType().equals("class java.util.Date")) {
                    	value=null;
					}
                    if(f.getName().equals(rsm.getColumnName(i))){
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                        break;
                    }
                }
                 
            }
            list.add(obj);
        }
        return list;
    }
}
