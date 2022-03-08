package com.example.demo.solomon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.demo.solomon.service.solomonService;
import com.example.demo.solomon.service.dao.solomonDAO;

@Service
public class solomonServiceImpl implements solomonService {

	@Resource(name = "solomonDAO")
	solomonDAO solomonDao;
	
	@Override
	public List<Map> solomonTest(HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = solomonDao.solomonTest(map);
		return result;
	}
	
	@Override
	public List<Map> solomonsearchTest(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = solomonDao.solomonTest(param);
		return result;
	}
	
	@Override
	public List<Map> positionSolomonTest(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = solomonDao.positionSolomonTest(param);
		return result;
	}
	
	@Override
	public List<Map> searchPosition(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = solomonDao.searchPosition(param);
		return result;
	}
	
	@Override
	public boolean positionInsertSolomonTest(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		solomonDao.positionInsertSolomonTest(param);
		return true;
	}

}