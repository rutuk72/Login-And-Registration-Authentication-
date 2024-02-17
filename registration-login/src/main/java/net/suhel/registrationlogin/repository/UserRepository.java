package net.suhel.registrationlogin.repository;

import net.suhel.registrationlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String NameOrEmail);
}
