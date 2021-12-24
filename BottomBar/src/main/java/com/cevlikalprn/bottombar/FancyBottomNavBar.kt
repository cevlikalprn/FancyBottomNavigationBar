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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cevlikalprn.bottombar.ui.theme.DefCenterItemColor

@ExperimentalAnimationApi
@Composable
fun FancyBottomNavigation(
    navController: NavController,
    bottomNavItemList: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    centerItemIcon: ImageVector,
    centerItemBackgroundColor: Color = DefCenterItemColor,
    bottomNavElevation: Dp = 12.dp,
    bottomNavBackgroundColor: Color = Color.White,
    centerItemOnClick: () -> Unit,
    navItemOnClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

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
                        selectedItemColor = Color.Blue,
                        unSelectedItemColor = Color.Black,
                        onClick = navItemOnClick
                    )
                }
            }
        }

        CompositionLocalProvider(LocalRippleTheme provides CenterItemRippleTheme) {
            FancyCenterItem(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(centerItemBackgroundColor)
                    .size(62.dp)
                    .align(Alignment.TopCenter),
                centerItemIcon = centerItemIcon,
                centerItemOnClick = centerItemOnClick
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
    onClick: (BottomNavItem) -> Unit,
) {

    BottomNavigationItem(
        selected = isItemSelected,
        onClick = { onClick(selectedItem) },
        selectedContentColor = selectedItemColor,
        unselectedContentColor = unSelectedItemColor,
        icon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = if (isItemSelected) Modifier
                    .background(Color.Blue.copy(alpha = 0.05f), shape = CircleShape)
                    .padding(8.dp) else Modifier
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