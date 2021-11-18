package com.example.stuheim.repositories;

import com.example.stuheim.models.Student;
import com.example.stuheim.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional()
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Page<User> findByGroupsId(Long groupId, Pageable pageable);

    Page<User> findAllByUsernameContains(Pageable pageable,String username);

    @Query(value =
            "SELECT * FROM APP_PRIVILEGES p WHERE p.ID in (" +
                    "SELECT DISTINCT(pm.PRIVILEGE_ID) FROM GROUPS_PRIVILEGES pm where pm.GROUP_ID in ("+
                    "        SELECT mem.GROUP_ID FROM USERS_GROUPS mem"+
                    "        WHERE mem.USER_ID = :userId))", nativeQuery = true)
    List<Object[]> getPrivilegesByUser(@Param("userId") Long userId);

    boolean existsByUsername(String username);
}
