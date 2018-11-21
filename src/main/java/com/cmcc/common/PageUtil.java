package com.cmcc.common;

public class PageUtil {
	
	public static int getStartPage(int page,int rows){
		
		int startPage = (page-1)*rows;
		return startPage;
	}

}
