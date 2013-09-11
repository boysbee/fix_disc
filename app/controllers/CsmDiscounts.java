package controllers;

import java.util.Date;
import java.util.List;

import models.CsmDiscount;
import models.CsmDiscountPK;
import play.Logger;
import util.DateUtil;

public class CsmDiscounts extends Application {
	public static void index() {
		render();
	}

	public static void list(String jobName, String discountCode, String ucrNo,
			String keyword, Integer size, Integer page) {
		Logger.info(
				"@@ list -> job_name : %s , discount_code : %s , ucr_no : %s , keyword : %s",
				jobName, discountCode, ucrNo, keyword);
		page = page != null ? page : 1;
		List<CsmDiscount> csmdiscounts = CsmDiscount.findList(jobName,
				discountCode, ucrNo, keyword, size, page);
		render(csmdiscounts, jobName, discountCode, ucrNo, size, page);
	}

	public static void show(String jobName, String ucrNo, String discountCode) {
		CsmDiscount item = CsmDiscount.findById(new CsmDiscountPK(jobName,
				discountCode, ucrNo));
		render(item);
	}

	public static void add() {
		CsmDiscount item = new CsmDiscount();
		render(item);
	}

	public static void edit(String jobName, String ucrNo, String discountCode) {
		CsmDiscount item = CsmDiscount.findById(new CsmDiscountPK(jobName,
				discountCode, ucrNo));
		render(item);

	}

	public static void save() {
		Logger.info("##### start save ######");
		CsmDiscount item = new CsmDiscount();
		item.pk.jobName = params.get("item.pk.jobName");
		item.pk.discountCode = params.get("item.pk.discountCode");
		item.pk.ucrNo = params.get("item.pk.ucrNo");
		item.devName = params.get("item.devName");
		item.businessOwner = params.get("item.businessOwner");
		item.soc = params.get("item.soc");
		item.pp = params.get("item.pp");
		item.propo = params.get("item.propo");
		item.keyword = params.get("item.keyword");
		item.accType = params.get("item.accType");
		item.accCate = params.get("item.accCate");
		if (item.accCate.lastIndexOf(",") > -1) {
			item.accCate = item.accCate.substring(0,
					(item.accCate.length() - 1));
		}
		item.actvCode = params.get("item.actvCode");
		item.actvRsnCode = params.get("item.actvRsnCode");
		item.benefit = params.get("item.benefit");
		item.advancePayment = params.get("item.advancePayment");
		String projectStartDate = params.get("item.projectStartDate");
		String projectEndDate = params.get("item.projectStartDate");
		item.remark = params.get("item.remark");
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