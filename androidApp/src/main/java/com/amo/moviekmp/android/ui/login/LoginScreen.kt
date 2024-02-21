package com.amo.moviekmp.android.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amo.moviekmp.android.ui.login.LoginScreenState.Loading
import com.amo.moviekmp.domain.login.LoginFormState
import com.amo.moviekmp.domain.login.isAbleToSubmit

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        Log.e("LoginScreen", formState.toString())
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxSize()
    ) {

        if (screenState is Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp),
            )
        } else {
            LoginForm(
                formState = formState,
                onEmailUpdate = viewModel::updateEmail,
                onPasswordUpdate = viewModel::updatePassword,
                onLogin = viewModel::login
            )
        }
    }

}


@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    formState: LoginFormState,
    onEmailUpdate: (email: String) -> Unit,
    onPasswordUpdate: (pwd: String) -> Unit,
    onLogin: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier, verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Email
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formState.email,
            onValueChange = onEmailUpdate,
            label = {
                Text(
                    text = "Email"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
            ),
            isError = formState.emailError != null
        )

        // Password
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formState.password,
            onValueChange = onPasswordUpdate,
            label = {
                Text(
                    text = "Password"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            isError = formState.passwordError != null
        )

        Spacer(modifier = Modifier.padding(8.dp))
        // Submit Button
        Button(
            onClick = { onLogin() },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            enabled = formState.isAbleToSubmit()
        ) {
            Text(text = "Login")
        }
    }


}

@Preview
@Composable
fun LoginForm_Preview() {
    LoginForm(
        formState = LoginFormState(),
        onEmailUpdate = { e -> },
        onPasswordUpdate = { p -> },
        onLogin = { })
}