package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;
    @GetMapping("/{lessonId}")
    public List<TaskResponse> getAllTask(@PathVariable Long lessonId) {
        return taskService.getAllTasks(lessonId);
    }

    @PostMapping("/save/{lessonId}")
    public TaskResponse saveStudent(@PathVariable Long lessonId,@RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId,taskRequest);
    }

    @GetMapping("/by/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id,taskRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        return taskService.deleteString(id);
    }
}

