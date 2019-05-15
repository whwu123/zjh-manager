package com.zjh.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zjh.base.BaseController;
import com.zjh.common.constant.StatusConstant;
import com.zjh.pojo.Example;
import com.zjh.service.ExampleService;

@Controller
@RequestMapping("case")
public class CaseController extends BaseController {

	@Autowired
	private ExampleService exampleService;
	
	@RequestMapping(value = "/list")
	public String list(Model model, String keyWord, String startTime, String endTime, Integer page) throws Exception {
		// 案例列表分页
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("pageInfo", exampleService.list(keyWord, startTime, endTime, page == null ? 1 : page, rows));
		return "case/list";
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select(Model model, Integer id) {
		// 新增页/更新页
		id = id == null ? 0 : id;
		if (id > 0){
			model.addAttribute("example", exampleService.getById(id));
		}
		return "case/select";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(HttpServletRequest request,String title, String author, String description, String content, @RequestParam("imageFile") MultipartFile imageFile, PrintWriter out) {
		// 新增方法
		Example example = new Example();
		example.setTitle(title);
		example.setAuthor(author);
		example.setDescription(description);
		example.setContent(content);
		example.setCreatetime(new Date());
		example.setStatus(StatusConstant.ENABLE_STATUS);
		
		if (!imageFile.isEmpty()) {
			String fileName = "case_" + System.currentTimeMillis() + ".jpg";
			 //获得项目的路径  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String path = sc.getRealPath("/images/zjh/cover/"); // 设定文件保存的目录  
	        File f = new File(path);  
	        if (!f.exists()) {
	        	  f.mkdirs(); 
	        }
			try {
				File myFile = new File(path, fileName);
            	//上传到本地
            	imageFile.transferTo(myFile);
				// 把文件复制一份到前台项目
            	//FileUtils.copyFile(myFile, new File(BaseController.COVER_PATH + fileName));
            	Thumbnails.of(myFile).size(250, 140).keepAspectRatio(false).toFile(new File(BaseController.COVER_PATH + fileName));
				//写一份后台列表展示图片
            	Thumbnails.of(myFile).size(50, 40).keepAspectRatio(false).toFile(new File(path+"/sysimg/"+ fileName));
				
            	example.setImg("/images/zjh/cover/" + fileName);
            	example.setSysimg("/images/zjh/cover/sysimg/"+fileName);
			} catch (IOException e) {
				logger.error("upload img fail.", e);
			} 
		}
		
		if (exampleService.add(example) > 0) {
			out.print(StatusConstant.SUCCESS);
		} else {
			out.print(StatusConstant.FAIL);
		}
		out.flush();
		out.close();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(HttpServletRequest request,Integer id, String title, String author, String description, String content, @RequestParam("imageFile") MultipartFile imageFile, PrintWriter out) {
		// 更新方法
		Example example = exampleService.getById(id);
		example.setTitle(title);
		example.setAuthor(author);
		example.setDescription(description);
		example.setContent(content);
		//上传封面
		if (!imageFile.isEmpty()) {
			String fileName = "case_" + System.currentTimeMillis() + ".jpg";
			 //获得项目的路径  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String path = sc.getRealPath("/images/zjh/cover/"); // 设定文件保存的目录  
	        File f = new File(path);  
	        if (!f.exists()) {
	        	  f.mkdirs(); 
	        }
			try {
				File myFile = new File(path, fileName);
            	//上传到本地
            	imageFile.transferTo(myFile);
				// 把文件复制一份到前台项目
            	//FileUtils.copyFile(myFile, new File(BaseController.COVER_PATH + fileName));
            	Thumbnails.of(myFile).size(250, 140).keepAspectRatio(false).toFile(new File(BaseController.COVER_PATH + fileName));
            	//写一份后台列表展示图片
            	Thumbnails.of(myFile).size(50, 40).keepAspectRatio(false).toFile(new File(path+"/sysimg/"+ fileName));
				
            	example.setImg("/images/zjh/cover/" + fileName);
            	example.setSysimg("/images/zjh/cover/sysimg/"+fileName);
			} catch (IOException e) {
				logger.error("upload img fail.", e);
			} 
		}
		
		if (exampleService.update(example) > 0) {
			out.print(StatusConstant.SUCCESS);
		} else {
			out.print(StatusConstant.FAIL);
		}
		out.flush();
		out.close();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id, PrintWriter out) {
		// 假删除方法
		Example example = exampleService.getById(id);
		example.setStatus(StatusConstant.DISABLE_STATUS);
		if (exampleService.update(example) > 0) {
			out.print(StatusConstant.SUCCESS);
		} else {
			out.print(StatusConstant.FAIL);
		}
		out.flush();
		out.close();
	}
	
}
