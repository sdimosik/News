package com.sdimosikvip.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = NewsDB.TABLE_NAME,
    indices = [
        Index(value = ["url_redirect"], unique = true)
    ]
)
data class NewsDB(
    @ColumnInfo(name = "url_redirect") val urlRedirect: String,
    @ColumnInfo(name = "url_img") val urlImg: String,
    @ColumnInfo(name = "tittle") val tittle: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "timestamp_string") val timestampString: String,
    @ColumnInfo(name = "description") val description: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        const val TABLE_NAME = "cache_news"
    }
}