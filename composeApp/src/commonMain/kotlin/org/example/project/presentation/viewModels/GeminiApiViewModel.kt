package org.example.project.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.useCases.ChatWithGeminiUseCase
import org.example.project.utils.Baseresponse

class GeminiApiViewModel(private val chatWithGeminiUseCase: ChatWithGeminiUseCase) :
    ViewModel() {

    private val _geminiChatResponse = MutableStateFlow<Baseresponse<String>>(Baseresponse.InitState())
    val geminiChatResponse = _geminiChatResponse.asStateFlow()


    fun chatWithGemini(prompt: String) = viewModelScope.launch {
        _geminiChatResponse.emit(Baseresponse.Loading())
        val response = chatWithGeminiUseCase.invoke(prompt = prompt)
        if (response.isEmpty()){
            _geminiChatResponse.emit(Baseresponse.Error("Something Went Wrong!"))
        }else{
            _geminiChatResponse.emit(Baseresponse.Success(response))
        }

    }

}