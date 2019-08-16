package com.online.college.opt.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.online.college.common.web.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;

import javax.servlet.http.HttpServletRequest;

/**
 * 轮播配置
 */
@Controller
@RequestMapping("/carousel")
public class SiteCarouselController{
	
	@Autowired
	private IConstsSiteCarouselService entityService;

	@RequestMapping(value = "/queryPage")
	public  ModelAndView queryPage(ConstsSiteCarousel queryEntity , TailPage<ConstsSiteCarousel> page){
		ModelAndView mv = new ModelAndView("cms/carousel/pagelist");
		mv.addObject("curNav", "carousel");
		page.setPageSize(5);//每页5条数据
		page = entityService.queryPage(queryEntity,page);
		mv.addObject("page",page);
		mv.addObject("queryEntity",queryEntity);
		return mv;
	}

	@RequestMapping(value = "/toMerge")
	public ModelAndView toMerge(ConstsSiteCarousel entity){
		ModelAndView mv = new ModelAndView("cms/carousel/merge");
		mv.addObject("curNav", "carousel");
		
		if(entity.getId() != null){
			entity = entityService.getById(entity.getId());
		}
		mv.addObject("entity",entity);
		return mv;
	}

	@RequestMapping(value = "/doMerge")
	public ModelAndView doMerge(ConstsSiteCarousel entity) throws  Exception{
		String key = null;
		String username = SessionContext.getUsername();

		entity.setCreateUser(username);
		entity.setUpdateUser(username);
		if(entity.getId() == null){
			entityService.create(entity);
		}else{
			entityService.update(entity);
		}
		return new ModelAndView("redirect:/carousel/queryPage.html");
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(ConstsSiteCarousel entity){
		entityService.delete(entity);
		return new JsonView().toString();
	}

	private static final String UPLOAD_PATH = "/res/upload/";

	/**
	 * 文件上传
	 *
	 * @param dropFile   Dropzone
	 * @param editorFiles wangEditor
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@RequestParam(value = "dropFile",required = false)MultipartFile dropFile, MultipartFile[] editorFiles, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		System.out.println(dropFile.getOriginalFilename());
		// Dropzone 上传
		if (dropFile != null) {
			result.put("fileName", writeFile(dropFile, request));
		}

		// wangEditor 上传
		if (editorFiles != null && editorFiles.length > 0) {
			List<String> fileNames = new ArrayList<>();

			for (MultipartFile editorFile : editorFiles) {
				fileNames.add(writeFile(editorFile, request));
			}

			result.put("errno", 0);
			result.put("data", fileNames);
		}

		return result;
	}

	/**
	 * 将图片写入指定目录
	 *
	 * @param multipartFile
	 * @param request
	 * @return 返回文件完整路径
	 */
	private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {
		// 获取文件后缀
		String fileName = multipartFile.getOriginalFilename();
		String fileSuffix = fileName.substring(fileName.lastIndexOf("."));

		// 文件存放路径
		String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

		// 判断路径是否存在，不存在则创建文件夹
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}

		// 将文件写入目标
		file = new File(filePath, UUID.randomUUID() + fileSuffix);
		try {
			multipartFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 返回文件完整路径
		String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		return serverPath + UPLOAD_PATH + file.getName();
	}
}

