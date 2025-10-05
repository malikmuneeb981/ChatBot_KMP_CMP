package org.example.project.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.example.project.presentation.viewModels.GeminiApiViewModel

@Composable
fun ChatBotScreen(geminiApiViewModel: GeminiApiViewModel) {

    val geminiResponse = geminiApiViewModel.geminiChatResponse.collectAsState()
    var prompt by remember { mutableStateOf("") }
    SideEffect {
        println(geminiResponse.value)
    }
    LaunchedEffect(Unit){
       // geminiApiViewModel.chatWithGemini("Hi")
    }

    Scaffold {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {

            Text(text = geminiResponse.value, modifier = Modifier.fillMaxWidth().weight(1f),
                textAlign = TextAlign.Center)

            Row(modifier = Modifier.fillMaxWidth()) {

                TextField(value = prompt, onValueChange = {
                    prompt = it
                }, modifier = Modifier.weight(1f))

                Button(onClick = {
                    geminiApiViewModel.chatWithGemini(prompt)
                }, content = {
                    Text("Send")
                })

            }




        }

    }


}