package com.main.moviebrowse_kmp.views.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.main.moviebrowse_kmp.models.Movie
import moviebrowse_kmp.composeapp.generated.resources.Res
import moviebrowse_kmp.composeapp.generated.resources.oswald_semi_bold
import org.jetbrains.compose.resources.Font

@ExperimentalAnimationSpecApi
@OptIn(markerClass = [ExperimentalSharedTransitionApi::class])
@Composable
actual fun SharedTransitionScope.MovieInfoPage(
    posterPath: String?,
    id: String,
    title: String,
    overview: String,
    releaseDate: String,
    originalLanguage: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    floatingActionButtonClick: () -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = floatingActionButtonClick,
            backgroundColor = Color.Black.copy(alpha = 0.5f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                null, tint = Color.White
            )
        }
    }) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${posterPath}"),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 25.dp)
                        .aspectRatio(9 / 16f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${id}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ -> tween(500) }),
                    contentScale = ContentScale.Fit,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 19.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        ),
                        fontFamily = FontFamily(
                            Font(Res.font.oswald_semi_bold, FontWeight.Normal)
                        ),
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "title/${id}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { initialBounds, targetBounds ->
                                keyframes {
                                    durationMillis = 500
                                    initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
                                    targetBounds at 500
                                }
                            })
                    )
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(bottom = 15.dp, start = 20.dp, end = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(17.dp)
                ) {
                    Text(
                        text = overview,
                        style = TextStyle(color = Color.LightGray, fontSize = 15.sp),
                        lineHeight = 23.sp
                    )
                    Text(
                        text = "Release date: $releaseDate",
                        style = TextStyle(color = Color.LightGray)
                    )
                    Text(
                        text = "Language: ${originalLanguage.uppercase()}",
                        style = TextStyle(color = Color.LightGray)
                    )
                }
            }
        }
    }

}