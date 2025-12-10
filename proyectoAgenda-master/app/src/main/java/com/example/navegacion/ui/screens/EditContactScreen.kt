package com.example.navegacion.ui.screens.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navegacion.viewmodel.ContactViewModel

@Composable
fun EditContactScreen(
    navController: NavHostController,
    viewModel: ContactViewModel,
    contactIndex: Int
) {
    val contacts by viewModel.contacts.collectAsState()

    // Evitar error si el índice no existe
    val contact = contacts.getOrNull(contactIndex)
    if (contact == null) {
        Text("Contacto no encontrado")
        return
    }

    var nombre by remember { mutableStateOf(contact.name) }
    var telefono by remember { mutableStateOf(contact.phone) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Editar contacto",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nombre.isNotBlank() && telefono.isNotBlank()) {
                    viewModel.editContact(contactIndex, nombre, telefono)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar cambios")
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}
