package com.zjh.util;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbnailsTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String oldPic = "d:\\admin-loginform-bg.png";
		
		String newPic = "D:\\new_"+System.currentTimeMillis()+".png";
		
		Thumbnails.of(oldPic).size(617, 280).keepAspectRatio(false).toFile(newPic);
		
	}

}
