import java.util.List;

import models.CsmDiscount;

import org.junit.Test;

import play.test.UnitTest;

public class CsmDiscountTest extends UnitTest {
	@Test
	public void findAll() {
		List list = CsmDiscount.all().fetch();
		
		assertNotNull("all list not null.",list);
	}
	
	@Test
	public void findList(){
		String jobName = "";
		String discountCode = "DIR040";
		String ucrNo = "";
		String keyword = "";
		Integer size = 10;
		Integer page = 1;
		List list = CsmDiscount.findList(jobName, discountCode, ucrNo, keyword, size, page);
		
		assertEquals("DIR040", ((CsmDiscount) list.get(0)).pk.discountCode);
	}
}
