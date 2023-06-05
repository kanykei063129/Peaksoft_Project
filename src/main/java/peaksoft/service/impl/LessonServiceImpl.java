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
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Lesson with id: " + courseId + " not found"));
            Lesson lesson = new Lesson();
            lesson.setLessonName(lessonRequest.getLessonName());
            lesson.setTime(lessonRequest.getTime());
            lesson.setCourse(course);
            lessonRepository.save(lesson);
            courseRepository.save(course);
            return LessonResponse.builder().id(lesson.getId()).lessonName(lessonRequest.getLessonName()).time(lessonRequest.getTime()).build();
        } catch (Exception e) {
            throw new NoSuchElementException("Lesson with id:" + courseId + " is not found");
        }
    }
    @Override
    public List<LessonResponse> getAllLessons(Long courseId) {
        return lessonRepository.getAllLesson(courseId);
    }
    @Override
    public LessonResponse getLessonById(Long lessonId) {
        return lessonRepository.getLessonById(lessonId).orElseThrow(() -> new RuntimeException("Lesson with id: " + lessonId + " not found!"));

    }
    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson=lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Course with id: " + lessonId + "not found!"));
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setTime(lessonRequest.getTime());
        lessonRepository.save(lesson);
        return LessonResponse.builder().id(lesson.getId()).lessonName(lessonRequest.getLessonName()).time(lessonRequest.getTime()).build();
    }
    @Override
    public String deleteLesson(Long lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId)
                    .orElseThrow(() -> new RuntimeException("Lesson with id: " + lessonId + " not found!"));
            Course course = lesson.getCourse();
            if (course != null) {
                course.getLessons().remove(course);
            }
            lessonRepository.delete(lesson);
            return  "Lesson with id: " + lessonId + " is deleted...";
        } catch (RuntimeException e) {
            return "Failed to delete course: " + e.getMessage();
        }
    }
    }

