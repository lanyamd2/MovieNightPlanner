package com.sparta.projectmovie1.movienightplanner.accounthistory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends MongoRepository<HistoryEntry,String> {
    @Query
    List<HistoryEntry> findHistoryEntriesByUserId(String userId);

    @Query
    Optional<HistoryEntry> findHistoryEntryByUserIdAndProductionId(String userId, Integer productionId);

    @Query
    List<HistoryEntry> findHistoryEntriesByUserIdAndDateGreaterThanEqual(String userId, Date startDate);

    @Query
    List<HistoryEntry> findHistoryEntriesByUserIdAndDateLessThanEqual(String userId, Date endDate);

    @Query
    void deleteByUserIdAndProductionId(String userId, Integer productionId);

    @Query
    void deleteByUserId(String userId);

}
