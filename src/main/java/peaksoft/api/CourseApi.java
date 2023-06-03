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
    private final CompanyService  companyService;
    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourse();
    }

//    @PostMapping
//    public CourseResponse saveCourse(@RequestBody CourseRequest courseRequest) {
//        return courseService.(courseRequest);
//    }
//    @GetMapping("/createHouse")
//    public CourseResponse createCourse(@RequestBody CourseRequest courseRequest,@PathVariable Long companyId) {
//       return companyService.savaCourse(courseRequest,companyId);


    @PostMapping
    public CourseResponse saveCourse(@RequestBody CourseRequest courseRequest,
                            @RequestParam("companyName") Long companyId) {
        return courseService.savaCourse(companyId, courseRequest);
    }
    @GetMapping("/{id}")
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
