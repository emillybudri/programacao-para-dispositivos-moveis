package com.example.controleviagens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Instância do DataStore via extensão do Context
val android.content.Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencias")

// Chave do DataStore
val NOME_MOTORISTA = stringPreferencesKey("nome_motorista")

class Viagem(
    val data: String,
    val kmInicial: Double,
    val kmFinal: Double,
    val litros: Double,
    val combustivel: String,
    val pedagio: Double,
    val valorCombustivel: Double,
    val id: Long = novoId()
) {
    fun calcularDistancia(): Double {
        return kmFinal - kmInicial
    }

    fun calcularCustoTotal(): Double {
        return valorCombustivel + pedagio
    }

    fun calcularKmL(): Double {
        return if (litros > 0) calcularDistancia() / litros else 0.0
    }

    companion object {
        private var ultimoId = 0L
        fun novoId() = ++ultimoId
    }
}

private val TIPOS_COMBUSTIVEL = listOf("Gasolina", "Etanol", "Diesel", "GNV")



private object Espaco {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 48.dp
    val alvoToque = 48.dp
    val larguraData = 44.dp

    /** Espaço livre no fim da lista para o FAB não cobrir o último item. */
    val folgaFab = 88.dp

    /** Largura máxima do conteúdo em telas grandes (tablet, paisagem). */
    val larguraMaxima = 600.dp
}

private val CoresClaras = lightColorScheme(
    primary = Color(0xFF2D4F7C),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD7E3F7),
    onPrimaryContainer = Color(0xFF0B2747),
    secondaryContainer = Color(0xFFDDE3EC),
    onSecondaryContainer = Color(0xFF1A2430),
    background = Color(0xFFF8F9FB),
    onBackground = Color(0xFF191C20),
    surface = Color(0xFFF8F9FB),
    onSurface = Color(0xFF191C20),
    surfaceVariant = Color(0xFFE1E4EA),
    onSurfaceVariant = Color(0xFF5A606B),
    surfaceContainer = Color(0xFFEEF1F5),
    surfaceContainerHigh = Color(0xFFE8EBF0),
    surfaceContainerLow = Color(0xFFF2F4F7),
    outline = Color(0xFF8A909B),
    outlineVariant = Color(0xFFDADEE4),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    inverseSurface = Color(0xFF2E3135),
    inverseOnSurface = Color(0xFFF0F1F4),
    inversePrimary = Color(0xFFA8C8F2)
)

private val CoresEscuras = darkColorScheme(
    primary = Color(0xFFA8C8F2),
    onPrimary = Color(0xFF0B3155),
    primaryContainer = Color(0xFF244569),
    onPrimaryContainer = Color(0xFFD7E3F7),
    secondaryContainer = Color(0xFF3B4552),
    onSecondaryContainer = Color(0xFFDDE3EC),
    background = Color(0xFF111316),
    onBackground = Color(0xFFE2E2E6),
    surface = Color(0xFF111316),
    onSurface = Color(0xFFE2E2E6),
    surfaceVariant = Color(0xFF42474F),
    onSurfaceVariant = Color(0xFFC2C7D0),
    surfaceContainer = Color(0xFF1D2024),
    surfaceContainerHigh = Color(0xFF272A2E),
    surfaceContainerLow = Color(0xFF191C1F),
    outline = Color(0xFF8C919A),
    outlineVariant = Color(0xFF3A3F46),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    inverseSurface = Color(0xFFE2E2E6),
    inverseOnSurface = Color(0xFF2E3135),
    inversePrimary = Color(0xFF2D4F7C)
)

private val Formas = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp)
)

/** Algarismos tabulares: valores alinham verticalmente na lista. */
private val TextStyle.numerico get() = copy(fontFeatureSettings = "tnum")

@Composable
fun ControleViagensTema(conteudo: @Composable () -> Unit) {
    val cores: ColorScheme = if (isSystemInDarkTheme()) CoresEscuras else CoresClaras
    val base = MaterialTheme.typography
    MaterialTheme(
        colorScheme = cores,
        shapes = Formas,
        typography = base.copy(
            headlineMedium = base.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
            titleLarge = base.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            titleMedium = base.titleMedium.copy(fontWeight = FontWeight.Medium),
            labelLarge = base.labelLarge.copy(fontWeight = FontWeight.SemiBold)
        ),
        content = conteudo
    )
}



private val localeBr: Locale = Locale.forLanguageTag("pt-BR")
private val formatoMoeda: NumberFormat = NumberFormat.getCurrencyInstance(localeBr)
private val formatoNumero: NumberFormat = NumberFormat.getNumberInstance(localeBr).apply {
    maximumFractionDigits = 1
}
private val MESES = listOf("jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez")

private fun Double.emReais(): String = formatoMoeda.format(this)
private fun Double.emKm(): String = "${formatoNumero.format(this)} km"
private fun Double.emKmL(): String = "${formatoNumero.format(this)} km/l"
private fun Double.emLitros(): String = "${formatoNumero.format(this)} L"

private val formatoCampo: NumberFormat = NumberFormat.getNumberInstance(localeBr).apply {
    isGroupingUsed = false
    maximumFractionDigits = 3
}

private val formatoCampoMoeda: NumberFormat = NumberFormat.getNumberInstance(localeBr).apply {
    isGroupingUsed = false
    minimumFractionDigits = 2
    maximumFractionDigits = 2
}

// Formatação para exibir números nos campos de texto
private fun Double.paraCampo(): String = formatoCampo.format(this)
private fun Double.paraCampoMoeda(): String = formatoCampoMoeda.format(this)

// Converte texto digitado para decimal (aceita ponto ou vírgula)
private fun String.paraDecimal(): Double? = trim().replace(',', '.').toDoubleOrNull()

private fun hojeEmDigitos(): String = SimpleDateFormat("ddMMyyyy", localeBr).format(Date())

private val anoAtual: Int by lazy { hojeEmDigitos().takeLast(4).toInt() }

private fun String.digitosParaData(): String = "${substring(0, 2)}/${substring(2, 4)}/${substring(4, 8)}"

private fun dataValida(digitos: String): Boolean {
    if (digitos.length != 8) return false
    val dia = digitos.substring(0, 2).toInt()
    val mes = digitos.substring(2, 4).toInt()
    return dia in 1..31 && mes in 1..12
}

private fun Viagem.partesData(): Triple<Int, Int, Int>? {
    val partes = data.split("/").mapNotNull { it.toIntOrNull() }
    return if (partes.size == 3) Triple(partes[0], partes[1], partes[2]) else null
}

private fun Viagem.chaveOrdenacao(): Int =
    partesData()?.let { (dia, mes, ano) -> ano * 10_000 + mes * 100 + dia } ?: 0

// Aplica a barra "/" automaticamente na data enquanto o usuário digita
private object MascaraData : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digitos = text.text
        val formatado = buildString {
            digitos.forEachIndexed { i, c ->
                append(c)
                if ((i == 1 || i == 3) && i < digitos.lastIndex) append('/')
            }
        }
        val mapeamento = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val t = when {
                    offset <= 1 -> offset
                    offset <= 3 -> offset + 1
                    else -> offset + 2
                }
                return t.coerceAtMost(formatado.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val o = when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    else -> offset - 2
                }
                return o.coerceIn(0, digitos.length)
            }
        }
        return TransformedText(AnnotatedString(formatado), mapeamento)
    }
}

private sealed interface Tela {
    data object Lista : Tela
    data class Formulario(val viagem: Viagem?) : Tela
}

@Composable
fun ControleViagensScreen() {
    val viagens = remember { mutableStateListOf<Viagem>() }
    var tela by remember { mutableStateOf<Tela>(Tela.Lista) }

    val snackbar = remember { SnackbarHostState() }
    val escopo = rememberCoroutineScope()

    fun avisar(mensagem: String) {
        escopo.launch {
            snackbar.currentSnackbarData?.dismiss()
            snackbar.showSnackbar(mensagem)
        }
    }

    BackHandler(enabled = tela is Tela.Formulario) { tela = Tela.Lista }

    Crossfade(targetState = tela, label = "tela") { atual ->
        when (atual) {
            Tela.Lista -> ListaViagens(
                viagens = viagens,
                snackbar = snackbar,
                onNovaViagem = { tela = Tela.Formulario(null) },
                onAbrirViagem = { tela = Tela.Formulario(it) }
            )

            is Tela.Formulario -> FormularioViagem(
                inicial = atual.viagem,
                onFechar = { tela = Tela.Lista },
                onSalvar = { viagem ->
                    val indice = viagens.indexOfFirst { it.id == viagem.id }
                    if (indice >= 0) {
                        viagens[indice] = viagem
                        avisar("Viagem atualizada")
                    } else {
                        viagens.add(viagem)
                        avisar("Viagem registrada")
                    }
                    tela = Tela.Lista
                },
                onExcluir = { viagem ->
                    viagens.removeAll { it.id == viagem.id }
                    avisar("Viagem excluída")
                    tela = Tela.Lista
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListaViagens(
    viagens: List<Viagem>,
    snackbar: SnackbarHostState,
    onNovaViagem: () -> Unit,
    onAbrirViagem: (Viagem) -> Unit
) {
    // Recalcula totais sempre que a lista de viagens muda
    val totalGasto = viagens.sumOf { it.calcularCustoTotal() }
    val totalKm = viagens.sumOf { it.calcularDistancia() }
    val totalLitros = viagens.sumOf { it.litros }
    val mediaKmL = if (totalLitros > 0) totalKm / totalLitros else 0.0

    // Mais recentes primeiro
    val ordenadas = viagens.sortedByDescending { it.chaveOrdenacao() }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val nomeFlow = remember {
        context.dataStore.data.map { preferences ->
            preferences[NOME_MOTORISTA] ?: ""
        }
    }
    // Começa com null: o cartão só aparece depois que o DataStore responde
    val nomeSalvo by nomeFlow.collectAsState(initial = null)

    fun salvarMotorista(nome: String) {
        scope.launch {
            context.dataStore.edit { preferences ->
                preferences[NOME_MOTORISTA] = nome
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Viagens") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNovaViagem,
                icon = { Icon(Icones.Adicionar, contentDescription = null) },
                text = { Text("Nova viagem") }
            )
        },
        snackbarHost = { SnackbarHost(snackbar) }
    ) { innerPadding ->

        if (viagens.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                nomeSalvo?.let { nome ->
                    CartaoMotorista(nome, { salvarMotorista(it) }, Modifier.limitarLargura())
                }
                EstadoVazio(Modifier.weight(1f))
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = Espaco.folgaFab),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            nomeSalvo?.let { nome ->
                item(key = "motorista") {
                    CartaoMotorista(nome, { salvarMotorista(it) }, Modifier.limitarLargura())
                }
            }

            item(key = "resumo") {
                ResumoViagens(
                    totalGasto = totalGasto,
                    totalKm = totalKm,
                    mediaKmL = mediaKmL,
                    quantidade = viagens.size,
                    modifier = Modifier.limitarLargura()
                )
            }

            item(key = "titulo") {
                Text(
                    text = "Histórico",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .limitarLargura()
                        .padding(start = Espaco.lg, end = Espaco.lg, top = Espaco.xl, bottom = Espaco.xs)
                )
            }

            items(ordenadas, key = { it.id }) { viagem ->
                Column(
                    Modifier
                        .limitarLargura()
                        .animateItem()
                ) {
                    ViagemItem(viagem = viagem, onClick = { onAbrirViagem(viagem) })
                    HorizontalDivider(
                        modifier = Modifier.padding(start = Espaco.lg + Espaco.larguraData + Espaco.lg),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

private fun Modifier.limitarLargura() = this
    .widthIn(max = Espaco.larguraMaxima)
    .fillMaxWidth()

@Composable
private fun CartaoMotorista(
    nomeSalvo: String,
    onSalvar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var editando by remember { mutableStateOf(false) }
    var nome by remember { mutableStateOf(nomeSalvo) }
    var erro by remember { mutableStateOf<String?>(null) }

    val cadastrado = nomeSalvo.isNotEmpty()
    // Sem nome salvo o campo já aparece para o cadastro
    val mostrarCampo = editando || !cadastrado

    fun salvar() {
        val nomeLimpo = nome.trim()
        if (nomeLimpo.isEmpty()) {
            erro = "Informe o nome do motorista"
        } else {
            onSalvar(nomeLimpo)
            editando = false
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = MaterialTheme.shapes.large,
        modifier = modifier.padding(horizontal = Espaco.lg, vertical = Espaco.sm)
    ) {
        Column(Modifier.padding(Espaco.lg)) {
            if (mostrarCampo) {
                Text(
                    text = if (cadastrado) "Alterar motorista" else "Bem-vindo! Quem está dirigindo?",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(Espaco.md))
                CampoTexto(
                    valor = nome,
                    onValorChange = {
                        nome = it
                        erro = null
                    },
                    rotulo = "Nome do motorista",
                    erro = erro,
                    tipoTeclado = KeyboardType.Text,
                    acaoTeclado = ImeAction.Done,
                    onConcluir = { salvar() },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(Espaco.sm))
                Row(horizontalArrangement = Arrangement.spacedBy(Espaco.sm)) {
                    Button(onClick = { salvar() }) {
                        Text(if (cadastrado) "Atualizar motorista" else "Salvar motorista")
                    }
                    if (cadastrado) {
                        TextButton(onClick = { editando = false }) { Text("Cancelar") }
                    }
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Motorista atual",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Olá, $nomeSalvo",
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    TextButton(onClick = {
                        nome = nomeSalvo
                        erro = null
                        editando = true
                    }) {
                        Text("Alterar")
                    }
                }
            }
        }
    }
}

@Composable
private fun ResumoViagens(
    totalGasto: Double,
    totalKm: Double,
    mediaKmL: Double,
    quantidade: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = MaterialTheme.shapes.large,
        modifier = modifier.padding(horizontal = Espaco.lg, vertical = Espaco.sm)
    ) {
        Column(Modifier.padding(Espaco.lg)) {
            Text(
                text = "Total gasto",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = totalGasto.emReais(),
                style = MaterialTheme.typography.headlineMedium.numerico,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(Espaco.lg))
            Row(horizontalArrangement = Arrangement.spacedBy(Espaco.md)) {
                Indicador("Distância", totalKm.emKm(), Modifier.weight(1f))
                Indicador("Média", mediaKmL.emKmL(), Modifier.weight(1f))
                Indicador("Viagens", quantidade.toString(), Modifier.weight(0.7f))
            }
        }
    }
}

@Composable
private fun Indicador(rotulo: String, valor: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = rotulo,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = valor,
            style = MaterialTheme.typography.titleMedium.numerico,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ViagemItem(viagem: Viagem, onClick: () -> Unit) {
    val cores = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClickLabel = "Editar viagem", onClick = onClick)
            .padding(horizontal = Espaco.lg, vertical = Espaco.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SeloData(viagem)

        Spacer(Modifier.size(Espaco.lg))

        Column(Modifier.weight(1f)) {
            Text(
                text = "${viagem.calcularDistancia().emKm()} · ${viagem.combustivel}",
                style = MaterialTheme.typography.bodyLarge.numerico,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${viagem.calcularKmL().emKmL()} · ${viagem.litros.emLitros()}",
                style = MaterialTheme.typography.bodySmall.numerico,
                color = cores.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(start = Espaco.md)
        ) {
            Text(
                text = viagem.calcularCustoTotal().emReais(),
                style = MaterialTheme.typography.titleMedium.numerico
            )
            if (viagem.pedagio > 0) {
                Text(
                    text = "pedágio ${viagem.pedagio.emReais()}",
                    style = MaterialTheme.typography.bodySmall.numerico,
                    color = cores.onSurfaceVariant
                )
            }
        }
    }
}

/** Dia em destaque e mês abaixo: facilita percorrer o histórico pela data. */
@Composable
private fun SeloData(viagem: Viagem) {
    val partes = viagem.partesData()

    Column(
        modifier = Modifier.widthIn(min = Espaco.larguraData),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (partes == null) {
            Text(viagem.data, style = MaterialTheme.typography.labelMedium)
            return@Column
        }
        val (dia, mes, ano) = partes
        Text(
            text = dia.toString().padStart(2, '0'),
            style = MaterialTheme.typography.titleLarge.numerico,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = MESES.getOrElse(mes - 1) { "" } + if (ano != anoAtual) " ${ano % 100}" else "",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun EstadoVazio(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Espaco.xl, vertical = Espaco.xxl),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icones.Carro,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.height(Espaco.lg))
            Text(
                text = "Nenhuma viagem registrada",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(Espaco.xs))
            Text(
                text = "Registre quilometragem, abastecimento e pedágio para acompanhar custos e consumo.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 320.dp)
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormularioViagem(
    inicial: Viagem?,
    onFechar: () -> Unit,
    onSalvar: (Viagem) -> Unit,
    onExcluir: (Viagem) -> Unit
) {
    // Estados para os campos de cadastro
    var data by rememberSaveable { mutableStateOf(inicial?.data?.filter(Char::isDigit) ?: hojeEmDigitos()) }
    var kmInicial by rememberSaveable { mutableStateOf(inicial?.kmInicial?.paraCampo() ?: "") }
    var kmFinal by rememberSaveable { mutableStateOf(inicial?.kmFinal?.paraCampo() ?: "") }
    var litros by rememberSaveable { mutableStateOf(inicial?.litros?.paraCampo() ?: "") }
    var combustivel by rememberSaveable { mutableStateOf(inicial?.combustivel ?: TIPOS_COMBUSTIVEL.first()) }
    var pedagio by rememberSaveable { mutableStateOf(inicial?.pedagio?.takeIf { it > 0 }?.paraCampoMoeda() ?: "") }
    var valorCombustivel by rememberSaveable { mutableStateOf(inicial?.valorCombustivel?.paraCampoMoeda() ?: "") }

    var tentouSalvar by rememberSaveable { mutableStateOf(false) }
    var confirmarExclusao by rememberSaveable { mutableStateOf(false) }

    val kmInitVal = kmInicial.paraDecimal()
    val kmEndVal = kmFinal.paraDecimal()
    val litrosVal = litros.paraDecimal()
    val valorCombVal = valorCombustivel.paraDecimal()
    val pedagioVal = if (pedagio.isBlank()) 0.0 else pedagio.paraDecimal()

    // Validação: erros aparecem só depois da primeira tentativa de salvar
    val erroData = "Data inválida".takeIf { !dataValida(data) }
    val erroKmInicial = "Informe o KM inicial".takeIf { kmInitVal == null || kmInitVal < 0 }
    val erroKmFinal = when {
        kmEndVal == null -> "Informe o KM final"
        kmInitVal != null && kmEndVal <= kmInitVal -> "Deve ser maior que o inicial"
        else -> null
    }
    val erroLitros = "Informe os litros".takeIf { litrosVal == null || litrosVal <= 0 }
    val erroValor = "Informe o valor".takeIf { valorCombVal == null || valorCombVal <= 0 }
    val erroPedagio = "Valor inválido".takeIf { pedagioVal == null || pedagioVal < 0 }
    val formularioValido = listOf(erroData, erroKmInicial, erroKmFinal, erroLitros, erroValor, erroPedagio)
        .all { it == null }

    fun salvar() {
        tentouSalvar = true
        if (!formularioValido) return
        onSalvar(
            Viagem(
                data = data.digitosParaData(),
                kmInicial = kmInitVal!!,
                kmFinal = kmEndVal!!,
                litros = litrosVal!!,
                combustivel = combustivel,
                pedagio = pedagioVal!!,
                valorCombustivel = valorCombVal!!,
                id = inicial?.id ?: Viagem.novoId()
            )
        )
    }

    fun erroVisivel(erro: String?) = erro.takeIf { tentouSalvar }

    // Prévia calculada enquanto o usuário preenche
    val distanciaPrevia = if (kmInitVal != null && kmEndVal != null && kmEndVal > kmInitVal) kmEndVal - kmInitVal else null
    val mediaPrevia = if (distanciaPrevia != null && litrosVal != null && litrosVal > 0) distanciaPrevia / litrosVal else null
    val custoPrevio = (valorCombVal ?: 0.0) + (pedagioVal ?: 0.0)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(if (inicial == null) "Nova viagem" else "Editar viagem") },
                navigationIcon = {
                    IconButton(onClick = onFechar) {
                        Icon(Icones.Fechar, contentDescription = "Fechar sem salvar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .limitarLargura()
                    .padding(horizontal = Espaco.lg, vertical = Espaco.sm),
                verticalArrangement = Arrangement.spacedBy(Espaco.xl)
            ) {
                Secao("Data") {
                    CampoTexto(
                        valor = data,
                        onValorChange = { data = it.filter(Char::isDigit).take(8) },
                        rotulo = "Data da viagem",
                        placeholder = "dd/mm/aaaa",
                        erro = erroVisivel(erroData),
                        mascara = MascaraData,
                        tipoTeclado = KeyboardType.Number,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Secao("Quilometragem") {
                    Row(horizontalArrangement = Arrangement.spacedBy(Espaco.md)) {
                        CampoTexto(
                            valor = kmInicial,
                            onValorChange = { kmInicial = it },
                            rotulo = "KM inicial",
                            sufixo = "km",
                            erro = erroVisivel(erroKmInicial),
                            modifier = Modifier.weight(1f)
                        )
                        CampoTexto(
                            valor = kmFinal,
                            onValorChange = { kmFinal = it },
                            rotulo = "KM final",
                            sufixo = "km",
                            erro = erroVisivel(erroKmFinal),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Secao("Abastecimento") {
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(Espaco.sm)
                    ) {
                        TIPOS_COMBUSTIVEL.forEach { tipo ->
                            FilterChip(
                                selected = combustivel == tipo,
                                onClick = { combustivel = tipo },
                                label = { Text(tipo) },
                                leadingIcon = if (combustivel == tipo) {
                                    { Icon(Icones.Confirmar, contentDescription = null, Modifier.size(18.dp)) }
                                } else {
                                    null
                                }
                            )
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(Espaco.md)) {
                        CampoTexto(
                            valor = litros,
                            onValorChange = { litros = it },
                            rotulo = "Litros",
                            sufixo = "L",
                            erro = erroVisivel(erroLitros),
                            modifier = Modifier.weight(1f)
                        )
                        CampoTexto(
                            valor = valorCombustivel,
                            onValorChange = { valorCombustivel = it },
                            rotulo = "Valor pago",
                            prefixo = "R$ ",
                            erro = erroVisivel(erroValor),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Secao("Pedágio") {
                    CampoTexto(
                        valor = pedagio,
                        onValorChange = { pedagio = it },
                        rotulo = "Valor total de pedágios",
                        prefixo = "R$ ",
                        erro = erroVisivel(erroPedagio),
                        apoio = "Opcional",
                        acaoTeclado = ImeAction.Done,
                        onConcluir = ::salvar,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                PreviaViagem(
                    distancia = distanciaPrevia,
                    media = mediaPrevia,
                    custo = custoPrevio
                )

                Column(verticalArrangement = Arrangement.spacedBy(Espaco.sm)) {
                    Button(
                        onClick = ::salvar,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = Espaco.alvoToque)
                    ) {
                        Text(if (inicial == null) "Registrar viagem" else "Salvar alterações")
                    }
                    if (inicial != null) {
                        TextButton(
                            onClick = { confirmarExclusao = true },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = Espaco.alvoToque)
                        ) {
                            Text("Excluir viagem")
                        }
                    }
                }

                Spacer(Modifier.height(Espaco.lg))
            }
        }
    }

    if (confirmarExclusao && inicial != null) {
        AlertDialog(
            onDismissRequest = { confirmarExclusao = false },
            title = { Text("Excluir viagem?") },
            text = { Text("A viagem de ${inicial.data} será removida do histórico e dos totais.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmarExclusao = false
                        onExcluir(inicial)
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) { Text("Excluir") }
            },
            dismissButton = {
                TextButton(onClick = { confirmarExclusao = false }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
private fun Secao(titulo: String, conteudo: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(Espaco.sm)) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        conteudo()
    }
}

@Composable
private fun PreviaViagem(distancia: Double?, media: Double?, custo: Double) {
    Column {
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Row(
            modifier = Modifier.padding(vertical = Espaco.lg),
            horizontalArrangement = Arrangement.spacedBy(Espaco.md)
        ) {
            Indicador("Distância", distancia?.emKm() ?: "0 km", Modifier.weight(1f))
            Indicador("Média", media?.emKmL() ?: "0 km/l", Modifier.weight(1f))
            Indicador("Custo total", custo.emReais(), Modifier.weight(1.2f))
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Composable
private fun CampoTexto(
    valor: String,
    onValorChange: (String) -> Unit,
    rotulo: String,
    modifier: Modifier = Modifier,
    erro: String? = null,
    apoio: String? = null,
    placeholder: String? = null,
    prefixo: String? = null,
    sufixo: String? = null,
    mascara: VisualTransformation = VisualTransformation.None,
    tipoTeclado: KeyboardType = KeyboardType.Decimal,
    acaoTeclado: ImeAction = ImeAction.Next,
    onConcluir: () -> Unit = {}
) {
    val textoApoio = erro ?: apoio
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(rotulo, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        placeholder = placeholder?.let { { Text(it) } },
        prefix = prefixo?.let { { Text(it) } },
        suffix = sufixo?.let { { Text(it) } },
        isError = erro != null,
        supportingText = textoApoio?.let { { Text(it) } },
        visualTransformation = mascara,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = tipoTeclado, imeAction = acaoTeclado),
        keyboardActions = KeyboardActions(onDone = { onConcluir() }),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    )
}



private object Icones {
    val Adicionar = icone("M19,13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z")
    val Fechar = icone(
        "M19,6.41L17.59,5 12,10.59 6.41,5 5,6.41 10.59,12 5,17.59 6.41,19 12,13.41 17.59,19 19,17.59 13.41,12z"
    )
    val Confirmar = icone("M9,16.17L4.83,12l-1.42,1.41L9,19 21,7l-1.41,-1.41z")
    val Carro = icone(
        "M18.92,6.01C18.72,5.42 18.16,5 17.5,5h-11c-0.66,0 -1.21,0.42 -1.42,1.01L3,12v8c0,0.55 0.45,1 1,1h1" +
            "c0.55,0 1,-0.45 1,-1v-1h12v1c0,0.55 0.45,1 1,1h1c0.55,0 1,-0.45 1,-1v-8l-2.08,-5.99z" +
            "M6.85,7h10.29l1.08,3.11H5.77L6.85,7zM19,17H5v-5h14v5z" +
            "M7.5,14.5m-1.5,0a1.5,1.5 0,1 1,3 0a1.5,1.5 0,1 1,-3 0" +
            "M16.5,14.5m-1.5,0a1.5,1.5 0,1 1,3 0a1.5,1.5 0,1 1,-3 0"
    )

    private fun icone(caminho: String): ImageVector =
        ImageVector.Builder(
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).addPath(pathData = addPathNodes(caminho), fill = SolidColor(Color.Black)).build()
}

@Preview(showBackground = true)
@Composable
fun ControleViagensPreview() {
    ControleViagensTema {
        ControleViagensScreen()
    }
}
