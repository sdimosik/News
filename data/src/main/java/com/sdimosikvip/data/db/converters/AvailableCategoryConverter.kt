package com.sdimosikvip.data.db.converters

import androidx.room.TypeConverter
import com.sdimosikvip.common.model.AvailableCategory

object AvailableCategoryConverter {
    @TypeConverter
    fun toCategory(value: String) = enumValueOf<AvailableCategory>(value)
    @TypeConverter
    fun fromCategory(value: AvailableCategory) = value.name
}