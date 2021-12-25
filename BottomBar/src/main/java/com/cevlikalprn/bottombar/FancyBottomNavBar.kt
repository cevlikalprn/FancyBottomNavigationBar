package com.cevlikalprn.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cevlikalprn.bottombar.ui.theme.*

@ExperimentalAnimationApi
@Composable
fun FancyBottomNavigation(
    navController: NavController,
    bottomNavItemList: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    centerItemIcon: ImageVector,
    centerItemBackgroundColor: Color = CenterItemColor,
    bottomNavElevation: Dp = BOTTOM_NAV_ELEVATION,
    bottomNavBackgroundColor: Color = BottomNavBackgroundColor,
    clicks: Pair<(BottomNavItem) -> Unit, () -> Unit>
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(BOX_HEIGHT),
            contentAlignment = Alignment.BottomCenter
        ) {

            BottomNavigation(
                modifier = modifier
                    .fillMaxWidth(),
                backgroundColor = bottomNavBackgroundColor,
                elevation = bottomNavElevation
            ) {
                bottomNavItemList.forEach { item ->

                    val isItemSelected = item.route == currentRoute

                    FancyBottomNavigationItem(
                        selectedItem = item,
                        isItemSelected = isItemSelected,
                        selectedItemColor = SelectedItemColor,
                        unSelectedItemColor = UnSelectedItemColor,
                        navItemOnClick = clicks.first
                    )
                }
            }
            FancyCenterItem(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(centerItemBackgroundColor)
                    .size(CENTER_ITEM_SIZE)
                    .align(Alignment.TopCenter),
                centerItemIcon = centerItemIcon,
                centerItemOnClick = clicks.second
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun RowScope.FancyBottomNavigationItem(
    selectedItem: BottomNavItem,
    isItemSelected: Boolean,
    selectedItemColor: Color,
    unSelectedItemColor: Color,
    navItemOnClick: (BottomNavItem) -> Unit,
) {

    BottomNavigationItem(
        selected = isItemSelected,
        onClick = { navItemOnClick(selectedItem) },
        selectedContentColor = selectedItemColor,
        unselectedContentColor = unSelectedItemColor,
        icon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(SMALL_GAP),
                modifier = if (isItemSelected) Modifier
                    .background(Color.Blue.copy(alpha = 0.05f), shape = CircleShape)
                    .padding(MEDIUM_PADDING) else Modifier
            ) {
                Icon(
                    imageVector = selectedItem.icon,
                    contentDescription = selectedItem.title
                )
                AnimatedVisibility(visible = isItemSelected) {
                    Text(
                        text = selectedItem.title,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}

@Composable
fun FancyCenterItem(
    modifier: Modifier,
    centerItemIcon: ImageVector,
    centerItemOnClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides CenterItemRippleTheme) {
        IconButton(
            modifier = modifier,
            onClick = (centerItemOnClick)
        ) {
            Icon(
                imageVector = centerItemIcon,
                contentDescription = "button"
            )
        }
    }
}