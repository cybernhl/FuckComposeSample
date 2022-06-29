package com.top.compose.sample.business.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIcons
import com.top.compose.sample.R
import com.top.compose.sample.ui.theme.AppThemeState
import com.top.compose.sample.ui.widget.EmptyScreen
import com.top.compose.sample.ui.widget.RotateIcon

@Composable
fun MainScreen(appThemeState: AppThemeState, modifier: Modifier) {

    //Default home screen state is always HOME
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.HOME) }
    val bottomNavBarContentDescription = stringResource(id = R.string.app_name)

    Column {
        HomeScreenContent(
            homeScreen = homeScreenState.value,
            appThemeState = appThemeState,
            modifier = modifier.weight(1f)
        )
        BottomNavigationContent(
            modifier = modifier
                .semantics { contentDescription = bottomNavBarContentDescription }
                .testTag("bottom_navigation"),
            homeScreenState = homeScreenState
        )
    }
}


@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: AppThemeState,
    modifier: Modifier
) {
    //WanAndroidClient.getApiUrl.login("", "")
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colors.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen("主页")
                    BottomNavType.SQUARE -> SquareScreen("广场")
                    BottomNavType.QUESTION_ANSWER -> EmptyScreen("问答")
                    BottomNavType.ME -> MeScreen()
                }
            }
        }
    }
}


@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }

    BottomNavigation(modifier  = Modifier.navigationBarsPadding()) {

        BottomNavigationItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.Home,
                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
            },
            label = { Text(text = stringResource(id = R.string.bottom_nav_home)) },
            modifier = Modifier.testTag("bottom_navigation_home")
        )

        BottomNavigationItem(
            icon = {
                RotateIcon(
                    state = animate,
                    faIcon = FaIcons.Square,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.SQUARE,
            onClick = {
                homeScreenState.value = BottomNavType.SQUARE
                animate=true
            },
            label = { Text(text = stringResource(id = R.string.bottom_nav_square)) },
            modifier = Modifier.testTag("bottom_navigation_square")
        )

        BottomNavigationItem(
            icon = {
                RotateIcon(
                    state = true,
                    faIcon = FaIcons.Question,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.QUESTION_ANSWER,
            onClick = {
                homeScreenState.value = BottomNavType.QUESTION_ANSWER
            },
            label = { Text(text = stringResource(id = R.string.bottom_nav_question_answer)) },
            modifier = Modifier.testTag("bottom_navigation_question_answer")
        )

        BottomNavigationItem(
            icon = {
                RotateIcon(
                    faIcon = FaIcons.User,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.ME,
            onClick = {
                homeScreenState.value = BottomNavType.ME
            },
            label = { Text(text = stringResource(id = R.string.bottom_nav_me)) },
            modifier = Modifier.testTag("bottom_navigation_me")
        )


    }
}


