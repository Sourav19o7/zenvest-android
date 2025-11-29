package com.zenvest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShowChart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenvest.feature_dashboard.presentation.screens.debt.DebtScreen
import com.zenvest.feature_dashboard.presentation.screens.emergency.EmergencyFundScreen
import com.zenvest.feature_dashboard.presentation.screens.goals.GoalsScreen
import com.zenvest.feature_dashboard.presentation.screens.help.HelpScreen
import com.zenvest.feature_dashboard.presentation.screens.home.DashboardHomeScreen
import com.zenvest.feature_dashboard.presentation.screens.investments.InvestmentsScreen
import com.zenvest.feature_dashboard.presentation.screens.profile.ProfileScreen
import com.zenvest.feature_dashboard.presentation.screens.settings.SettingsScreen
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.DebtViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.EmergencyFundViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.GoalsViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.InvestmentsViewModel

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun DashboardScaffold(
    onLogout: () -> Unit
) {
    val navItems = listOf(
        BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("Goals", Icons.Filled.Flag, Icons.Outlined.Flag),
        BottomNavItem("Invest", Icons.Filled.ShowChart, Icons.Outlined.ShowChart),
        BottomNavItem("Debt", Icons.Filled.AccountBalance, Icons.Outlined.AccountBalance),
        BottomNavItem("More", Icons.Filled.Person, Icons.Outlined.Person)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    var moreMenuSelection by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            if (index == 4) moreMenuSelection = 0
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedIndex) {
            0 -> {
                val viewModel: DashboardViewModel = hiltViewModel()
                DashboardHomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            }
            1 -> {
                val viewModel: GoalsViewModel = hiltViewModel()
                GoalsScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            }
            2 -> {
                val viewModel: InvestmentsViewModel = hiltViewModel()
                InvestmentsScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            }
            3 -> {
                val viewModel: DebtViewModel = hiltViewModel()
                DebtScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            }
            4 -> {
                MoreMenuScreen(
                    selectedItem = moreMenuSelection,
                    onItemSelected = { moreMenuSelection = it },
                    onLogout = onLogout,
                    paddingValues = paddingValues
                )
            }
        }
    }
}

@Composable
private fun MoreMenuScreen(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    onLogout: () -> Unit,
    paddingValues: androidx.compose.foundation.layout.PaddingValues
) {
    when (selectedItem) {
        0 -> MoreMenuList(
            onItemSelected = onItemSelected,
            onLogout = onLogout,
            paddingValues = paddingValues
        )
        1 -> {
            val viewModel: EmergencyFundViewModel = hiltViewModel()
            EmergencyFundScreen(
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }
        2 -> ProfileScreen(
            onLogoutClick = onLogout,
            paddingValues = paddingValues
        )
        3 -> SettingsScreen(paddingValues = paddingValues)
        4 -> HelpScreen(paddingValues = paddingValues)
    }
}

@Composable
private fun MoreMenuList(
    onItemSelected: (Int) -> Unit,
    onLogout: () -> Unit,
    paddingValues: androidx.compose.foundation.layout.PaddingValues
) {
    val menuItems = listOf(
        MoreMenuItem("Emergency Fund", Icons.Outlined.Savings, 1),
        MoreMenuItem("Profile", Icons.Outlined.Person, 2),
        MoreMenuItem("Settings", Icons.Outlined.Settings, 3),
        MoreMenuItem("Help", Icons.Outlined.Help, 4)
    )

    LazyColumn(
        modifier = Modifier.padding(paddingValues).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "More",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(16.dp))
        }

        items(menuItems.size) { index ->
            val item = menuItems[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemSelected(item.destination) },
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

private data class MoreMenuItem(
    val title: String,
    val icon: ImageVector,
    val destination: Int
)
