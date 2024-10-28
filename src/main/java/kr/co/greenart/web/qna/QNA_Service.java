package kr.co.greenart.web.qna;

import java.util.List;

// 요구사항 도출 / 검증
public interface QNA_Service {
	// 게시글조회
	QNA findById(Integer artcleId);
	
	// 조회수 증가
	int updateCount(int articleId);
	
	int save(QNA qna);
	
	List<QNA> findAll(int pageSize, int offset);
	
	List<QNA> findBySecureIsFalse(int pageSize, int offset);
	
	int countAll();
	
	int updateQNA(QNA qna);
	
	int delete(Integer artcleId);
	
	List<QNA> searchQnas(String searchTerm, String sortColumn, String sortOrder, int limit, int offset);
	
	List<QNA> searchQnasSort(String sortColumn, String sortOrder, int limit, int offset);
	
	int count(String searchTerm);
	/*

	5. 관리자 기능

	비밀 게시글 조회
	게시글 강제 삭제

	 */
}
