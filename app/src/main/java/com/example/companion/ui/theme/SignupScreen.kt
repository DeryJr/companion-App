package com.example.companion.ui.theme

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.companion.R
import com.example.companion.data.User
import com.example.companion.data.UserDB
import com.example.companion.ui.theme.states.EmailState
import com.example.companion.ui.theme.states.PasswordState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

// @Darijus, label na text fieldovima kad klikneš i ode gore se ne vidi
// Pokušao sam da izmajmunišem nešto sa pozadinom ali mi ne da da idem to granica outTexFielda
// Uglavnom, znam da je ružno...
// Skontao neki fix ili ukini pozadinu skroz i promjeni boju texta, idk.

@Composable
fun SignUpScreen(navController: NavController, database: UserDB) {
    SignupBackgroundImage()
    SignupContent(navController = navController, database = database)
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
fun SignupContent(navController: NavController, database: UserDB) {
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
        val localFocusManager = LocalFocusManager.current
        val emailState = remember { EmailState() }
        Email(
            emailState.text,
            emailState.error,
            onEmailChanged = {
                emailState.text = it
                emailState.validate()
            },
            onImeAction = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        val passwordState = remember { PasswordState() }
        Password(
            passwordState.text,
            passwordState.error,
            onPasswordChanged = {
                passwordState.text = it
                passwordState.validate()
            },
            onImeAction = {
                localFocusManager.clearFocus()
                if (emailState.isValid() && passwordState.isValid()) {
                    signUp(emailState.text, passwordState.text)
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        SignupButton(
            enabled = emailState.isValid() && passwordState.isValid(),
            email = emailState.text,
            password = passwordState.text,
            database = database
        )
    }
}

fun signUp(email: String, password: String) {
    Log.d("TEST", "email $email, password $password")
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
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                        fontSize = 30.sp,
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
fun Email(
    email: String,
    error: String?,
    onEmailChanged: (String) -> Unit,
    onImeAction: () -> Unit
) {
    OutlinedTextField(
        value = email,
        shape = RoundedCornerShape(16.dp),
        onValueChange = { value -> onEmailChanged(value) },
        label = {
            Text(
                text = "email",
                color = Color(0xFF3C0101),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                    .background(Color(0xFFFFB1B1))
                    .padding(4.dp)
            )
        },
        isError = error != null,
        placeholder = { Text(text = "smtn@stu.ibu.edu.ba", color = Color(0x8C3C0101)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFFFB1B1),
            textColor = Color(0xFF3C0101),
            cursorColor = Color(0xFF3C0101),
            unfocusedBorderColor = Color(0xFF521616),
            focusedBorderColor = Color(0xFF3C0101),
            errorBorderColor = Color(0xFFD60000),
            errorCursorColor = Color(0xFFD60000),
            errorLabelColor = Color(0xFFD60000),
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onImeAction()
            }
        )
    )

    error?.let { ErrorField(it) }
}

@Composable
fun ErrorField(error: String) {
    Text(
        text = error,
        color = Color(0xFFD60000),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xC8FFB1B1))
            .padding(4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(
    password: String,
    error: String?,
    onPasswordChanged: (String) -> Unit,
    onImeAction: () -> Unit
) {

    val showPassword = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        shape = RoundedCornerShape(16.dp),
        onValueChange = { value -> onPasswordChanged(value) },
        label = {
            Text(
                text = "password",
                color = Color(0xFF3C0101),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                    .background(Color(0xFFFFB1B1))
                    .padding(4.dp)
            )
        },
        placeholder = { Text(text = "....", color = Color(0x8C3C0101)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFFFB1B1),
            textColor = Color(0xFF3C0101),
            cursorColor = Color(0xFF3C0101),
            unfocusedBorderColor = Color(0xFF521616),
            focusedBorderColor = Color(0xFF3C0101),
            errorBorderColor = Color(0xFFD60000),
            errorCursorColor = Color(0xFFD60000),
            errorLabelColor = Color(0xFFD60000),
        ),
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
        }),
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
        },
        isError = error != null
    )

    error?.let { ErrorField(error = it) }
}

@Composable
fun SignupButton(enabled: Boolean, email: String, password: String, database: UserDB) {
    Button(
        onClick = {
            val user = User(0, email = email, password = password)
            CoroutineScope(Dispatchers.IO).launch {
                database.userDao().updateUser(user)
//                val user = database.userDao().getUser(1).firstOrNull()
//                user?.let {
//                    database.userDao().deleteUser(user)
//                }
            }
        },
        modifier = Modifier.fillMaxWidth(.6f),
        colors = ButtonDefaults.buttonColors(Color(0xFFFFB1B1)),
        enabled = enabled
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
