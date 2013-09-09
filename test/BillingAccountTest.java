import models.BillingAccount;

import org.junit.Test;

import play.test.UnitTest;


public class BillingAccountTest extends UnitTest{
	@Test 
	public void findBillingAccount(){
		int ban = 500006120;
		BillingAccount obj = BillingAccount.find("ban = ?1", ban).first();
		
		assertNotNull(obj);
		
		BillingAccount obj2 = BillingAccount.findWithBan(ban);
		
		assertNotNull(obj2);
	}
}
