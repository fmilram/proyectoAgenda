package com.example.navegacion.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacion.ui.screens.about.AboutScreen
import com.example.navegacion.ui.screens.add.AddContactScreen
import com.example.navegacion.ui.screens.delete.DeleteContactScreen
import com.example.navegacion.ui.screens.edit.EditContactScreen
import com.example.navegacion.ui.screens.list.ListContactScreen
import com.example.navegacion.ui.screens.menu.MainMenu
import com.example.navegacion.ui.screens.search.SearchContactScreen
import com.example.navegacion.viewmodel.ContactViewModel

@Composable
fun MainScreen(viewModel: ContactViewModel) {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "menu-principal",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("menu-principal") {
                MainMenu(navController)
            }
            composable("agregar-contacto") {
                AddContactScreen(navController, viewModel)
            }
            composable("buscar-contacto") {
                SearchContactScreen(navController, viewModel)
            }
            composable("eliminar-contacto") {
                DeleteContactScreen(navController, viewModel)
            }
            composable("listar-contactos") {
                ListContactScreen(navController, viewModel)
            }
            composable("acerca-de") {
                AboutScreen(navController)
            }
            composable("editar-contacto/{index}") { backStackEntry ->
                val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
                EditContactScreen(navController, viewModel, index)
            }
        }
    }
}
