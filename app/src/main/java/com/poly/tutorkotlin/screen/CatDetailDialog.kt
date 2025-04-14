package com.poly.tutorkotlin.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.poly.tutorkotlin.models.Cat


@Composable
fun CatDetailDialog(cat: Cat, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 4.dp) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "🐱 Mèo ID: ${cat.id}", style = MaterialTheme.typography.titleMedium)
                AsyncImage(
                    model = "https://cataas.com/cat/${cat.id}",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Text(text = "Tag: ${cat.tags.joinToString(", ")}")
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End).padding(top = 8.dp)
                ) {
                    Text("Đóng")
                }
            }
        }
    }
}