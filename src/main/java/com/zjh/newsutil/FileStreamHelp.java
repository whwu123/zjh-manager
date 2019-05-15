package com.zjh.newsutil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class FileStreamHelp {
	
	
	public String ReadFile(String filePath) throws Exception{
		String templateContent="";
		FileInputStream fileinputstream = new FileInputStream(filePath);//读取模块文件
		int lenght = fileinputstream.available();
		byte bytes[] = new byte[lenght];
		fileinputstream.read(bytes);
		fileinputstream.close();
		templateContent = new String(bytes, "UTF-8");
		return templateContent;
	}
	
	public void WriteFile(String htmlcode,String OutHTMLpath) throws Exception{
		try {
            FileOutputStream fos = new FileOutputStream(OutHTMLpath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(htmlcode);
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}	
