package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
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
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest){
        return instructorService.saveInstructor(instructorRequest);
    }
    @GetMapping("/{id}")
    public InstructorResponse getById(@PathVariable Long id){
        return instructorService.getInstructorById(id);
    }
    @PutMapping("/{id}")
    public InstructorResponse update(@PathVariable Long id,@RequestBody InstructorRequest instructorRequest){
        return instructorService.updateInstructor(id,instructorRequest);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return instructorService.deleteString(id);
    }
}
