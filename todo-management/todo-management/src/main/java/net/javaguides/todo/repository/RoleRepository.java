package net.javaguides.todo.repository;

import net.javaguides.todo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
