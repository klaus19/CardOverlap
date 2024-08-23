package com.example.cardoverlap

class NameRepository(private val nameDao: NameDao) {

    suspend fun insert(name: Name) {
        nameDao.insert(name)
    }

    suspend fun update(name: Name) {
        nameDao.update(name)
    }

    suspend fun delete(name: Name) {
        nameDao.delete(name)
    }

    suspend fun getAllNames(): List<Name> {
        return nameDao.getAllNames()
    }

    suspend fun getNameById(id: Int): Name? {
        return nameDao.getNameById(id)
    }
}
