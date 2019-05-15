package com.zjh.mapper;

import com.zjh.pojo.Affix;
import com.zjh.pojo.Note;
import com.zjh.pojo.NoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoteMapper {
    int countByExample(NoteExample example);

    int deleteByExample(NoteExample example);

    int deleteByPrimaryKey(Integer id);

    List<Note> selectByExampleWithBLOBs(NoteExample example);

    List<Note> selectByExample(NoteExample example);

    Note selectByPrimaryKey(Integer id);

   
    
    /*---------------------自定义方法-------------------------*/
    /**
     * 查询总数量
     */
    int getTotalCount(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="content")String content);
    /**
     * 查询总条数
     */
    List<Note> getNoteData(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="content")String content,@Param("pagenum")Integer pageNum,@Param("pagesize")Integer pageSize);
    
    
    
}