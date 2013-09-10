package controllers;

import static play.Logger.info;

import java.util.ArrayList;
import java.util.List;

import util.ObjectUtils;

import models.CsmDiscount;

public class FillUp extends Application {
	

	public static void fill(String type,String query){
		info("@@ type : %s , query : %s",type,query);
		List<?> list = null;
		if("jobName".equals(type)) {
			list = searchJobName(query);
		}
		if("ucrNo".equals(type)){
			list = searchUcrNo(query);
		}
		
		if("discountCode".equals(type)) {
			list = searchDiscountCode(query);
		}
		renderJSON(list);
	}
	
	private static List<?> searchDiscountCode(String query) {
		List<CsmDiscount> list = CsmDiscount.find("lower(pk.discountCode) like ?1" , "%" + query.toLowerCase() + "%").fetch();
		return list == null ? new ArrayList<CsmDiscount>() : list;
	}


	private static List<?> searchUcrNo(String query) {
		List<CsmDiscount> list = CsmDiscount.find("lower(pk.ucrNo) like ?1" , "%" + query.toLowerCase() + "%").fetch();
		return list == null ? new ArrayList<CsmDiscount>() : list;
	}

	private static List<?> searchJobName(String query)  {
		List<CsmDiscount> list = CsmDiscount.find("lower(pk.jobName) like ?1" , "%" + query.toLowerCase() + "%").fetch();
		return list == null ? new ArrayList<CsmDiscount>() : list;
	}
	
}