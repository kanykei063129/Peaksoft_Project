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

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    @Override
    public TaskResponse save(Long lessonId,TaskRequest taskRequest) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Lesson with id: " + lessonId + " not found"));
            Task task = new Task();
            task.setTaskName(taskRequest.getTaskName());
            task.setTaskText(taskRequest.getTaskText());
            task.setDeadLine(LocalDate.now());
            task.setLesson(lesson);
            lesson.getTasks().add(task);
            taskRepository.save(task);
            lessonRepository.save(lesson);
            return TaskResponse.builder().id(task.getId()).taskName(taskRequest.getTaskName()).taskText(taskRequest.getTaskText()).deadline(taskRequest.getDeadline()).build();
        } catch (Exception e) {
            throw new NoSuchElementException("Task with id:" + lessonId + " is not found");
        }
    }

    @Override
    public List<TaskResponse> getAllTasks(Long lessonId) {
        return taskRepository.getAllTask(lessonId);
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        return taskRepository.getTaskById(taskId).orElseThrow(() -> new RuntimeException("Task with id: " + taskId + " not found!"));

    }
    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest) {
        Task task=taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task with id: " + taskId + "not found!"));
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadLine(LocalDate.now());
        taskRepository.save(task);
        return TaskResponse.builder().id(task.getId()).taskName((taskRequest.getTaskName())).taskText(taskRequest.getTaskText()).deadline(taskRequest.getDeadline()).build();
    }


    @Override
    public String deleteTask(Long taskId) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Course with id: " + taskId + " not found!"));
            Lesson lesson = task.getLesson();
            if (lesson != null) {
                lesson.getTasks().remove(task);
            }
            taskRepository.delete(task);
            return "Task with id: " + taskId + " is deleted...";
        } catch (RuntimeException e) {
            return  "Failed to delete task: " + e.getMessage();
        }
    }
}
