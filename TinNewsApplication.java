package com.laioffer.tinnews;


import android.app.Application;

import androidx.room.Room;

import com.ashokvarma.gander.Gander;
import com.ashokvarma.gander.imdb.GanderIMDB;
import com.laioffer.tinnews.database.TinNewsDatabase;


// 单例
// 整个app的application，管理内存，全局设置等 app setting
// 控制 mainActivity

public class TinNewsApplication extends Application {
    private TinNewsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        // create db
        database = Room.databaseBuilder(this, TinNewsDatabase.class, "tinnews_db").build();
    }

    // getter
    public TinNewsDatabase getDatabase() {
        return database;
    }
}
