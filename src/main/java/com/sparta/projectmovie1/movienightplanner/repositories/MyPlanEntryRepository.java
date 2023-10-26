package com.sparta.projectmovie1.movienightplanner.repositories;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPlanEntryRepository extends MongoRepository<MyPlanEntry, String> {
  @Query
  List<MyPlanEntry> findMyPlanEntriesByDate(Date date);

  @Query
  List<MyPlanEntry> findMyPlanEntriesByUserIdAndDate(String userId, Date date);

  @Query
  List<MyPlanEntry> findMyPlanEntriesByDateGreaterThanEqual(Date date);

  @Query
  List<MyPlanEntry> findMyPlanEntriesByUserIdAndDateGreaterThanEqual(String userId, Date date);
}
