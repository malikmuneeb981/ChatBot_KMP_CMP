package org.example.project.di

import dev.shreyaspatil.ai.client.generativeai.GenerativeModel
import org.example.project.data.apiServices.GeminiApiService
import org.example.project.data.apiServices.GeminiRepoImpl
import org.example.project.domain.repos.GeminiApiRepo
import org.example.project.domain.useCases.ChatWithGeminiUseCase
import org.example.project.presentation.viewModels.GeminiApiViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AppModule = module {
    single <GenerativeModel>{
        GenerativeModel(
            modelName = "gemini-2.0-flash",
            //TODO
            apiKey = "Api_Key"
        )
    }
    singleOf(::GeminiApiService)
    single<GeminiApiRepo>{ GeminiRepoImpl(get())}
    singleOf(::ChatWithGeminiUseCase)
}