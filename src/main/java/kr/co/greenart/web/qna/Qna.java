package kr.co.greenart.web.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qna {
	private int article_id;
	private String title;
	private String content;
	private String username;
	private String password;
	private int views;
	private String created_at;
	private String updated_at;
	private int is_secure;
	private int is_deleted;
}
