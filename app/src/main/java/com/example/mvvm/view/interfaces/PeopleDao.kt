package com.example.mvvm.view.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mvvm.model.People

@Dao
interface PeopleDao {

    @Query("Select * from people")
    fun getPeopleList(): List<People?>?

    @Insert
    fun insert(people: List<People?>?)

    @Query("DELETE FROM people")
    fun delete()

    @Update
    fun update(people: People?)
}