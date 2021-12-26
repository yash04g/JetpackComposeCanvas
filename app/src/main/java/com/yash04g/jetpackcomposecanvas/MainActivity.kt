package com.yash04g.jetpackcomposecanvas

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.graphics.Paint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.sp
import com.yash04g.jetpackcomposecanvas.ui.theme.JetpackComposeCanvasTheme

@Composable
fun DrawGradientText(name: String, modifier: Modifier = Modifier) {

//    val paint = Paint().asFrameworkPaint()
    val nudgeGradientList = listOf(
        Color(0xFFD9B031),
        Color(0xFFFFE490),
        Color(0xFFD9B031),
    )
//    val nudgeGradientList = listOf(
//        Color.Red,
//        Color.Blue,
//    )

    Canvas(modifier) {
        val gradientShader: Shader = LinearGradientShader(
            from = Offset(0f, 0f),
            to = Offset(180f, 0f),
            nudgeGradientList,
            tileMode = TileMode.Mirror
        )
//        paint.apply {
//            isAntiAlias = true
//            textSize = 50f
//            typeface = Typeface.DEFAULT_BOLD
//            style = android.graphics.Paint.Style.FILL
//            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
//        }
        val paint = Paint()
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.LEFT
        paint.textSize = 10.sp.toPx()
        paint.shader = gradientShader
        paint.typeface = Typeface.DEFAULT_BOLD

        drawIntoCanvas { canvas ->
//            canvas.save()
//            canvas.nativeCanvas.translate(2f, 5f)
//            canvas.nativeCanvas.drawText(name, 0f, 50f, paint)
//            canvas.restore()
//            paint.shader = gradientShader
//            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
//            canvas.nativeCanvas.drawText(name, 0f, 50f, paint)
//            canvas.nativeCanvas.translate(2f, 5f)
//            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
//            canvas.nativeCanvas.drawText(name, 0f, 50f, paint)
            canvas.nativeCanvas.drawText(name, 0f, 10.sp.toPx(), paint)
        }
//        paint.reset()
    }
}

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCanvasTheme {
                var showText by rememberSaveable {
                    mutableStateOf(true)
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                )
                {
                    Text("Hello World", fontSize = 10.sp, modifier = Modifier.onGloballyPositioned {
                        it.size.width
                    })

                    AnimatedVisibility(
                        visible = showText,
                        enter = expandHorizontally(animationSpec = tween(durationMillis = 1000)),
                        exit = shrinkHorizontally(animationSpec = tween(durationMillis = 1000)),
                    ) {
                        DrawGradientText(
                            name = "UPGRADE",
                            modifier = Modifier
                                .width(60.dp)
                                .height(10.dp)
                                .clickable {
                                    showText = false
                                }
                        )
                    }
                    DrawGradientText(
                        name = "SUBSCRIBE",
                        modifier = Modifier
                            .width(60.dp)
                            .height(10.dp)
                            .clickable {
                                showText = true
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeCanvasTheme {
        Greeting("Android")
    }
}