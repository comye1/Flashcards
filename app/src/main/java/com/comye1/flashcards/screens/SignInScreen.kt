package com.comye1.flashcards.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.flashcards.R
import com.comye1.flashcards.utils.AuthResultContract
import com.comye1.flashcards.viewmodels.CheggViewModel
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun SignInView(errorText: String?, onClick: () -> Unit) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleSignInButtonUi(
                text = "Sign Up With Google",
                loadingText = "Signing In....",
                onClick = onClick
            )
            errorText?.let {
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SignInScreen(viewModel: CheggViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf<String?>(null)
    }
    val user by remember(viewModel) {
        viewModel.user
    }.collectAsState()
    val signInRequestCode = 1
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google Sign In Failed"
                } else {
                    coroutineScope.launch {
                        viewModel.signIn(
                            email = account.email,
                            displayName = account.displayName
                        )
                    }
                }
            } catch (e: ApiException) {
                text = "Google SignIn Failed"
            }
        }
    SignInView(errorText = text, onClick = {
        text = null
        authResultLauncher.launch(signInRequestCode)
    })
}

@ExperimentalMaterialApi
@Composable
fun GoogleSignInButtonUi(
    text: String = "",
    loadingText: String = "",
    onClick: () -> Unit
) {
    var clicked by remember {
        mutableStateOf(false)
    }

    Surface(
        onClick = { clicked = !clicked },
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = MaterialTheme.colors.surface
    ) {
        Row(
            Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_icon),
                contentDescription = "Google SignIn Button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (clicked) loadingText else text)

            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colors.primary
                )
                onClick()
            }
        }
    }

}

@ExperimentalMaterialApi
@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleSignInButtonUi(text = "Sign Up With Google", loadingText = "Signing In....") {

    }
}