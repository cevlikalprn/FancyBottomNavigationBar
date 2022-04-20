package com.cevlikalprn.fancybottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import com.cevlikalprn.bottombar.BottomNavItem

class BottomNavItemProvider private constructor() {

    companion object {
        fun provideBottomNavItemList() = listOf(
            BottomNavItem("home", "Home", Icons.Outlined.Home),
            BottomNavItem("chat", "Chat", Icons.Outlined.Notifications)
        )
    }
}