package com.example.demo.solomon.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface solomonService {
	public List<Map> solomonTest(HttpServletRequest request);
	
	public List<Map> solomonsearchTest(Map param, HttpServletRequest request);
	
	public List<Map> positionSolomonTest(Map param, HttpServletRequest request);
	
	public List<Map> searchPosition(Map param, HttpServletRequest request);
	
	public boolean positionInsertSolomonTest(Map param, HttpServletRequest request);
}
