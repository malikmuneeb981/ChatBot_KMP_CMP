package org.example.project.commons

import org.example.project.presentation.viewModels.GeminiApiViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun viewModelModule(): Module {
   return module {
       singleOf(::GeminiApiViewModel)
   }
}