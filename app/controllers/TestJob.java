package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import models.BillingAccount;
import models.CsmDiscount;
import models.CsmDiscountPK;
import models.ResultBean;
import models.ServiceAgreement;
import models.Subscriber;
import models.SubscriberContract;
import play.Logger;
import util.ObjectUtils;

public class TestJob extends Application {
	public static void testJob(String jobName, String ucrNo, String discountCode) {
		Logger.info(
				"@@ testJob -> job_name : %s,discount code : %s , ucr_no : %s",
				jobName, discountCode, ucrNo);
		render(jobName, discountCode, ucrNo);
	}

	public static void testResult(String jobName, String discountCode,
			String ucrNo, String ban, String subscriberNo) {
		Logger.info(
				"@@ testResult -> job_name : %s,discount code : %s , ucr_no : %s , ban : %s , subscriber_no : %s",
				jobName, discountCode, ucrNo, ban, subscriberNo);
		java.util.List<models.ResultBean> resultPriceplans = new java.util.ArrayList<models.ResultBean>();
		java.util.List<models.ResultBean> resultPropositions = new java.util.ArrayList<models.ResultBean>();
		java.util.List<models.ResultBean> resultSocs = new java.util.ArrayList<models.ResultBean>();
		// csm_discount data
		
		CsmDiscount data = CsmDiscount.findById(new CsmDiscountPK(jobName,
				discountCode, ucrNo));
		Logger.info("@@ testResult find CSM_DISCOUNT -> %s",data.toString() );
		List<String> propositionExpected = new ArrayList<String>();
		List<String> pricePlanExpected = new ArrayList<String>();
		List<String> socExpected = new ArrayList<String>();
		
		if (data != null && data.propo != null && !"".equals(data.propo)) {
			if (data.propo.indexOf(",") > 0) {
				propositionExpected = Arrays.asList(data.propo.split(","));
			} else {
				propositionExpected.add(data.propo);
			}
		}
		if (data != null && data.pp != null && !"".equals(data.pp)) {
			if (data.pp.indexOf(",") > 0) {
				pricePlanExpected = Arrays.asList(data.pp.split(","));
			} else {
				pricePlanExpected.add(data.pp);
			}

		}
		if (data != null && data.soc != null && !"".equals(data.soc)) {
			if (data.soc.indexOf(",") > 0) {
				socExpected = Arrays.asList(data.soc.split(","));
			} else {
				socExpected.add(data.soc);
			}
		}
		// find BillingAccount
		BillingAccount billingAccount = null;
		int currentBan = -1;
		if (ban != null && !"".equals(ban)) {
			Logger.info("@@ find billing account with ban : %s ", ban);
			int conv = Integer.parseInt(ban);
			billingAccount = findBillingAccount(conv);
		} else {
			Logger.info("@@ find current ban with subscirber_no : %s",
					subscriberNo);
			currentBan = findCurrentBan(subscriberNo, "RM");
			billingAccount = findBillingAccount(currentBan);
		}
		currentBan = billingAccount.ban;
		String accountType = billingAccount.accountType;
		String accCate = billingAccount.accountCate;

		List<ServiceAgreement> currentSoc = findCurrentSoc(currentBan,
				subscriberNo);
		List<SubscriberContract> currentPropo = findCurrentProposition(
				currentBan, subscriberNo);
		String currentPricePlan = findCurrentPricePlan(currentBan, subscriberNo);

		// test Result
		resultPriceplans = mockResultPricePlan(pricePlanExpected,
				currentPricePlan);
		resultPropositions = mockResultPropo(propositionExpected, currentPropo);
		resultSocs = mockResultSoc(socExpected,currentSoc);

		render(currentPricePlan, currentPropo, resultPriceplans,
				resultPropositions, resultSocs);
	}

	private static BillingAccount findBillingAccount(int ban) {
		// TODO use BillingAccount to find account profile.
		return BillingAccount.findWithBan(ban);
	}

	private static int findCurrentBan(String subscriberNo, String companyCode) {
		// TODO use Subscriber to find curret customer id

		return Subscriber.findCurrentBan(subscriberNo, companyCode);
	}

	private static List<ServiceAgreement> findCurrentSoc(int ban,
			String subscriberNo) {
		// TODO user ServiceAgreement to finc current socs.
		return ServiceAgreement.findSocList(ban, subscriberNo);
	}

	private static List<SubscriberContract> findCurrentProposition(int ban,
			String subscriberNo) {
		// TODO Use SubscriberContract to find currentProposition
		return SubscriberContract.findListSubscriberContract(ban, subscriberNo);
	}

	private static String findCurrentPricePlan(int ban, String subscriberNo) {
		// TODO use ServiceAgreement to find current price plan
		return ServiceAgreement.findCurrentPricePlan(ban, subscriberNo);
	}

	private static java.util.List<models.ResultBean> mockResultPricePlan(
			List<String> pricePlanExpected, String currentPricePlan) {
		
		java.util.List<models.ResultBean> resultPriceplans = new java.util.ArrayList<models.ResultBean>();
		String result= "";
		for (String pp : pricePlanExpected) {
			result = "fail";
			if(pp.equals(currentPricePlan)) {
				result = "pass";
			}
			resultPriceplans.add(new ResultBean(pp,result));
		}
		
		return resultPriceplans;
	}

	private static java.util.List<models.ResultBean> mockResultPropo(List<String> propositionExpected, List<SubscriberContract> currentPropo) {
		java.util.List<models.ResultBean> resultPropositions = new java.util.ArrayList<models.ResultBean>();
		String result = "";
		for (String proposition : propositionExpected) {
			result = "fail";
			for(SubscriberContract contract : currentPropo) {
				if( proposition.equals(contract.proposition )) {
					result = "pass";
				}
			}
			resultPropositions.add(new ResultBean(proposition, result));
			
		}
		Logger.debug("@@ test proposition -> %s ",ObjectUtils.reflectionAllToString(resultPropositions));
		return resultPropositions;
	}

	private static java.util.List<models.ResultBean> mockResultSoc(List<String> socExpected, List<ServiceAgreement> currentSoc) {
		java.util.List<models.ResultBean> resultSoc = new java.util.ArrayList<models.ResultBean>();
		String result = "";
		for (String soc : socExpected) {
			result = "fail";
			for(ServiceAgreement serviceAgreement : currentSoc) {
				if( soc.equals(serviceAgreement.soc )) {
					result = "pass";
				}
			}
			resultSoc.add(new ResultBean(soc, result));	
		}

		return resultSoc;
	}
}
