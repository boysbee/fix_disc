import models.Subscriber;

import org.junit.Test;

import play.test.UnitTest;


public class SubscriberTest extends UnitTest{
	@Test 
	public void findCurrentBan(){
		int ban = -1;
		
		String subscriberNo = "0863425243";
		String companyCode ="RM";
				
		int ban2 = Subscriber.findCurrentBan(subscriberNo,companyCode);
		
		assertEquals(897064401, ban2);
	}
}
