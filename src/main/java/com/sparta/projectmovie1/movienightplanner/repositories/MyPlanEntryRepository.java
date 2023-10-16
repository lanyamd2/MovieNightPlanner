package com.sparta.projectmovie1.movienightplanner.repositories;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPlanEntryRepository extends MongoRepository<MyPlanEntry, String> {

}
