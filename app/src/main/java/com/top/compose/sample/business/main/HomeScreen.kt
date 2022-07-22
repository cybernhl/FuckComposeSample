package com.top.compose.sample.business.main

import android.text.Html
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIcons
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.bean.HomeArticle
import com.top.compose.sample.business.viewmodel.HomeUiAction
import com.top.compose.sample.business.viewmodel.WanAndroidViewModel
import com.top.compose.sample.ui.theme.AppThemeState
import com.top.compose.sample.ui.theme.ColorPallet
import com.top.compose.sample.ui.theme.Purple700
import com.top.compose.widget.GlideImage
import com.top.compose.widget.TopAppBarCenter
import com.top.compose.widget.showDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun HomeScreen(
    title: String,
    viewModel: WanAndroidViewModel = hiltViewModel(),
    appThemeState: MutableState<AppThemeState>
) {


    val articles = viewModel.articles.collectAsLazyPagingItems()

    val banner by viewModel.banner.collectAsState(initial = null)


    val state: LazyListState = rememberLazyListState()

    val showPalletMenu = remember {
        mutableStateOf(false)
    }


    TopAppBarCenter(
        title = {
            Text(text = title, color = Color.White)
        },
        actions = {
            IconButton(onClick = {

            }) {
                FaIcon(faIcon = FaIcons.Search, tint = Color.White)
            }
            IconButton(onClick = {
                showPalletMenu.value = !showPalletMenu.value
            }) {
                FaIcon(faIcon = FaIcons.Palette, tint = Color.White)
            }

        },
        backgroundColor = MaterialTheme.colors.primary,
        isImmersive = true
    ) {

        showPalletMenu(
            showPalletMenu = showPalletMenu
        ) { newPalletSelected ->
            appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState((articles.loadState.refresh is LoadState.Loading && articles.itemCount > 0)),
            onRefresh = { articles.refresh() }
        ) {
            when (articles.loadState.refresh) {
                is LoadState.NotLoading -> {

                }
                is LoadState.Loading -> {
                    if (articles.itemCount == 0) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                        }
                    }
                }
                is LoadState.Error -> {
                    //加载失败的错误页面
                    Box(modifier = Modifier.fillMaxSize()) {
                        Button(modifier = Modifier.align(alignment = Alignment.Center),
                            onClick = { articles.refresh() }) {
                            Text(text = "加载失败！请重试")
                        }
                    }
                }
                else -> {

                }
            }

            LazyColumn(state = state, modifier = Modifier.background(Color.LightGray), content = {
                itemsIndexed(articles) { index, data ->
                    if (index == 0) {
                        Banner(banner)
                    } else {
                        HomeArticle(data)
                    }
                }

                if (articles.loadState.append is LoadState.Loading) {
                    //下一页的load状态
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                        }
                    }
                }

            })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(banner: List<Banner>?) {

    val pagerState = rememberPagerState()

    var currentTime by remember {
        mutableStateOf(10L)
    }
    LaunchedEffect(key1 = currentTime) {
        delay(3000)
        if (pagerState.currentPage == (banner?.size?.minus(1) ?: 0)) {
            pagerState.animateScrollToPage(0)
        } else {
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
        currentTime = System.currentTimeMillis()
    }

    HorizontalPager(
        count = banner?.size ?: 0,
        state = pagerState,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 4.dp)
        //.background(Color.Red)
    ) { page ->

        val imagePath = banner?.get(page)?.imagePath
        Box {
            //val title = banner?.get(page)?.title
            AsyncImage(
                model = imagePath,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(4.dp)
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
        )
    }


}

@Composable
fun HomeArticle(
    data: Article?,
    viewModel: WanAndroidViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val animatedProgress =
        remember { androidx.compose.animation.core.Animatable(initialValue = 0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )
    }

    val zan = viewModel.zan.collectAsState(false)



    Card(
        modifier =
        modifier
            .padding(horizontal = 14.dp, vertical = 4.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .alpha(animatedProgress.value)
            .scale(animatedProgress.value)
            .rotate(animatedProgress.value)

    ) {
        ConstraintLayout {
            val (author, title, time, isPraise, chapter, topText, newText) = createRefs()

            Praise(
                modifier = Modifier
                    .constrainAs(isPraise) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                isPraise = zan.value,
                onClick = {
                    //viewModel.collect(data?.id ?: 0)
                }
            )

            if (!data?.shareUser.isNullOrEmpty()) {
                Text(
                    text = data?.shareUser ?: "",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .constrainAs(author) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(title.top)
                        })
            }

            if (data?.top == true) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(1.dp, Color.Red, RoundedCornerShape(4.dp))
                        .constrainAs(topText) {
                            top.linkTo(author.top)
                            bottom.linkTo(author.bottom)
                            start.linkTo(author.start)
                        }
                ) {
                    Text(
                        text = "置顶",
                        fontSize = 12.sp,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }

            if (data?.fresh == true) {
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red, RoundedCornerShape(4.dp))
                        .constrainAs(newText) {
                            if (data.top) {
                                top.linkTo(topText.top)
                                bottom.linkTo(topText.bottom)
                                start.linkTo(topText.end)
                            } else {
                                top.linkTo(author.top)
                                bottom.linkTo(author.bottom)
                                start.linkTo(author.end)
                            }
                        }
                ) {
                    Text(
                        text = "新",
                        fontSize = 12.sp,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }


            Text(
                text = data?.niceDate ?: "",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .constrainAs(time) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(title.top)
                    })

            Text(
                text = Html.fromHtml(data?.title ?: "Title", HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString(),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(
                text = "分类: " + data?.superChapterName + "/" + data?.chapterName,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .constrainAs(chapter) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(title.bottom)
                    })

        }
    }


}

@Composable
fun Praise(modifier: Modifier = Modifier, isPraise: Boolean = false, onClick: () -> Unit) {
    var isPraiseFlag by remember {
        mutableStateOf(isPraise)
    }
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        if (isPraiseFlag) {
            FaIcon(faIcon = FaIcons.Heart, tint = Color.Red)

        } else {
            FaIcon(
                faIcon = FaIcons.HeartRegular,
                tint = Color.Red,
            )
        }
    }
}


@Composable
fun showPalletMenu(
    showPalletMenu: MutableState<Boolean> = mutableStateOf(false),
    onPalletChange: (ColorPallet) -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopEnd)
    ) {

        DropdownMenu(expanded = showPalletMenu.value, onDismissRequest = {
            showPalletMenu.value = !showPalletMenu.value
        }) {
            ColorPallet.values().forEach {
                DropdownMenuItem(onClick = {
                    showPalletMenu.value = false
                    onPalletChange.invoke(it)
                }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        tint = it.color
                    )
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(start = 10.dp),
                        color = it.color
                    )
                }
            }
        }
    }


}