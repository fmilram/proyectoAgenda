package com.example.navegacion.ui.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { navController.navigate("agregar-contacto") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar contacto")
        }

        Button(
            onClick = { navController.navigate("buscar-contacto") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar contacto")
        }

        Button(
            onClick = { navController.navigate("eliminar-contacto") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Eliminar contacto")
        }

        Button(
            onClick = { navController.navigate("listar-contactos") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lista de contactos")
        }

        Button(
            onClick = { navController.navigate("acerca-de") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Acerca de")
        }
    }
}
