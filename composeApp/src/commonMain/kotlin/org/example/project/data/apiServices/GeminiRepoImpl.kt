package org.example.project.data.apiServices

import org.example.project.domain.repos.GeminiApiRepo

class GeminiRepoImpl(private val geminiApiService: GeminiApiService): GeminiApiRepo {
    override suspend fun getGeminiResponse(prompt: String): String {
        return geminiApiService.getGeminiResponse(prompt)
    }
}