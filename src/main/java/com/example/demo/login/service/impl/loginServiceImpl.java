package com.example.demo.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.demo.login.service.loginService;
import com.example.demo.login.service.dao.loginDAO;

@Service
public class loginServiceImpl implements loginService {

	@Resource(name = "loginDAO")
	loginDAO loginDao;
	
	@Override
	public List<Map> loginCheck(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		List<Map> result = loginDao.loginCheck(param);
		return result;
	}
	
	@Override
	public Map<String, Object> insertUser(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		List<Map> result = loginDao.loginIdCheck(param);
		String successMessage = "";
		boolean successRet = false;
		if(result.size() == 0) {
			try {
				int success = loginDao.inserUser(param);
				if(success == 1) {
		//			성공시
					successMessage = "회원가입 성공";
					successRet = true;
				}else {
		//			실패시
					successMessage = "회원가입시 알수없는 오류가 발생했습니다";
					successRet = false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				successMessage = "회원가입시 알수없는 오류가 발생했습니다";
				successRet = false;
			}
		} else {
			successMessage = "이미 회원가입된 아이디입니다";
			successRet = false;
		}
		resultMap.put("message", successMessage);
		resultMap.put("success", successRet);
		return resultMap;
	}

}