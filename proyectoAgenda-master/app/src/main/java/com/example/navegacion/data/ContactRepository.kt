package com.example.navegacion.data

import android.content.Context
import android.util.Log
import java.io.File

class ContactRepository(private val context: Context) {

    private val fileName = "contacts.csv"
    private var contacts = mutableListOf<Contact>()

    init {
        loadContactsFromCsv()
    }

    fun getContacts(): List<Contact> = contacts.toList()
    fun getContact(index: Int): Contact? = contacts.getOrNull(index)

    fun addContact(contact: Contact) {
        val newId = if (contacts.isEmpty()) 1 else (contacts.maxOf { it.id } + 1)
        contacts.add(contact.copy(id = newId))
        saveContactsToCsv()
    }

    fun updateContact(index: Int, contact: Contact) {
        contacts.getOrNull(index)?.let {
            contacts[index] = contact.copy(id = it.id)
            saveContactsToCsv()
        }
    }

    fun deleteContact(index: Int) {
        if (index in contacts.indices) {
            contacts.removeAt(index)
            saveContactsToCsv()
        }
    }

    private fun loadContactsFromCsv() {
        try {
            val file = File(context.filesDir, fileName)
            if (!file.exists()) {
                contacts = mutableListOf()
                return
            }

            contacts = file.readLines()
                .mapNotNull { line ->
                    val parts = line.split(",")
                    if (parts.size == 3) {
                        val id = parts[0].toIntOrNull() ?: return@mapNotNull null
                        val name = parts[1]
                        val phone = parts[2]
                        Contact(id, name, phone)
                    } else null
                }.toMutableList()

        } catch (e: Exception) {
            Log.e("ContactRepository", "Error al leer CSV: ${e.message}")
            contacts = mutableListOf()
        }
    }

    private fun saveContactsToCsv() {
        try {
            val file = File(context.filesDir, fileName)
            file.bufferedWriter().use { out ->
                contacts.forEach { contact ->
                    out.write("${contact.id},${contact.name},${contact.phone}\n")
                }
            }
        } catch (e: Exception) {
            Log.e("ContactRepository", "Error al guardar CSV: ${e.message}")
        }
    }
}
