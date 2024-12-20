package com.main.moviebrowse_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.main.moviebrowse_kmp.di.appModule
import org.koin.core.context.GlobalContext.startKoin

fun main() {
    startKoin { modules(appModule) }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "MovieBrowse-KMP",
        ) {
            App()
        }
    }
}