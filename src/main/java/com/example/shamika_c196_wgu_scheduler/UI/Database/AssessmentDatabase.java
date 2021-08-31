package com.example.shamika_c196_wgu_scheduler.UI.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shamika_c196_wgu_scheduler.UI.DAO.AssessmentDAO;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.AssessmentEntity;

@Database(entities = {AssessmentEntity.class}, version= 2, exportSchema = false)
public abstract class AssessmentDatabase extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();

    public static volatile AssessmentDatabase INSTANCE;

    static AssessmentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AssessmentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AssessmentDatabase.class, "AssessmentDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
