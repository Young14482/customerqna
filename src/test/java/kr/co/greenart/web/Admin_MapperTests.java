package kr.co.greenart.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.greenart.web.qna.QNA;
import kr.co.greenart.web.qna.QnaMapper;
import kr.co.greenart.web.qna.admin.Admin;
import kr.co.greenart.web.qna.admin.Admin_Mapper;
import kr.co.greenart.web.qna.admin.Admin_Service;

@SpringBootTest
class Admin_MapperTests {
	@Autowired
	private Admin_Mapper mapper;
	@Autowired
	private QnaMapper qmapper;
	@Autowired
	private Admin_Service service;
	
	@Test
	void test() {
		List<Admin> all = mapper.findAll();
		List<QNA> all2 = qmapper.findAll(20, 0);
		
		assertEquals(1, all.size());
		assertEquals(20, all2.size());
	}
	
	@Test
	void admin() {
		List<Admin> all = mapper.findAll();
		Admin a = all.get(0);
		assertEquals("admin", a.getId());
	}
	
	@Test
	void admins() {
		List<Admin> all = service.findAll();
		Admin a = all.get(0);
		assertEquals("admin", a.getId());
	}

	@Test
	void curAdmin() {
		Integer cur = mapper.findCur("admin", "admin");
		
		assertEquals(1, cur);
	}
	@Test
	void curAdminS() {
		Integer cur = service.findCur("admin", "admin");
		
		assertEquals(1, cur);
	}
	
}
