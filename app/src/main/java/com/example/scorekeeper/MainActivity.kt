package com.example.scorekeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scorekeeper.ui.theme.ScorekeeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScorekeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ButtonBar()
                }
            }
        }
    }
}

@Composable
fun ButtonBar() {

    var numBalls by remember { mutableStateOf(0) }
    var numStrikes by remember { mutableStateOf(0) }
    var numOuts by remember { mutableStateOf(0) }

    fun addBall() {
        if (numBalls > 2) {
            numBalls = 0
            numStrikes = 0
        } else {
            numBalls++
        }
    }

    fun addStrike(func:()-> Unit) {
        if (numStrikes > 1) {
            numStrikes = 0
            numBalls = 0
            func()
        } else {
            numStrikes++
        }
    }

    fun addOut() {
        if (numOuts > 1) {
            numOuts = 0
            numStrikes = 0
            numBalls = 0
        } else {
            numOuts++
            numStrikes = 0
            numBalls = 0
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            //horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addBall() })
            {
                Text(text = "ball")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addStrike({ addOut() }) }) {
                Text(text = "strike")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addOut() }) {
                Text(text = "out")
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Balls: $numBalls",
                modifier = Modifier.padding(6.dp)
            )

            Text(
                text = "Strikes: $numStrikes",
                modifier = Modifier.padding(6.dp)
            )

            Text(
                text = "Outs: $numOuts",
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScorekeeperTheme {
        ButtonBar()
    }
}