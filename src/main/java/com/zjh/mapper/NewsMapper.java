package com.zjh.mapper;

import com.zjh.pojo.News;
import com.zjh.pojo.NewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    int countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExampleWithBLOBs(NewsExample example);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExampleWithBLOBs(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);
    
    /**
	 * 取得上一篇
	 * @param newsId
	 * @return
	 */
	News getNewsPrev(@Param("newsId")Integer newsId,@Param("type")Integer type);
	/**
	 * 取得第二篇
	 * @param newsId
	 * @return
	 */
	News getNewsNext(@Param("newsId")Integer newsId,@Param("type")Integer type);
}