package com.example.navegacion.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navegacion.ui.components.ContactItemCard
import com.example.navegacion.ui.dialogs.ConfirmDialog
import com.example.navegacion.viewmodel.ContactViewModel

@Composable
fun ListContactScreen(
    navController: NavHostController,
    viewModel: ContactViewModel
) {
    val contacts by viewModel.contacts.collectAsState()
    var contactToDeleteIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (contacts.isEmpty()) {
            Text("No se encontraron contactos.")
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(contacts) { index, contact ->
                    ContactItemCard(
                        contact = contact,
                        index = index,
                        onEdit = { navController.navigate("editar-contacto/$index") },
                        onDelete = { contactToDeleteIndex = it }
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

    // Diálogo de confirmación para eliminar
    if (contactToDeleteIndex != null) {
        ConfirmDialog(
            title = "Eliminar contacto",
            message = "¿Seguro que deseas eliminar este contacto?",
            onConfirm = {
                viewModel.deleteContact(contactToDeleteIndex!!)
                contactToDeleteIndex = null
            },
            onCancel = { contactToDeleteIndex = null }
        )
    }
}
