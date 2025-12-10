package com.example.navegacion.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.navegacion.data.Contact
import com.example.navegacion.data.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContactRepository(application.applicationContext)

    // StateFlow que expone la lista de contactos
    private val _contacts = MutableStateFlow<List<Contact>>(repository.getContacts())
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    // Agregar contacto
    fun addContact(name: String, phone: String) {
        viewModelScope.launch {
            repository.addContact(Contact(id = 0, name = name, phone = phone))
            _contacts.value = repository.getContacts()
        }
    }

    // Editar contacto por índice
    fun editContact(index: Int, name: String, phone: String) {
        viewModelScope.launch {
            repository.updateContact(index, Contact(id = 0, name = name, phone = phone))
            _contacts.value = repository.getContacts()
        }
    }

    // Eliminar contacto por índice
    fun deleteContact(index: Int) {
        viewModelScope.launch {
            repository.deleteContact(index)
            _contacts.value = repository.getContacts()
        }
    }

    // Obtener un contacto por índice
    fun getContact(index: Int): Contact? {
        return repository.getContact(index)
    }
}
