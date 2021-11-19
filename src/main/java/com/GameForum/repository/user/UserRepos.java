package com.gameforum.repository.user;

import com.gameforum.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    void deleteUserByUsername(String username);
}