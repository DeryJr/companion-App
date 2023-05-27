package com.example.companion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TopBarTitleText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, top = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Companion.",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(.2f)
                    .height(2.dp)
                    .background(Color.DarkGray, RoundedCornerShape(4.dp))
                    .padding(start = 20.dp, end = 16.dp, top = 16.dp)
            )
        }
    }
}