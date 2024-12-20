package com.main.moviebrowse_kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.main.moviebrowse_kmp.di.appModule
import org.koin.core.context.startKoin


fun MainViewController() = ComposeUIViewController(configure = { startKoin { modules(appModule) } }) { App() }