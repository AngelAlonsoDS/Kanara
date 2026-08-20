package com.angelalonso.kanara.error

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Text(text = message, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, fontSize = 20.sp)

    Button(onClick = onRetry) {
        Text(text = "Retry", fontSize = 20.sp)
    }
}