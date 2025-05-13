package com.nohjason.cheongfordo.screens.chat

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.nohjason.cheongfordo.screens.ui.titlebar.TitleBar
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.screens.chat.data.ChatViewModel
import com.nohjason.cheongfordo.screens.ui.text.MinariInputField


// --- 메인 채팅 화면 ---
@Composable
fun ChatScreen(
    naviController: NavController,
    viewModel: ChatViewModel = hiltViewModel()
) {
    // StateFlow 값 구독
    val chatList by viewModel.chatList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var inputText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // LazyColumn 스크롤 상태 저장
    val listState = rememberLazyListState()

    // 새로운 메시지가 추가되면 자동으로 스크롤 맨 아래로 이동
    LaunchedEffect(chatList.size) {
        if (chatList.isNotEmpty()) {
            listState.animateScrollToItem(chatList.size - 1)
        }
    }

    Scaffold(
        topBar = {
            TitleBar(
                imgResId = R.drawable.ic_back,
                title = "새로운 채팅",
                onClick = { naviController.popBackStack() }
            )
        },
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 5.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    MinariInputField(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(id = R.drawable.ic_send),
                        label = "무엇이든 물어봐주세요!",
                        onValueChange = { inputText = it },
                        onClickAction = {
                            if (inputText.isNotBlank() && !isLoading) {
                                viewModel.sendMessage(inputText)
                                inputText = ""
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            reverseLayout = false
        ) {
            items(chatList) { msg ->
                Spacer(Modifier.height(8.dp))
                if (msg.isUser) {
                    MinariChatText(msg.text)
                } else {
                    AiChatText(msg.text)
                }
            }
            if (isLoading) {
                item {
                    Spacer(Modifier.height(8.dp))
                    AiLoadingAnimation()
                }
            }
        }
    }
}


