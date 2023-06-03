package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<TaskResponse> getTaskById(Long id);

    @Query("select new peaksoft.dto.response.TaskResponse(t.id,t.taskName,t.taskText,t.deadline) from Task  t")
    List<TaskResponse> getAllTask();
}
