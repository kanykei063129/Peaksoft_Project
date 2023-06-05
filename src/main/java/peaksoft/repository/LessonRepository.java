package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entity.Lesson;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    @Query("select new peaksoft.dto.response.LessonResponse(l.id,l.lessonName,l.time) from Lesson l where l.id=?1")
    Optional<LessonResponse>getLessonById(Long id);

    @Query("select new peaksoft.dto.response.LessonResponse(l.id,l.lessonName,l.time) from Lesson l where l.course.id = :courseId")
    List<LessonResponse> getAllLesson(Long courseId);
}
