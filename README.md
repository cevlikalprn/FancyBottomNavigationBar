# FancyBottomNavigationBar

A library that you can use for bottom navigation bar. Written with Jetpack Compose.

<h2> Tech Stack </h2>

* Kotlin
* Jetpack Compose
* Material Design

<h2> Pictures </h2>
<h3> Light Theme </h3>
<img src="https://user-images.githubusercontent.com/74617424/147411871-fbc3075e-11d4-4f9d-8f94-92b548bda8c2.png"/>
<h3> Dark Theme </h3>
<img src="https://user-images.githubusercontent.com/74617424/147411921-bde52117-c8c5-4220-a89a-9b70bcf7d851.png"/>


<h2>Gifs</h2>
<img src="https://user-images.githubusercontent.com/74617424/147406323-923fe863-4ad4-4490-b5d6-566a065024df.gif" width="400" height="800" />
<br>
<img src="https://user-images.githubusercontent.com/74617424/147406328-a2aed163-d4ae-42dc-8984-015c17b2671f.gif" width="400" height="800" />



<h2> Usage </h2>

```

FancyBottomNavigation(
    navController = navController,
    bottomNavItemList = bottomNavItemList,
    modifier = Modifier,
    centerItemIcon = Icons.Outlined.Share,
    centerItemBackgroundColor = Color.Magenta,
    centerItemContentColor = Color.White,
    bottomNavBackgroundColor = Color.White,
    bottomNavItemColor = Color.Black,
    bottomNavElevation = 8.dp,
    clicks = Pair(
        first = { bottomNavItem -> 
           navController.navigate(bottomNavItem.route)
        },
        second = {
           Log.i("TAG", "Center Item Clicked!")
        }
    )
)

```
