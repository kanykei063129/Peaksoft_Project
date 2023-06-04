package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

import java.util.List;
@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping("/{companyId}")
    public List<CourseResponse> getAllCourses(@PathVariable Long companyId, @RequestParam String ascOrDesc) {
        return courseService.getAllCourse(companyId, ascOrDesc);
    }

    @PostMapping("/save/{companyId}")
    public CourseResponse saveCourse(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest) {
        return courseService.savaCourse(companyId, courseRequest);
    }

    @GetMapping("/by/{id}")
    public CourseResponse getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest){
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id){
        return courseService.deleteString(id);
    }
}

