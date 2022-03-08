package com.example.demo.consumer.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface consumerService {
	public List<Map> searchPosition(Map param, HttpServletRequest request);
	
	public List<Map> searchStore(Map param, HttpServletRequest request);
	
	public List<Map> searchCategory(Map param, HttpServletRequest request);
	
	public List<Map> searchStoreName(Map param, HttpServletRequest request);
	
	public int insertStore(Map param, HttpServletRequest request);
	
	public int insertMenu(Map param, HttpServletRequest request);
	
	public int positionInsertconsumerTest(Map param, HttpServletRequest request);
	
	public int storeFileMain(Map param, HttpServletRequest request);
	
	public int storeFileAttach(Map param, HttpServletRequest request);
}
