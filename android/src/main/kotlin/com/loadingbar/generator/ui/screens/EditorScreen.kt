package com.loadingbar.generator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun EditorScreen(navController: NavHostController) {
    var prompt by remember { mutableStateOf("") }
    var speed by remember { mutableStateOf(1000f) }
    var color by remember { mutableStateOf("#6200EE") }
    var isLoading by remember { mutableStateOf(false) }
    
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        TopAppBar(
            title = { Text("Create Loading Bar") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            )
        )
        
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
        ) {
            Text("Describe your loading bar:", modifier = Modifier.padding(bottom = 8.dp))
            TextField(
                value = prompt,
                onValueChange = { prompt = it },
                modifier = Modifier.fillMaxWidth().height(100.dp),
                placeholder = { Text("E.g., Neon futuristic spinner") }
            )
            
            Spacer(Modifier.height(24.dp))
            Text("Speed: ${speed.toInt()}ms")
            Slider(
                value = speed,
                onValueChange = { speed = it },
                valueRange = 500f..3000f,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(Modifier.height(24.dp))
            Text("Color:")
            TextField(
                value = color,
                onValueChange = { color = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("#6200EE") }
            )
            
            Spacer(Modifier.height(32.dp))
            Button(
                onClick = {
                    if (prompt.isNotBlank()) {
                        isLoading = true
                        navController.navigate("preview/{\"prompt\":\"$prompt\",\"speed\":$speed,\"color\":\"$color\"}")
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = !isLoading
            ) {
                Text(if (isLoading) "Generating..." else "Generate & Preview")
            }
        }
    }
}