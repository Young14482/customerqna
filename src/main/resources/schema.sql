CREATE TABLE IF NOT EXISTS mytable (
	no INT PRIMARY KEY AUTO_INCREMENT
	, str VARCHAR(20) NOT NULL
);

-- 1. 익명 고객센터 문의게시판 테이블을 생성하는 쿼리문을 작성해주세요
-- 2. 글 작성 쿼리문 
-- 3. 게시글 목록 (article_id, title, username, is_secure)
-- 3-1. 게시글 조회시 is_secure의 값이 false인 행만 조회
-- 4. 게시글 조회 (id로 검색, title, content, username)
-- 5. 게시글의 비밀 여부 조회 (is_secure)
-- ㄴ> 무엇을 기준으로 검색을 해야하나요? >> article_id
-- 6. views count 수정(1 증가)
-- 7. 글 논리 삭제(pk 및 password일치) : is delete

CREATE TABLE IF NOT EXISTS customerqna (
	article_id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(200) NOT NULL,
	content TEXT NOT NULL,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(64) NOT NULL,
	views INT DEFAULT 0,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	is_secure bit DEFAULT 0,
	is_deleted bit DEFAULT 0
);

INSERT INTO customerqna (title, content, username, password, views, created_at, updated_at, is_secure, is_deleted)
VALUES 
    ('첫 번째 질문', '첫 번째 질문의 내용입니다.', 'user1', 'password1', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0),
    ('두 번째 질문', '두 번째 질문의 내용입니다.', 'user2', 'password2', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0),
    ('세 번째 질문', '세 번째 질문의 내용입니다.', 'user3', 'password3', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0),
    ('네 번째 질문', '네 번째 질문의 내용입니다.', 'user4', 'password4', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 0),
    ('다섯 번째 질문', '다섯 번째 질문의 내용입니다.', 'user5', 'password5', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 1);

