import java.util.List;

import models.SubscriberContract;

import org.junit.Test;

import play.test.UnitTest;
import util.ObjectUtils;


public class SubscirberContractTest extends UnitTest{
	@Test 
	public void findListSubscirberContract(){
		List list = null;
		int ban = 897064401;
		String subscriberNo = "0863425243";
		
		list = SubscriberContract.findListSubscriberContract(ban , subscriberNo);
		
		assertNotNull(list);
		System.out.println(ObjectUtils.reflectionAllToString(list));

	}
}
