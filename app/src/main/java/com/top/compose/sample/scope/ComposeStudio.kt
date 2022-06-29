package com.top.compose.sample.business

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.*
import com.top.compose.sample.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Preview
@Composable
fun TwoColorBox() {

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        val color = remember {
            mutableStateOf(Color.Cyan)
        }

        OneColorBox(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            color.value = it
        }

        Box(
            modifier = Modifier
                .background(color = color.value)
                .fillMaxSize(1.0f)
                .weight(1f)
        )

    }
}

@Preview
@Composable
fun OneColorBox(modifier: Modifier, updateColor: (Color) -> Unit) {

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1.0f
                )
            )
        })
}

@Preview
@Composable
fun ModifiersTest() {
    Column(
        modifier = Modifier.background(Color.Green)
            .fillMaxHeight(0.5f)
            //.width(200.dp)
            //.requiredWidth(100.dp)
            .fillMaxWidth(0.8f)
            .padding(5.dp)
            .border(5.dp, Color.Magenta)
            .padding(5.dp)
            .border(5.dp, Color.Black)
            .padding(5.dp)
            .border(5.dp, Color.Yellow)

    ) {
        Text(
            "Hello",
            modifier = Modifier
                .background(Color.Yellow)
                .offset(50.dp, 0.dp)
                .padding(20.dp)
                .border(5.dp, Color.Red)
                .padding(20.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text("World")
    }
}

@Preview
@Composable
fun ImageCardTest() {
    val painter = painterResource(id = R.drawable.android)
    val des = "Test in ImageCard for des"
    val title = "Test in ImageCard for title"

    Box {
        ImageCard(
            painter = painter,
            contentDescription = des,
            title = title,
            modifier = Modifier
                .background(Color.Gray)
                .padding(10.dp)
        )
    }
}

@Preview
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .background(Color.Yellow),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(1f).fillMaxHeight(1f)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Red),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}

@Preview
@Composable
fun StyleTextTest() {

    val fontFamily = FontFamily(Font(R.font.zcoo))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(color = Color.Green, fontSize = 30.sp),
                ) {
                    append("T")
                }
                append("ext ")
                withStyle(
                    style = SpanStyle(color = Color.Green, fontSize = 30.sp),
                ) {
                    append("S")
                }
                append("tyleText Test")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun Count() {

    val rememberScrollState = rememberScaffoldState()

    val scope = rememberCoroutineScope()
    Scaffold(scaffoldState = rememberScrollState) {

//        val counter by remember {
//            mutableStateOf(0)
//        }

        val counter = produceState(initialValue = 0) {
            delay(3000)
            value = 4
        }


        if (counter.value % 5 == 0 && counter.value > 0) {
//            scope.launch {
//                rememberScrollState.snackbarHostState.showSnackbar("Hello:$counter")
//            }
            LaunchedEffect(key1 = rememberScrollState.snackbarHostState) {
                rememberScrollState.snackbarHostState.showSnackbar("Hello:$counter")
            }
        }


        Button(onClick = { }) {
            Text("Click me: ${counter.value}")
        }
    }
}


@Preview
@Composable
fun SideEffectAndEffectHandlers() {

    var i = 0
    SideEffect {
        i++
    }

    Button(onClick = {}) {
        Text("Click me")
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ConstraintTest() {
    Scaffold {
        val constraintSet = ConstraintSet {
            val greenBox = createRefFor("greenBox")
            val redBox = createRefFor("redBox")

            constrain(greenBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            constrain(redBox) {
                top.linkTo(parent.top)
                start.linkTo(greenBox.end)
                end.linkTo(parent.end)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            createHorizontalChain(greenBox,redBox, chainStyle = ChainStyle.Packed)
        }

        ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.background(Color.Green).layoutId("greenBox"))
            Box(modifier = Modifier.background(Color.Red).layoutId("redBox"))
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Scaffold() {

    val scaffoldState = rememberScaffoldState()

    val rememberCoroutineScope = rememberCoroutineScope()
    //Scaffold脚手架
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text("Draw") },
        topBar = {
            TopAppBar(title = { Text("Title") }, navigationIcon = {
                IconButton(onClick = {
                    rememberCoroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }){
                    Icon(Icons.Filled.Menu, contentDescription = "")
                }
            })
        }
    ) {
        Box {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(20.dp)
            ) {
                Text("昵称")
            }
        }
    }
}