package peaksoft.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
    @AllArgsConstructor
    public class LessonRequest {
        @NotEmpty(message = "Name should not be empty")
        private String lessonName;
        private int time;
    }
