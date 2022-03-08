package com.example.demo.solomon.web;

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
import com.example.demo.solomon.service.solomonService;

//@RestController
@RestController
@RequestMapping(value = "/")
public class solomonController {

	@Autowired
	solomonService solomonService;
	
	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@GetMapping(value = "/sample.do")
	public List<Map> indexSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		List<Map> result = solomonService.solomonTest(request);
		return result;
	}

	@GetMapping(value = "/sampleJson.do")
	public ModelAndView jsonSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		List<Map> result = solomonService.solomonTest(request);
		ModelAndView mv = new ModelAndView("jsonView");

		mv.addObject("response", result);
		return mv;
	}

	@GetMapping(value = "/testPost.do")
	public List<Map> postSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		List<Map> result = solomonService.solomonTest(request);

		return result;
	}

	@GetMapping(value = "/testPostId.do")
	public List<Map> postIdSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = solomonService.solomonsearchTest(param, request);

		return result;
	}

	@PostMapping(value = "/testPPostId.do")
	public List<Map> ppostIdSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = solomonService.solomonsearchTest(param, request);

		return result;
	}

	@PostMapping(value = "/testPPosition.do")
	public List<Map> positionSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = solomonService.positionSolomonTest(param, request);

		return result;
	}

	@PostMapping(value = "/searchPosition.do")
	public List<Map> searchPositionSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = solomonService.searchPosition(param, request);

		return result;
	}

	@PostMapping(value = "/positionInsert.do")
	public List<Map> positionInsertSolomonTest(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = null;
		solomonService.positionInsertSolomonTest(param, request);

		return result;
	}

	@PostMapping(value = "/fileTest.do")
	public void fileTest(MultipartHttpServletRequest filerequest, HttpServletRequest request) {

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

	}
}
