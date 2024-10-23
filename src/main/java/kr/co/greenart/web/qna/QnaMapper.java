package kr.co.greenart.web.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QnaMapper {
	// 2번
	@Insert(value = {"INSERT INTO customerqna (title, content, username, password)"
			, " VALUES (#{title}, #{content}, #{username}, #{password})"})
	void pooping(String title, String content, String username, String password);
	
	// 3번
	@Select("SELECT article_id, title, username, is_secure FROM customerqna WHERE is_deleted = 0 AND is_secure = 0")
	List<Qna> findAll();
	
	// 4번
	@Select("SELECT title, content, user FROM customerqna WHERE article_id = #{article_id} AND is_secure = 0")
	List<Qna> findById(int article_id);
	
	// 5번
	@Select("SELECT is_secure FROM customerqna WHERE article_id = #{article_id}")
	Integer isSecure(int article_id);
	
	// 6번
	@Update("UPDATE customerqna SET views = (views + 1) WHERE article_id = #{article_id}")
	void viewContent(int article_id);
	
	// 7번
	@Update("UPDATE customerqna SET is_deleted = 1 WHERE article_id = #{article_id} AND password = #{password}")
	void deleteQna(int article_id, String password);
}
