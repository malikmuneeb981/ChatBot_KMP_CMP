package org.example.project.data.apiServices

import dev.shreyaspatil.ai.client.generativeai.GenerativeModel


class GeminiApiService(private val  generativeModel: GenerativeModel) {
    suspend fun getGeminiResponse(prompt: String): String{
        try {
            val response = generativeModel.generateContent(prompt)
            return response.text.toString()
        } catch (e: Exception) {
            return  "Sorry Cant Perform This... ${e.toString()}"
        }
    }
}