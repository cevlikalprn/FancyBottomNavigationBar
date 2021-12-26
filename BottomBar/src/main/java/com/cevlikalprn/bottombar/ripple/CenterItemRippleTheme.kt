package com.cevlikalprn.bottombar.ripple

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object CenterItemRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color =
        RippleTheme.defaultRippleColor(
            Color.White,
            lightTheme = true
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            defaultColor(),
            lightTheme = true
        )
}