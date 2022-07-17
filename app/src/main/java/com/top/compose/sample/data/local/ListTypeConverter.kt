package com.top.compose.sample.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.top.compose.sample.bean.Tag

internal class ListTypeConverter {


//    @Inject
//    lateinit var gson: Gson


    var gson: Gson = Gson()


    @TypeConverter
    public fun stringTotList(data: String): List<Tag>? {
        val listType = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    public fun listToString(someObjects: List<Tag>?): String {
        if (someObjects == null) {
            return "{}"
        } else {
            return gson.toJson(someObjects)
        }
    }
}