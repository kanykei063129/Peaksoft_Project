package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public GroupResponse saveGroup(Long courseId,GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "not found"));
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        course.getGroups().add(group);
        groupRepository.save(group);
        courseRepository.save(course);
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription());
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        return groupRepository.getGroupById(id).orElseThrow(() -> new NoSuchElementException("Group with id: " + id + " is not found"));
    }

    @Override
    public List<GroupResponse> getAllGroups() {
      return groupRepository.getAllGroup();
    }
    @Override
    public GroupResponse updateGroup(Long id, GroupRequest groupRequest) {
        Group groupResponse = groupRepository.findById(id).orElseThrow(() -> new NullPointerException("Group with id: " + id + " is not found"));
        groupResponse.setGroupName(groupRequest.getGroupName());
        groupResponse.setDescription(groupRequest.getDescription());
        groupResponse.setImageLink(groupRequest.getImageLink());
        groupRepository.save(groupResponse);
        return new GroupResponse(groupResponse.getId(),groupResponse.getGroupName(),groupResponse.getDescription(),groupResponse.getImageLink());
    }

    @Override
    public String deleteString(Long id) {
        boolean exists=groupRepository.existsById(id);
       if (!exists){
           throw new NoSuchElementException("Group with id: " + id + " is not found");
       }
       groupRepository.deleteById(id);
        return "Group with id: " + id + " is deleted...";
    }

}