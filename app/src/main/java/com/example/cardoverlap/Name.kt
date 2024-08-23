package com.example.cardoverlap

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "names")
data class Name(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
