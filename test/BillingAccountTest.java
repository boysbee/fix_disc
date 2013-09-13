import models.BillingAccount;

import org.junit.Test;

import play.test.UnitTest;


public class BillingAccountTest extends UnitTest{
	@Test 
	public void findBillingAccount(){
		int ban = 200068764;
		BillingAccount obj = BillingAccount.findWithBan(ban);
		
		assertNotNull(obj);
	
	}
}
