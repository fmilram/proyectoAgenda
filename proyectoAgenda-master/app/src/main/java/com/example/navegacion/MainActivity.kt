package com.example.navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.navegacion.ui.MainScreen
import com.example.navegacion.ui.theme.NavegacionTheme
import com.example.navegacion.viewmodel.ContactViewModel

class MainActivity : ComponentActivity() {

    // Instanciamos el ViewModel, ahora es AndroidViewModel
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacionTheme {
                // Inyectamos el ViewModel en MainScreen
                MainScreen(viewModel = contactViewModel)
            }
        }
    }
}
