package com.example.demo.provider.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface providerService {
	public List<Map> providerTest(HttpServletRequest request);
	
	public List<Map> providersearchTest(Map param, HttpServletRequest request);
	
	public List<Map> positionproviderTest(Map param, HttpServletRequest request);
	
	public List<Map> searchPosition(Map param, HttpServletRequest request);
	
	public boolean positionInsertproviderTest(Map param, HttpServletRequest request);
}
