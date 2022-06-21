package com.example.scorekeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
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
                    ScorekeeperApp()
                }
            }
        }
    }
}

@Composable
fun ScorekeeperApp() {
    // State variables for balls, strikes and outs
    var numBalls by remember { mutableStateOf(0) }
    var numStrikes by remember { mutableStateOf(0) }
    var numOuts by remember { mutableStateOf(0) }

    // State variables for player stats
    var numHits by remember { mutableStateOf(0) }
    var numSingles by remember { mutableStateOf(0) }
    var numDoubles by remember { mutableStateOf(0) }
    var numTriples by remember { mutableStateOf(0) }
    var numHomeruns by remember { mutableStateOf(0) }
    var numWalks by remember { mutableStateOf(0) }
    var numStrikeouts by remember { mutableStateOf(0) }
    var numHitByPitch by remember { mutableStateOf(0) }

    // Variable boolean to display/hide player stats
    var showPlayerStats by remember { mutableStateOf(false)}
    fun togglePlayerStats() {
        showPlayerStats = !showPlayerStats
    }

    fun addBall() {
        if (numBalls > 2) {
            numBalls = 0
            numStrikes = 0
        } else {
            numBalls++
        }
    }

    // Pass addStrike a function so that addOut can be invoked on the third strike
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

    // Functions for updating stats
    fun addSingle() {
        numHits++
        numSingles++
    }
    fun addDouble() {
        numHits++
        numDoubles++
    }
    fun addTriple() {
        numHits++
        numTriples++
    }
    fun addHomerun() {
        numHits++
        numHomeruns++
    }
    fun addWalk() {
        numWalks++
    }
    fun addStrikeout() {
        numStrikeouts++
    }
    fun addHitByPitch() {
        numHitByPitch++
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {

        if (!showPlayerStats) {

            ButtonBar(
                addBall = { addBall() },
                addStrike = { addStrike { addOut() } },
                addOut = { addOut() },
                numBalls = numBalls,
                numStrikes = numStrikes,
                numOuts = numOuts
            )

            BallInPlayButtonBar(
                addSingle = { addSingle() },
                addDouble = { addDouble() },
                addTriple = { addTriple() },
                addHomerun = { addHomerun() },
                addWalk = { addWalk() },
                addStrikeout = { addStrikeout() },
                addHitByPitch = { addHitByPitch() },

            )

        } else {
            PlayerStatsDisplay(
                numHits =numHits,
                numSingles =numSingles,
                numDoubles =numDoubles,
                numTriples =numTriples,
                numHomeruns =numHomeruns,
                numWalks =numWalks,
                numStrikeouts =numStrikeouts,
                numHitByPitch =numHitByPitch
            )
        }

        PlayersStatsToggleButton(
            showPlayerStats = showPlayerStats,
            togglePlayerStats = { togglePlayerStats() },
            alignment = Alignment.BottomCenter
        )

    }
}

@Composable
fun ButtonBar(
    addBall: () -> Unit,
    addStrike: (func: () -> Unit) -> Unit,
    addOut: () -> Unit,
    numBalls: Int,
    numStrikes: Int,
    numOuts: Int
) {
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

@Composable
fun BallInPlayButtonBar(
    addSingle: () -> Unit,
    addDouble: () -> Unit,
    addTriple: () -> Unit,
    addHomerun: () -> Unit,
    addWalk: () -> Unit,
    addStrikeout: () -> Unit,
    addHitByPitch: () -> Unit,

) {

    LazyRow(
        //horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(10.dp)
    ) {
        item {
            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addSingle() }
            ) {
                Text(text = "1B")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addDouble() }
            ) {
                Text(text = "2B")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addTriple() }
            ) {
                Text(text = "3B")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addHomerun() }
            ) {
                Text(text = "HR")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addWalk() }
            ) {
                Text(text = "BB")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addStrikeout() }
            ) {
                Text(text = "SO")
            }

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = { addHitByPitch() }
            ) {
                Text(text = "HBP")
            }
        }
    }
}

@Composable
fun PlayerStatsDisplay(
    numHits: Int,
    numSingles: Int,
    numDoubles: Int,
    numTriples: Int,
    numHomeruns: Int,
    numWalks: Int,
    numStrikeouts: Int,
    numHitByPitch: Int
) {
    Column() {
        Text(text = "Hits: $numHits")
        Text(text = "Singles: $numSingles")
        Text(text = "Doubles: $numDoubles")
        Text(text = "Triples: $numTriples")
        Text(text = "Homeruns: $numHomeruns")
        Text(text = "Walks: $numWalks")
        Text(text = "Strikeouts: $numStrikeouts")
        Text(text = "Hit by pitch: $numHitByPitch")
    }
}

@Composable
fun PlayersStatsToggleButton(
    showPlayerStats: Boolean,
    togglePlayerStats: () -> Unit,
    alignment: Alignment
) {
    Button(onClick = { togglePlayerStats() }
        
    ) {
        Text(if (showPlayerStats) "Hide Stats" else "Show Stats")
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScorekeeperTheme {
        ScorekeeperApp()
    }
}