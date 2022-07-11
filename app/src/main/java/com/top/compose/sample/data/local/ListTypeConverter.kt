package com.top.compose.sample.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject

//@ProvidedTypeConverter
//class ListTypeConverter {
//
//
//    @Inject
//    lateinit var gson: Gson
//
//    @TypeConverter
//    public  fun stringToSomeObjectList( data:String):List<Any> {
//
//        val listType = object : TypeToken<List<Any>>() {}.type
//
//        return gson.fromJson(data, listType);
//    }
//
//    @TypeConverter
//    public fun someObjectListToString (someObjects:List<Any>):String {
//        return gson.toJson(someObjects);
//    }
//}