package com.example.mobileportofolio.present.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CardHistory(date: String, nominal:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(80.dp)
    ){
        Column(
            modifier = Modifier.padding(10.dp)
        )  {
            Text(
                text = "Date : $date",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Rp. $nominal",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

