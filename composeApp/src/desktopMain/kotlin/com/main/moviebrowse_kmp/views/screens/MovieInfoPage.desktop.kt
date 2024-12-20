package com.main.moviebrowse_kmp.views.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
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
    Scaffold(
        backgroundColor = Color.Transparent,
        floatingActionButtonPosition = FabPosition.Start,
        floatingActionButton = {
            FloatingActionButton(
                onClick = floatingActionButtonClick,
                backgroundColor = Color.Black.copy(alpha = 0.5f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    null, tint = Color.White
                )
            }
        }) {
        Row(
            modifier = Modifier.fillMaxSize().padding(vertical = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${posterPath}"),
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 80.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(elevation = 40.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ -> tween(500) }),
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(
                    title, fontFamily = FontFamily(
                        Font(Res.font.oswald_semi_bold, FontWeight.Normal)
                    ), style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier.sharedBounds(
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
                Text(
                    overview, style = TextStyle(color = Color.LightGray, fontSize = 15.sp),
                    lineHeight = 23.sp
                )
                Spacer(Modifier.weight(1f))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Original language: ${originalLanguage.uppercase()}",
                        style = TextStyle(color = Color.LightGray)
                    )
                    Text("Release date: $releaseDate", style = TextStyle(color = Color.LightGray))
                }
            }
        }
    }
}