package peaksoft.service;

import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {
    GroupResponse saveGroup(Long courseId, GroupRequest groupRequest);
    List<GroupResponse> getAllGroups();
    GroupResponse getGroupById(Long groupId);
    GroupResponse updateGroup(Long groupId, GroupRequest groupRequest);
    String deleteGroup(Long groupId);
}

