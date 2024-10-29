package kr.co.greenart.web.qna.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Admin_Mapper {
	@Select("SELECT * FROM admin")
	List<Admin> findAll();
	
	@Select("SELECT * FROM admin WHERE id = #{id} AND password = #{password}")
	Integer findCur(String id, String password);
}
