package com.zjh.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.log4j.Logger;
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
import com.zjh.newsutil.EntityUtils;
import com.zjh.newsutil.FileStreamHelp;
import com.zjh.pojo.Items;
import com.zjh.pojo.News;
import com.zjh.service.ItemsService;
import com.zjh.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
	private static Logger logger = Logger.getLogger(NewsController.class); 
	@Autowired
	private NewsService newsService;
	//@Autowired
//	private AffixService affixService;
	@Autowired
	private ItemsService itemsService;

	@RequestMapping(value = "/list")
	public String list(Model model, String keyWord, Integer type, String startTime, String endTime, Integer page) throws Exception {
		//新闻列表分页
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("type", type == null ? 0 : type);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("pageInfo", newsService.list(keyWord, type == null ? 0 : type, startTime, endTime, page == null ? 1 : page, rows));
		return "news/list";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select(Model model, Integer id) {
		// 新增页/更新页
		id = id == null ? 0 : id;
		if (id > 0) {
			model.addAttribute("news", newsService.getById(id));
		}
		return "news/select";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request,String title, String author, String sources, Integer type, String description, String content, @RequestParam("imageFile") MultipartFile imageFile) throws Exception {
		// 新增方法
		News news = new News();
		news.setTitle(title);
		news.setAuthor(author);
		news.setSources(sources);
		news.setType(type);
		news.setDescription(description);
		news.setContent(content);
		news.setCreatetime(new Date());
		news.setStatus(StatusConstant.ENABLE_STATUS);
		
		if (!imageFile.isEmpty()) {
			String fileName = imageFile.getOriginalFilename();
			String fileFormat = fileName.substring(fileName.lastIndexOf("."));
			fileName = "news_" + System.currentTimeMillis() + fileFormat;
			 //获得项目的路径  上传位置  
	        String path =  request.getServletContext().getRealPath("/images/zjh/cover/"); // 设定文件保存的目录  
	        File f = new File(path);  
	        if (!f.exists()) {
	        	  f.mkdir(); 
	        }
			try {
				File myFile = new File(path, fileName);
            	//上传到本地
            	imageFile.transferTo(myFile);
				//写一份首页展示图片
            	//FileUtils.copyFile(myFile, new File(BaseController.COVER_PATH + fileName));
            	Thumbnails.of(myFile).size(150, 105).keepAspectRatio(false).toFile(new File(BaseController.SMALL_PATH + fileName));
				//写一份新闻列表的展示图片
            	Thumbnails.of(myFile).size(235, 135).keepAspectRatio(false).toFile(new File(BaseController.COVER_PATH + fileName));
				//写一份到后台展示是小图片
            	Thumbnails.of(myFile).size(50, 40).keepAspectRatio(false).toFile(new File(path+"/sysimg/"+ fileName));
            	news.setSysimg("/images/zjh/cover/sysimg/"+fileName);
            	news.setImg("/images/zjh/cover/" + fileName);
            	news.setSmallimg("/images/zjh/cover/small/"+fileName);
			} catch (IOException e) {
				logger.error("upload img fail.", e);
			} 
		}else {
			news.setSysimg("/images/mr1.png");
        	news.setImg("/images/mr1.png" );
        	news.setSmallimg("/images/mr1.png");
		}
		/*
		 * if(!affixFile.isEmpty()){ Affix affixModel = new Affix(); //获得项目的路径
		 * ServletContext sc = request.getSession().getServletContext(); // 设定文件保存的目录
		 * String path = sc.getRealPath("/upload/affix/news/"); File file = new
		 * File(path); if(!file.exists()) { logger.info("文件夹不存在,则新建文件夹"); file.mkdirs();
		 * } String affixType =
		 * affixFile.getOriginalFilename().substring(affixFile.getOriginalFilename().
		 * lastIndexOf(".")+1); long affixSize = affixFile.getSize(); // byte[]
		 * affixContent= affixFile.getBytes(); String affixName =
		 * affixFile.getOriginalFilename().substring(0,affixFile.getOriginalFilename().
		 * lastIndexOf(".")); String uid = UUID.randomUUID().toString().replace("-",
		 * ""); String savePath = path+"/"+uid+affixName+"."+affixType; File myFile =
		 * new File(savePath); //上传到本地 affixFile.transferTo(myFile);
		 * affixModel.setAffixname(affixName); affixModel.setAffixtime(new Date());
		 * affixModel.setAffixtype(affixType); //affixModel.setContent(affixContent);
		 * affixModel.setStatus(0); affixModel.setSize(affixSize);
		 * affixModel.setAffixpath(savePath); affixModel.setDescrption("新闻附件"); int id =
		 * affixService.insertAffixBackId(affixModel); if(id>0){
		 * logger.info("新的文件ID："+affixModel.getId());
		 * news.setAffixid(affixModel.getId()); }
		 * 
		 * }
		 */
		
		if (newsService.add(news) > 0) {
			String newsStaticPage =String.valueOf(System.currentTimeMillis())+".html";
			//Connection connection = EntityUtils.getConnection();
			//生成新闻静态页
			String Modelpath = BaseController.NEWS_STATIC_PAGR_TEMPLATE; //行业新闻模板文件地址
		    String OutHTMLpath = BaseController.NEWS_STATIC_PAGR;//生成静态页文件地址
		    String HyModelpath = BaseController.HY_NEWS_STATIC_PAGR_TEMPLATE; //行业新闻模板文件地址
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    if(news.getType() ==1) {
		    	try {
			        FileStreamHelp fsh = new FileStreamHelp();//实例化文件操作辅助类
			        String htmlcode = fsh.ReadFile(Modelpath);//读取模板文件
			        htmlcode=htmlcode.replaceAll("###htmltitle###",news.getTitle());
			        if(news.getDescription().length()>30){
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription().substring(0, 30));
			        }else{
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription());
			        }
			        htmlcode=htmlcode.replaceAll("###newtitle###", news.getTitle());
			        htmlcode=htmlcode.replaceAll("###newsauthor###", news.getAuthor());
			        htmlcode=htmlcode.replaceAll("###newsources###", news.getSources());
			        htmlcode=htmlcode.replaceAll("###newstime###", sdf.format(news.getCreatetime()));
			        htmlcode=htmlcode.replaceAll("###newscontent###", news.getContent());
			        Items items = itemsService.selectItemsByKey("company_address");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###address###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_email");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_email###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("postcode");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###postcode###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_person");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_person###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_tel");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_tel###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone2");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone2###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_qq");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_qq###",items.getfContent());
			        }
			        //友情链接
			    	List<Items> getItemsYL = itemsService.getitemsYL();
			    	if(getItemsYL.size()>0) {
			    		String html = "";
				    	for (int i = 0; i < getItemsYL.size(); i++) {
							html += "<p><a href=\""+getItemsYL.get(i).getfUrl()+"\">"+getItemsYL.get(i).getfTitle()+"</a></p>";
						}
				    	htmlcode=htmlcode.replaceAll("###itemsYL###",html);
			    	}
			        //News newsPrev = NewsSelUtil.getNewsPrev(connection, news.getId());
			    	News newsPrev = newsService.getNewsPrev(news.getId(),1);
			        if(newsPrev != null && newsPrev!=null){
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "<a href='"+newsPrev.getStaticpage()+"'>"+newsPrev.getTitle()+"</a>");
			        }else{
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "没有新闻了");
			        }
			        //News newsNex = NewsSelUtil.getNewsPrev(connection);
			        htmlcode=htmlcode.replaceAll("###newsnext###", "没有新闻了"); 
			        fsh.WriteFile(htmlcode, OutHTMLpath+newsStaticPage);//生成静态文件
			        news.setStaticpage(newsStaticPage);
			        if(newsService.update(news)>0){
			        	logger.info("生成公司新闻静态页成功："+newsStaticPage);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        return StatusConstant.FAIL;
			    }
		    }else if(news.getType() == 2) {
		    	try {
			        FileStreamHelp fsh = new FileStreamHelp();//实例化文件操作辅助类
			        String htmlcode = fsh.ReadFile(HyModelpath);//读取模板文件
			        htmlcode=htmlcode.replaceAll("###htmltitle###",news.getTitle());
			        if(news.getDescription().length()>30){
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription().substring(0, 30));
			        }else{
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription());
			        }
			        htmlcode=htmlcode.replaceAll("###newtitle###", news.getTitle());
			        htmlcode=htmlcode.replaceAll("###newsauthor###", news.getAuthor());
			        htmlcode=htmlcode.replaceAll("###newsources###", news.getSources());
			        htmlcode=htmlcode.replaceAll("###newstime###", sdf.format(news.getCreatetime()));
			        htmlcode=htmlcode.replaceAll("###newscontent###", news.getContent());
			        Items items = itemsService.selectItemsByKey("company_address");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###address###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_email");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_email###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("postcode");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###postcode###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_person");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_person###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_tel");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_tel###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone2");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone2###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_qq");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_qq###",items.getfContent());
			        }
			        //友情链接
			    	List<Items> getItemsYL = itemsService.getitemsYL();
			    	if(getItemsYL.size()>0) {
			    		String html = "";
				    	for (int i = 0; i < getItemsYL.size(); i++) {
							html += "<p><a href=\""+getItemsYL.get(i).getfUrl()+"\">"+getItemsYL.get(i).getfTitle()+"</a></p>";
						}
				    	htmlcode=htmlcode.replaceAll("###itemsYL###",html);
			    	}
			        //News newsPrev = NewsSelUtil.getNewsPrev(connection, news.getId());
			    	News newsPrev = newsService.getNewsPrev(news.getId(),2);
			        if(newsPrev!=null && newsPrev!=null){
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "<a href='"+newsPrev.getStaticpage()+"'>"+newsPrev.getTitle()+"</a>");
			        }else{
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "没有新闻了");
			        }
			        //News newsNex = NewsSelUtil.getNewsPrev(connection);
					/*
					 * News newsNex = newsService.getNewsNext(2); if(newsNex!=null){
					 * htmlcode=htmlcode.replaceAll("###newsnext###",
					 * "<a href='"+newsNex.getStaticpage()+"'>"+newsNex.getTitle()+"</a>"); }else{
					 * htmlcode=htmlcode.replaceAll("###newsnext###", "没有新闻了"); }
					 */
			        htmlcode=htmlcode.replaceAll("###newsnext###", "没有新闻了"); 
			        fsh.WriteFile(htmlcode, OutHTMLpath+newsStaticPage);//生成静态文件
			        news.setStaticpage(newsStaticPage);
			        if(newsService.update(news)>0){
			        	logger.info("生成行业新闻静态页成功："+newsStaticPage);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        return StatusConstant.FAIL;
			    }
		    }
		    
		    //EntityUtils.close();
			//out.print(StatusConstant.SUCCESS);
		    return StatusConstant.SUCCESS;
		} else {
			return StatusConstant.FAIL;
		}
		//out.flush();
		//out.close();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request,Integer id, String title, String author, String sources, Integer type, String description, String content, @RequestParam("imageFile") MultipartFile imageFile) {
		// 更新方法
		News news = newsService.getById(id);
		news.setTitle(title);
		news.setAuthor(author);
		news.setSources(sources);
		news.setType(type);
		news.setDescription(description);
		news.setContent(content);
		if (!imageFile.isEmpty()) {
			String fileName = imageFile.getOriginalFilename();
			String fileFormat = fileName.substring(fileName.lastIndexOf("."));
			fileName = "news_" + System.currentTimeMillis() + fileFormat;
			 //获得项目的路径  
	        // 上传位置  
	        String path = request.getServletContext().getRealPath("/images/zjh/cover/"); // 设定文件保存的目录  
	        File f = new File(path);  
	        if (!f.exists()) {
	        	  f.mkdir(); 
	        }
//	        File f2 = new File(BaseController.COVER_PATH);
//	        if (!f2.exists()) {
//	        	  f2.mkdirs(); 
//	        }
			try {
				File myFile = new File(path, fileName);
            	//上传到本地
            	imageFile.transferTo(myFile);
            	//写一份首页展示图片
            	//FileUtils.copyFile(myFile, new File(BaseController.COVER_PATH + fileName));
            	Thumbnails.of(myFile).size(150, 105).keepAspectRatio(false).toFile(new File(BaseController.SMALL_PATH + fileName));
				//写一份新闻列表的展示图片
            	Thumbnails.of(myFile).size(235, 135).keepAspectRatio(false).toFile(new File(BaseController.COVER_PATH + fileName));
            	//写一份到后台展示是小图片
            	Thumbnails.of(myFile).size(50, 40).keepAspectRatio(false).toFile(new File(path+"/sysimg/"+ fileName));
            	news.setSysimg("/images/zjh/cover/sysimg/"+fileName);
            	news.setImg("/images/zjh/cover/" + fileName);
            	news.setSmallimg("/images/zjh/cover/small/"+fileName);
				
            	
            	
			} catch (IOException e) {
				logger.error("upload img fail.", e);
			} 
		}
		
		if (newsService.update(news) > 0) {
			String newsStaticPage =String.valueOf(System.currentTimeMillis())+".html";
			//生成新闻静态页
			String Modelpath = BaseController.NEWS_STATIC_PAGR_TEMPLATE; //模板文件地址
		    String OutHTMLpath = BaseController.NEWS_STATIC_PAGR;//生成静态页文件地址
		    String HyModelpath = BaseController.HY_NEWS_STATIC_PAGR_TEMPLATE; //行业新闻模板文件地址
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    if(news.getType() == 1 ) {
		    	try {
			        FileStreamHelp fsh = new FileStreamHelp();//实例化文件操作辅助类
			        String htmlcode = fsh.ReadFile(Modelpath);//读取模板文件
			        htmlcode=htmlcode.replaceAll("###htmltitle###",news.getTitle());
			        if(news.getDescription().length()>30){
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription().substring(0, 30));
			        }else{
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription());
			        }
			        htmlcode=htmlcode.replaceAll("###newtitle###", news.getTitle());
			        htmlcode=htmlcode.replaceAll("###newsauthor###", news.getAuthor());
			        htmlcode=htmlcode.replaceAll("###newsources###", news.getSources());
			        htmlcode=htmlcode.replaceAll("###newstime###", sdf.format(news.getCreatetime()));
			        htmlcode=htmlcode.replaceAll("###newscontent###", news.getContent());
			        htmlcode=htmlcode.replaceAll("###newtitle###", news.getTitle());
			        htmlcode=htmlcode.replaceAll("###newsauthor###", news.getAuthor());
			        htmlcode=htmlcode.replaceAll("###newsources###", news.getSources());
			        htmlcode=htmlcode.replaceAll("###newstime###", sdf.format(news.getCreatetime()));
			        htmlcode=htmlcode.replaceAll("###newscontent###", news.getContent());
			        Items items = itemsService.selectItemsByKey("company_address");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###address###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_email");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_email###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("postcode");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###postcode###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_person");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_person###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_tel");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_tel###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone2");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone2###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_qq");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_qq###",items.getfContent());
			        }
			        //友情链接
			    	List<Items> getItemsYL = itemsService.getitemsYL();
			    	String html = "";
			    	for (int i = 0; i < getItemsYL.size(); i++) {
						html += "<p><a href=\""+getItemsYL.get(i).getfUrl()+"\">"+getItemsYL.get(i).getfTitle()+"</a></p>";
					}
			    	htmlcode=htmlcode.replaceAll("###itemsYL###",html);
			    	
			        //News newsPrev = NewsSelUtil.getNewsPrev(connection, news.getId());
			        News newsPrev = newsService.getNewsPrev(news.getId(), 1);
			        if(newsPrev!=null && newsPrev.getStaticpage()!=null){
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "<a href='"+newsPrev.getStaticpage()+"'>"+newsPrev.getTitle()+"</a>");
			        }else{
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "没有新闻了");
			        }
			        //News newsNex = NewsSelUtil.getNewsNext(connection);
			        News newsNex = newsService.getNewsNext(news.getId(),1);
			        if(newsNex!=null && newsNex.getStaticpage()!=null){
			        	 htmlcode=htmlcode.replaceAll("###newsnext###", "<a href='"+newsNex.getStaticpage()+"'>"+newsNex.getTitle()+"</a>");
			        }else{
			        	 htmlcode=htmlcode.replaceAll("###newsnext###", "没有新闻了");
			        }
			        fsh.WriteFile(htmlcode, OutHTMLpath+newsStaticPage);//生成静态文件
			        news.setStaticpage(newsStaticPage);
			        if(newsService.update(news)>0){
			        	logger.info("生成新闻静态页成功："+newsStaticPage);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		    }else if(news.getType() == 2) {
		    	try {
			        FileStreamHelp fsh = new FileStreamHelp();//实例化文件操作辅助类
			        String htmlcode = fsh.ReadFile(HyModelpath);//读取模板文件
			        htmlcode=htmlcode.replaceAll("###htmltitle###",news.getTitle());
			        if(news.getDescription().length()>30){
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription().substring(0, 30));
			        }else{
			        	htmlcode=htmlcode.replaceAll("###htmlcontent###", news.getDescription());
			        }
			        htmlcode=htmlcode.replaceAll("###newtitle###", news.getTitle());
			        htmlcode=htmlcode.replaceAll("###newsauthor###", news.getAuthor());
			        htmlcode=htmlcode.replaceAll("###newsources###", news.getSources());
			        htmlcode=htmlcode.replaceAll("###newstime###", sdf.format(news.getCreatetime()));
			        htmlcode=htmlcode.replaceAll("###newscontent###", news.getContent());
			        htmlcode=htmlcode.replaceAll("###newtitle###", news.getTitle());
			        htmlcode=htmlcode.replaceAll("###newsauthor###", news.getAuthor());
			        htmlcode=htmlcode.replaceAll("###newsources###", news.getSources());
			        htmlcode=htmlcode.replaceAll("###newstime###", sdf.format(news.getCreatetime()));
			        htmlcode=htmlcode.replaceAll("###newscontent###", news.getContent());
			        Items items = itemsService.selectItemsByKey("company_address");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###address###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_email");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_email###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("postcode");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###postcode###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_person");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_person###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_tel");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_tel###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("phone2");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###phone2###",items.getfContent());
			        }
			        items = itemsService.selectItemsByKey("company_qq");
			        if(items!=null) {
			        	htmlcode=htmlcode.replaceAll("###company_qq###",items.getfContent());
			        }
			        //友情链接
			    	List<Items> getItemsYL = itemsService.getitemsYL();
			    	String html = "";
			    	for (int i = 0; i < getItemsYL.size(); i++) {
						html += "<p><a href=\""+getItemsYL.get(i).getfUrl()+"\">"+getItemsYL.get(i).getfTitle()+"</a></p>";
					}
			    	htmlcode=htmlcode.replaceAll("###itemsYL###",html);
			    	
			        //News newsPrev = NewsSelUtil.getNewsPrev(connection, news.getId());
			        News newsPrev = newsService.getNewsPrev(news.getId(), 2);
			        if(newsPrev !=null &&  newsPrev.getStaticpage()!=null){
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "<a href='"+newsPrev.getStaticpage()+"'>"+newsPrev.getTitle()+"</a>");
			        }else{
			        	 htmlcode=htmlcode.replaceAll("###newsprev###", "没有新闻了");
			        }
			        //News newsNex = NewsSelUtil.getNewsNext(connection);
			        News newsNex = newsService.getNewsNext(news.getId(),2);
			        if(newsNex !=null && newsNex.getStaticpage()!=null){
			        	 htmlcode=htmlcode.replaceAll("###newsnext###", "<a href='"+newsNex.getStaticpage()+"'>"+newsNex.getTitle()+"</a>");
			        }else{
			        	 htmlcode=htmlcode.replaceAll("###newsnext###", "没有新闻了");
			        }
			        fsh.WriteFile(htmlcode, OutHTMLpath+newsStaticPage);//生成静态文件
			        news.setStaticpage(newsStaticPage);
			        if(newsService.update(news)>0){
			        	logger.info("生成新闻静态页成功："+newsStaticPage);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		    }
		    return StatusConstant.SUCCESS;
		} else {
			return StatusConstant.FAIL;
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id, PrintWriter out) {
		// 假删除方法
		News news = newsService.getById(id);
		news.setStatus(StatusConstant.DISABLE_STATUS);
		if (newsService.update(news) > 0) {
			out.print(StatusConstant.SUCCESS);
		} else {
			out.print(StatusConstant.FAIL);
		}
		out.flush();
		out.close();
	}
	
}
