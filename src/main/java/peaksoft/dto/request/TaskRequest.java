package peaksoft.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {
    @NotEmpty(message = "Name should not be empty")
    private String taskName;
    private String taskText;
    private LocalDate deadline;
}
