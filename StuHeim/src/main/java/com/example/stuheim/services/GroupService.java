package com.example.stuheim.services;

import com.example.stuheim.models.Group;
import com.example.stuheim.models.User;
import com.example.stuheim.repositories.GroupRepository;
import com.example.stuheim.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupService {

    final
    GroupRepository groupRepository;

    final
    UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group findGroupByName(String username){
        return groupRepository.findByName(username);
    }

    public Page<Group> getGroups(Pageable pageable){
        return groupRepository.findAll(pageable);
    }

    public List<Group> getGroups(){
        return groupRepository.findAll();
    }

    public Group getGroup(Long id){
        Group group = groupRepository.getOne(id);
        return group;
    }

    public Group save(Group g, UserDetails creator){
        Group group = new Group();
        if(g.getId() != null){
            group = groupRepository.getOne(g.getId());
        }
        group.setName(g.getName());
        if(group.getId() == null){
            group.setCreatedBy(creator.getUsername());
        }
        group.setModifiedBy(creator.getUsername());
        return groupRepository.save(group);
    }

    public Group save(Group g){
        Group group = new Group();
        if(g.getId() != null){
            group = groupRepository.getOne(g.getId());
        }
        group.setName(g.getName());
        if(group.getId() == null){
            group.setCreatedBy("system");
        }
        group.setModifiedBy("system");
        return groupRepository.save(group);
    }

    public Boolean remove(Long id, UserDetails creator){
        Group group = groupRepository.getOne(id);
        group.setModifiedBy(creator.getUsername());
        groupRepository.save(group);
        groupRepository.delete(group);
        return Boolean.TRUE;
    }

    public Page<User> getGroupMembers(Long id, PageRequest pageRequest){
        return userRepository.findByGroupsId(id, pageRequest);
    }

    @Transactional
    public Boolean addUserToGroup(Long groupId, Long userId){
        Group group = groupRepository.getOne(groupId);
        User user1 = userRepository.getOne(userId);
        user1.getGroups().add(group);
        userRepository.save(user1);
        return Boolean.TRUE;
    }

    public Boolean removeUserFromGroup(Long groupId, Long userId){
        Group group = groupRepository.getOne(groupId);
        User user = userRepository.getOne(userId);
        user.getGroups().remove(group);
        userRepository.save(user);
        return Boolean.TRUE;
    }
}
