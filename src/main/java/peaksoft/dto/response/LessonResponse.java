package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class LessonResponse {
    private Long id;
    private String lessonName;
    private int time;

    public LessonResponse(Long id, String lessonName, int time) {
        this.id = id;
        this.lessonName = lessonName;
        this.time = time;
    }
    public LessonResponse() {
    }
}
