package com.top.compose.sample.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.top.compose.sample.bean.Tag
import javax.inject.Inject

@ProvidedTypeConverter
class ListTypeConverter {


    @Inject
    lateinit var gson: Gson

    @TypeConverter
    public  fun stringToSomeObjectList( data:String):List<Tag> {

        val listType = object : TypeToken<List<Any>>() {}.type

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public fun someObjectListToString (someObjects:List<Tag>):String {
        return gson.toJson(someObjects);
    }
}