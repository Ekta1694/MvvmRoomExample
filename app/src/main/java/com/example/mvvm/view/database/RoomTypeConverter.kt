package com.example.mvvm.view.database

import androidx.room.TypeConverter
import com.example.mvvm.model.*
import com.google.gson.Gson

class RoomTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Name {
        return Gson().fromJson(
            value,
            Name::class.java
        )
    }

    @TypeConverter
    fun fromobj(list: Name?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromlocString(value: String?): Location {
        return Gson().fromJson(
            value,
            Location::class.java
        )
    }

    @TypeConverter
    fun fromlocobj(list: Location?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromlogString(value: String?): Login {
        return Gson().fromJson(value, Login::class.java)
    }

    @TypeConverter
    fun fromlogobj(list: Login?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun frompicString(value: String?): Picture {
        return Gson().fromJson(
            value,
            Picture::class.java
        )
    }

    @TypeConverter
    fun frompicobj(list: Picture?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromidString(value: String?): Id {
        return Gson().fromJson(value, Id::class.java)
    }

    @TypeConverter
    fun fromidobj(list: Id?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}