package com.poly.tutorkotlin.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.poly.tutorkotlin.models.Cat
import com.poly.tutorkotlin.network.RetrofitInstance
import kotlinx.coroutines.launch


@Composable
fun CatListScreen() {
    val coroutineScope = rememberCoroutineScope()
    var cats by remember { mutableStateOf<List<Cat>>(emptyList()) }
    var selectedCat by remember { mutableStateOf<Cat?>(null) }

    LaunchedEffect(true) {
        coroutineScope.launch {
            try {
                cats = RetrofitInstance.api.getCats()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(cats) { cat ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { selectedCat = cat },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = "https://cataas.com/cat/${cat.id}",
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("ID: ${cat.id}", style = MaterialTheme.typography.titleSmall)
                        Text("Tags: ${cat.tags.joinToString()}")
                    }
                }
            }
        }
    }

    selectedCat?.let {
        CatDetailDialog(cat = it, onDismiss = { selectedCat = null })
    }
}