package com.example.shamika_c196_wgu_scheduler.UI.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shamika_c196_wgu_scheduler.UI.Entities.TermEntity;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermEntity termEntity);

    @Update
    void update(TermEntity termEntity);

    @Delete
    void delete(TermEntity termEntity);

    @Query("SELECT * FROM TERM_TABLE ORDER BY termID ASC")
    List<TermEntity> getAllTerms();

    @Query("DELETE FROM TERM_TABLE")
    void deleteAllTerms();


}
