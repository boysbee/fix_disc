import java.util.List;

import models.ServiceAgreement;

import org.junit.Test;

import play.test.UnitTest;


public class ServiceAgreementTest extends UnitTest{
	@Test 
	public void findSocList(){
		// given
		List socList = null;
		int ban = 898408975;
		String subscriberNo = "0886054168";
		// when
		socList = ServiceAgreement.findSocList(ban , subscriberNo);
		// then
		assertNotNull(socList);
	}

	@Test 
	public void findCurrentPricePlan(){
		int ban = 898408975;
		String subscriberNo = "0886054168";

		String pp = ServiceAgreement.findCurrentPricePlan(ban,subscriberNo);

		assertNotNull(pp);
	}
}
