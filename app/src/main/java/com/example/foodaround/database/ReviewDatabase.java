package com.example.foodaround.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {ReviewEntity.class}, version = 1, exportSchema = false)
public abstract class ReviewDatabase extends RoomDatabase {

    public abstract ReviewDao reviewDao();

    private static ReviewDatabase INSTANCE;

    public static ReviewDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ReviewDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ReviewDatabase.class, "review_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static final Executor databaseWriteExecutor =
            Executors.newFixedThreadPool(4);
}
