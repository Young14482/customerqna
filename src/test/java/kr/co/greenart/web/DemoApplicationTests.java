package kr.co.greenart.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.greenart.web.qna.QNA;
import kr.co.greenart.web.qna.QnaMapper;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 테스트 진행 순서 조절을 위해 선언함
class DemoApplicationTests {
	@Autowired
	private QnaMapper mapper;
	
	@Test
	@Order(1) // 테스트를 진행할 순서를 지정함 >> 순서가 같을 경우 메소드의 이름 알파벳 순으로 진행
	void testInsert() {
		QNA qna = QNA.builder().title("제목").content("내용").username("유저네임").password("비밀번호").build();
		int rows = mapper.save(qna);
		
		assertEquals(1, rows);
		assertNotNull(qna.getArticleId());
	}
	
	@Test
	@Order(2)
	void atestSelect() {
		List<QNA> all = mapper.findAll(10, 0);
		
		assertNotEquals(0, all.size()); // 위의 값을 넣는 테스트를 진행하지 않으면 오류가 생김
	}
}
