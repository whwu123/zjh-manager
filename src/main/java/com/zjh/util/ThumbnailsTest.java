package com.zjh.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import net.coobird.thumbnailator.Thumbnails;


public class ThumbnailsTest {

	private static Logger logger = Logger.getLogger(ThumbnailsTest.class); 
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		  String oldPic = "d:\\admin-loginform-bg.png";
		  
		  String newPic = "D:\\new_"+System.currentTimeMillis()+".png";
		  
		  Thumbnails.of(oldPic).size(617, 280).keepAspectRatio(false).toFile(newPic);
		 
		
		logger.info("aaaa");
		
		logger.debug("bbbb");
		
		logger.error("cccc");
		
	}

}
