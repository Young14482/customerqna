package kr.co.greenart.web.qna;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page {
	private int page;
	private int size;
	private int totalItems;
	private int totalPages;
}
