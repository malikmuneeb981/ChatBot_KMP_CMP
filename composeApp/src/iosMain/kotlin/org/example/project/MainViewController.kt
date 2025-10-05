package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.di.KoinApplicationInit

fun MainViewController() = ComposeUIViewController { KoinApplicationInit()}