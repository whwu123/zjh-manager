package com.zjh.controller.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String fileName = upfile.getOriginalFilename();
		String fileFormat = fileName.substring(fileName.lastIndexOf("."));
		String saveName =UUID.randomUUID().toString().replaceAll("-", "")+fileFormat;
		 //获得项目的路径  
        ServletContext sc = request.getServletContext();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
        String dateStr =ft.format(new Date());
        // 上传位置  
        String path = sc.getRealPath("/images/zjh/"+dateStr); // 设定文件保存的目录  
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
            	FileUtils.copyFile(myFile, new File(BaseController.REMOTE_PATH +dateStr+ saveName));
          
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        Ueutil ueutil = new Ueutil();
        ueutil.setOriginal(fileName);
        ueutil.setTitle(fileName);
        ueutil.setSize(upfile.getSize());
        ueutil.setState("SUCCESS");
        ueutil.setType(upfile.getContentType());
        ueutil.setUrl(dateStr+"/"+saveName);
        JSONObject jsonObject  = JSONObject.fromObject(ueutil);
        String json = jsonObject.toString();
		return json;
		
	}
	
}
