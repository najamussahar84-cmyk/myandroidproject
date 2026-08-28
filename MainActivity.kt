package com.snooker.scorecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScoreApp()
                }
            }
        }
    }
}

@Composable
fun ScoreApp() {
    var scoreA by remember { mutableStateOf(0) }
    var scoreB by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Snooker Score Calculator", fontSize = 24.sp, modifier = Modifier.padding(bottom = 32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Player A", fontSize = 20.sp)
                Text(text = "$scoreA", fontSize = 48.sp, modifier = Modifier.padding(vertical = 16.dp))
                Button(onClick = { scoreA += 1 }) { Text("+1 Point") }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { if (scoreA > 0) scoreA -= 1 }) { Text("-1 Point") }
            }
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Player B", fontSize = 20.sp)
                Text(text = "$scoreB", fontSize = 48.sp, modifier = Modifier.padding(vertical = 16.dp))
                Button(onClick = { scoreB += 1 }) { Text("+1 Point") }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { if (scoreB > 0) scoreB -= 1 }) { Text("-1 Point") }
            }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = { scoreA = 0; scoreB = 0 }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
            Text("Reset Match")
        }
    }
}
