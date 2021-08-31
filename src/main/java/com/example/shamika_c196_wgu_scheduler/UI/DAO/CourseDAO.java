package com.example.shamika_c196_wgu_scheduler.UI.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shamika_c196_wgu_scheduler.UI.Entities.CourseEntity;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CourseEntity courseEntity);

    @Update
    void update(CourseEntity courseEntity);

    @Delete
    void delete(CourseEntity courseEntity);

    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    @Query("SELECT * FROM COURSE_TABLE ORDER BY courseID ASC")
    List<CourseEntity> getAllCourses();
}
