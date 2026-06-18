package com.loadingbar.generator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("AI Custom Loader", fontSize = 32.sp, modifier = Modifier.padding(16.dp))
        Text("Generate custom loading bars with AI", fontSize = 16.sp, modifier = Modifier.padding(16.dp))
        
        Button(
            onClick = { navController.navigate("editor") },
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Icon(Icons.Default.Add, "Create", modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(8.dp))
            Text("Create New Loader")
        }
        
        Spacer(Modifier.height(16.dp))
        
        Button(
            onClick = { navController.navigate("saved") },
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Icon(Icons.Default.Bookmarks, "Saved", modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(8.dp))
            Text("View Saved Loaders")
        }
    }
}