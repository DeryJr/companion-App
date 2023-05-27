package com.example.companion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignUpScreen(navController: NavController) {
    Image(
        painter = painterResource(R.drawable.signup_background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 25.dp)
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0x8CFAB1B1))

            ) {
                Text(
                    text = "Companion.",
                    color = Color(0xFF3C0101),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(.2f)
                        .height(4.dp)
                        .background(Color(0xFFFFDCDC), RoundedCornerShape(4.dp))
                        .padding(start = 20.dp, end = 16.dp, top = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 80.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0x8CFAB1B1))
                .align(Alignment.Start)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF3C0101), // Override color here
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Welcome,")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFFFEAEA),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("\n\nSignup to continue")
                    }
                },
                textAlign = TextAlign.Start,
            )
        }

        Column(modifier = Modifier.fillMaxHeight(1f), verticalArrangement = Arrangement.SpaceEvenly) {

        }
    }
}