package com.coda.oncescreens.features.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coda.oncescreens.R
import com.coda.oncescreens.components.AppIcon
import com.coda.oncescreens.components.AppIconButton
import com.coda.oncescreens.components.SpacerHeight
import com.coda.oncescreens.components.SpacerWidth
import com.coda.oncescreens.ui.theme.ChattingBg
import com.coda.oncescreens.ui.theme.ChattingBlack
import com.coda.oncescreens.ui.theme.ChattingDark
import com.coda.oncescreens.ui.theme.ChattingDarkGray
import com.coda.oncescreens.ui.theme.ChattingGray
import com.coda.oncescreens.ui.theme.ChattingLightGray
import com.coda.oncescreens.ui.theme.ChattingOrange
import com.coda.oncescreens.ui.theme.ChattingVoilet
import com.coda.oncescreens.ui.theme.interFont
import com.coda.oncescreens.ui.theme.montserratFont
import java.util.UUID

@Composable
fun ChattingScreen() {
    val lazyListState = rememberLazyListState()
    val chatList = remember {
        mutableStateListOf<ChattingModal>().apply {
            addAll(chattingModalList)
        }
    }
    LaunchedEffect(key1 = chatList.size) {
        lazyListState.scrollToItem(chatList.size)
    }

    ChattingRow(
        header = { ChattingHeaderRow() },
        type = {
            MessageType {
                chatList.add(it)
            }
        },
        chats = { chatLists(chatList.toList()) },
        lazyListState = lazyListState
    )
}


@Composable
private fun ChatEachRow(
    modifier: Modifier = Modifier,
    data: ChattingModal
) {
    Box(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        if (!data.isImage)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = if (data.isLeftDirection) Alignment.Start else Alignment.End
            ) {
                if (data.isJoined)
                    Text(
                        text = data.chat, style = TextStyle(
                            color = ChattingLightGray,
                            fontFamily = montserratFont,
                            fontSize = 13.sp
                        )
                    )
                else if (data.isLeftDirection)
                    Box {
                        Text(
                            text = data.chat, style = TextStyle(
                                color = Color.White,
                                fontFamily = montserratFont,
                                fontSize = 13.sp
                            ), modifier = Modifier
                                .drawBehind {
                                    drawRoundRect(
                                        color = ChattingGray,
                                        cornerRadius = CornerRadius(50.dp.toPx())
                                    )
                                }
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                        )
                        AppIcon(
                            icon = data.profilePic,
                            modifier = Modifier
                                .size(20.dp)
                                .offset(x = 10.dp, y = 5.dp)
                                .align(Alignment.BottomEnd)
                        )
                    }
                else
                    Text(
                        text = data.chat, style = TextStyle(
                            color = Color.White,
                            fontFamily = montserratFont,
                            fontSize = 13.sp
                        ), modifier = Modifier
                            .widthIn(max = 300.dp)
                            .drawBehind {
                                drawRoundRect(
                                    color = ChattingBlack,
                                    cornerRadius = CornerRadius(50.dp.toPx())
                                )
                            }
                            .padding(vertical = 8.dp, horizontal = 12.dp),
                        textAlign = TextAlign.End
                    )

            }

        if (data.isImage)
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppIcon(icon = R.drawable.close, modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = ChattingOrange,
                        )
                    }
                    .padding(5.dp))
                SpacerWidth()
                AppIcon(icon = R.drawable.arrow_down, modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = Color.White,
                        )
                    }
                    .padding(5.dp))
                SpacerWidth()
                Image(
                    painter = painterResource(id = R.drawable.girl_image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(130.dp)
                        .height(97.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }

    }
}


private fun LazyListScope.chatLists(
    data: List<ChattingModal>
) {
    item {
        SpacerHeight(35.dp)
        Text(
            text = stringResource(R.string._3_mar_13_34), style = TextStyle(
                fontSize = 12.sp,
                fontFamily = interFont,
                color = Color.White
            )
        )
    }
    items(data, key = { it.id }) {
        ChatEachRow(data = it)
    }
    item {
        SpacerHeight(20.dp)
    }

}


@Composable
private fun MessageType(
    modifier: Modifier = Modifier,
    onChatUpdate: (ChattingModal) -> Unit
) {
    var message by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        BasicTextField(
            value = message,
            onValueChange = { message = it },
            modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            textStyle = TextStyle(
                color = Color.White,
                fontFamily = montserratFont,
                fontSize = 14.sp
            ),
            cursorBrush = SolidColor(Color.White),
        ) {
            Row(
                modifier = Modifier
                    .background(
                        ChattingDark,
                        RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, top = 20.dp, bottom = 20.dp)
                        .weight(1f)
                ) {
                    if (message.isEmpty())
                        Text(
                            text = stringResource(R.string.write), style = TextStyle(
                                color = ChattingLightGray,
                                fontFamily = montserratFont,
                                fontSize = 14.sp
                            )
                        )
                    it.invoke()
                }
                if (message.isNotEmpty()) {
                    AppIconButton(
                        icon = R.drawable.message, tint = Color(0XFFC4C4C4), modifier = Modifier
                            .size(
                                48.dp
                            )
                            .drawBehind {
                                drawRoundRect(
                                    color = ChattingGray,
                                    cornerRadius = CornerRadius(10.dp.toPx())
                                )
                            }
                            .padding(5.dp)
                    ) {
                        onChatUpdate(
                            ChattingModal(
                                id = UUID.randomUUID().toString(),
                                chat = message,
                                isLeftDirection = false
                            )
                        )
                        message = ""
                        keyboardController?.hide()
                    }
                }
            }
        }

    }
}

@Composable
private fun ChattingRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    chats: (LazyListScope.() -> Unit)? = null,
    type: (@Composable () -> Unit)? = null,
    lazyListState: LazyListState
) {

    Column(
        modifier = modifier
            .background(ChattingBg)
            .fillMaxSize()
    ) {
        header?.invoke()
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = lazyListState
        ) {
            chats?.invoke(this)
        }
        type?.invoke()
    }
}

@Composable
private fun ChattingHeaderRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        modifier = modifier.padding(top = 24.dp)
    ) {
        item {
            SpacerWidth(20.dp)
        }
        items( storyModalList, key = {
            it.id
        }) {
            AppIcon(
                icon = it.icon,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .offset(x = 0.dp, y = if (it.id % 2 == 0) (10.dp) else 0.dp)
                    .size(50.dp)
                    .drawBehind {
                        drawCircle(
                            color = it.bg
                        )
                    }
                    .padding(2.dp))
        }
    }
}

private data class ChattingModal(
    val id: String,
    val chat: String,
    @DrawableRes val profilePic: Int = R.drawable.girl_1,
    val isLeftDirection: Boolean,
    val isImage: Boolean = false,
    val isJoined: Boolean = false
)

private val chattingModalList = listOf(
    ChattingModal(
        "1", "Anybody affected by coronavirus?",
        R.drawable.girl_1,
        isLeftDirection = true
    ),
    ChattingModal(
        "2", "At out office 3 ppl are infected. We work from home.",
        R.drawable.girl_1,
        isLeftDirection = false
    ),
    ChattingModal(
        "3", "All good here. We wash hands and stay home.",
        R.drawable.girl_2,
        isLeftDirection = true
    ),
    ChattingModal(
        "4", "All good here. We wash hands and stay home.",
        R.drawable.girl_2,
        isLeftDirection = false,
        isImage = true
    ),
    ChattingModal(
        "5", "This is our new manager, She will join chat. Her name is Ola.",
        R.drawable.girl_1,
        isLeftDirection = false
    ),
    ChattingModal(
        "6", "Marissa joined.",
        R.drawable.girl_1,
        isLeftDirection = true,
        isJoined = true
    ),
    ChattingModal(
        "7", "Hello everybody! I’m Ola.",
        R.drawable.girl_3,
        isLeftDirection = true
    ),
    ChattingModal(
        "8", "Hi Ola!",
        R.drawable.girl,
        isLeftDirection = true
    ),
    ChattingModal(
        "9", "All good here. We wash hands and stay home.",
        R.drawable.girl_2,
        isLeftDirection = true
    ),
    ChattingModal(
        "10", "All good here. We wash hands and stay home.",
        R.drawable.girl_2,
        isLeftDirection = false,
        isImage = true
    ),
    ChattingModal(
        "11", "This is our new manager, She will join chat. Her name is Ola.",
        R.drawable.girl_1,
        isLeftDirection = false
    ),
    ChattingModal(
        "12", "Anybody affected by coronavirus?",
        R.drawable.girl_1,
        isLeftDirection = true
    ),
    ChattingModal(
        "13", "At out office 3 ppl are infected. We work from home.",
        R.drawable.girl_1,
        isLeftDirection = false
    ),
    ChattingModal(
        "14", "All good here. We wash hands and stay home.",
        R.drawable.girl_2,
        isLeftDirection = true
    ),
)

data class StoryModal(
    val id: Int,
    @DrawableRes val icon: Int,
    val bg: Color
)

val storyModalList = listOf(
    StoryModal(
        1,
        R.drawable.man,
        ChattingVoilet
    ),
    StoryModal(
        2,
        R.drawable.girl,
        ChattingOrange
    ),
    StoryModal(
        3,
        R.drawable.girl_1,
        ChattingVoilet
    ),
    StoryModal(
        4,
        R.drawable.girl_2,
        ChattingOrange
    ),
    StoryModal(
        5,
        R.drawable.girl_3,
        ChattingDarkGray
    ),
    StoryModal(
        6,
        R.drawable.man,
        ChattingOrange
    ),
    StoryModal(
        7,
        R.drawable.girl_1,
        ChattingVoilet
    ),
    StoryModal(
        8,
        R.drawable.girl_2,
        ChattingOrange
    ),
)