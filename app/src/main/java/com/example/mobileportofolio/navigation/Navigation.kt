package com.example.mobileportofolio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileportofolio.present.HomeScreen
import com.example.mobileportofolio.present.details.Others
import com.example.mobileportofolio.present.details.QrisPayment
import com.example.mobileportofolio.present.details.TarikTunai
import com.example.mobileportofolio.present.details.TopUp

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable(route = "home"){
            HomeScreen(navController = navController)
        }
        composable(route = "tariktunai"){
            TarikTunai(navController = navController)
        }
        composable(route = "qrispayment"){
            QrisPayment(navController = navController)
        }
        composable(route = "topup"){
            TopUp(navController = navController)
        }
        composable(route = "others"){
            Others(navController = navController)
        }
    }
}