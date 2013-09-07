package controllers;

import java.util.Date;
import java.util.List;

import models.CsmDiscount;
import play.Logger;
import util.DateUtil;

public class CsmDiscounts extends Application {
	public static void index() {
		render();
	}

	public static void list(String jobName, String discountCode, String ucrNo,String keyword,
			Integer size, Integer page) {
		Logger.info(
				"@@ list -> job_name : %s , discount_code : %s , ucr_no : %s , keyword : %s",
				jobName, discountCode, ucrNo,keyword);
		List<CsmDiscount> csmdiscounts = null;
		page = page != null ? page : 1;
		if (jobName.trim().length() == 0 && discountCode.trim().length() == 0
				&& ucrNo.trim().length() == 0) {
			csmdiscounts = CsmDiscount.all().fetch(page, size);
		} else {
			String sql = "1=1 ";
			if (jobName != null && !"".equals(jobName)) {
				jobName = jobName.toLowerCase();
				sql += "AND lower(pk.jobName) like '%" + jobName + "%' ";
			}
			if (discountCode != null && !"".equals(discountCode)) {
				discountCode = discountCode.toLowerCase();
				sql += "AND lower(pk.discountCode) like '%" + discountCode
						+ "%' ";
			}
			if (ucrNo != null && !"".equals(ucrNo)) {
				ucrNo = ucrNo.toLowerCase();
				sql += "AND lower(pk.ucrNo) like '%" + ucrNo + "%' ";
			}

			Logger.debug("@@ find with HQL : %s", sql);
			csmdiscounts = CsmDiscount.find(sql).fetch(page, size);
		}
		render(csmdiscounts, jobName, discountCode, ucrNo, size, page);
	}

	public static void show(String jobName, String ucrNo, String discountCode) {
		CsmDiscount item = CsmDiscount.find(
				"pk.jobName = ?1 and pk.ucrNo = ?2 and pk.discountCode = ?3",
				jobName, ucrNo, discountCode).first();
		render(item);
	}

	
	public static void add() {
		CsmDiscount item = new CsmDiscount();
	
		render(item);
	}


	
	public static void save() {
		Logger.info("##### start save ######");
		CsmDiscount item = new CsmDiscount();
		item.pk.jobName = request.params.get("item.pk.jobName");
		item.pk.discountCode = request.params.get("item.pk.discountCode");
		item.pk.ucrNo = request.params.get("item.pk.ucrNo");
		item.devName = request.params.get("item.devName");
		item.businessOwner = request.params.get("item.businessOwner");
		item.soc = request.params.get("item.soc");
		item.pp = request.params.get("item.pp");
		item.propo = request.params.get("item.propo");
		item.keyword = request.params.get("item.keyword");
		item.accType = request.params.get("item.accType");
		item.accCate = request.params.get("item.accCate");
		item.actvCode = request.params.get("item.actvCode");
		item.actvRsnCode = request.params.get("item.actvRsnCode");
		item.benefit = request.params.get("item.benefit");
		item.advancePayment = request.params.get("item.advancePayment");
		String projectStartDate = request.params.get("item.projectStartDate");
		String projectEndDate = request.params.get("item.projectStartDate");
		item.remark = request.params.get("item.remark");
		item.setProjectStartDate(DateUtil.convertDate(projectStartDate,
				"yyyy-MM-dd", "dd/MM/yyyy"));
		item.setProjectEndDate(DateUtil.convertDate(projectEndDate,
				"yyyy-MM-dd", "dd/MM/yyyy"));
		item.setSysCreationDate(new Date());
		item.setSysUpdateDate(new Date());

		Logger.info("@@ .... save %s ", item.toString());
		boolean isSuccess = item.create();
		if (isSuccess) {
			Logger.info("@@ save :%s  -> success ", item.toString());
		} else {
			Logger.info("@@ save :%s  -> fail ", item.toString());
		}
		Logger.info("@@ redirect to index page.");
		index();
	}
}