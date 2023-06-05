package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public GroupResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "not found"));
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        course.getGroups().add(group);
        groupRepository.save(group);
        return new GroupResponse(group.getId(), group.getGroupName(), group.getImageLink(),group.getDescription());
    }


    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepository.getAllGroup();

    }
    @Override
    public GroupResponse getGroupById(Long groupId) {
        return groupRepository.getGroupById(groupId).orElseThrow(() -> new NoSuchElementException("Group with id:" + groupId + " doesn't exist"));
    }
    @Override
    public GroupResponse updateGroup(Long groupId, GroupRequest groupRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group with id:" + groupId + " not found"));
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImageLink(groupRequest.getImageLink());
        groupRepository.save(group);
        return GroupResponse.builder().id(group.getId()).groupName(groupRequest.getGroupName()).description(groupRequest.getDescription()).imageLink(groupRequest.getImageLink()).build();
    }
    @Override
    public String deleteGroup(Long groupId) {
        try {
            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new NoSuchElementException("Group with id: " + groupId + " not found!"));
            for (Course course : group.getCourses()) {
                if (course != null) {
                    course.getGroups().remove(group);
                }
                groupRepository.delete(group);
            }
            return "Group with id:" + groupId + " is deleted...";


        } catch (RuntimeException e) {
            return "Group with id:"+ groupId + " is not found :" + e.getMessage();
        }
    }
}