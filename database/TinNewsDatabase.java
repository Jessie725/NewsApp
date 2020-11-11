package com.laioffer.tinnews.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.laioffer.tinnews.model.Article;


// abstract
// We do not implement it
// the Room annotation processor will
// version -- current version
// Entities  the tables the database contains
// exportSchema option is for dumping a database schema to file system.

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class TinNewsDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();
}

