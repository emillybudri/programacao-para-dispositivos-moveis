package com.example.meuperfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val android.content.Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(
            name = "preferencias"
        )

val NOME_USUARIO =
    stringPreferencesKey("nome_usuario")

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeuPerfil()
        }
    }
}

@Composable
fun MeuPerfil() {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var nome by remember {
        mutableStateOf("")
    }

    val nomeFlow = remember {
        context.dataStore.data.map { preferences ->
            preferences[NOME_USUARIO] ?: ""
        }
    }

    val nomeSalvo by nomeFlow.collectAsState(
        initial = ""
    )

    Column {

        Text("Meu Perfil")

        TextField(
            value = nome,
            onValueChange = {
                nome = it
            }
        )

        Button(
            onClick = {
                scope.launch {
                    context.dataStore.edit { preferences ->
                        preferences[NOME_USUARIO] = nome
                    }
                }
            }
        ) {
            Text("Salvar")
        }

        Text("Nome salvo: $nomeSalvo")
    }
}
