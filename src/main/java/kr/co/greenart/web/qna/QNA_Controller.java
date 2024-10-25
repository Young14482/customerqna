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
	public String qna(@PageableDefault Pageable page, Model model) {

		log.info("size=" + page.getPageSize());
		log.info("page=" + page.getPageNumber());
		log.info("offset=" + page.getOffset());
		log.info("sort=" + page.getSort());
		
		List<QNA> all = service.findAll(20, 0);

		int totalItems = service.count();
		int totalPages = (int) (Math.ceil((double) totalItems / 20)) - 1;
		model.addAttribute("qnaList", all);
		model.addAttribute("totalItems", totalItems); // 총 항목 수 추가
		model.addAttribute("totalPages", totalPages);
		return "qna";
	}

	// 메인화면 (페이지 요청 있을때)
	@GetMapping(params = "page")
	public String qna(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {

		int limit = size;
		int offset = page * size;

		int totalItems = service.count();
		int totalPages = (int) (Math.ceil((double) totalItems / size)) - 1;

		List<QNA> all = service.findAll(limit, offset);
		model.addAttribute("qnaList", all);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page); // 현재 페이지 추가
		model.addAttribute("size", size); // 페이지 크기 추가
		model.addAttribute("totalItems", totalItems); // 총 항목 수 추가

		return "qna";
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
	public String setEdit(@PathVariable Integer articleId, @RequestParam String username, @RequestParam String password, @RequestParam String title,
			@RequestParam String content, @RequestParam String secure, Model model) {
		
		boolean isSecure = Boolean.parseBoolean(secure);
		QNA origin = service.findById(articleId);
		QNA update = QNA.builder()
				.articleId(articleId)
				.username(username)
				.password(password)
				.title(title)
				.createdAt(origin.getCreatedAt())
				.content(content)
				.secure(isSecure)
				.build();
		
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
