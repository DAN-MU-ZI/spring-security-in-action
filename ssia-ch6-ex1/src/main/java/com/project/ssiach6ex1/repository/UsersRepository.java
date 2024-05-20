package com.project.ssiach6ex1.repository;

import com.project.ssiach6ex1.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findUsersByUsername(String username);
}
