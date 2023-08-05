package com.jamesco.littlelemon

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class MenuItem(
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price:String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "category") val category: String
)

@Dao
interface MenuDao{
    @Query("SELECT * FROM MenuItem")
    fun getMenuItems(): List<MenuItem>

    @Query("SELECT * FROM MenuItem WHERE id IN (:itemId)")
    fun getMenuItemById(itemId: Int): MenuItem

    @Insert
    fun insertMenuItem(menuItem:MenuItem)

    @Update
    fun updateMenuItem(menuItem: MenuItem)

    @Delete
    fun delete(menuItem: MenuItem)
}

@Database(entities = [MenuItem::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract  fun menuDao(): MenuDao
}