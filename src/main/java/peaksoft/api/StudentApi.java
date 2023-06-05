package peaksoft.api;

import jakarta.validation.Valid;
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
    public List<StudentResponse> getAllStudents(@PathVariable Long groupId) {
        return studentService.getAllStudents(groupId);
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PostMapping("/{id}/{groupId}")
    public String assignStudentToGroup(@PathVariable Long id, @PathVariable Long groupId) {
        return studentService.assignStudentsToGroup(id, groupId);
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

    @PostMapping("/block/{studentId}")
    public String blockUnblockStudent(@PathVariable Long studentId, @RequestParam Boolean block) {
        return studentService.blockUnblockStudent(studentId, block);
    }
        @GetMapping("/block")
    public List<StudentResponse>getStudentsByBlockedOrNot(@RequestParam (required = false) Boolean isBlocked){
        return studentService.getAllIsBlocked(isBlocked);
    }

    @GetMapping("/filter")
    public  List<StudentResponse>filterStudent(@RequestParam (required = false) StudyFormat studyFormat){
        return studentService.filterStudents(studyFormat);
    }
}
