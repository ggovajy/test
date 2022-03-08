package com.example.demo.provider.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.demo.provider.service.providerService;
import com.example.demo.provider.service.dao.providerDAO;

@Service
public class providerServiceImpl implements providerService {

	@Resource(name = "providerDAO")
	providerDAO providerDao;
	
	@Override
	public List<Map> providerTest(HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = providerDao.providerTest(map);
		return result;
	}
	
	@Override
	public List<Map> providersearchTest(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = providerDao.providerTest(param);
		return result;
	}
	
	@Override
	public List<Map> positionproviderTest(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = providerDao.positionproviderTest(param);
		return result;
	}
	
	@Override
	public List<Map> searchPosition(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = providerDao.searchPosition(param);
		return result;
	}
	
	@Override
	public boolean positionInsertproviderTest(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		providerDao.positionInsertproviderTest(param);
		return true;
	}

}