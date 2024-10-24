package kr.co.greenart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kr.co.greenart.web.qna.QNA;
import kr.co.greenart.web.qna.QnaMapper;

@SpringBootApplication // 컴포넌트 스캔이 포함되어 있음 >> 해당 클래스의 패키지 내부의 컴포넌트들을 스캔
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private QnaMapper mapper;
	
	// 메인 메소드가 기본적을 존재
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	// h2 데이터를 이런식으로 삽입이 가능하다
	@Override
	public void run(String... args) throws Exception {
		for (int i = 22; i < 30; i++) {
			mapper.save(QNA.builder()
							.title("title"+i)
							.username("username"+i)
							.content("content"+i)
							.password("password"+i)
							.build());
		}
	}

}
