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
	
	int count();
	/*
	1. 게시글 작성

	필수 입력 항목: 제목, 내용, 유저이름, 비밀번호
	비밀번호는 게시글 수정/삭제 시 필요

	2. 게시글 조회

	모든 사용자가 게시글 열람 가능 (비밀글은 비밀번호 일치시)
	조회수 자동 증가
	최신순/조회수순 정렬 가능
	페이지당 20개 게시글 표시

	3. 게시글 수정/삭제

	작성 시 입력한 비밀번호로 인증
	수정 이력 저장
	삭제 시 실제 삭제가 아닌 논리 삭제 처리

	4. 검색 기능

	검색 대상: 제목, 내용, 태그

	5. 관리자 기능

	비밀 게시글 조회
	게시글 강제 삭제

	6. 공유

	게시글 공유 링크 생성
	 */
}
