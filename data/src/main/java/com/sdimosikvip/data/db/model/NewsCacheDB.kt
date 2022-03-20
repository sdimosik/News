package com.sdimosikvip.data.db.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.sdimosikvip.common.model.AvailableCategory

@Entity(
    tableName = NewsCacheDB.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = NewsDB::class,
            parentColumns = ["id"],
            childColumns = ["id_news"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )],
    indices = [
        Index(value = ["id_news", "category"], unique = true)
    ],
)
data class NewsCacheDB(
    @ColumnInfo(name = "id_news")
    val idNews: Long,

    @ColumnInfo(name = "category")
    val category: AvailableCategory
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        const val TABLE_NAME = "cache_news_with_category"
    }
}