package com.zjh.newsutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zjh.pojo.News;

public class MyData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String Modelpath = "E:/Workspaces/MavenDome/zjh-manager/src/main/webapp/newspages/newsThmplate.html"; //模板文件地址
	    String OutHTMLpath = "E:/Workspaces/MavenDome/zjh-manager/src/main/webapp/newspages/";//生成静态页文件地址
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection connection = EntityUtils.getConnection();
		List<News> nList = getNewsList(connection);
		for (News news : nList) {
		    try {
		        FileStreamHelp fsh = new FileStreamHelp();//实例化文件操作辅助类
		        String htmlcode = fsh.ReadFile(Modelpath);//读取模板文件
		        htmlcode=htmlcode.replaceAll("###htmltitle###", news.getTitle());
		        if(news.getDescription().length()>30){
		        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription().substring(0, 30));
		        }else{
		        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription());
		        }
		        htmlcode=htmlcode.replaceAll("###newtitle###", news.getTitle());
		        htmlcode=htmlcode.replaceAll("###newsauthor###", news.getAuthor());
		        htmlcode=htmlcode.replaceAll("###newsources###", news.getSources());
		        htmlcode=htmlcode.replaceAll("###newstime###", sdf.format(news.getCreatetime()));
		        htmlcode=htmlcode.replaceAll("###newscontent###", news.getContent());
		        News newsPrev = getNewsPrev(connection, news.getId());
		        if(newsPrev!=null){
		        	 htmlcode=htmlcode.replaceAll("###newsprev###", "<a href='../newsView.html?newsId="+newsPrev.getId()+"'>"+newsPrev.getTitle()+"</a>");
		        }else{
		        	 htmlcode=htmlcode.replaceAll("###newsprev###", "没有新闻了");
		        }
		        News nextNews = getNewsNext(connection, news.getId());
		        if(nextNews!=null){
		        	 htmlcode=htmlcode.replaceAll("###newsnext###", "<a href='../newsView.html?newsId="+nextNews.getId()+"'>"+nextNews.getTitle()+"</a>");
		        }else{
		        	 htmlcode=htmlcode.replaceAll("###newsnext###", "没有新闻了");
		        }
		        fsh.WriteFile(htmlcode, OutHTMLpath+System.currentTimeMillis()+news.getId()+".html");//生成静态文件
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			System.out.println(news.getTitle()+"==="+sdf.format(news.getCreatetime()));
		}
		EntityUtils.close();
	}
	
	public static List<News> getNewsList(Connection connection){
		List<News> list = new ArrayList<News>();
		try {
			String sql = " select * from news ORDER BY createtime desc ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setAuthor(rs.getString("author"));
				news.setSources(rs.getString("sources"));
				news.setCreatetime(new Date(rs.getTimestamp("createtime").getTime()));
				news.setContent(rs.getString("content"));
				news.setType(rs.getInt("type"));
				news.setStatus(rs.getInt("status"));
				news.setSysimg(rs.getString("sysimg"));
				news.setSmallimg(rs.getString("smallimg"));
				news.setImg(rs.getString("img"));
				news.setDescription(rs.getString("description"));
				news.setAffixid(rs.getInt("affixid"));
				list.add(news);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 找出新闻的上一篇
	 * @param connection
	 * @param newsId
	 * @return
	 */
	public static News getNewsPrev(Connection connection,Integer newsId){
		News news = new News();
		try {
			String sql = " select * from news where news.id < ? and status =0 order by id desc limit 0,1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, newsId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setAuthor(rs.getString("author"));
				news.setSources(rs.getString("sources"));
				news.setCreatetime(new Date(rs.getTimestamp("createtime").getTime()));
				news.setContent(rs.getString("content"));
				news.setType(rs.getInt("type"));
				news.setStatus(rs.getInt("status"));
				news.setSysimg(rs.getString("sysimg"));
				news.setSmallimg(rs.getString("smallimg"));
				news.setImg(rs.getString("img"));
				news.setDescription(rs.getString("description"));
				news.setAffixid(rs.getInt("affixid"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
	
	/**
	 * 查询出下一篇
	 * @param connection
	 * @param newsId
	 * @return
	 */
	public static News getNewsNext(Connection connection,Integer newsId) {
		News news = new News();
		try {
			String sql = " select * from news where news.id > ? and status =0 order by id asc limit 0,1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, newsId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setAuthor(rs.getString("author"));
				news.setSources(rs.getString("sources"));
				news.setCreatetime(new Date(rs.getTimestamp("createtime").getTime()));
				news.setContent(rs.getString("content"));
				news.setType(rs.getInt("type"));
				news.setStatus(rs.getInt("status"));
				news.setSysimg(rs.getString("sysimg"));
				news.setSmallimg(rs.getString("smallimg"));
				news.setImg(rs.getString("img"));
				news.setDescription(rs.getString("description"));
				news.setAffixid(rs.getInt("affixid"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
}
