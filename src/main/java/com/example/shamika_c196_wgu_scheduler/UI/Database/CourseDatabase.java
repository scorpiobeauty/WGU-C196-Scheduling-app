package com.example.shamika_c196_wgu_scheduler.UI.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shamika_c196_wgu_scheduler.UI.DAO.CourseDAO;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.CourseEntity;


@Database(entities = {CourseEntity.class}, version= 2, exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();

    public static volatile CourseDatabase INSTANCE;

    static CourseDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CourseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CourseDatabase.class, "CourseDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
