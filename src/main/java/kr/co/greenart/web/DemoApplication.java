package kr.co.greenart.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 컴포넌트 스캔이 포함되어 있음 >> 해당 클래스의 패키지 내부의 컴포넌트들을 스캔
public class DemoApplication {
	// 메인 메소드가 기본적을 존재
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
