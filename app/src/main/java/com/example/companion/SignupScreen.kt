package com.example.companion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignUpScreen(navController: NavController) {
    SignupBackgroundImage()
    SignupContent(navController)
}

@Composable
fun SignupBackgroundImage() {
    Image(
        painter = painterResource(R.drawable.signup_background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun SignupContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignupTopBar()
        Spacer(modifier = Modifier.padding(top = 80.dp))
        SignupText()
        Spacer(modifier = Modifier.weight(1f))
        Email()
        Password()
        Spacer(modifier = Modifier.weight(1f))
        SignupButton()

    }
}

@Composable
fun SignupTopBar() {
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
                modifier = Modifier.padding(horizontal = 16.dp)
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
}

@Composable
fun SignupText() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0x8CFAB1B1))
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF3C0101),
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email() {

    val emailState = remember { mutableStateOf("") }

    OutlinedTextField(
        value = emailState.value,
        onValueChange = { emailState.value = it },
        label = { Text(text = "email", color = Color(0xFF3C0101), fontWeight = FontWeight.Bold) },
        placeholder = { Text(text = "smtn@stu.ibu.edu.ba", color = Color(0xFF3C0101)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFFFB1B1),
            textColor = Color(0xFF3C0101),
            cursorColor = Color(0xFF3C0101),
            unfocusedBorderColor = Color(0xFF521616),
            focusedBorderColor = Color(0xFF3C0101),
            errorBorderColor = Color.Red,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password() {

    val passwordState = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }


    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = {
            Text(
                text = "password",
                color = Color(0xFF3C0101),
                fontWeight = FontWeight.Bold
            )
        },
        placeholder = { Text(text = "******", color = Color(0xFF3C0101)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFFFB1B1),
            textColor = Color(0xFF3C0101),
            cursorColor = Color(0xFF3C0101),
            unfocusedBorderColor = Color(0xFF521616),
            focusedBorderColor = Color(0xFF3C0101),
            errorBorderColor = Color.Red
        ),
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visibility_on),
                        contentDescription = stringResource(id = R.string.hide_password),
                        tint = Color(0xFF3C0101)
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visibility_off),
                        contentDescription = stringResource(id = R.string.hide_password),
                        tint = Color(0xFF3C0101)
                    )
                }
            }
        }
    )
}

@Composable
fun SignupButton() {
    Button(
        onClick = { },
        modifier = Modifier.fillMaxWidth(.6f),
        colors = ButtonDefaults.buttonColors(Color(0xFFFFB1B1))
    ) {
        Text(
            text = "Sign Up",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3C0101)
        )
    }
    Text(text = "or", color = Color(0xFFFFB1B1))
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF682B2B))

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF682B2B))

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_meta),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
    Text(
        text = "Already have an account?",
        color = Color(0xFFFFB1B1),
        modifier = Modifier.padding(top = 16.dp)
    )
    TextButton(onClick = { /*TODO*/ }, modifier = Modifier) {
        Text(
            text = "Login",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFB1B1),
            fontSize = 20.sp
        )
    }
}
