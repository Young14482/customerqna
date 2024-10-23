package kr.co.greenart.web.qna;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QNA {
	private Integer article_id;
	private String title;
	private String content;
	private String username;
	private String password;
	private Integer views;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private Boolean secure;
	private Boolean deleted;
}
