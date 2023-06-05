package peaksoft.service;

import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    TaskResponse save(Long lessonId,TaskRequest taskRequest);
    List<TaskResponse> getAllTasks(Long lessonId);
    TaskResponse getTaskById(Long taskId);

    TaskResponse updateTask(Long taskId, TaskRequest taskRequest);
    String deleteTask(Long taskId);
}
