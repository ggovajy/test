package com.example.demo.consumer.web;

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
import com.example.demo.consumer.service.consumerService;

//@RestController
@RestController
@RequestMapping(value = "/consumer")
public class consumerController {

	@Autowired
	consumerService consumerService;

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@PostMapping(value = "/position")
	public List<Map> searchPosition(@RequestParam Map param, HttpServletRequest request) {

		List<Map> result = consumerService.searchPosition(param, request);
		return result;
	}

	@PostMapping(value = "/searchStore")
	public List<Map> searchStroe(@RequestParam Map param, HttpServletRequest request) {
		System.out.println("param ===" + param);
		List<Map> result = consumerService.searchStore(param, request);
		return result;
	}

	@PostMapping(value = "/searchCategory")
	public List<Map> searchCategory(@RequestParam Map param, HttpServletRequest request) {

		List<Map> result = consumerService.searchCategory(param, request);
		return result;
	}

	@PostMapping(value = "/searchStoreName")
	public List<Map> searchStoreName(@RequestParam Map param, HttpServletRequest request) {

		List<Map> result = consumerService.searchStoreName(param, request);
		return result;
	}

	@PostMapping(value = "/insertStore")
	public List<Map> insertStore(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = null;
		consumerService.insertStore(param, request);

		return result;
	}

	@PostMapping(value = "/insertMenu")
	public List<Map> insertMenu(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = null;
		consumerService.insertMenu(param, request);

		return result;
	}

	@PostMapping(value = "/positionInsert.do")
	public List<Map> positionInsertconsumerTest(@RequestParam Map param, HttpServletRequest request) {

		System.out.println("param = " + param);
		List<Map> result = null;
		consumerService.positionInsertconsumerTest(param, request);

		return result;
	}

	@PostMapping(value = "/fileTest.do")
	public void fileTest(@RequestParam Map param, MultipartHttpServletRequest filerequest, HttpServletRequest request) {

		MultipartFile file = filerequest.getFile("uploaded_file");
		/*
		 * String root_path = request.getSession().getServletContext().getRealPath("/");
		 */
		System.out.println("param = " + param);
		String root_path = "C:\\dev\\file\\";
		String attach_path = "resources/upload/";
		String upload_path = root_path + attach_path;
		boolean succCheck = false;

		System.out.println("file.getName = " + file.getName());
		System.out.println("file.getName = " + file.getSize());
		System.out.println("file.getName = " + file.getOriginalFilename());
		byte[] data = null;
		String sAttcFileNo = UUID.randomUUID().toString();
		List<Map<String, Object>> lAttachFilelist = null;
		try {
			// 실제 파일 저장
			lAttachFilelist = fileUtils.parseInsertFileInfo(sAttcFileNo, upload_path, request);
			// smt_attach_main 에 추가
			consumerService.storeFileMain(lAttachFilelist.get(0), request);
			System.out.println("lAttachFilelist = " + lAttachFilelist);
			// smt_attach_files 에 추가
			consumerService.storeFileAttach(lAttachFilelist.get(0), request);
			succCheck = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			succCheck = false;
		}

		if (succCheck) {
			System.out.println("param = 메뉴 및 가게 테이블 데이터 추가" + param);
		}

	}
}
