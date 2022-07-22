package com.top.compose.sample.scope

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.*
import androidx.navigation.NavHostController
import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIcons
import com.top.compose.sample.R
import com.top.compose.sample.ui.theme.ColorPallet
import com.top.compose.sample.ui.theme.FuckComposeSampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun TwoColorBox() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
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

@Composable
fun ModifiersTest() {
    Column(
        modifier = Modifier
            .background(Color.Green)
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
                modifier = Modifier
                    .fillMaxSize(1f)
                    .fillMaxHeight(1f)
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

            createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
        }

        ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenBox")
            )
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redBox")
            )
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
                }) {
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

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun JueJin() {
    FuckComposeSampleTheme(colorPallet = ColorPallet.TEST) {
        val context = LocalContext.current
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val bottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)



        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    //返回按钮
                    IconButton(onClick = {
                        Toast.makeText(context, "返回", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                    //标题
                    Text(
                        "contentPadding的标题",
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .wrapContentSize(Alignment.Center)
                    )
                    //右侧分享按钮
                    IconButton(onClick = {
                        Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.Share, null)
                    }
                    //右侧设置按钮
                    IconButton(onClick = {
                        Toast.makeText(context, "设置", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.Settings, null)
                    }
                }
            },
            bottomBar = {
                BottomAppBar(
                    cutoutShape = MaterialTheme.shapes.small.copy(
                        CornerSize(percent = 50)
                    )
                ) {

                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                ) {
                    FaIcon(faIcon = FaIcons.Search)
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            drawerContent = {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                // Drawer items
            }

        ) {

            BottomDrawer(
                drawerState = bottomDrawerState,
                drawerContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = AnnotatedString("李之阳")
                        )
                        Text(
                            text = AnnotatedString("李之阳")
                        )

                        Text(
                            text = AnnotatedString("李之阳")
                        )

                        Button(onClick = {}) {
                            Text(
                                text = AnnotatedString("李之阳")
                            )
                        }
                    }
                }
            ) {
                ModalDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = AnnotatedString("李之阳")
                            )
                            Text(
                                text = AnnotatedString("李之阳")
                            )

                            Text(
                                text = AnnotatedString("李之阳")
                            )

                            Button(onClick = {}) {
                                Text(
                                    text = AnnotatedString("李之阳")
                                )
                            }
                        }


                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {

                        Text(
                            text = AnnotatedString("李之阳")
                        )
                        Text(
                            text = AnnotatedString("李之阳")
                        )

                        Text(
                            text = AnnotatedString("李之阳")
                        )

                        Button(onClick = {}) {
                            Text(
                                text = AnnotatedString("李之阳")
                            )
                        }

                        Button(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Text(
                                text = AnnotatedString("Open Drawer")
                            )
                        }

                        Button(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }

                            }
                        }) {
                            Text(
                                text = AnnotatedString("Open ModalDrawer")
                            )
                        }

                        Button(onClick = {
                            scope.launch {
                                bottomDrawerState.apply {
                                    if (isClosed) open() else close()
                                }

                            }
                        }) {
                            Text(
                                text = AnnotatedString("Open BottomDrawer")
                            )
                        }


                        Button(
                            onClick = { /* ... */ },
                            // Uses ButtonDefaults.ContentPadding by default
                            contentPadding = PaddingValues(
                                start = 20.dp,
                                top = 12.dp,
                                end = 20.dp,
                                bottom = 12.dp
                            )
                        ) {
                            // Inner content including an icon and a text label
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Favorite",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text("Like")
                        }

                        Switch(checked = false, onCheckedChange = {})
                        Switch(checked = true, onCheckedChange = {})

                        Image(painter = painterResource(R.drawable.logo), contentDescription = "")

                        TextField(value = "李之阳", onValueChange = {})

                        ExtendedFloatingActionButton(
                            onClick = { /* ... */ },
                            icon = {
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Favorite"
                                )
                            },
                            text = { Text("Like") }
                        )

                        ExtendedFloatingActionButton(
                            text = { Text("Show snackbar") },
                            onClick = {
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState
                                        .showSnackbar(
                                            message = "Snackbar",
                                            actionLabel = "Action",
                                            duration = SnackbarDuration.Short
                                        )
                                    when (result) {
                                        SnackbarResult.ActionPerformed -> {
                                            /* Handle snackbar action performed */
                                        }
                                        SnackbarResult.Dismissed -> {
                                            /* Handle snackbar dismissed */
                                        }
                                    }
                                }
                            }
                        )

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeCompos() {
    val flow = flow<String> {
        emit("Hello ")
        delay(2000)
        emit("Hello 345")
        delay(2000)
        emit("fuck you")

    }
    val flowState = flow.collectAsState("345")


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        Button(onClick = {}) {
            if (flowState.value.isEmpty()) {
                Text(text = "hello", fontSize = 50.sp)
            } else {
                Text(text = flowState.value, fontSize = 50.sp)
            }
        }

    }
}
