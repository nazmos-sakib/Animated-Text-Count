package com.example.animatedcountertext

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun AnimatedCounter(
    count:Int,
    modifier: Modifier = Modifier,
    style:TextStyle = MaterialTheme.typography.bodyLarge
) {
    var oldCount by remember {
        mutableIntStateOf(count)
    }

    //after the recomposition the oldCount will be updated
    //SideEffect is called on every recomposition
    SideEffect {
        oldCount = count
    }

    Row(modifier) {
        val countString = count.toString()
        val oldCountString = oldCount.toString()
        for(i in countString.indices){
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if(oldChar==newChar){
                oldCountString[i]
            }else{
                countString[i]
            }

            AnimatedContent(
                targetState = char,
                label = "",
                transitionSpec = {
                    slideInVertically { it } togetherWith
                            slideOutVertically { -it }
                }
            ) {
                Text(text = char.toString(), style = style, softWrap = false)
            }
        }

    }

}