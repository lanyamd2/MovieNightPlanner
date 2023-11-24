package com.sparta.projectmovie1.movienightplanner.accounthistory;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistoryRepositoryTests {
    @Autowired
    HistoryRepository historyRepository;

    public static HistoryEntry getHistoryEntry(){
        HistoryEntry historyEntry = new HistoryEntry("userId", 1234, "production name", new Date());
//        historyEntry.setId("testId");
        return historyEntry;
    }

    @Test
    @Order(1)
    @DisplayName("Testing save method")
    void shouldSaveAndReturnHistoryEntry(){
        HistoryEntry historyEntry = getHistoryEntry();
        HistoryEntry savedEntry = historyRepository.save(historyEntry);
        Assertions.assertEquals("userId", savedEntry.getUserId());
    }

    @Test
    @Order(2)
    @DisplayName("Testing findHistoryEntriesByUserId method")
    void shouldReturnListOfHistoryEntries(){
        HistoryEntry newHistoryEntry = getHistoryEntry();
        newHistoryEntry.setProductionId(5678);
        historyRepository.save(newHistoryEntry);
        List<HistoryEntry> allEntries = historyRepository.findHistoryEntriesByUserId("userId");
        Assertions.assertFalse(allEntries.isEmpty());

    }

    @Test
    @Order(3)
    @DisplayName("Testing findHistoryEntriesByUserId method for correct user IDs")
    void shouldReturnListOfHistoryEntryOfMatchingUserIds(){
        HistoryEntry newHistoryEntry = getHistoryEntry();
        newHistoryEntry.setUserId("differentUserId");
        historyRepository.save(newHistoryEntry);

        List<HistoryEntry> userIdEntries = historyRepository.findHistoryEntriesByUserId("userId");
        System.out.println(userIdEntries);
        for(HistoryEntry historyEntry : userIdEntries){
            Assertions.assertEquals("userId", historyEntry.getUserId());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Testing findHistoryEntryByUserIdAndProductionId method")
    void shouldReturnHistoryEntryWithTheSameUserId(){
        Optional<HistoryEntry> historyEntryOptional = historyRepository.findHistoryEntryByUserIdAndProductionId("userId", 1234);
        Assertions.assertEquals("userId", historyEntryOptional.get().getUserId());
    }

    @Test
    @Order(5)
    @DisplayName("Testing findHistoryEntryByUserIdAndProductionId method")
    void shouldReturnHistoryEntryWithTheSameProductionId(){
        Optional<HistoryEntry> historyEntryOptional = historyRepository.findHistoryEntryByUserIdAndProductionId("userId", 1234);
        Assertions.assertEquals(1234, historyEntryOptional.get().getProductionId());

    }

    @Test
    @Order(6)
    @DisplayName("Testing findHistoryEntryByUserIdAndProductionId method")
    void shouldReturnEmptyOptionalGivenNonExistingProductionId(){
        Optional<HistoryEntry> historyEntryOptional = historyRepository.findHistoryEntryByUserIdAndProductionId("userId", 1111);
        Assertions.assertTrue(historyEntryOptional.isEmpty());
    }

    @Test
    @Order(7)
    @DisplayName("Testing findHistoryEntryByUserIdAndProductionId method")
    void shouldReturnEmptyOptionalGivenNonExistingUserId(){
        Optional<HistoryEntry> historyEntryOptional = historyRepository.findHistoryEntryByUserIdAndProductionId("notAUserId", 1234);
        Assertions.assertTrue(historyEntryOptional.isEmpty());
    }

    @Test
    @Order(8)
    @DisplayName("Testing findHistoryEntryByUserIdAndProductionId method")
    void shouldReturnEmptyOptionalGivenNonExistingUserIdAndProductionId(){
        Optional<HistoryEntry> historyEntryOptional = historyRepository.findHistoryEntryByUserIdAndProductionId("notAUserId", 1111);
        Assertions.assertTrue(historyEntryOptional.isEmpty());
    }
    @Test
    @Order(9)
    @DisplayName("Testing findHistoryEntriesByUserIdAndDateGreaterThanEqual method")
    void shouldReturnListOfHistoryEntriesGivenGreaterThanOrEqual(){
        HistoryEntry newHistoryEntry = getHistoryEntry();
        Date date = new Date();
        String str= "1999-01-01";
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        newHistoryEntry.setDate(date);
        historyRepository.save(newHistoryEntry);

        List<HistoryEntry> entries = historyRepository.findHistoryEntriesByUserIdAndDateGreaterThanEqual("userId", date);

        for(HistoryEntry entry : entries){
            System.out.println(entry);
        }

        Assertions.assertFalse(entries.isEmpty());
    }

    @Test
    @Order(10)
    @DisplayName("Testing findHistoryEntriesByUserIdAndDateLessThanEqual method")
    void shouldReturnListOfHistoryEntriesGivenLessThanOrEqual(){
        HistoryEntry newHistoryEntry = getHistoryEntry();
        Date date = new Date();
        String str= "2010-12-12";
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        newHistoryEntry.setDate(date);
        historyRepository.save(newHistoryEntry);

        List<HistoryEntry> entries = historyRepository.findHistoryEntriesByUserIdAndDateLessThanEqual("userId", date);
        System.out.println(date);
        for(HistoryEntry entry : entries){
            System.out.println(entry);
        }

        Assertions.assertFalse(entries.isEmpty());
    }

    @Test
    @Order(11)
    @DisplayName("Testing findHistoryEntryByUserIdAndProductionIdAndDate method")
    void shouldReturnHistoryEntryWithMatchingUserIdProductionIdAndDate(){
        HistoryEntry entry = getHistoryEntry();
        historyRepository.save(entry);
        Date date = entry.getDate();
        Optional<HistoryEntry> savedEntry = historyRepository.findHistoryEntryByUserIdAndProductionIdAndDate("userId", 1234, date);
        Assertions.assertTrue(savedEntry.isPresent());
        System.out.println(savedEntry.get());
    }
    @Test
    @Order(12)
    @DisplayName("Testing findHistoryEntryByUserIdAndProductionIdAndDate method")
    void shouldReturnEmptyWithMatchingUserIdProductionIdAndDate(){
        HistoryEntry entry = getHistoryEntry();
        Optional<HistoryEntry> savedEntry = historyRepository.findHistoryEntryByUserIdAndProductionIdAndDate(entry.getUserId(), 983, entry.getDate());
        Assertions.assertTrue(savedEntry.isEmpty());
    }

    @Test
    @Order(13)
    @DisplayName("Testing findHistoryEntriesByUserIdAndProductionNameContainingIgnoreCase method")
    void shouldReturnListGivenUserIdAndTitleContaining(){
        List<HistoryEntry> entries = new ArrayList<>();
        entries.add(new HistoryEntry("userId",1,"Lion King",new Date()));
        entries.add(new HistoryEntry("userId",251,"Chronicles of Narnia: The Lion, The Witch and the Wardrobe",new Date()));
        entries.add(new HistoryEntry("userId",45,"Dandelion",new Date()));
        entries.add(new HistoryEntry("userId",21,"Overdrive",new Date()));
        entries.add(new HistoryEntry("testUserId",21,"Lionheart",new Date()));
        historyRepository.saveAll(entries);

        List<HistoryEntry> result = historyRepository.findHistoryEntriesByUserIdAndProductionNameContainingIgnoreCase("userId", "lion");
        for(HistoryEntry entry: result){
            System.out.println(entry);
        }
        Assertions.assertFalse(result.isEmpty());
        historyRepository.deleteByUserId("testUserId");
    }

    @Test
    @Order(14)
    @DisplayName("Testing deleteByUserIdAndProductionId method")
    void shouldDeleteHistoryEntryWithMatchingUserIdAndProductionId(){
        historyRepository.deleteByUserIdAndProductionId("differentUserId",1234);
        Optional<HistoryEntry> entryOptional = historyRepository.findHistoryEntryByUserIdAndProductionId("differentUserId",1234);
        Assertions.assertTrue(entryOptional.isEmpty());

    }

    @Test
    @Order(15)
    @DisplayName("Testing deleteByUserId method")
    void shouldDeleteHistoryEntriesWithMatchingUserId(){
        historyRepository.deleteByUserId("userId");
        List<HistoryEntry> entryList = historyRepository.findHistoryEntriesByUserId("userId");
        Assertions.assertTrue(entryList.isEmpty());
    }
}
