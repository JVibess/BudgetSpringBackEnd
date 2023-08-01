package com.vibratorystudios.demo.user.repo;

import com.vibratorystudios.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

}
