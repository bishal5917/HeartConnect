package com.example.heartconnect.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun CustomAlertDialog() {
    AlertDialog(
        onDismissRequest = {

        },
        title = {
            CustomText(data = "Logout", fontSize = 16, fontWeight = FontWeight.W500)
        },
        text = {
            CustomText(
                data = "Are you sure that you want to log out ?",
                fontSize = 14,
                fontWeight = FontWeight.W300
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NormalButton(buttonText = "No") {
                }
                NormalButton(buttonText = "Yes", borderColor = Color.Red) {
                }
            }
        },
    )
}