package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonResponse {
    private Long id;
    private String lessonName;

    public LessonResponse(Long id, String lessonName) {
        this.id = id;
        this.lessonName = lessonName;
    }

    public LessonResponse() {
    }
}
