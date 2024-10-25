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
import org.apache.ibatis.jdbc.SQL;

import kr.co.greenart.web.util.MyOrder;

@Mapper
public interface QnaMapper {
	// 글작성
	@Insert({
		"INSERT INTO customerqna (title, content, username, password, is_secure)"
			, "VALUES (#{title}, #{content}, #{username}, #{password}, #{secure})"
			})
	@Options(useGeneratedKeys = true, keyProperty = "articleId") // h2 버젼 AI된 id가져오기
	int save(QNA qna);
	
	// 게시글 목록 >> limit와 offset을 통해 페이지 구현 준비
	// TODO 나중에 연동하면 false바꾸기
	@Select({
		"SELECT * FROM customerqna"
		, "WHERE is_deleted = false"
		, "ORDER BY article_id DESC"
		, "LIMIT #{pageSize} OFFSET #{offset}"
	})
	@Results(id = "qnaList"
		, value = {
				@Result(column = "article_id", property = "articleId", id = true)
				, @Result(column = "title", property = "title")
				, @Result(column = "password", property = "password")
				, @Result(column = "content", property = "content")
				, @Result(column = "username", property = "username")
				, @Result(column = "views", property = "views")
				, @Result(column = "created_at", property = "createdAt")
				, @Result(column = "updated_at", property = "updatedAt")
				, @Result(column = "is_secure", property = "secure")
				, @Result(column = "is_deleted", property = "deleted")
		}
	)
	List<QNA> findAll(int pageSize, int offset);
	
	// 게시글 조회 시 is_secure의 값이 false인 행만 조회
	@Select({
		"SELECT article_id, title, content, username, views, is_secure FROM customerqna WHERE is_deleted = 0 AND is_secure = 0"
		, "ORDER BY article_id DESC"
		, "LIMIT #{pageSize} OFFSET #{offset}"
	})
	@ResultMap("qnaList")
	List<QNA> findBySecureIsFalse(int pageSize, int offset);
	
	// views count 수정(1 증가)
	@Update("UPDATE customerqna SET views = (views + 1) WHERE article_id = #{articleId}")
	int updateCount(int articleId);

	// TODO : 선생님이랑 아직안함 >> 혼자 만든 거는 존재
	// 게시글 조회 (id로 검색, title, content, username)
	// TODO : 맨끝의 false 나중에 mysql전환할때 0으로
	@Select("SELECT * FROM customerqna WHERE article_id = #{articleId} AND is_deleted = false")
	@Results(id = "qnaMapping"
	, value = {
			@Result(column = "article_id", property = "articleId", id = true)
			, @Result(column = "title", property = "title")
			, @Result(column = "password", property = "password")
			, @Result(column = "content", property = "content")
			, @Result(column = "username", property = "username")
			, @Result(column = "views", property = "views")
			, @Result(column = "created_at", property = "createdAt")
			, @Result(column = "updated_at", property = "updatedAt")
			, @Result(column = "is_secure", property = "secure")
			, @Result(column = "is_deleted", property = "deleted")
		}
	)
	QNA findById(Integer articleId);
	
	// 글 논리 삭제(pk 및 password일치) : is delete -> 1
	@Update("UPDATE customerqna SET is_deleted = 1 WHERE article_id = #{article_id}")
	int updateDelete(Integer article_id);
	
	// 전체글수
	@Select("SELECT count(*) FROM customerqna")
	int count();
	
	@Update({
		"UPDATE customerqna SET"
			, " title = #{title}, "
			, " password = #{password}, "
			, " content = #{content}, "
			, " username = #{username}, "
			, " is_secure = #{secure}, "
			, " updated_at = NOW()"
			, " WHERE article_id = #{articleId};"
			})
	int updateQNA(QNA qna);
	
	class SQLProvider {
		public String selectOrderBy(MyOrder order) {
			return new SQL()
					.SELECT("*")
					.FROM("customerqna")
					.ORDER_BY(order.get정렬방식())
					.LIMIT(20)
					.OFFSET(0).toString();
					
		}
	}
}
