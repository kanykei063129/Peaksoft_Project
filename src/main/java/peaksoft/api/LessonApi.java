package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.service.LessonService;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;


}
