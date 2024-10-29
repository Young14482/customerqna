package kr.co.greenart.web.qna.admin;

import java.util.List;

public interface Admin_Service {
	List<Admin> findAll();
	
	Integer findCur(String id, String password);
}
