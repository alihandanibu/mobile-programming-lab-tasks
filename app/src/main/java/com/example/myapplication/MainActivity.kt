package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.screens.QuestsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.screens.StateBasicsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                StateBasicsScreen()
            }
        }
    }
}