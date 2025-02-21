package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspaceapp.data.Arts
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpace(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }
    val currentArt = Arts[currentIndex]

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtImage(imageRes = currentArt.imageResourceId)

        Spacer(modifier = Modifier.height(16.dp))

        ArtDescription(
            titleRes = currentArt.artTitle,
            artistRes = currentArt.artArtist,
            yearRes = currentArt.artYear
        )

        Spacer(modifier = Modifier.height(16.dp))

        ArtNavigation(currentIndex,

            previousImage = {
            currentIndex = if (currentIndex > 0)
            currentIndex - 1
            else Arts.size - 1
            },

            nextImage = {
            currentIndex = if (currentIndex < Arts.size - 1)
            currentIndex + 1
            else 0
            }
        )
    }
}

@Composable
fun ArtImage(imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .shadow(8.dp)
            .background(Color.White)
            .padding(top = 45.dp, bottom = 45.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(250.dp)
                .height(300.dp)
        )
    }
}

@Composable
fun ArtDescription(titleRes: Int, artistRes: Int, yearRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFECECEC))
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = titleRes),
            fontSize = 28.sp,
        )
        Row {
            Text(
                text = stringResource(id = artistRes),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = yearRes),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ArtNavigation(currentIndex: Int, previousImage: () -> Unit, nextImage: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = previousImage, modifier = Modifier.size(110.dp,40.dp)) {
            Text("Previous")
        }
        Button(onClick = nextImage, modifier = Modifier.size(110.dp,40.dp)) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpace()
    }
}
