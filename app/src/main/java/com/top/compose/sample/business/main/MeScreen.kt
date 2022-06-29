package com.top.compose.sample.business.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.top.compose.sample.R
import com.top.compose.sample.business.login.launchDetailsActivity
import com.top.compose.sample.lottie.LottieAnimationImage
import com.top.compose.sample.vm.LoginViewModel
import com.top.compose.widget.TextImage
import com.top.compose.widget.TopAppBarCenter


@Preview
@Composable
fun MeScreen(viewModel: LoginViewModel = hiltViewModel()) {

    //msg.observeAsState()
    val user by viewModel.user.observeAsState()

    val login: (Int) -> Unit = {
        viewModel.login(
            "leo94666", "13524653020yANg"
        )
    }

    val current = LocalContext.current
    Surface(Modifier.fillMaxSize()) {
        MeContent(user?.nickname) {
            login(2)
        }
    }

}

@Composable
fun MeContent(
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
                MeInfo(modifier = Modifier.background(Color.LightGray), title)

                Setting(modifier = Modifier.background(Color.LightGray))
            }
        }

    }
}


@Composable
fun MeInfo(modifier: Modifier = Modifier, title: String?) {
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
            onClick = { launchDetailsActivity(context) }) {
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


@Composable
fun SuperTextView(
    modifier: Modifier = Modifier,

    leftImageVector: ImageVector = Icons.Filled.Person,
    leftTitleString: String = "",
    centerTitleString: String = "",
    rightTitleString: String = "",
    rightImageVector: ImageVector = Icons.Filled.KeyboardArrowRight,

    onClickLeftIcon: () -> Unit = {},
    onClickLeftTitle: () -> Unit = {},
    onClickCenterTitle: () -> Unit = {},
    onClickRightTitle: () -> Unit = {},
    onClickRightIcon: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .background(Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

            val (leftIcon, leftTitle, centerTitle, rightTitle, rightIcon) = createRefs()

            IconButton(
                onClick = onClickLeftIcon,
                modifier = Modifier
                    .constrainAs(leftIcon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
            ) {
                Icon(
                    imageVector = leftImageVector,
                    contentDescription = "leftIcon",
                    tint = Color.LightGray
                )
            }

            ClickableText(
                text = AnnotatedString(leftTitleString),
                modifier = Modifier.constrainAs(leftTitle) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(leftIcon.end)
                }) {
                onClickLeftTitle()
            }

            ClickableText(
                text = AnnotatedString(centerTitleString),
                modifier = Modifier.constrainAs(centerTitle) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(parent)
                }) {
                onClickCenterTitle()
            }

            ClickableText(
                text = AnnotatedString(rightTitleString),
                modifier = Modifier.constrainAs(rightTitle) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(rightIcon.start)
                }) {
                onClickRightTitle()
            }


            IconButton(
                onClick = onClickRightIcon,
                modifier = Modifier
                    .constrainAs(rightIcon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
            ) {
                Icon(
                    imageVector = rightImageVector,
                    contentDescription = "rightIcon",
                    tint = Color.LightGray
                )
            }
        }
    }
}

