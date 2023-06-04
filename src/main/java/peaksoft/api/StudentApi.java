package peaksoft.api;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;
    @GetMapping("/{groupId}")
    public List<StudentResponse> getAllStudents(@PathVariable Long groupId, StudyFormat studyFormat) {
        return studentService.getAllStudents(groupId,studyFormat);
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PostMapping("/{id}/{groupId}")
    public String assignStudentToGroup(@PathVariable Long id, @PathVariable Long groupId) {
        return studentService.assignStudentToGroup(id, groupId);
    }

    @GetMapping("/by{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PostMapping("/{studentId}")
    public String blockUnblockStudent(@PathVariable Long studentId, @RequestParam Boolean block) {
        return studentService.blockUnblockStudent(studentId, block);
    }
}
