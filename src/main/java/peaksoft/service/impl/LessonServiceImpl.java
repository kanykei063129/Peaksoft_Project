package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public LessonResponse saveLesson(Long courseId,LessonRequest lessonRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "not found"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setTime(lessonRequest.getTime());
        course.getLessons().add(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        courseRepository.save(course);
        return new LessonResponse(lesson.getId(),lesson.getLessonName());
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        return lessonRepository.getLessonById(id).orElseThrow(() -> new NoSuchElementException("Lesson with id: " + id + " is not found"));
    }

    @Override
    public List<LessonResponse> getAllLessons(Long courseId) {
        return lessonRepository.getAllLesson(courseId);
    }

    @Override
    public LessonResponse updateLesson(Long id, LessonRequest lessonRequest) {
        Lesson lessonResponse = lessonRepository.findById(id).orElseThrow(() -> new NullPointerException("Lesson with id: " + id + " is not found"));
        lessonResponse.setLessonName(lessonRequest.getLessonName());
        lessonRepository.save(lessonResponse);
        return new LessonResponse(lessonResponse.getId(),lessonResponse.getLessonName());
    }

    @Override
    public String deleteString(Long id) {
        boolean exists=lessonRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException("Lesson with id: " + id + " is not found");
        }
        lessonRepository.deleteById(id);
        return "Lesson with id: " + id + " is deleted...";
    }
    }

