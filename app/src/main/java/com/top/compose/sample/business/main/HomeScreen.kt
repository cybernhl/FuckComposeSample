package com.top.compose.sample.business.main

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIcons
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.business.viewmodel.WanAndroidViewModel
import com.top.compose.sample.ui.theme.Purple700
import com.top.compose.widget.GlideImage
import com.top.compose.widget.TopAppBarCenter
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    title: String,
    viewModel: WanAndroidViewModel = hiltViewModel()
) {

    val articles = viewModel.getArticleData().collectAsLazyPagingItems()

    val banner = viewModel.getBanner().collectAsState(initial = null)

    TopAppBarCenter(
        title = {
            Text(text = title, color = Color.White)
        },
        actions = {
            IconButton(onClick = {

            }) {
                FaIcon(faIcon = FaIcons.Search, tint = Color.White)
            }
        },
        backgroundColor = Purple700,
        isImmersive = true
    ) {
        Column {
            LazyColumn(modifier = Modifier.background(Color.LightGray), content = {
                itemsIndexed(articles) { index, data ->

                    if (index == 0) {
                        Banner(banner.value)
                    } else {
                        HomeArticle(data)
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
        if (pagerState.currentPage == Int.MAX_VALUE - 1) {
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
fun HomeArticle(data: Article?) {

    Card(
        modifier =
        Modifier
            .padding(horizontal = 14.dp, vertical = 4.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
    ) {
        ConstraintLayout {
            val (author, title, time, isPraise, chapter, topText, newText) = createRefs()

            Praise(
                modifier = Modifier
                    .constrainAs(isPraise) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                isPraise = data?.zan != 0
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
fun Praise(modifier: Modifier = Modifier, isPraise: Boolean = false) {
    var isPraiseFlag by remember {
        mutableStateOf(isPraise)
    }


    IconButton(
        onClick = { isPraiseFlag = !isPraiseFlag },
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