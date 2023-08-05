package com.jamesco.littlelemon.ui.theme

import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jamesco.littlelemon.R

// Set of Material typography styles to start with
val karlaFamily = FontFamily(
     Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_bold, FontWeight.Bold),
    Font(R.font.karla_medium, FontWeight.Medium)
)
val markaziTextFamily = FontFamily(
    Font(R.font.markazi_text_regular, FontWeight.Normal)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val karlaTypography = Typography(
    defaultFontFamily = karlaFamily,
    caption = TextStyle(
        Color.Black,
        fontSize =  18.sp,
        fontWeight = FontWeight.Medium
    ),
    subtitle1 = TextStyle(
        Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    subtitle2 = TextStyle(
        Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    body1 =  TextStyle(
        Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    body2 = TextStyle(
        Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )

)

val marzakiTypography = Typography(
    markaziTextFamily,
    h1 = TextStyle(
        Primary2,
        fontSize = 64.sp,
        fontWeight = FontWeight.Normal
    ),
    subtitle1 = TextStyle(
        Color.White,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    )
)