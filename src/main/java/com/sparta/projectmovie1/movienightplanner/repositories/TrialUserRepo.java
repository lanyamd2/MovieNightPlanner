package com.sparta.projectmovie1.movienightplanner.repositories;

import com.sparta.projectmovie1.movienightplanner.models.TrialUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrialUserRepo extends MongoRepository<TrialUser,Integer> {
}
