package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NullPointerException("Lesson with id: " + lessonId + "not found"));
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        lesson.getTasks().add(task);
        taskRepository.save(task);
        return new TaskResponse(task.getId(),task.getTaskName(),task.getTaskText(),task.getDeadline());
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return taskRepository.getTaskById(id).orElseThrow(() -> new NoSuchElementException("Task with id: " + id + "is not found"));
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.getAllTask();
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task taskResponse = taskRepository.findById(id).orElseThrow(() -> new NullPointerException("Task with id: " + id + " is not found"));
        taskResponse.setTaskName(taskRequest.getTaskName());
        taskResponse.setTaskText(taskRequest.getTaskText());
        taskResponse.setDeadline(taskRequest.getDeadline());
        taskRepository.save(taskResponse);
        return new TaskResponse(taskResponse.getId(),taskResponse.getTaskName(),taskResponse.getTaskText(),taskResponse.getDeadline());
    }

    @Override
    public String deleteString(Long id) {
        boolean exists=taskRepository.existsById(id);
        if (!exists){
            throw new NoSuchElementException("Task with id: " + id + " is not found");
        }
        taskRepository.deleteById(id);
        return "Task with id: " + id + " is deleted...";
    }
}
