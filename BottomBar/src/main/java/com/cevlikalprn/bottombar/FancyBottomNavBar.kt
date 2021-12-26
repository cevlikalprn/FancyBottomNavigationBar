package com.cevlikalprn.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    centerItemBackgroundColor: Color = if (isSystemInDarkTheme()) CenterItemBackgroundColorDark else CenterBackgroundItemColorLight,
    centerItemContentColor: Color = CenterItemContentColor,
    bottomNavBackgroundColor: Color = if (isSystemInDarkTheme()) BottomNavBackgroundColorDark else BottomNavBackgroundColorLight,
    bottomNavItemColor: Color = if (isSystemInDarkTheme()) UnSelectedItemColorDark else UnSelectedItemColorLight,
    bottomNavElevation: Dp = BOTTOM_NAV_ELEVATION,
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
                        unSelectedItemColor = bottomNavItemColor,
                        navItemOnClick = clicks.first
                    )
                }
            }
            FancyCenterItem(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                centerItemIcon = centerItemIcon,
                contentColor = centerItemContentColor,
                backgroundColor = centerItemBackgroundColor,
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
                    .background(NavItemBackgroundColor, shape = CircleShape)
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
    contentColor: Color,
    backgroundColor: Color,
    centerItemOnClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides CenterItemRippleTheme) {
        FloatingActionButton(
            modifier = modifier,
            onClick = (centerItemOnClick),
            contentColor = contentColor,
            backgroundColor = backgroundColor
        ) {
            Icon(
                imageVector = centerItemIcon,
                contentDescription = "fab button"
            )
        }
    }
}