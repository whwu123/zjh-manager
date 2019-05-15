package com.zjh.mapper;

import com.zjh.pojo.Affix;
import com.zjh.pojo.AffixExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AffixMapper {
    int countByExample(AffixExample example);

    int deleteByExample(AffixExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Affix record);

    int insertSelective(Affix record);

    List<Affix> selectByExampleWithBLOBs(AffixExample example);

    List<Affix> selectByExample(AffixExample example);

    Affix selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Affix record, @Param("example") AffixExample example);

    int updateByExampleWithBLOBs(@Param("record") Affix record, @Param("example") AffixExample example);

    int updateByExample(@Param("record") Affix record, @Param("example") AffixExample example);

    int updateByPrimaryKeySelective(Affix record);

    int updateByPrimaryKeyWithBLOBs(Affix record);

    int updateByPrimaryKey(Affix record);
    
    
    
    
    //--------------------------------------------------一下为自定义代码-------------------------------------------------------------
    /**
     * 根据条件取得总数量
     * @param startTime
     * @param endTime
     * @param affixName
     * @return
     */
    int getTotalCount(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="affixName")String affixName);
    int getTotalReclycCount(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="affixName")String affixName);
    
    /**
     * 取得分页数据
     * @param startTime
     * @param endTime
     * @param affixName
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Affix> getAffixData(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="affixName")String affixName,@Param("pagenum")Integer pageNum,@Param("pagesize")Integer pageSize);
    
    
    Affix findById(@Param("id")Integer id);
    
    int deleteAffix(@Param("id")Integer id,@Param("status")Integer status);
    
    List<Affix> getAffixReclycData(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="affixName")String affixName,@Param("pagenum")Integer pageNum,@Param("pagesize")Integer pageSize);
   
    int affixback(@Param("id")Integer id);
    
    int insertAffixBackId(Affix record);
}