package com.example.syncup_android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.syncup_android.R
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.ui.navigation.SnackbarVisualsWithError
import com.example.syncup_android.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (snackBar: SnackbarHostState, onLoginClicked: () -> Unit, modifier: Modifier = Modifier, loginViewModel: LoginViewModel = viewModel()){
    val loginUIState by loginViewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current;
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()) {
        Image(modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop, painter = painterResource(id = R.drawable.loginbg), contentDescription = null)
        Column(modifier = Modifier.padding(start = 50.dp, end = 50.dp, bottom = 24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), label = { Text(color = MaterialTheme.colorScheme.primary, text = "Email")}, value = loginUIState.email, onValueChange = {loginViewModel.changeUsername(it)},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp), label = { Text(color = MaterialTheme.colorScheme.primary, text = "Password")}, value = loginUIState.password, onValueChange = {loginViewModel.changePassword(it)},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus()}
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, end = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(modifier = Modifier.padding(0.dp), checked = loginUIState.rememberMe, onCheckedChange = {loginViewModel.changeRememberMe()})
                    ClickableText(style = TextStyle(fontSize = 13.sp, color = MaterialTheme.colorScheme.primary), text = AnnotatedString(
                        "Remember me"), onClick = {loginViewModel.changeRememberMe()})
                }
                Text(fontSize = 13.sp, color = MaterialTheme.colorScheme.primary, text = "Forgot password?")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(modifier = Modifier.width(130.dp), onClick = {

                    scope.launch {
                        try {
                            val user = loginViewModel.login().user
                            UserContext.login(user)
                            onLoginClicked()
                        } catch (e: Exception) {
                            snackBar.showSnackbar(SnackbarVisualsWithError(e.message!!, true))
                        }
                    }
            }) {
                Text(text = "Log in")
            }
            Text(fontSize = 13.sp, modifier = Modifier.padding(top = 15.dp), color = MaterialTheme.colorScheme.primary, text = "Dont have an account? Sign up")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    LoginScreen(snackBar = SnackbarHostState(), onLoginClicked = {})
}
