package com.example.controleviagens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// A tela, o modelo e o tema ficam em ControleViagens.kt
class ControleViagensActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControleViagensTema {
                ControleViagensScreen()
            }
        }
    }
}
