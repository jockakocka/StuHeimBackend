package com.example.stuheim.repositories;

import com.example.stuheim.models.StudentDormitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentDormitoryRepository extends JpaRepository<StudentDormitory, Long> {

}
