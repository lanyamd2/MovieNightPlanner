package com.sparta.projectmovie1.movienightplanner.repositories;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.MyProviderEntry;
import com.sparta.projectmovie1.movienightplanner.models.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MyProviderEntryRepository extends MongoRepository<MyProviderEntry, String> {

    List<MyProviderEntry> findByUserId(String userId);

    MyProviderEntry findByUserIdAndProviderId(String userId, Integer providerId);

}
