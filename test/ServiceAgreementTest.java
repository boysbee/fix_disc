import java.util.List;

import models.ServiceAgreement;

import org.junit.Test;

import play.test.UnitTest;


public class ServiceAgreementTest extends UnitTest{
	@Test 
	public void findSocList(){
		// given
		List socList = null;
		int ban = 500006121;
		String subscriberNo = "0910790690";
		// when
		socList = ServiceAgreement.findSocList(ban , subscriberNo);
		// then
		assertNotNull(socList);
	}

	@Test 
	public void findCurrentPricePlan(){
		int ban = 857244410;
		String subscriberNo = "0830156708";

		String pp = ServiceAgreement.findCurrentPricePlan(ban,subscriberNo);

		assertNotNull(pp);
	}
}
