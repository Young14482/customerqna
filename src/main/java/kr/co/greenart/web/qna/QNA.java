package kr.co.greenart.web.qna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QNA {
	private Integer articleId;
	private String title;
	private String content;
	private String username;
	private String password;
	private Integer views;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean secure;
	private Boolean deleted;
	private String createdAtStr;
	private String updatedAtStr;
	
	// 포맷터 정의
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // createdAt 설정 시 createdAtStr도 업데이트
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.createdAtStr = (createdAt != null) ? createdAt.format(formatter) : null;
    }

    // updatedAt 설정 시 updatedAtStr도 업데이트
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        this.updatedAtStr = (updatedAt != null) ? updatedAt.format(formatter) : null;
        // updatedAtStr에 대한 설정을 원하신다면 추가 구현
    }
}
