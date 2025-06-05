package com.example.sevenwonders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sevenwonders.ui.theme.SevenWondersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SevenWondersTheme {
               Surface (
                   modifier = Modifier.fillMaxSize()
               ) {
                   SevenWonderApp()
               }
            }
        }
    }
}
@Composable
fun SevenWonderApp() {
    var num by remember { mutableIntStateOf(1) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedContent(
        targetState = num,
        label = "WonderTransition"
    ) { targetNum ->
        val (image, name, info) = when (targetNum) {
            1 -> Triple(R.drawable.chichen_itza, R.string.chichen_itza, R.string.ci)
            2 -> Triple(R.drawable.christ_the_redeemer, R.string.christ_the_redeemer, R.string.ctr)
            3 -> Triple(R.drawable.colosseum, R.string.colosseum, R.string.c)
            4 -> Triple(R.drawable.great_wall_of_china, R.string.great_wall_of_china, R.string.gwoc)
            5 -> Triple(R.drawable.machu_pichu, R.string.machu_pichu, R.string.mp)
            6 -> Triple(R.drawable.petra, R.string.petra, R.string.p)
            else -> Triple(R.drawable.taj_mahal, R.string.taj_mahal, R.string.tm)
        }

        Wonder(image = image, name = name, info = info)
    }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = {
                    if (num == 1) num = 7
                    else num--
                },
                modifier = Modifier.size(width = 120.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                ) {
                Text(
                    text = stringResource(R.string.previousButton),
                )
            }

            Button(
                onClick = {
                    if (num == 7) num = 1
                    else num++
                },
                modifier = Modifier.size(width = 120.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(
                    text = stringResource(R.string.nextButton),
                )
            }
        }
    }
}

@Composable
fun Wonder(
    image: Int,
    name: Int,
    info: Int
) {
    Column (
        modifier = Modifier
            .padding(top = 40.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Surface (
            shadowElevation = 4.dp,
            modifier = Modifier
                .size(height = 480.dp, width = 340.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Image(
                modifier = Modifier
                    .border(width = 8.dp, color = MaterialTheme.colorScheme.outline, shape = RectangleShape)
                    .padding(30.dp)
                ,
                painter = painterResource(image),
                contentDescription = null
            )
        }

        Spacer(Modifier.size(28.dp))

        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .size(height = 120.dp, width = 340.dp)
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                ,
                text = stringResource(name),
                fontSize = 36.sp,
                fontWeight = FontWeight(180),
                lineHeight = 36.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                ,
                text = stringResource(info),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SevenWondersTheme {
        SevenWonderApp()
    }
}