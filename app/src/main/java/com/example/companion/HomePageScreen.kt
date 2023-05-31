package com.example.companion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(navController: NavController) {
    val text = remember { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }
    val items = remember {
        mutableStateListOf(
            "Ivan",
            "Android Dev"
        )
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 30.dp),
        colors = SearchBarDefaults.colors(
            containerColor = Color(0xFFEEEEEE),
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.LightGray,
                disabledTextColor = Color.DarkGray,
            )
        ),
        query = text.value,
        onQueryChange = { text.value = it },
        onSearch = {
            items.add(text.value)
            active.value = false
            text.value = ""
        },
        active = active.value,
        onActiveChange = {
            active.value = it
        },
        placeholder = { Text(text = "Search", color = Color.Black) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = Color.Black
            )
        },
        trailingIcon = {
            if (active.value) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.value.isNotEmpty()) {
                            text.value = ""
                        } else {
                            active.value = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close)
                )
            }
        }
    ) {
        items.forEach {
            Row(modifier = Modifier.padding(14.dp)) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = stringResource(id = R.string.history)
                )
                Text(text = it, color = Color.Black)
            }
        }
    }
}