package com.example.myfirstapp.common.converters

import androidx.room.TypeConverter
import com.example.myfirstapp.screens.main.data.local.entity.LocalGeo
import com.google.gson.Gson

class AppConverters {
    @TypeConverter
    fun fromGeo(localGeo: LocalGeo?): String? {
        return localGeo?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toGeo(geoString: String?): LocalGeo? {
        return geoString?.let { Gson().fromJson(it, LocalGeo::class.java) }
    }
}