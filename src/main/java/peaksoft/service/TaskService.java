package peaksoft.service;

import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    TaskResponse saveTask(Long lessonId,TaskRequest taskRequest);
    TaskResponse getTaskById(Long id);
    List<TaskResponse> getAllTasks(Long lessonId);
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    String deleteString(Long id);
}
