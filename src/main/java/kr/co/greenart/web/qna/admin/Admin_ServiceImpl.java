package kr.co.greenart.web.qna.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Admin_ServiceImpl implements Admin_Service{
	@Autowired
	private Admin_Mapper mapper;
	
	@Override
	public List<Admin> findAll() {
		return mapper.findAll();
	}

	@Override
	public Integer findCur(String id, String password) {
		return mapper.findCur(id, password);	
	}

}
