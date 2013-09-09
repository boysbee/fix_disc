import models.Subscriber;

import org.junit.Test;

import play.test.UnitTest;


public class SubscriberTest extends UnitTest{
	@Test 
	public void findCurrentBan(){
		int ban = -1;
		
		String subscriberNo = "0910790690";
		String companyCode ="RM";
		Subscriber obj = Subscriber.find("subscriberNo = ?1 and companyCode =?2", subscriberNo , companyCode).first();
		
		ban = obj.customerId;
		assertEquals(500006121, ban);
		
		int ban2 = Subscriber.findCurrentBan(subscriberNo,companyCode);
		
		assertEquals(500006121, ban2);
	}
}
