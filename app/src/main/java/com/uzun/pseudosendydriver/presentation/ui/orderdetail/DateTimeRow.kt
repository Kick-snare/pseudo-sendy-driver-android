package com.uzun.pseudosendydriver.presentation.ui.orderdetail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.model.SendyTime
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme

@Composable
fun TextWithIcon(
    @DrawableRes iconId: Int,
    text: String,
    textStyle: TextStyle = PseudoSendyTheme.typography.MediumBold,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    content = {
        Icon(painterResource(id = iconId), contentDescription = null)
        Spacer(Modifier.size(UIConst.SPACE_XXS))
        Text(text = text, style = textStyle)
    }
)

fun LazyListScope.dateTimeRow(
    dateTime: SendyTime,
) = item {
    BasicRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalPadding = UIConst.SPACE_M
    ) {
        TextWithIcon(
            iconId = R.drawable.icon_solid_calendar,
            text = dateTime.toStringOfPattern("MM.dd(${dateTime.getKoreanDay()})")
        )
        Text("/")
        TextWithIcon(
            iconId = R.drawable.icon_solid_clock,
            text = dateTime.toStringOfPattern("HH:mm 상차")
        )
    }
}