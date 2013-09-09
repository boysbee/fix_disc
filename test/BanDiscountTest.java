import org.junit.Test;

import play.test.UnitTest;
import java.util.*;

import models.*;

public class BanDiscountTest extends UnitTest{
	@Test 
	public void findDiscounts(){
		List list = null;
		int ban = 857244410;
		String subscriberNo = "0830156708";

		list = BanDiscount.findDiscountList(ban , subscriberNo);

		assertNotNull(list);
	}
}
