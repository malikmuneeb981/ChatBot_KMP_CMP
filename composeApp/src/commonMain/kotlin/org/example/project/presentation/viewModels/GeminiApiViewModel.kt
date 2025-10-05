package org.example.project.presentation.viewModels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.useCases.ChatWithGeminiUseCase

class GeminiApiViewModel(private val chatWithGeminiUseCase: ChatWithGeminiUseCase) :
    BaseViewModel() {

    private val _geminiChatResponse = MutableStateFlow<String>("")
    val geminiChatResponse = _geminiChatResponse.asStateFlow()


    fun chatWithGemini(prompt: String) = viewModelScope.launch {
        val response = chatWithGeminiUseCase.invoke(prompt = prompt)
        _geminiChatResponse.emit(response)
    }

}