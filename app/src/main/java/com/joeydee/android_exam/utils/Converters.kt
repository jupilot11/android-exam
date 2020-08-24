package com.joeydee.android_exam.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.joeydee.android_exam.objects.*

class Converters {

    //for id
    @TypeConverter
    fun idToString(app: Id): String = Gson().toJson(app)
    @TypeConverter
    fun stringToID(string: String): Id = Gson().fromJson(string, Id::class.java)

    //for pic
    @TypeConverter
    fun picToString(app: Picture): String = Gson().toJson(app)
    @TypeConverter
    fun stringToPic(string: String): Picture = Gson().fromJson(string, Picture::class.java)

    //for name
    @TypeConverter
    fun nameToString(app: Name): String = Gson().toJson(app)
    @TypeConverter
    fun stringToname(string: String): Name = Gson().fromJson(string, Name::class.java)

    //for dob
    @TypeConverter
    fun dobToString(app: Dob): String = Gson().toJson(app)
    @TypeConverter
    fun stringToDob(string: String): Dob = Gson().fromJson(string, Dob::class.java)

    //for location
    @TypeConverter
    fun locToString(app: Location): String = Gson().toJson(app)
    @TypeConverter
    fun stringToLoc(string: String): Location = Gson().fromJson(string, Location::class.java)
    @TypeConverter
    fun coordToString(app: Coordinates): String = Gson().toJson(app)
    @TypeConverter
    fun stringToCoord(string: String): Coordinates = Gson().fromJson(string, Coordinates::class.java)
    @TypeConverter
    fun timezToString(app: Timezone): String = Gson().toJson(app)
    @TypeConverter
    fun stringToTime(string: String): Timezone = Gson().fromJson(string, Timezone::class.java)

}