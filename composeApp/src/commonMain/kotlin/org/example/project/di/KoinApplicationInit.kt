package org.example.project.di

import androidx.compose.runtime.Composable
import org.example.project.commons.viewModelModule
import org.example.project.navigation.NavGraph
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

@Composable
fun KoinApplicationInit(){
    KoinApplication(application = {

        modules(AppModule,viewModelModule())
    }, content = {
        NavGraph()
    })
}