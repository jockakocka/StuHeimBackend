package com.example.stuheim.services;

import com.example.stuheim.models.Group;
import com.example.stuheim.models.Privilege;
import com.example.stuheim.repositories.GroupRepository;
import com.example.stuheim.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PrivilegeService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    GroupRepository groupRepository;

    public Privilege findByName(String username){
        return privilegeRepository.findByName(username);
    }

    public List<Privilege> getPrivileges(){
        return privilegeRepository.findAll();
    }

    public Privilege getPrivilege(Long id){
        return privilegeRepository.getOne(id);
    }

    @Transactional
    public Privilege addPrivilegeToGroup(Long privilegeId, Long groupId){
        Privilege privilege = getPrivilege(privilegeId);
        Group group = groupRepository.getOne(groupId);
        privilege.getGroups().add(group);

        return privilegeRepository.save(privilege);
    }

    public Privilege save(Privilege privilege){
        if(privilege.getId() == null){
            privilege.setCreatedBy("system");
        }
        privilege.setModifiedBy("system");
        return privilegeRepository.save(privilege);
    }


    public Privilege removePrivilegeFromGroup(Long privilegeId, Long groupId){
        Privilege privilege = getPrivilege(privilegeId);
        Group group = groupRepository.getOne(groupId);
        privilege.getGroups().remove(group);

        return privilegeRepository.save(privilege);
    }

    public Boolean remove(Long id){
        Privilege privilege = privilegeRepository.getOne(id);
        privilegeRepository.delete(privilege);
        return Boolean.TRUE;
    }
}
