package org.example.project.domain.repos

interface GeminiApiRepo {

    suspend fun getGeminiResponse(prompt: String): String
}