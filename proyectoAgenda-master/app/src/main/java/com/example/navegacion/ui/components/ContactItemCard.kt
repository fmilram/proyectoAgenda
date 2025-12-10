package com.example.navegacion.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navegacion.data.Contact

@Composable
fun ContactItemCard(
    contact: Contact,
    index: Int,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onClick: ((Int) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick?.invoke(index) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                Text(text = contact.phone, style = MaterialTheme.typography.bodyMedium)
            }

            Row {
                TextButton(onClick = { onEdit(index) }) {
                    Text("Editar")
                }
                TextButton(onClick = { onDelete(index) }) {
                    Text("Borrar")
                }
            }
        }
    }
}
