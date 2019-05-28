package com.zjh.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjh.base.BaseController;
import com.zjh.base.PageView;
import com.zjh.pojo.Affix;
import com.zjh.pojo.Items;
import com.zjh.service.ItemsService;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping("/system-base")
	public String base(Model model,String fKey, Integer pageNum,String startTime,String endTime,Integer fType,String fTitle,Integer fStatus) {
		if(pageNum==null){
			pageNum=1;
		}
		int pageSize = 10;
		if(fType == null) {
			fType = 1;
		}
		PageView<Items> pageView   = itemsService.getList(null, fKey, fTitle, fType, null, fStatus, startTime, endTime, pageNum, pageSize);
		model.addAttribute("pageView", pageView);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("fKey", fKey);
		model.addAttribute("fType", fType);
		model.addAttribute("fTitle", fTitle);
		model.addAttribute("fStatus", fStatus);
		return "admin/base_list";
	}
	
	@RequestMapping(value = "/add-base", method = RequestMethod.GET)
	public String addbase(Model model,String fId) {
		// 新增页/更新页
		if (fId != null && !fId.isEmpty()) {
			model.addAttribute("items", itemsService.selectItemsByFid(fId, null));
		}
		return "admin/add_base";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Model model,Items items) {
		String uid = UUID.randomUUID().toString().replace("-", "");
		if(items!=null) {
			items.setfId(uid);
			items.setfCreatetime(new Date());
		}
		if(itemsService.insertSelective(items)) {
			return "success";
		}else {
			return "fail";
		}
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Model model,Items items) {
		if(items!=null) {
			if(itemsService.updateSelective(items)) {
				return "success";
			}
		}
		return "fail";
	}
	
	@RequestMapping(value = "/items_del", method = RequestMethod.POST)
	@ResponseBody
	public String items_del(Model model,String fId) {
		if(fId!=null) {
			if(itemsService.deleteByPrimaryKey(fId)) {
				return "success";
			}
		}
		return "fail";
	}
	
	@RequestMapping("/system-data")
	public String data(Model model,String fKey, Integer pageNum,String startTime,String endTime,Integer fType,String fTitle,Integer fStatus) {
		if(pageNum==null){
			pageNum=1;
		}
		int pageSize = 10;
		if(fType == null) {
			fType = 2;
		}
		PageView<Items> pageView   = itemsService.getList(null, fKey, fTitle, fType, null, fStatus, startTime, endTime, pageNum, pageSize);
		model.addAttribute("pageView", pageView);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("fKey", fKey);
		model.addAttribute("fType", fType);
		model.addAttribute("fTitle", fTitle);
		model.addAttribute("fStatus", fStatus);
		return "admin/base_data";
	}
	@RequestMapping(value = "/add-base-data", method = RequestMethod.GET)
	public String addbasedata(Model model,String fId) {
		// 新增页/更新页
		if (fId != null && !fId.isEmpty()) {
			model.addAttribute("items", itemsService.selectItemsByFid(fId, null));
		}
		return "admin/add_base_data";
	}
	
	//禁用启用修改
	@RequestMapping(value = "/update_base")
	@ResponseBody
	public String update_base(String fId, Integer status){
		if(fId!=null) {
			Items items = itemsService.selectItemsByFid(fId, null);
			if(items != null) {
				items.setfStatus(status);
				if(itemsService.updateSelective(items)) {
					return "success";
				}
			}
		}
		return "fail";
	}
}
