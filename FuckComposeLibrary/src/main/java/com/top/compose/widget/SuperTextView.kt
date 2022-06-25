package com.top.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


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