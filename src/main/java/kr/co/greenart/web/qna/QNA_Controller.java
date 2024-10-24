package kr.co.greenart.web.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/qna")
public class QNA_Controller {
	@Autowired
	private QnaMapper mapper;

	// 메인화면(페이지 요청 없을때)
	@GetMapping()
	public String qna(Model model) {

		List<QNA> all = mapper.findAll(10, 1);
		
		int totalItems = mapper.count();
		int totalPages = (int) (Math.ceil((double) totalItems / 10)) - 1;
		model.addAttribute("qnaList", all);
		model.addAttribute("totalItems", totalItems); // 총 항목 수 추가
		model.addAttribute("totalPages", totalPages);
		return "qna";
	}

	// 메인화면 (페이지 요청 있을때)
	@GetMapping(params = "page")
	public String qna(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		int limit = size;
		int offset = page * size;

		int totalItems = mapper.count();
		int totalPages = (int) (Math.ceil((double) totalItems / size)) - 1;

		List<QNA> all = mapper.findAll(limit, offset);
		model.addAttribute("qnaList", all);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page); // 현재 페이지 추가
		model.addAttribute("size", size); // 페이지 크기 추가
		model.addAttribute("totalItems", totalItems); // 총 항목 수 추가

		return "qna";
	}

	@GetMapping("/{articleId}")
	public String qnaDetail(@PathVariable int articleId, Model model) {
		QNA byPk = mapper.findByPk(articleId);
		mapper.updateCount(articleId);
		model.addAttribute("QNA", byPk);
		return "qnaDetail";
	}
	
	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
	@PostMapping()
	public String write( @RequestParam String username,
            @RequestParam String password,
            @RequestParam String title,
            @RequestParam String content,
            Model model) {
		QNA qna = QNA.builder().username(username).password(password).title(title).content(content).build();
		mapper.save(qna);
		model.addAttribute(qna);
		return "qnaDetail";
	}
	
}
