package com.spring.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.command.FileUploadCommand;

@Controller
public class FileUploadController {

	@GetMapping("/fileUploadForm")
	public void fileUpload() {
	}

	private String uploadPath = "C:/fileuipload/".replace("/", File.separator);

	//추천
	@PostMapping(value = "/multipartFile", produces = "text/plain;charset=utf-8")
	public String uploadByMultipartFile(String title, MultipartFile file, Model model) throws Exception {

		String url = "/fileUploaded";

		File saveFile = new File(uploadPath, file.getOriginalFilename());

		saveFile.mkdirs();

		// 파일 저장
		file.transferTo(saveFile);

		model.addAttribute("title", title);
		model.addAttribute("originalFileName", file.getOriginalFilename());
		model.addAttribute("uploadedFileName", saveFile.getName());
		model.addAttribute("uploadPath", saveFile.getAbsolutePath());

		return url;
	}
	// 비추천
	@PostMapping(value = "/multipartHttpServletRequest", produces = "text/plain;charset=utf-8")
	public String uploadByMultipartHttpServletRequest(MultipartHttpServletRequest request, Model model)
			throws Exception {
		String url = null;

		String title = request.getParameter("title");
		MultipartFile multi = request.getFile("file");

		url = uploadByMultipartFile(title, multi, model);

		return url;
	}
	//추천 - 전체를 받을 때
	@PostMapping(value = "/commandObject", produces = "text/plain;charset=utf-8")
	public String uploadByCommandObject(FileUploadCommand cmd, Model model) throws Exception {
		String url = null;

		url = uploadByMultipartFile(cmd.getTitle(), cmd.getFile(), model);
		
		return url;
	}
}
