package org.example.project.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import chatbot.composeapp.generated.resources.Res
import chatbot.composeapp.generated.resources.ic_send
import org.example.project.domain.models.GeminiChatModel
import org.example.project.presentation.composables.LeftChat
import org.example.project.presentation.composables.RightChat
import org.example.project.presentation.viewModels.GeminiApiViewModel
import org.example.project.utils.Baseresponse
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChatBotScreen(geminiApiViewModel: GeminiApiViewModel) {
    val geminiResponse by geminiApiViewModel.geminiChatResponse.collectAsState()
    var prompt by remember { mutableStateOf("") }
    val chatList = remember { mutableStateListOf<GeminiChatModel>() }

    when(geminiResponse){
        is Baseresponse.Error<*> -> {
            val geminiResponse = geminiResponse as Baseresponse.Error
            chatList.add(GeminiChatModel(from = "Gemini", message = geminiResponse.errormessage.toString()))
        }
        is Baseresponse.InitState<*> -> {

        }
        is Baseresponse.Loading<*> -> {

        }
        is Baseresponse.Success<*> -> {
            val geminiResponse = geminiResponse as Baseresponse.Success
            chatList.add(GeminiChatModel(from = "Gemini", message = geminiResponse.data.toString()))
        }
    }


    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            // Chat messages
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chatList) { currentChat ->
                    if (currentChat.from == "User") {
                        RightChat(currentChat)
                    } else {
                        LeftChat(currentChat)
                    }
                }
            }

            // Input box and send button
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TextField(
                    value = prompt,
                    onValueChange = { prompt = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    placeholder = { Text("Ask something...") }
                )

                Box(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(35.dp))
                        .clip(RoundedCornerShape(35.dp))
                        .clickable {
                            if (prompt.isNotBlank()) {
                                // Add user message
                                chatList.add(GeminiChatModel(from = "User", message = prompt))

                                // Call API
                                geminiApiViewModel.chatWithGemini(prompt)

                                // Clear prompt
                                prompt = ""
                            }
                        }
                        .padding(7.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_send),
                        contentDescription = "Send",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ChatBotScreenPrev() {
   // ChatBotScreen(geminiApiViewModel = FakeGeminiViewModel())
}


