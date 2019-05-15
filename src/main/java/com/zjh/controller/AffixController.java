package com.zjh.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zjh.base.BaseController;
import com.zjh.base.PageView;
import com.zjh.common.constant.StatusConstant;
import com.zjh.pojo.Affix;
import com.zjh.service.AffixService;

@Controller
@RequestMapping("/affix")
public class AffixController extends BaseController {

	@Autowired
	private AffixService affixService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, String keyWord, Integer page) {
		// 新闻列表分页
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("page", page == null ? 1 : page);
		model.addAttribute("rows", rows);
		model.addAttribute("list", affixService.list(keyWord, page == null ? 1 : page, rows));
		return "affix/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Model model, Integer id, PrintWriter out) {
		// 假删除方法
		Affix affix = affixService.getById(id);
		affix.setStatus(StatusConstant.DISABLE_STATUS);
		if (affixService.update(affix) > 0) {
			out.print(1);
		}
		out.flush();
		out.close();
	}
	//------------------------------------------以下为重构代码-----------------------------
	@RequestMapping("add_affix")
	public String addAffix(){
		return "affix/add_affix";
	}
	@RequestMapping("affix_list")
	public String affixList(String startTime,String endTime,String affixName,Integer pageNum,Model model){
		if(pageNum==null){
			pageNum=1;
		}
		int pageSize = 10;
		PageView<Affix> pageView   = affixService.getList(startTime, endTime, affixName, pageNum, pageSize);
		model.addAttribute("pageView", pageView);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("affixName", affixName);
		//查询附件分页去页面展示
		return "affix/affix_list";
	}
	
	/**
	 * 附件上传
	 */
	@RequestMapping("affixUpload")
	@ResponseBody
	public String affixUpload(MultipartFile affix,String affixName,String affixDesc,HttpServletRequest request) throws IllegalStateException, IOException {
		Affix affixModel = new Affix();
		 //获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 设定文件保存的目录  
        String path = sc.getRealPath("/upload/affix/");
		long affixSize =0;
		//byte[] affixContent = null;
		String affixType = null;
		String savePath = null;
		if(affix!=null){
			affixType = affix.getOriginalFilename().substring(affix.getOriginalFilename().lastIndexOf(".")+1);
			affixSize = affix.getSize();
			//affixContent = affix.getBytes();
			String uid = UUID.randomUUID().toString().replace("-", "");
			savePath = path+"/"+uid+affixName+"."+affixType;
			File myFile = new File(savePath);
        	//上传到本地
			affix.transferTo(myFile);
		}
		System.out.println("affixName:"+affixName);
		affixModel.setAffixname(affixName);
		affixModel.setAffixtime(new Date());
		affixModel.setAffixtype(affixType);
		affixModel.setContent(null);
		affixModel.setStatus(0);
		affixModel.setSize(affixSize);
		affixModel.setDescrption(affixDesc);
		affixModel.setAffixpath(savePath);
		int result =affixService.add(affixModel);
		if(result>0){
			return "ok";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 附件下载
	 * @throws Exception 
	 */
	@RequestMapping("download")
	public void affixDownload(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Affix affix = null;
		if(id!=null){
			affix = affixService.findById(id);
			String affixPath = affix.getAffixpath();
			File file=new File(affixPath);  
			response.setCharacterEncoding("utf-8");
			String fileName = affix.getAffixname()+"."+affix.getAffixtype();
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
			response.addHeader("Content-Length", "" + affix.getSize()); 
			response.setContentType("application/octet-stream;charset=UTF-8");
		    OutputStream os = response.getOutputStream(); 
		    FileInputStream fis = new FileInputStream(file);  
		    byte[] buffer = new byte[2048];  
	        int readlength = 0;  
	        while((readlength = fis.read(buffer)) != -1){  
	        	os.write(buffer,0,readlength);   
	        } 
			fis.close();
			os.flush();
			os.close();
		}
	}
	/**
	 * 附件删除
	 */
	@RequestMapping("affix_del")
	@ResponseBody
	public String affix_del(Integer id){
		Affix affix = new Affix();
		affix.setId(id);
		affix.setStatus(1);
		//修改附件的状态为1 status = 1
		int result = affixService.update(affix);
		if(result>0){
			return String.valueOf(result);
		}else{
			return "0";
		}
	}
	/**
	 * 附件回收站列表
	 */
	@RequestMapping("recycle_list")
	public String recycle_list(String startTime,String endTime,String affixName,Integer pageNum,Model model){
		if(pageNum==null){
			pageNum=1;
		}
		int pageSize = 10;
		PageView<Affix> pageView   = affixService.getRecyleList(startTime, endTime, affixName, pageNum, pageSize);
		model.addAttribute("pageView", pageView);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("affixName", affixName);
		//查询附件分页去页面展示
		return "affix/recyle_list";
	}
	/**
	 * 撤销删除
	 */
	@RequestMapping("affixBack")
	@ResponseBody
	public String affixBack(Integer id){
		int a = affixService.affixBack(id);
		if(a>0){
			return "ok";
		}
		return "fail";
	}
	/**
	 * 彻底删除（数据库数据也一并删除）
	 */
	@RequestMapping("affix_realdel")
	@ResponseBody
	public String affix_realdel(Integer id){
		int result = affixService.affix_realdel(id);
		if(result>0){
			return "ok";
		}
		return "fail";
	}
}
