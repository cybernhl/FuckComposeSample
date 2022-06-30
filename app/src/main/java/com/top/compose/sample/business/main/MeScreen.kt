package com.top.compose.sample.business.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.top.compose.sample.R
import com.top.compose.sample.business.viewmodel.LoginViewModel
import com.top.compose.sample.ui.lottie.LottieAnimationImage
import com.top.compose.widget.SuperTextView
import com.top.compose.widget.TextImage
import com.top.compose.widget.TopAppBarCenter
import com.top.fix.sample.business.ConstantRoute


@Composable
fun MeScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val user by viewModel.user.observeAsState()

    Surface(Modifier.fillMaxSize()) {
        MeContent(navController, user?.nickname) {
            viewModel.getUser()
        }
    }

}

@Composable
fun MeContent(
    navController: NavHostController,
    title: String?,
    onClick: () -> Unit = {}
) {
    Column {
        TopAppBarCenter(
            title = {
                Text(text = stringResource(R.string.bottom_nav_me), color = Color.White)
            },
            actions = {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        Icons.Filled.Email, contentDescription = "",
                        tint = Color.White
                    )
                }

            },
            backgroundColor = Color.LightGray,
            isImmersive = true
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                MeInfo(navController, modifier = Modifier.background(Color.LightGray), title)

                Setting(modifier = Modifier.background(Color.LightGray))
            }
        }

    }
}


@Composable
fun MeInfo(
    navController: NavHostController,
    modifier: Modifier = Modifier, title: String?
) {
    val avatarRadius by remember {
        mutableStateOf(28.dp)
    }

    val context = LocalContext.current
    Box(
        modifier = modifier.height(220.dp)
    ) {

        Card(
            modifier = Modifier
                .padding(
                    top = avatarRadius,
                    bottom = avatarRadius,
                    start = avatarRadius.div(2),
                    end = avatarRadius.div(2)
                )
                .fillMaxSize()
        ) {
            title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .padding(top = avatarRadius * 1.2f)
                )
            }
        }

        IconButton(modifier = Modifier
            .size(avatarRadius * 2f)
            .align(alignment = Alignment.TopCenter)
            .clip(shape = RoundedCornerShape(100)),
            onClick = {
                navController.navigate(ConstantRoute.LOGIN_SCREEN)
            }) {
            LottieAnimationImage(
                R.raw.android,
                modifier = Modifier
                    .size(avatarRadius * 2f)
                    .align(alignment = Alignment.TopCenter)
                    .clip(shape = RoundedCornerShape(100))
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(
                    top = avatarRadius,
                    bottom = avatarRadius,
                    start = avatarRadius.div(2),
                    end = avatarRadius.div(2)
                )
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                TextImage(painter = painterResource(R.drawable.android), text = "收藏文章")
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                TextImage(painter = painterResource(R.drawable.android), text = "分享文章")
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                TextImage(painter = painterResource(R.drawable.android), text = "收藏网站")
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                TextImage(painter = painterResource(R.drawable.android), text = "分享项目")
            }
        }
    }
}

@Composable
fun Setting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(20.dp)) {
            SuperTextView(
                modifier = Modifier,
                rightTitleString = "Go",
                onClickCenterTitle = {
                    Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

