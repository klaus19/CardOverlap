package com.example.cardoverlap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NameViewModel(private val repository: NameRepository) : ViewModel() {

    fun insert(name: Name) = viewModelScope.launch {
        repository.insert(name)
    }

    fun update(name: Name) = viewModelScope.launch {
        repository.update(name)
    }

    fun delete(name: Name) = viewModelScope.launch {
        repository.delete(name)
    }

    suspend fun getAllNames(): List<Name> {
        return repository.getAllNames()
    }

    suspend fun getNameById(id: Int): Name? {
        return repository.getNameById(id)
    }
}

class NameViewModelFactory(private val repository: NameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
