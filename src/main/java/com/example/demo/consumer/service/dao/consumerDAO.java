package com.example.demo.consumer.service.dao;

import java.util.List;
import java.util.Map;

public interface consumerDAO {
	
	public List<Map> searchPosition(Map map);
	
	public List<Map> searchStore(Map map);
	
	public List<Map> searchCategory(Map map);
	
	public List<Map> searchStoreName(Map map);
	
	public void insertStore(Map map);
	
	public void insertMenu(Map map);
	
	public void positionInsertconsumerTest(Map map);
	
	public void storeFileMain(Map map);
	
	public void storeFileAttach(Map map);

}
