package com.angelsheaven.demo.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.angelsheaven.demo.utilities.DataConverters

/**
 * Database instance for local storage
 */
@Database(
    entities = [Article::class]
    , version = 8
)
@TypeConverters(DataConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}