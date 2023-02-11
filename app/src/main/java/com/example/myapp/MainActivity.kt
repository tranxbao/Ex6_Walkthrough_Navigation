package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.navigation.compose.composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                    ScaffoldApp()
                }
            }
        }
    }

@Composable
fun ScaffoldApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(route = "Home"){
            MainScreen(navController = navController)
        }
        composable(route = "Info"){
            InfoScreen(navController = navController)
        }
        composable(route = "Settings"){
            SettingScreen(navController = navController)
        }
    }
}



@Composable
fun MainTopBar(title:String, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = title)},
        actions = {
            IconButton(
                onClick = { 
                    expanded = !expanded
                }
            ) {
               Icon(Icons.Filled.MoreVert,contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded, 
                onDismissRequest = { expanded= false}) {
                DropdownMenuItem(onClick = { navController.navigate("info") }) {
                    Text(text = "Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings") }) {
                    Text(text = "Settings")
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar(title: String,navController: NavController){
    TopAppBar(
        title= { Text(text = title)},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController = navController )},
        content = { Text(text = "Content for Info screen")},
    )
}

@Composable
fun InfoScreen(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController = navController )},
        content = { Text(text = "Content for Info screen")},
    )
}

@Composable
fun SettingScreen(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController = navController)},
        content = { Text(text = "Content for Settings screen")},
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        ScaffoldApp()
    }
}