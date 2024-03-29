package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//  JpaRepository  <JPA ENTITY,Data type of Primary Key>

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
