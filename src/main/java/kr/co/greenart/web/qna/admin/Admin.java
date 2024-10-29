package kr.co.greenart.web.qna.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Admin {
	private Integer no;
	private String id;
	private String password;
}
