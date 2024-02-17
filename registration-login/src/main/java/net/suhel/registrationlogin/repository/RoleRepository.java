package net.suhel.registrationlogin.repository;

import net.suhel.registrationlogin.entity.Role;
import net.suhel.registrationlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName (String name);
}
