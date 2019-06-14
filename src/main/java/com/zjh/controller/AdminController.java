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
import com.zjh.pojo.Dept;
import com.zjh.pojo.Items;
import com.zjh.pojo.Roles;
import com.zjh.service.DeptService;
import com.zjh.service.ItemsService;
import com.zjh.service.RolesService;
import com.zjh.util.BaseVariable;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
	@Autowired
	private ItemsService itemsService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RolesService rolesService;
	
	@RequestMapping("/system-base")
	public String base(Model model,String fKey, Integer pageNum,String startTime,String endTime,Integer fType,String fTitle,Integer fStatus) {
		if(pageNum==null){
			pageNum=1;
		}
		if(fType == null) {
			fType = 1;
		}
		PageView<Items> pageView   = itemsService.getList(null, fKey, fTitle, fType, null, fStatus, startTime, endTime, pageNum, BaseVariable.PAGE_SIZE);
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
		if(fType == null) {
			fType = 2;
		}
		PageView<Items> pageView   = itemsService.getList(null, fKey, fTitle, fType, null, fStatus, startTime, endTime, pageNum, BaseVariable.PAGE_SIZE);
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
	
	@RequestMapping("/admin_dept")
	public String admin_dept(Model model,String dCode,String dName,Integer dStatus,
			String startTime,String endTime,Integer pageNum) {
		if(pageNum==null){
			pageNum=1;
		}
		
		if(dStatus == null) {
			dStatus = 1;
		}
		PageView<Dept> pageView   = deptService.getList(dCode, dName, dStatus, startTime, endTime, pageNum,  BaseVariable.PAGE_SIZE);
		model.addAttribute("pageView", pageView);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("dCode", dCode);
		model.addAttribute("dName", dName);
		model.addAttribute("dStatus", dStatus);
		return "admin/dept/dept_list";
	}
	
	
	@RequestMapping(value = "/dept/add-dept", method = RequestMethod.GET)
	public String adddept(Model model,String dId) {
		// 新增页/更新页
		if (dId != null && !dId.isEmpty()) {
			model.addAttribute("items", deptService.getDeptByDid(dId));
		}
		return "admin/dept/add_base";
	}
	
	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	@ResponseBody
	public String doAdd(Model model,Dept dept) {
		String uid = UUID.randomUUID().toString().replace("-", "");
		if(dept!=null) {
			dept.setdId(uid);
			dept.setdCreateTime(new Date());
		}
		if(deptService.insertSelective(dept)) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/dept/update", method = RequestMethod.POST)
	@ResponseBody
	public String deptUpdate(Model model,Dept dept) {
		if(dept!=null) {
			if(deptService.updateSelective(dept)) {
				return "success";
			}
		}
		return "fail";
	}
	
	@RequestMapping(value = "/dept/dept_del", method = RequestMethod.POST)
	@ResponseBody
	public String dept_del(Model model,String dId) {
		if(dId!=null) {
			if(deptService.deleteDeptByDid(dId)) {
				return "success";
			}
		}
		return "fail";
	}
	
	//===角色
	@RequestMapping("/admin_roles")
	public String admin_roles(Model model,String rCode,String rName,Integer rStatus,
			String startTime,String endTime,Integer pageNum) {
		if(pageNum==null){
			pageNum=1;
		}
		
		if(rStatus == null) {
			rStatus = 1;
		}
		PageView<Roles> pageView   = rolesService.getList(rCode, rName, rStatus, startTime, endTime, pageNum,  BaseVariable.PAGE_SIZE);
		model.addAttribute("pageView", pageView);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("rCode", rCode);
		model.addAttribute("rName", rName);
		model.addAttribute("rStatus", rStatus);
		return "admin/role/role_list";
	}
	@RequestMapping(value = "/role/add_role", method = RequestMethod.GET)
	public String add_role(Model model,String rId) {
		// 新增页/更新页
		if (rId != null && !rId.isEmpty()) {
			model.addAttribute("items", rolesService.getRolesByRid(rId));
		}
		return "admin/role/add_base";
	}
		
	@RequestMapping(value = "/role/add", method = RequestMethod.POST)
	@ResponseBody
	public String doRoleAdd(Model model,Roles role) {
		String uid = UUID.randomUUID().toString().replace("-", "");
		if(role!=null) {
			role.setrId(uid);
			role.setrDate(new Date());
		}
		if(rolesService.insertSelective(role)) {
			return "success";
		}else {
			return "fail";
		}
	}
	@RequestMapping(value = "/role/update", method = RequestMethod.POST)
	@ResponseBody
	public String roleUpdate(Model model,Roles role) {
		if(role!=null) {
			if(rolesService.updateSelective(role)) {
				return "success";
			}
		}
		return "fail";
	}
	
	@RequestMapping(value = "/role/role_del", method = RequestMethod.POST)
	@ResponseBody
	public String role_del(Model model,String rId) {
		if(rId!=null) {
			if(rolesService.deleteRolesByRid(rId)) {
				return "success";
			}
		}
		return "fail";
	}
}
