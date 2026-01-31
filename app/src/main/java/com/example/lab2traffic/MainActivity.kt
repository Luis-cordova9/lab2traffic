package com.example.lab2traffic

import android.os.Bundle
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.CardDefaults
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.lab2traffic.ui.theme.Lab2trafficTheme
import androidx.compose.material3.Card
import androidx.compose.foundation.shape.RoundedCornerShape
enum class Light {
    Red, Yellow, Green
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2trafficTheme {
                TrafficLightScreen()

            }
        }
    }
}

@Composable
fun TrafficLightScreen() {

    var state by remember { mutableStateOf(Light.Red) }

    LaunchedEffect(Unit) {
        while (true) {
            state = Light.Red
            delay(2000)

            state = Light.Green
            delay(2000)

            state = Light.Yellow
            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF222222))
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TrafficLightCircle(state == Light.Red, Color.Red)
                Spacer(modifier = Modifier.height(16.dp))
                TrafficLightCircle(state == Light.Yellow, Color.Yellow)
                Spacer(modifier = Modifier.height(16.dp))
                TrafficLightCircle(state == Light.Green, Color.Green)
            }
        }
    }
}

@Composable
fun TrafficLightCircle(isActive: Boolean, color: Color) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(if (isActive) color else Color.Gray)
    )
}


