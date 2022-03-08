package com.example.demo.login.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface loginService {
	public List<Map> loginCheck(Map param, HttpServletRequest request);

	public Map<String, Object> insertUser(Map param, HttpServletRequest request);
}
