package controllers;
import models.ResultBean;
import play.Logger;
public class TestJob extends Application {
	public static void testJob(String jobName, String ucrNo , String discountCode ){
		Logger.info("job_name : %s,discount code : %s , ucr_no : %s",jobName,discountCode,ucrNo);
		render(jobName,discountCode,ucrNo);
	}
	
	public static void testResult(String jobName , String discountCode , String ucrNo , String ban, String subscriberNo){
		java.util.List<models.ResultBean> resultPriceplans = new java.util.ArrayList<models.ResultBean> ();
		java.util.List<models.ResultBean> resultPropositions = new java.util.ArrayList<models.ResultBean> ();
		java.util.List<models.ResultBean> resultSocs = new java.util.ArrayList<models.ResultBean> ();

		// MOCK
		resultPriceplans = mockResultPricePlan();
		resultPropositions = mockResultPropo();
		resultSocs = mockResultSoc();
		String currentPropo = "RMV0000000391";
		String currentPricePlan = "SMRTPP70";
		render(currentPricePlan,currentPropo,resultPriceplans,resultPropositions,resultSocs);
	}

	private static java.util.List<models.ResultBean> mockResultPricePlan(){
		java.util.List<models.ResultBean> resultPriceplans = new java.util.ArrayList<models.ResultBean> ();

		resultPriceplans.add(new ResultBean("SMRTPP60","fail"));
		resultPriceplans.add(new ResultBean("SMRTPP70","pass"));
		resultPriceplans.add(new ResultBean("SMRTPP80","fail"));
		resultPriceplans.add(new ResultBean("SMRTPP91","fail"));

		return resultPriceplans;
	}

	private static java.util.List<models.ResultBean> mockResultPropo(){
		java.util.List<models.ResultBean> resultPropositions = new java.util.ArrayList<models.ResultBean> ();

		resultPropositions.add(new ResultBean("RMV0000000390","fail"));
		resultPropositions.add(new ResultBean("RMV0000000391","pass"));

		return resultPropositions;
	}

	private static java.util.List<models.ResultBean> mockResultSoc(){
		java.util.List<models.ResultBean> resultSoc = new java.util.ArrayList<models.ResultBean> ();

		resultSoc.add(new ResultBean("SHRMS10","pass"));
		resultSoc.add(new ResultBean("SHRMS12","pass"));

		return resultSoc;
	}
}
