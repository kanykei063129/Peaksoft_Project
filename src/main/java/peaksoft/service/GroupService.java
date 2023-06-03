package peaksoft.service;

import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {
    GroupResponse saveGroup(GroupRequest groupRequest,Long courseId);
    GroupResponse getGroupById(Long id);
    List<GroupResponse> getAllGroups();
    GroupResponse updateGroup(Long id, GroupRequest groupRequest);
    String deleteString(Long id);
}
