package com.example.stuheim.repositories;

import com.example.stuheim.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
