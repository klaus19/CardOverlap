package com.example.cardoverlap

import android.app.Application

class MyApp : Application() {

    val database by lazy { NameDatabase.getDatabase(this) }
    val repository by lazy { NameRepository(database.nameDao()) }
}
