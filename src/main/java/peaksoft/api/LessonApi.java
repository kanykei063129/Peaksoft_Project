package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;
    @GetMapping("/{courseId}")
    public List<LessonResponse> getAllLesson(@PathVariable Long courseId){
        return lessonService.getAllLessons(courseId);
}
    @PostMapping("/save/{courseId}")
    public LessonResponse saveLesson(@PathVariable Long courseId,@RequestBody LessonRequest lessonRequest){
        return lessonService.saveLesson(courseId,lessonRequest);
    }
    @GetMapping("/by/{id}")
    public LessonResponse getLessonById(@PathVariable Long id){
        return lessonService.getLessonById(id);
    }
    @PutMapping("/{id}")
    public LessonResponse update(@PathVariable Long id, @RequestBody LessonRequest lessonRequest){
        return lessonService.updateLesson(id,lessonRequest);
    }
    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable Long id){
        return lessonService.deleteString(id);
    }
}