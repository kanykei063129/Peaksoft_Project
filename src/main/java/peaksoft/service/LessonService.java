package peaksoft.service;

import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {
    LessonResponse saveLesson(Long courseId,LessonRequest lessonRequest);
    List<LessonResponse> getAllLessons(Long courseId);
    LessonResponse getLessonById(Long lessonId);
    LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest);
    String deleteLesson(Long lessonId);
}