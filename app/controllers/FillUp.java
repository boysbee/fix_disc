package controllers;

import play.*;
import static play.Logger.*;
import java.util.*;

public class FillUp extends Application {
	

	public static void fill(String type,String query){
		List<?> list = null;
		if("jobName".equals(type)) {
			list = searchJobName(query);
		}

		return renderJSON(list);
	}
	
	private static List searchJobName(String query) throws Exception {
		info("FillUp.searchJobName()");
		
		List<CsmDiscount> list = CsmDiscount.find("jobName like ?1" , "'%" + query + "%'").fetch();
		return list;
	}
	
}