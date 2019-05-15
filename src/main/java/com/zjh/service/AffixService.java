package com.zjh.service;

import com.github.pagehelper.PageInfo;
import com.zjh.base.PageView;
import com.zjh.pojo.Affix;

public interface AffixService {

	/*分页搜索列表*/
	PageInfo<Affix> list(String keyWord, Integer page, Integer rows);
	/*查询*/
	Affix getById(Integer id);
	/*添加*/
	int add(Affix mapper);
	/*更新*/
	int update(Affix mapper);
	
	PageView<Affix> getList(String startTime,String endTime,String affixName,int pageNum,int pageSize);
	
	Affix findById(Integer id);
	
	/*假删除附件 ，修改状态为 status=1 */
	 int deleteAffix(int id,int status);
	 /**
	  * 取得回收站列表
	  * @return
	  */
	 PageView<Affix> getRecyleList(String startTime,String endTime,String affixName,int pageNum,int pageSize);
		
	 int affixBack(Integer id);
	 
	 int affix_realdel(Integer id);
	 
	 int insertAffixBackId(Affix record);
}
