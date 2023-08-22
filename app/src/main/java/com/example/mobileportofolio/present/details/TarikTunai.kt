package com.example.mobileportofolio.present.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobileportofolio.present.component.CardHistory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TarikTunai(
    navController: NavController
){
    Scaffold {
        Column(
            modifier = Modifier.padding(16.dp)
        )  {
            Text(
                text = "Tarik Tunai",
            style = MaterialTheme.typography.headlineSmall
            )
            CardHistory(date = "21/10/2023", nominal = "1.000.000")
            CardHistory(date = "20/10/2023", nominal = "500.000")
            CardHistory(date = "19/10/2023", nominal = "1.000.000")
        }
    }
}