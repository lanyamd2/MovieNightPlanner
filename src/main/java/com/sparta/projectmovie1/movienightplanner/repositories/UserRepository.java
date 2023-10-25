package com.sparta.projectmovie1.movienightplanner.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sparta.projectmovie1.movienightplanner.models.users.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}

