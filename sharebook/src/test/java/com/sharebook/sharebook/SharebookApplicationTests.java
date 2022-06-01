package com.sharebook.sharebook;

import com.sharebook.sharebook.dao.JPAFundingDAO;
import com.sharebook.sharebook.domain.Funding;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SharebookApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void testFundingDAO() {
		System.out.println("****************************************");
		System.out.println("JPAFundingDAO TEST");
		System.out.println("****************************************");
		
		JPAFundingDAO dao = new JPAFundingDAO();
		
		Funding funding1 = new Funding(100, "TEST_TITLE", "TEST_AUTOR",
										"TEST_IMAGE", "TEST_DESCRIPTION",
										0, 999999, new Date(), 5);
		Funding funding2 = new Funding(200, "TEST_TITLE", "TEST_AUTOR",
										"TEST_IMAGE", "TEST_DESCRIPTION",
										0, 999999, new Date(), 5);
		Funding funding3 = new Funding(300, "TEST_TITLE", "TEST_AUTOR",
										"TEST_IMAGE", "TEST_DESCRIPTION",
										0, 999999, new Date(), 5);
		dao.create(funding1);
		dao.create(funding2);
		dao.create(funding3);
		
		dao.delete(300);
		
		funding2.setTitle("UPDATE_TEST");
		dao.update(200, funding2);
		
		Funding funding4 = dao.findFundingById(1);
		System.out.println("JPAFundingDAO findFundingById(funding_id)");
		System.out.println(funding4);
		System.out.println("****************************************");
		
		List<Funding> list1 = dao.findFundingByTitle("펀딩제목1");
		System.out.println("JPAFundingDAO findFundingByTitle(title)");
		System.out.println(list1);
		System.out.println("****************************************");

		List<Funding> list2 = dao.findFundingByAuthor("김저자");
		System.out.println("JPAFundingDAO findFundingByTitle(author)");
		System.out.println(list2);
		System.out.println("****************************************");
		
		List<Funding> list3 = dao.findFundingList();
		System.out.println("JPAFundingDAO findFundingList()");
		System.out.println(list3);
		System.out.println("****************************************");

		List<Funding> list4 = dao.findFundingList(5);
		System.out.println("JPAFundingDAO findFundingByTitle(user_id)");
		System.out.println(list4);
		System.out.println("****************************************");
	}

}
