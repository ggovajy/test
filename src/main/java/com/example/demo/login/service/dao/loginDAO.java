package com.example.demo.login.service.dao;

import java.util.List;
import java.util.Map;

public interface loginDAO {
	
	public List<Map> loginCheck(Map map);
	
	public List<Map> loginIdCheck(Map map);
	
	public int inserUser(Map map);

}
