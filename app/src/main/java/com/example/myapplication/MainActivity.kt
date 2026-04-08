package com.example.myapplication

import kotlin.math.round
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.screens.QuestsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runAllLab2Tasks()
        setContent {
            MyApplicationTheme {
                QuestsScreen()
            }
        }
    }
}