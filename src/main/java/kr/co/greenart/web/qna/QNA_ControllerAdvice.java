package kr.co.greenart.web.qna;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import kr.co.greenart.web.util.QNA_NotFoundException;

@ControllerAdvice(assignableTypes = QNA_Controller.class)
public class QNA_ControllerAdvice {
	// 없는게시글 예외처리
	@ExceptionHandler(QNA_NotFoundException.class)
	public ModelAndView notFound(QNA_NotFoundException e) {
		ModelAndView mv = new ModelAndView("notFound");
		mv.setStatus(HttpStatusCode.valueOf(404));
		return mv;
	}
}
