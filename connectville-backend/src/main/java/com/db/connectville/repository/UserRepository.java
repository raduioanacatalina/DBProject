package com.db.connectville.repository;

import com.db.connectville.exception.UserNotFoundException;
import com.db.connectville.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username) throws UserNotFoundException;
    User getUserById(int id);
}
