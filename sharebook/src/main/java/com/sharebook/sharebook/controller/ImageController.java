package com.sharebook.sharebook.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/book/images")
public class ImageController implements ApplicationContextAware{
	@Value("/upload/")
	private String uploadDirLocal;
	private WebApplicationContext context;
	private String uploadDir;

	@Override // life-cycle callback method
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath(this.uploadDirLocal);
		System.out.println(this.uploadDir);
	}

	@ResponseBody
	@PostMapping("/imgUpload.do")
	public Map<String, Object> imgUpload(@RequestParam("upload") MultipartFile image) throws IOException{
		return uploadFile(image);
	}
	@ResponseBody
	@GetMapping("/upload/{filename}")
	public Resource imgDownload(@PathVariable("filename") String filename) throws IOException{
		return new UrlResource("file:" + this.uploadDir + filename);
	}
	
	private Map<String,Object> uploadFile(MultipartFile image) throws IOException{
		if(image.isEmpty()) {
			return null;
		}
		Map<String,Object> map = new HashMap<>();
		String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
		String folder = this.uploadDir;
		System.out.print(false);
		File file = new File(folder + filename);

		try {
			image.transferTo(file);
			map.put("uploaded",1);
			map.put("fileName", filename);
			map.put("url","/book/images/upload/"+filename);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
