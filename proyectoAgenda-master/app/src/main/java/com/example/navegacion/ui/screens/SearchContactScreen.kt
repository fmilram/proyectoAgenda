package com.example.navegacion.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navegacion.ui.components.ContactItemCard
import com.example.navegacion.viewmodel.ContactViewModel

@Composable
fun SearchContactScreen(
    navController: NavHostController,
    viewModel: ContactViewModel
) {
    val contacts by viewModel.contacts.collectAsState()
    var query by remember { mutableStateOf("") }

    val filteredContacts = remember(query, contacts) {
        if (query.isBlank()) contacts
        else contacts.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.phone.contains(query, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar contacto") },
            modifier = Modifier.fillMaxWidth()
        )

        if (filteredContacts.isEmpty()) {
            Text("No se encontraron contactos.")
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(filteredContacts) { index, contact ->
                    ContactItemCard(
                        contact = contact,
                        index = index,
                        onEdit = { navController.navigate("editar-contacto/$index") },
                        onDelete = { /* opcional, no borramos aquí */ }
                    )
                }
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
