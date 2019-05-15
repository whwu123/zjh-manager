package com.zjh.controller.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zjh.base.BaseController;

@Controller
@RequestMapping("")
public class FileUploadController extends BaseController {

	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping("fileUpload")
	@ResponseBody
	public String springUpload(HttpServletRequest request, HttpServletResponse response,MultipartFile upfile) {
		//String uid = UUID.randomUUID().toString().replace("-", "");
		String fileName = upfile.getOriginalFilename();
		String saveName = System.currentTimeMillis()+fileName;
		 //获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 上传位置  
        String path = sc.getRealPath("/images/zjh/"); // 设定文件保存的目录  
		File f = new File(path);  
        if (!f.exists()) {
        	  f.mkdirs(); 
        }
       
        if (!upfile.isEmpty()) {  
            try {  
            	File myFile = new File(path, saveName);
            	//上传到本地
            	upfile.transferTo(myFile);
            	//复制一份到前台项目
            	FileUtils.copyFile(myFile, new File(BaseController.REMOTE_PATH + saveName));
          
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        Ueutil ueutil = new Ueutil();
        ueutil.setOriginal(upfile.getOriginalFilename());
        ueutil.setTitle(saveName);
        ueutil.setSize(upfile.getSize());
        ueutil.setState("SUCCESS");
        ueutil.setType(upfile.getContentType());
        ueutil.setUrl(saveName);
        JSONObject jsonObject  = JSONObject.fromObject(ueutil);
        String json = jsonObject.toString();
		return json;
		
	}
	
}
