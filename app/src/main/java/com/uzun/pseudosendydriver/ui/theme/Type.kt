package com.uzun.pseudosendydriver.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.uzun.pseudosendydriver.R
import javax.annotation.concurrent.Immutable

val NotoSansKR = FontFamily(
    Font(R.font.notosanskr_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.notosanskr_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.notosanskr_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.notosanskr_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.notosanskr_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.notosanskr_thin, FontWeight.Thin, FontStyle.Normal),
)

fun getNotoSansTextStyle(
    fontSize : TextUnit,
    weight: FontWeight = FontWeight.Normal,
    color: Color = DayGrayscale100
) = TextStyle(
    fontFamily = NotoSansKR,
    fontWeight = weight,
    fontSize = fontSize,
    platformStyle = PlatformTextStyle(includeFontPadding = false),
    color = color
)

@Immutable
data class SendyTypography(
    val XXXLBold : TextStyle = getNotoSansTextStyle(32.sp, FontWeight.Bold),
    val XXXL : TextStyle = getNotoSansTextStyle(32.sp),
    val XXLBold : TextStyle = getNotoSansTextStyle(28.sp, FontWeight.Bold),
    val XXL : TextStyle = getNotoSansTextStyle(28.sp),
    val XLBold : TextStyle = getNotoSansTextStyle(24.sp, FontWeight.Bold),
    val XL : TextStyle = getNotoSansTextStyle(24.sp),
    val LargeBold : TextStyle = getNotoSansTextStyle(20.sp, FontWeight.Bold),
    val Large : TextStyle = getNotoSansTextStyle(20.sp),
    val MediumBold : TextStyle = getNotoSansTextStyle(18.sp, FontWeight.Bold),
    val Medium : TextStyle = getNotoSansTextStyle(18.sp),
    val NormalBold : TextStyle = getNotoSansTextStyle(16.sp, FontWeight.Bold),
    val Normal : TextStyle = getNotoSansTextStyle(16.sp),
    val SmallBold : TextStyle = getNotoSansTextStyle(14.sp, FontWeight.Bold),
    val Small : TextStyle = getNotoSansTextStyle(14.sp),
    val XSBold : TextStyle = getNotoSansTextStyle(13.sp, FontWeight.Bold),
    val XS : TextStyle = getNotoSansTextStyle(13.sp),
    val XXSBold : TextStyle = getNotoSansTextStyle(12.sp, FontWeight.Bold),
    val XXS : TextStyle = getNotoSansTextStyle(12.sp),
)

val sendyTypography = SendyTypography()