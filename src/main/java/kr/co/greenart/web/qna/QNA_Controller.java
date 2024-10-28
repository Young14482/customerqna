package kr.co.greenart.web.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/qna")
@Slf4j
public class QNA_Controller {
	@Autowired
	private QNA_Service service;

	// 메인화면(페이지 요청 없을때)
	@GetMapping()
	public String qna(@PageableDefault(size = 20) Pageable pageable
			, @RequestParam(required = false) String sortColumn
			, @RequestParam(required = false, defaultValue = "DESC") String sortOrder
			, @RequestParam(required = false) String searchTerm, Model model) {
		int limit = pageable.getPageSize();
		int offset = pageable.getPageNumber() * limit;

		List<QNA> all;
		int totalItems;

		if (searchTerm == null || searchTerm.isEmpty() && sortColumn == null) {
			// 검색어가 없을 때
			all = service.findAll(limit, offset);
			totalItems = service.countAll();
		} else if (sortColumn != null) {
			// 검색어는 없는데 정렬기준은 있을때
			all = service.searchQnasSort(sortColumn, sortOrder, limit, offset);
			totalItems = service.countAll();
		} else {
			// 검색어가 있을 때
			all = service.searchQnas(searchTerm, sortColumn != null ? sortColumn : "createdAt", sortOrder, limit,
					offset);
			totalItems = service.count(searchTerm);
		}

		int totalPages = (totalItems > 0) ? (int) Math.ceil((double) totalItems / pageable.getPageSize()) - 1 : 0;

		model.addAttribute("qnaList", all);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", pageable.getPageNumber());
		model.addAttribute("size", limit);
		model.addAttribute("searchTerm", searchTerm);
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortOrder", sortOrder);

		return "qna"; // JSP 파일 이름
	}

	@GetMapping("/{articleId}")
	public String qnaDetail(@PathVariable Integer articleId, Model model) {
		QNA byPk = service.findById(articleId);
		if (byPk.getSecure()) {
			return "notFound";
		}
		model.addAttribute("QNA", byPk);
		return "qnaDetail";
	}

	@PostMapping("/{articleId}")
	public String asdqnaDetail(@PathVariable Integer articleId, Model model) {
		QNA byPk = service.findById(articleId);

		model.addAttribute("QNA", byPk);
		return "qnaDetail";
	}

	@GetMapping("/write")
	public String write() {
		return "write";
	}

	// 글작성
	@PostMapping()
	public String write(@RequestParam String username, @RequestParam String password, @RequestParam String title,
			@RequestParam String content, @RequestParam String secure, Model model) {

		boolean isSecure = Boolean.parseBoolean(secure);
		QNA qna = QNA.builder().username(username).password(password).title(title).content(content).secure(isSecure)
				.build();
		service.save(qna);
		model.addAttribute("QNA", qna);
		return "qnaDetail";
	}

	// articleId에 해당하는 비밀번호 가져오기
	@GetMapping("/getPassword")
	@ResponseBody
	public ResponseEntity<Map<String, String>> getPassword(@RequestParam Integer articleId) {
		QNA byId = service.findById(articleId);
		String password = byId.getPassword();

		Map<String, String> response = new HashMap<>();
		response.put("password", password);

		return ResponseEntity.ok(response);
	}

	// 글 수정
	@GetMapping("/edit/{articleId}")
	public String getEditInfo(@PathVariable Integer articleId, Model model) {
		QNA byId = service.findById(articleId);
		model.addAttribute("QNA", byId);
		return "edit";
	}

	@PostMapping("/edit/{articleId}")
	public String setEdit(@PathVariable Integer articleId, @RequestParam String username, @RequestParam String password,
			@RequestParam String title, @RequestParam String content, @RequestParam String secure, Model model) {

		boolean isSecure = Boolean.parseBoolean(secure);
		QNA origin = service.findById(articleId);
		QNA update = QNA.builder().articleId(articleId).username(username).password(password).title(title)
				.createdAt(origin.getCreatedAt()).content(content).secure(isSecure).build();

		int result = service.updateQNA(update);

		if (result != 1) {
			return "notFound";
		}
		QNA rebuild = service.findById(articleId);
		System.out.println(rebuild.getUpdatedAt());
		model.addAttribute("QNA", rebuild);
		return "qnaDetail";
	}

	@GetMapping("/delete/{articleId}")
	public String deleteQNA(@PathVariable Integer articleId) {
		int result = service.delete(articleId);
		if (result != 1) {
			return "notFound";
		}
		return "deleted";
	}
}
