package com.example.demo.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	private static final String filePath = "C:\\dev\\file\\";

	  public List<Map<String,Object>> parseInsertFileInfo(String sAttcFileNo, String filePath, HttpServletRequest request) throws Exception{
		    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		    
		    MultipartFile multipartFile = null;
		    String originalFileName = null;
		    String originalFileNameNonExt = null;
		    String originalFileExtension = null;
		    String storedFileName = null;
		     
		    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		    Map<String, Object> listMap = null;
		     
		    File file = new File(filePath);
		    //경로가 존재하지 않을 경우 디렉토리를 만든다.
		    if(file.exists() == false){
		        file.mkdirs();
		    }
		    
		    while(iterator.hasNext()){
		      multipartFile = multipartHttpServletRequest.getFile(iterator.next());
		      if(multipartFile.isEmpty() == false){
		        //업로드한 파일의 확장자를 포함한 파일명이다.
		        originalFileName = multipartFile.getOriginalFilename();
		        
		        //업로드한 파일의 마지막 .을 포함한 확장자를 substring 한 것.
		        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		        
		        //업로드한 파일의 확장자를 제외한 파일명
		        originalFileNameNonExt = originalFileName.substring(0, originalFileName.lastIndexOf("."));
		        
		        System.out.println("originalFileExtension  ==" + originalFileExtension);
		        System.out.println("originalFileNameNonExt  == " + originalFileNameNonExt);
		        //확장자를 제거한 파일명에 '-'와 32자리의 숫자를 포함한 랜덤 문자열을 합쳐 저장 파일명 생성
		        String sUUID = UUID.randomUUID().toString();
		        // 2021-01-14 ECM에서 공백이 파일명에 포함될경우 에러나기때문에 UUID만 사용하도록 변경
		        //storedFileName = originalFileNameNonExt + '-' + sUUID;
		        storedFileName = sUUID;
		        
		        /*
//		        System.out.println("=====> originalFileName : " + originalFileName);
//		        System.out.println("=====> originalFileExtension : " + originalFileExtension);
//		        System.out.println("=====> originalFileNameNonExt : " + originalFileNameNonExt);
//		        System.out.println("=====> storedFileName : " + storedFileName);
//		        System.out.println("=====> save Path : " + filePath + File.separator + storedFileName);
		        */
		        System.out.println("============> Save Path : " + filePath + File.separator + storedFileName);
		        
		        file = new File(filePath + File.separator + storedFileName);
		        multipartFile.transferTo(file);
		        
		        //String ecmFileId = "";
		        String ecmFileId = "";
		        
		        String sServerName = request.getServerName();
		        if(sServerName.indexOf("local") > -1) {
		          /**
		           * 로컬에서 테스틀 위해 임의로 32 byte uuid를 생성한다.
		           */
		          ecmFileId = UUID.randomUUID().toString().substring(0, 32);
		        } else {
		          Map<String, Object> paramMap = new HashMap<String, Object>();
		          //fileVO.set
		          
		          //첨부파일 attach no 를 넣는다.
		          paramMap.put("name", sAttcFileNo);
//		          System.out.println("============> sAttcFileNo : " + sAttcFileNo);
		          List<Map<String, Object>> listFile = new ArrayList<Map<String,Object>>();
		          Map<String, Object> fileEntry = new HashMap<String, Object>();
//		          
		          System.out.println("============> originalFileName : " + originalFileName);
//		          System.out.println("============> storedFileName : " + storedFileName);
		          fileEntry.put("name", originalFileName);
		          fileEntry.put("fileId", storedFileName);
		          fileEntry.put("status", "NEW");
		          
		          listFile.add(fileEntry);
		          
		        }
		        
		        listMap = new HashMap<String,Object>();
		        //업로드할 당시의 파일이름
		        listMap.put("uuidName", sAttcFileNo);
		        listMap.put("logicFileNm", originalFileNameNonExt);
		        listMap.put("logicFileExt", originalFileExtension);
		        listMap.put("filePath", filePath);
		        //저장할 파일 이름
		        listMap.put("physicFileNm", storedFileName);
		        listMap.put("fileSize", multipartFile.getSize());
		        listMap.put("fileSavLocFg", "C022200002");
		        listMap.put("ecmFileId", ecmFileId);
		        
		        list.add(listMap);
		      }
		    }
		    return list;
		  } 
}
