package com.top.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
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
    onClickRightIcon: () -> Unit = {},
    onClickItem: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .background(Color.White)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures {
                    onClickItem()
                }
            },
        horizontalArrangement = Arrangement.Start,
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

            val (leftIcon, leftTitle, centerTitle, rightTitle, rightIcon, bottomDivider) = createRefs()

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
            Box(modifier = Modifier.constrainAs(bottomDivider) {
                top.linkTo(parent.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                Spacer(
                    modifier = Modifier.fillMaxWidth(0.8f).height(1.dp).background(Color.LightGray)
                        .align(alignment = Alignment.Center)
                )
            }
        }
    }
}