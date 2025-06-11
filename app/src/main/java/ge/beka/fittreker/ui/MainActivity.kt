package ge.beka.fittreker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// local theme
import ge.beka.fittreker.ui.FitTrekerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitTrekerTheme {
                AppScaffold()
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Templates : BottomNavItem("templates", Icons.Default.List, "Шаблоны")
    object Sessions : BottomNavItem("sessions", Icons.Default.Event, "Сессии")
    object Exercises : BottomNavItem("exercises", Icons.Default.FitnessCenter, "Упражнения")
    object Participants : BottomNavItem("participants", Icons.Default.Person, "Участники")
}

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavigationGraph(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Templates,
        BottomNavItem.Sessions,
        BottomNavItem.Exercises,
        BottomNavItem.Participants
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Templates.route, modifier = modifier.fillMaxSize()) {
        composable(BottomNavItem.Templates.route) { SimpleScreen("Шаблоны") }
        composable(BottomNavItem.Sessions.route) { SimpleScreen("Сессии") }
        composable(BottomNavItem.Exercises.route) { SimpleScreen("Упражнения") }
        composable(BottomNavItem.Participants.route) { SimpleScreen("Участники") }
    }
}

@Composable
fun SimpleScreen(title: String) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = title)
    }
}

*** End of File ***
