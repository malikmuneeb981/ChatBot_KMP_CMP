package org.example.project.domain.useCases

import org.example.project.domain.repos.GeminiApiRepo

class ChatWithGeminiUseCase(private val geminiApiRepo: GeminiApiRepo) {

    suspend operator fun invoke(prompt: String) = geminiApiRepo.getGeminiResponse(prompt = prompt)
}