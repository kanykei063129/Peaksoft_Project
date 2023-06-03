package peaksoft.service;

import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {
    LessonResponse saveLesson(LessonRequest lessonRequest,Long courseId);
    LessonResponse getLessonById(Long id);
    List<LessonResponse> getAllLessons();
    LessonResponse updateLesson(Long id, LessonRequest lessonRequest);
    String deleteString(Long id);
}
