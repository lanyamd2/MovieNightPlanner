package com.sparta.projectmovie1.movienightplanner.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sparta.projectmovie1.movienightplanner.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}

