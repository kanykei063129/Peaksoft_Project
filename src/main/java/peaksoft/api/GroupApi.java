package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

        @PostMapping("/{courseId}")
        public GroupResponse saveGroup(@RequestBody GroupRequest groupRequest, @PathVariable Long courseId) {
            return groupService.saveGroup(groupRequest, courseId);
    }
    @GetMapping("/{id}")
    public GroupResponse getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }

    @PutMapping("/{id}")
    public GroupResponse updateGroup(@PathVariable Long id,@RequestBody GroupRequest groupRequest){
        return groupService.updateGroup(id, groupRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id){
        return groupService.deleteString(id);
    }
}
