package com.example.shamika_c196_wgu_scheduler.UI.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shamika_c196_wgu_scheduler.UI.DAO.TermDAO;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.TermEntity;

@Database(entities = {TermEntity.class}, version= 1, exportSchema = false)
public abstract class TermDatabase extends RoomDatabase {
    public abstract TermDAO termDAO();

    public static volatile TermDatabase INSTANCE;

    static TermDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TermDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermDatabase.class, "TermDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
