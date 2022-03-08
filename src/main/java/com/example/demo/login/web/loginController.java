package com.example.demo.login.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.common.FileUtils;
import com.example.demo.login.service.loginService;

//@RestController
@RestController
@RequestMapping(value = "/login")
public class loginController {

	@Autowired
	loginService loginService;

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;
	
	@PostMapping(value = "")
	public Map loginCheck(@RequestParam Map param, HttpServletRequest request) {
		Map resultMap = new HashMap<>();
		List<Map> result = loginService.loginCheck(param, request);
		System.out.println("result.size = "+result.size());
		String authCheck = "X";
		String success = "fail"; 
		if(result.size() > 0 ) {
			switch (result.get(0).get("user_auth") == null ? "" : result.get(0).get("user_auth").toString() ) {
			case "A":
				authCheck = "A";
				success = "success";
				break;
			case "U":
				authCheck = "U";
				success = "success";
				break;
			case "O":
				authCheck = "O";
				success = "success";
				break;
			default:
				success = "fail";
				break;
			}
		}
		
		resultMap.put("success", success);
		resultMap.put("result", result);
		resultMap.put("authCheck", authCheck);
		
		return resultMap;
	}

	@PostMapping(value = "/insertUser")
	public Map insertUser(MultipartHttpServletRequest filerequest, @RequestParam Map param, HttpServletRequest request) {
		Map resultMap = new HashMap<>();
		System.out.println("param = " + param);
		String retUuid;
		retUuid = attachFile(filerequest, request);
		param.put("", retUuid);
		resultMap = loginService.insertUser(param, request);
		return resultMap;
	}

	public String attachFile(MultipartHttpServletRequest filerequest, HttpServletRequest request) {

		MultipartFile file = filerequest.getFile("file");
		/*
		 * String root_path = request.getSession().getServletContext().getRealPath("/");
		 */
		String root_path = "C:\\dev\\file\\";
		String attach_path = "resources/upload/";
		String upload_path = root_path + attach_path;  

		System.out.println("file.getName = " + file.getName());
		System.out.println("file.getName = " + file.getSize());
		System.out.println("file.getName = " + file.getOriginalFilename());
		byte[] data = null;
		String sAttcFileNo = UUID.randomUUID().toString();
		List<Map<String, Object>> lAttachFilelist = null;
		try {
			lAttachFilelist = fileUtils.parseInsertFileInfo(sAttcFileNo, upload_path, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("lAttachFilelist = "+lAttachFilelist);
		
		return sAttcFileNo;
	}
	
}
