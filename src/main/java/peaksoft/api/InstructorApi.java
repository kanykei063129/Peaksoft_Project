package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simpl.Instructorr;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping
    public List<InstructorResponse> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @PostMapping("/assign/{id}/{companyId}")
    public String assignInstructorToCompany(@PathVariable Long id, @PathVariable Long companyId) {
        return instructorService.assignInstructorToCompany(id, companyId);
    }

    @GetMapping("/{id}")
    public InstructorResponse getById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @PutMapping("/{id}")
    public InstructorResponse update(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(id, instructorRequest);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return instructorService.deleteInstructor(id);
    }

    @PostMapping("/{courseId}/{instructorId}")
    public String assignCourseToInstructor(@PathVariable Long courseId, @PathVariable Long instructorId) {
        return instructorService.assignInstructorToCourse(courseId, instructorId);
    }

    @GetMapping("/info/{instructorId}")
    public Instructorr instructor(@PathVariable Long instructorId) {
        return instructorService.infoInstructor(instructorId);
    }
}
