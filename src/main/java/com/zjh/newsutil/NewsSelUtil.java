package com.zjh.newsutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.zjh.pojo.News;

public class NewsSelUtil {
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
				news.setStaticpage(rs.getString("staticpage"));
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
				news.setStaticpage(rs.getString("staticpage"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
	
	//第2条
	public static News getNewsPrev(Connection connection){
		News news = new News();
		try {
			String sql = " select * from news where status =0 order by id desc limit 2,1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
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
				news.setStaticpage(rs.getString("staticpage"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
}
