package kr.co.greenart.web.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface QnaMapper {
	// 글작성
	@Insert(value = {"INSERT INTO customerqna (title, content, username, password)"
			, " VALUES (#{title}, #{content}, #{username}, #{password})"})
	void save(String title, String content, String username, String password);
	
	// 게시글 목록 (article_id, title, username, is_secure)
	@Select("SELECT article_id, title, username, is_secure FROM customerqna WHERE is_deleted = 0 AND is_secure = 0")
	void findAll();
	
	// 게시글 조회 시 is_secure의 값이 false인 행만 조회
	@Select("SELECT title, content, user FROM customerqna WHERE article_id = #{article_id} AND is_secure = 0")
	void findBySecureIsFalse(int article_id);

	// 게시글 조회 (id로 검색, title, content, username)
	@Select("SELECT title, content, user FROM customerqna WHERE article_id = #{article_id} AND is_secure = 0")
	void findByPk(int article_id);
	
	// 게시글의 비밀 여부 조회 (is_secure)
	@Select("SELECT is_secure FROM customerqna WHERE article_id = #{article_id}")
	void findSecureByPk(int article_id);
	
	// views count 수정(1 증가)
	@Update("UPDATE customerqna SET views = (views + 1) WHERE article_id = #{article_id}")
	void updateCount(int article_id);
	
	// 글 논리 삭제(pk 및 password일치) : is delete -> 1
	@Update("UPDATE customerqna SET is_deleted = 1 WHERE article_id = #{article_id} AND password = #{password}")
	void updateDelete(int article_id, String password);
}
