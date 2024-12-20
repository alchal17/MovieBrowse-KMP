package com.main.moviebrowse_kmp.views.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moviebrowse_kmp.composeapp.generated.resources.Res
import moviebrowse_kmp.composeapp.generated.resources.oswald_semi_bold
import org.jetbrains.compose.resources.Font


@Composable
fun MainTopBar() {
    TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                "Featured Films", fontSize = 35.sp, color = Color.LightGray, fontFamily = FontFamily(
                    Font(
                        Res.font.oswald_semi_bold,
                        FontWeight.Normal
                    )
                )
            )
        }
    }
}