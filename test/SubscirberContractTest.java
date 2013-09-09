import java.util.List;

import models.SubscriberContract;

import org.junit.Test;

import play.test.UnitTest;


public class SubscirberContractTest extends UnitTest{
	@Test 
	public void findListSubscirberContract(){
		List list = null;
		int ban = 857244410;
		String subscriberNo = "0830156708";
		
		list = SubscriberContract.findListSubscriberContract(ban , subscriberNo);
		
		assertNotNull(list);

	}
}
