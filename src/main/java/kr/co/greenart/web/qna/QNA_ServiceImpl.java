package kr.co.greenart.web.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.greenart.web.util.QNA_NotFoundException;

@Service
public class QNA_ServiceImpl implements QNA_Service {
	@Autowired
	private QnaMapper mapper;

	@Override
	@Transactional
	public QNA findById(Integer articleId) {
		QNA qna = mapper.findById(articleId);

		if (qna == null) {
			throw new QNA_NotFoundException(articleId);
		}
		// 과거의 정보를 들고오고 있는중임 >> 자체적으로 객체의 조회수 +1 시켜서 정보 최신화
		int rows = mapper.updateCount(articleId);
		if (rows == 1) {
			qna.setViews(qna.getViews() + 1);
		}

		return qna;
	}

	@Override
	public int save(QNA qna) {
		return mapper.save(qna);
	}

	@Override
	public List<QNA> findAll(int pageSize, int offset) {
		return mapper.findAll(pageSize, offset);
	}

	@Override
	public List<QNA> findBySecureIsFalse(int pageSize, int offset) {
		return mapper.findBySecureIsFalse(pageSize, offset);
	}

	@Override
	public int updateCount(int articleId) {
		return mapper.updateCount(articleId);
	}

	@Override
	public int countAll() {
		return mapper.countAll();
	}

	@Override
	public int updateQNA(QNA qna) {
		return mapper.updateQNA(qna);
	}

	@Override
	public int delete(Integer artcleId) {
		return mapper.updateDelete(artcleId);
	}
	// 검색 및 정렬
	@Override
	public List<QNA> searchQnas(String searchTerm, String sortColumn, String sortOrder, int limit, int offset) {
        return mapper.searchQnas(searchTerm, sortColumn, sortOrder, limit, offset);
    }
	// 검색 및 정렬
	@Override
	public List<QNA> searchQnasSort(String sortColumn, String sortOrder, int limit, int offset) {
		return mapper.searchQnasSort(sortColumn, sortOrder, limit, offset);
	}
	@Override
    public int count(String searchTerm) {
        return mapper.count(searchTerm);
    }
}
