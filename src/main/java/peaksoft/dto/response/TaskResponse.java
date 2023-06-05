package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Builder
@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadline;

    public TaskResponse(Long id, String taskName, String taskText, LocalDate deadline) {
        this.id = id;
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }

    public TaskResponse() {
    }
}
