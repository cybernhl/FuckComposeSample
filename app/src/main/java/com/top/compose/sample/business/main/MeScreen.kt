package com.top.compose.sample.business.main

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.top.compose.sample.R
import com.top.compose.sample.business.login.LoginViewModel
import com.top.compose.widget.GlideImage
import com.top.compose.widget.SuperTextView
import com.top.compose.widget.TopAppBarCenter
import com.top.fix.sample.business.ConstantRoute
import com.top.react.activity.LoadReactActivity


@Composable
fun MeScreen(
    onNavigateTo: (String) -> Unit
) {

    TopAppBarCenter(
        title = {
            Text(text = stringResource(R.string.bottom_nav_me), color = Color.White)
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Email, contentDescription = "",
                    tint = Color.White
                )
            }
        },
        backgroundColor = Color.LightGray,
        isImmersive = true
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .background(Color.LightGray)
        ) {
            MeInfo(onNavigateTo)
            Setting()
        }
    }

}


@Composable
fun MeInfo(
    onNavigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()

) {
    val isLogin by viewModel.isLogin.observeAsState()

    val user by viewModel.user.observeAsState()

    SideEffect {
        Log.i("EffectEffectEffect", "================SideEffect")
        viewModel.getUser()
    }

    LaunchedEffect(Unit) {
        Log.i("EffectEffectEffect", "================LaunchedEffect")
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.i("EffectEffectEffect", "================DisposableEffect")
        }
    }

    val avatarRadius by remember {
        mutableStateOf(28.dp)
    }

    Box(
        modifier = modifier.height(200.dp)
    ) {

        Card(
            modifier = Modifier
                .padding(
                    top = avatarRadius,
                    bottom = avatarRadius.div(2),
                    start = avatarRadius.div(2),
                    end = avatarRadius.div(2)
                )
                .fillMaxSize(),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = user?.nickname ?: "昵称",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = avatarRadius * 1.2f)
                )


                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    Text("id: ${user?.id}")
                    Spacer(modifier = Modifier.width(30.dp))
                    Text("排名: ${user?.id}")
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text("Email: ${user?.email}")
            }
        }

        IconButton(modifier = Modifier
            .size(avatarRadius * 2f)
            .align(alignment = Alignment.TopCenter)
            .clip(shape = RoundedCornerShape(100)),
            onClick = {
                if (isLogin != true) {
                    onNavigateTo(ConstantRoute.LOGIN_SCREEN)
                }
            }) {

            GlideImage(
                "https://produceapi.e-lab.cn/image/3a02ae4e53b9b11b0711512bc32c241a.jpg",
                modifier = Modifier
                    .size(avatarRadius * 2f)
                    .align(alignment = Alignment.TopCenter)
                    .clip(shape = RoundedCornerShape(100))
            )
        }


    }
}

@Composable
fun Setting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            SuperTextView(
                leftImageVector = Icons.Default.Close,
                leftTitleString = "我的积分",
                onClickItem = {
                    Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show()
                }
            )

            SuperTextView(
                leftTitleString = "我的收藏",
                onClickItem = {
                    Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show()
                }
            )
            SuperTextView(
                leftTitleString = "我的文章",
                onClickItem = {
                    Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show()
                }
            )
            SuperTextView(
                leftTitleString = "我的项目",
                onClickItem = {
                    Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context,LoadReactActivity::class.java))
                }
            )
            SuperTextView(
                leftTitleString = "系统设置",
                onClickItem = {
                    Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

