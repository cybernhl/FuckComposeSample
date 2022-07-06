package com.top.compose.sample.business.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIcons
import com.top.compose.sample.HorizontalDottedProgressBar
import com.top.compose.sample.R
import com.top.compose.sample.ui.lottie.LottieLoginAnimation
import com.top.compose.widget.TopAppBarCenter
import com.top.fix.sample.business.ConstantRoute

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    TopAppBarCenter(
        title = {
            Text(text = stringResource(R.string.login), color = Color.Black)
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                FaIcon(faIcon = FaIcons.ArrowLeft, tint = Color.Black)
            }
        },
        backgroundColor = Color.White,
        isImmersive = true
    ) {
        LoginContent(navController)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginContent(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var account by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var hasError by remember {
        mutableStateOf(false)
    }

    var passwordVisualTransformation by remember {
        mutableStateOf<VisualTransformation>(
            PasswordVisualTransformation()
        )
    }

    val passwordInteractionState = remember { MutableInteractionSource() }

    val loading by viewModel.loading.observeAsState()


    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                LottieLoginAnimation()
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                OutlinedTextField(
                    value = account,
                    leadingIcon = {
                        FaIcon(
                            faIcon = FaIcons.User,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    },
                    maxLines = 1,
                    isError = hasError,
                    label = { Text(text = "Account") },
                    placeholder = { Text(text = "leo94666") },
                    trailingIcon = {

                    },
                    onValueChange = {
                        account = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }
            item { Spacer(modifier = Modifier.height(2.dp)) }
            item {
                OutlinedTextField(
                    value = password,
                    leadingIcon = {
                        FaIcon(
                            faIcon = FaIcons.Key,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    },
                    trailingIcon = {

                        IconButton(onClick = {
                            passwordVisualTransformation =
                                if (passwordVisualTransformation != VisualTransformation.None) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                        }) {
                            if (passwordVisualTransformation != VisualTransformation.None) {
                                FaIcon(
                                    faIcon = FaIcons.EyeSlash,
                                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                                )
                            } else {
                                FaIcon(
                                    faIcon = FaIcons.Eye,
                                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                                )
                            }

                        }

                    },
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "Password") },
                    onValueChange = {
                        password = it
                    },
                    interactionSource = passwordInteractionState,
                    visualTransformation = passwordVisualTransformation,
                )
            }
            item {
                Button(
                    onClick = {
                        if (invalidInput(account = account.text, password = password.text)) {
                            hasError = true
                            viewModel.loading.value = false
                        } else {
                            hasError = false
                            viewModel.loading.value = true
                            viewModel.login(account.text, password.text)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    if (loading == true) {
                        HorizontalDottedProgressBar()
                    } else {
                        Text(text = "Login")
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(vertical = 16.dp)) {
                    Spacer(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                    Text(
                        text = "Or Register",
                        color = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(MaterialTheme.colors.background)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
            item {
                OutlinedButton(
                    onClick = { }, modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    FaIcon(
                        faIcon = FaIcons.Registered,
                        tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                    )
                    ClickableText(
                        onClick = {
                            navController.navigate(ConstantRoute.REGISTER_SCREEN)
                        },
                        text = AnnotatedString("Register Account"),
                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        ),

                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}


fun invalidInput(account: String, password: String) = account.isBlank() || password.isBlank()