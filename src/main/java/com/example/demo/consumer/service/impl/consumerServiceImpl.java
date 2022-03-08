package com.example.demo.consumer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.demo.consumer.service.consumerService;
import com.example.demo.consumer.service.dao.consumerDAO;

@Service
public class consumerServiceImpl implements consumerService {

	@Resource(name = "consumerDAO")
	consumerDAO consumerDao;
	
	@Override
	public List<Map> searchPosition(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = consumerDao.searchPosition(param);
		return result;
	}
	
	@Override
	public List<Map> searchStore(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = consumerDao.searchStore(param);
		return result;
	}
	
	@Override
	public List<Map> searchCategory(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = consumerDao.searchCategory(param);
		return result;
	}
	
	@Override
	public List<Map> searchStoreName(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = consumerDao.searchStoreName(param);
		return result;
	}
	
	
	@Override
	public int insertStore(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		List<Map> result = consumerDao.searchPosition(param);
		param.put("str_dist", result.get(0) == null ? " " : result.get(0).get("str_dist"));
		System.out.println("param === "+ param);
		consumerDao.insertStore(param);
		return 1;
	}
	
	@Override
	public int insertMenu(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		consumerDao.insertMenu(param);
		return 1;
	}
	
	@Override
	public int positionInsertconsumerTest(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		consumerDao.positionInsertconsumerTest(param);
		return 1;
	}
	
	@Override
	public int storeFileMain(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		consumerDao.storeFileMain(param);
		return 1;
	}
	
	@Override
	public int storeFileAttach(Map param, HttpServletRequest request){
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		consumerDao.storeFileAttach(param);
		return 1;
	}

}