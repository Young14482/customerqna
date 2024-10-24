package kr.co.greenart.web.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QnaMapper {
	// 글작성
	@Insert({
		"INSERT INTO customerqna (title, content, username, password)"
			, "VALUES (#{title}, #{content}, #{username}, #{password})"
			})
	@Options(useGeneratedKeys = true, keyProperty = "articleId") // h2 버젼 AI된 id가져오기
	int save(QNA qna);
	
	// 게시글 목록 >> limit와 offset을 통해 페이지 구현 준비
	@Select({
		"SELECT article_id, title, content, username, views, is_secure FROM customerqna"
		, "ORDER BY article_id DESC"
		, "LIMIT #{pageSize} OFFSET #{offset}"
	})
	@Results(id = "qnaList"
		, value = {
				@Result(column = "article_id", property = "articleId")
				, @Result(column = "title", property = "title")
				, @Result(column = "content", property = "content")
				, @Result(column = "username", property = "username")
				, @Result(column = "views", property = "views")
				, @Result(column = "is_secure", property = "secure")
		}
			)
	List<QNA> findAll(int pageSize, int offset);
	
	// 게시글 조회 시 is_secure의 값이 false인 행만 조회
	@Select({
		"SELECT article_id, title, content, username, views, is_secure FROM customerqna WHERE is_deleted = 0 AND is_secure = 0"
		, "WHERE is_secure = 0"
		, "ORDER BY article_id DESC"
		, "LIMIT #{pageSize} OFFSET #{offset}"
	})
	@ResultMap("qnaList")
	List<QNA> findBySecureIsFalse(int pageSize, int offset);

	// TODO : 선생님이랑 아직안함 >> 혼자 만든 거는 존재
	// 게시글 조회 (id로 검색, title, content, username)
	//TODO : 맨끝의 false 나중에 mysql전환할때 0으로
	@Select("SELECT title, content, username FROM customerqna WHERE article_id = #{articleId} AND is_secure = false")
	QNA findByPk(int articleId);
	
	// 게시글의 비밀 여부 조회 (is_secure)
	@Select("SELECT is_secure FROM customerqna WHERE article_id = #{articleId}")
	void findSecureByPk(int articleId);
	
	// views count 수정(1 증가)
	@Update("UPDATE customerqna SET views = (views + 1) WHERE article_id = #{articleId}")
	void updateCount(int articleId);
	
	// 글 논리 삭제(pk 및 password일치) : is delete -> 1
	@Update("UPDATE customerqna SET is_deleted = 1 WHERE article_id = #{article_id} AND password = #{password}")
	void updateDelete(int article_id, String password);
	
	// 전체글수
	@Select("SELECT count(*) FROM customerqna")
	int count();
	
}
