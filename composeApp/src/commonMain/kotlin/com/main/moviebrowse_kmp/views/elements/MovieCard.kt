package com.main.moviebrowse_kmp.views.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.main.moviebrowse_kmp.models.Movie

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun SharedTransitionScope.MovieCard(
    movie: Movie,
    onClick: (
        posterPath: String?,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {


    var visible by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = visible, enter = scaleIn()) {
        Card(
            elevation = 16.dp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable {
                    onClick(
                        movie.posterPath,
                        movie.uniqueID,
                        movie.title,
                        movie.overview,
                        movie.releaseDate,
                        movie.originalLanguage
                    )
                },
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    AsyncImage(
                        contentDescription = "Movie poster",
                        model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${movie.uniqueID}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ -> tween(500) })
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 5.dp, top = 4.dp)
                            .clip(shape = RoundedCornerShape(50))
                            .background(color = Black.copy(alpha = 0.7f)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Yellow
                        )
                        Text(
                            text = "${movie.voteAverage.toString().slice(0..2)} / 10",
                            style = TextStyle(color = Color.White, fontSize = 13.sp),
                            modifier = Modifier
                        )
                    }
                }
                Text(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    text = movie.title,
                    style = (TextStyle(fontSize = 12.sp, color = Black)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 5.dp)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "title/${movie.uniqueID}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { initialBounds, targetBounds ->
                                keyframes {
                                    durationMillis = 500
                                    initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
                                    targetBounds at 500
                                }
                            }
                        )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        visible = true
    }
}

//@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
//@Composable
//fun  MovieCard(
//    movie: Movie,
//    onClick: () -> Unit,
//) {
//    var visible by remember { mutableStateOf(false) }
//
//    AnimatedVisibility(visible = visible, enter = scaleIn()) {
//        Card(
//            elevation = 16.dp,
//            modifier = Modifier
//                .padding(horizontal = 8.dp)
//                .clickable {
//                    onClick()
//                },
//        ) {
//            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                ) {
//
//                    AsyncImage(
//                        contentDescription = "Movie poster",
//                        model =
//                            "https://image.tmdb.org/t/p/w500${movie.posterPath}",
//
//                        contentScale = ContentScale.FillBounds,
//                        modifier = Modifier
//                            .fillMaxSize()
//                    )
//                    Row(
//                        modifier = Modifier
//                            .padding(start = 5.dp, top = 4.dp)
//                            .clip(shape = RoundedCornerShape(50))
//                            .background(color = Black.copy(alpha = 0.7f)),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(5.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = null,
//                            tint = Color.Yellow
//                        )
//                        Text(
//                            text = "${movie.voteAverage.toString().slice(0..2)} / 10",
//                            style = TextStyle(color = Color.White, fontSize = 13.sp),
//                            modifier = Modifier
//                        )
//                    }
//                }
//                Text(
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold,
//                    text = movie.title,
//                    style = (TextStyle(fontSize = 12.sp, color = Black)),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 4.dp, vertical = 5.dp)
//                )
//            }
//        }
//    }
//
//    LaunchedEffect(Unit) {
//        visible = true
//    }
//}
