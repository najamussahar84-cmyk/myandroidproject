package com.snooker.scorecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ScoreApp() }
    }
}

@Composable
fun ScoreApp() {
    var scoreA by remember { mutableIntStateOf(0) }
    var scoreB by remember { mutableIntStateOf(0) }
    var teamATurn by remember { mutableStateOf(true) }
    var winner by remember { mutableStateOf<String?>(null) }
    var message by remember { mutableStateOf("Team A starts") }
    val values = listOf(2,3,4,5,6,7,10)

    fun applyScore(value: Int) {
        if (winner != null) return
        val current = if (teamATurn) scoreA else scoreB
        val name = if (teamATurn) "Team A" else "Team B"
        val next = current + value
        if (next > 100) {
            message = "$name needs exactly ${100-current}. $value cannot be added."
            return
        }
        if (teamATurn) scoreA = next else scoreB = next
        if (next == 100) {
            winner = name
            message = "🏆 $name wins with exactly 100!"
        } else {
            teamATurn = !teamATurn
            message = "Turn switched to ${if (teamATurn) "Team A" else "Team B"}"
        }
    }

    fun foul() {
        if (winner != null) return
        if (teamATurn) scoreA -= 5 else scoreB -= 5
        teamATurn = !teamATurn
        message = "Foul! −5 points. Turn switched to ${if (teamATurn) "Team A" else "Team B"}"
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("SNOOKER SCORE CALCULATOR", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Text("Target: EXACTLY 100", fontSize = 16.sp)
                Spacer(Modifier.height(20.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    TeamCard("TEAM A", scoreA, 100-scoreA)
                    TeamCard("TEAM B", scoreB, 100-scoreB)
                }

                Spacer(Modifier.height(20.dp))
                Text(
                    if (winner == null) "${if (teamATurn) "TEAM A" else "TEAM B"}'S TURN" else "GAME OVER",
                    fontSize = 22.sp, fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(message, textAlign = TextAlign.Center)

                Spacer(Modifier.height(18.dp))
                values.chunked(4).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        row.forEach { value ->
                            Button(
                                onClick = { applyScore(value) },
                                modifier = Modifier.weight(1f).height(58.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) { Text("+$value", fontSize = 18.sp) }
                        }
                        repeat(4-row.size) { Spacer(Modifier.weight(1f)) }
                    }
                    Spacer(Modifier.height(8.dp))
                }

                Spacer(Modifier.height(6.dp))
                Button(
                    onClick = { foul() },
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("FOUL  −5", fontSize = 19.sp, fontWeight = FontWeight.Bold) }

                Spacer(Modifier.height(10.dp))
                OutlinedButton(
                    onClick = {
                        scoreA = 0; scoreB = 0; teamATurn = true
                        winner = null; message = "Team A starts"
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("NEW GAME / RESET") }
            }
        }
    }
}

@Composable
fun TeamCard(name: String, score: Int, need: Int) {
    Card(modifier = Modifier.width(155.dp), shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(name, fontWeight = FontWeight.Bold)
            Text("$score", fontSize = 38.sp, fontWeight = FontWeight.Bold)
            Text("Need: $need")
        }
    }
}
