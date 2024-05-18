package com.monkeybased.hearthtechnicalexercise.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.monkeybased.hearthtechnicalexercise.R
import com.monkeybased.hearthtechnicalexercise.data.model.NavBarFeature
import com.monkeybased.hearthtechnicalexercise.ui.theme.DarkLava
import com.monkeybased.hearthtechnicalexercise.ui.theme.HearthstoneBlueButton
import com.monkeybased.hearthtechnicalexercise.ui.theme.HearthstoneWhite
import com.monkeybased.hearthtechnicalexercise.ui.viewmodel.CardViewModel

enum class HearthstoneScreen(val labelResId: Int) {
    CLASS_LIST(R.string.browse_by_class),
    SET_LIST(R.string.browse_by_set),
    SEARCH(R.string.global_search),
    CARD_LIST(R.string.global_cards),
    CARD_DETAIL(R.string.card_details)
}

@Composable
@Preview
fun MainScreen(viewModel: CardViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
    val bottomNavItems = listOf(
        NavBarFeature.Class,
        NavBarFeature.Set,
        NavBarFeature.Search
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = HearthstoneScreen.valueOf(backStackEntry?.destination?.route ?: HearthstoneScreen.CLASS_LIST.name)

    Scaffold(
        backgroundColor = Color.LightGray,
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            BottomNavBar(navController, bottomNavItems)
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            MainScreenNavigationConfig(viewModel, navController)
        }
    }
}

@Composable
private fun MainScreenNavigationConfig(viewModel: CardViewModel, navController: NavHostController) {
    val cardsList by viewModel.cards.observeAsState()
    val uiState by viewModel.uiState.collectAsState()
    val searchCardsList by viewModel.searchCards.observeAsState()
    NavHost(navController = navController, startDestination = NavBarFeature.Class.screen.name) {
        composable(HearthstoneScreen.CLASS_LIST.name) {
            ClassScreen(onClassSelected = {
                viewModel.fetchCardByClass(it)
                navController.navigate(HearthstoneScreen.CARD_LIST.name)
            })
        }
        composable(HearthstoneScreen.SET_LIST.name) {
            SetScreen {
                viewModel.fetchCardBySet(it)
                navController.navigate(HearthstoneScreen.CARD_LIST.name)
            }
        }
        composable(HearthstoneScreen.SEARCH.name) {
            SearchCardList(
                searchCards = searchCardsList,
                onSearchAction = { viewModel.fetchCardBySearch(it) },
                onCardSelected = {
                    viewModel.setSelectedCard(it)
                    navController.navigate(HearthstoneScreen.CARD_DETAIL.name)
                },
                onErrorLoadingCards = {}
            )
        }
        composable(HearthstoneScreen.CARD_LIST.name) {
            CardList(
                cards = cardsList,
                onCardSelected = {
                    viewModel.setSelectedCard(it)
                    navController.navigate(HearthstoneScreen.CARD_DETAIL.name)
                },
                onErrorLoadingCards = {
                    navController.popBackStack()
                }
            )
        }
        composable(HearthstoneScreen.CARD_DETAIL.name) {
            if (uiState.selectedCard != null) {
                CardDetails(card = uiState.selectedCard!!)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(currentScreen: HearthstoneScreen, canNavigateBack: Boolean, navigateUp: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = HearthstoneBlueButton
        ),
        title = { Text(text = stringResource(id = currentScreen.labelResId), color = HearthstoneWhite) },
        modifier = modifier.background(DarkLava),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.global_back),
                        tint = HearthstoneWhite
                    )
                }
            }
        }
    )
}

@Composable
fun BottomNavBar(navController: NavHostController, items: List<NavBarFeature>) {
    BottomNavigation(backgroundColor = HearthstoneBlueButton) {
        val currentRoute = currentRoute(navController)
        items.forEach { navBarFeature ->
            BottomNavigationItem(
                selected = currentRoute == navBarFeature.screen.name,
                onClick = {
                    if (currentRoute != navBarFeature.screen.name) {
                        navController.navigate(navBarFeature.screen.name)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = navBarFeature.iconResId),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = HearthstoneWhite
                    )
                },
                label = { Text(stringResource(id = navBarFeature.labelResId), textAlign = TextAlign.Center, color = HearthstoneWhite) }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}