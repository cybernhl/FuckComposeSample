package com.top.compose.sample.business.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.top.compose.sample.R

@Composable
fun LoginScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {

            Card(
                modifier = Modifier.background(color = Color.White),
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(12.dp)
                ) {
                    val emailText = remember {
                        mutableStateOf("")
                    }

                    val passwordText = remember {
                        mutableStateOf("")
                    }

                    val colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.Blue
                    )


                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "",
                        modifier = Modifier.height(64.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "账号",
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(0.85f),
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    //添加文本框
                    MyTextField(
                        value = emailText.value,
                        colors = colors,
                        trailingIcon = Icons.Default.Email,
                        trailingtintIcon = Color.Gray,
                        modifier = Modifier.fillMaxWidth(0.85f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        onValueChange = {
                            emailText.value = it
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "密码",
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(0.85f),
                        textAlign = TextAlign.Left
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    MyTextField(
                        value = passwordText.value,
                        colors = colors,
                        trailingIcon = Icons.Default.Lock,
                        trailingtintIcon = Color.Gray,
                        modifier = Modifier.fillMaxWidth(0.85f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        onValueChange = {
                            passwordText.value = it
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimateButton(emailText, passwordText)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "忘记密码", color = Color.Black, fontSize = 12.sp)

                }
            }
        }
    }
}

@Composable
fun MyTextField(
    value: String,
    colors: TextFieldColors,
    trailingIcon: ImageVector,
    trailingtintIcon: Color,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        colors = colors,
        trailingIcon = {
            Icon(
                trailingIcon,
                contentDescription = "",
                tint = trailingtintIcon
            )
        },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        onValueChange = onValueChange

    )

}
