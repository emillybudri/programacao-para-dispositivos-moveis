package com.example.listacompras

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
import java.util.Locale


// Instância do DataStore via extensão do Context
val android.content.Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencias")

// Chave do DataStore
val NOME_USUARIO = stringPreferencesKey("nome_usuario")

// Telas do app
private enum class Tela { CARREGANDO, BOAS_VINDAS, LISTA, PERFIL }

class Produto(
    val nome: String,
    val preco: Double,
    val quantidade: Int,
    val comprado: Boolean = false,
    val id: Long = gerarId()
) {

    fun calcularTotal(): Double {
        return preco * quantidade
    }

    fun copiar(
        nome: String = this.nome,
        preco: Double = this.preco,
        quantidade: Int = this.quantidade,
        comprado: Boolean = this.comprado
    ) = Produto(nome, preco, quantidade, comprado, id)

    companion object {
        private var ultimoId = 0L
        private fun gerarId() = ++ultimoId
    }
}




private object Espaco {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 48.dp
    val alvoToque = 48.dp
    val alturaItem = 64.dp

    // Recuo do texto após a checkbox
    val recuoItem = sm + alvoToque
}

private val CoresClaras = lightColorScheme(
    primary = Color(0xFF2E6A4F),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD6EADF),
    onPrimaryContainer = Color(0xFF0E3A26),
    secondary = Color(0xFF55625A),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFFAFAF7),
    onBackground = Color(0xFF1B1D1A),
    surface = Color(0xFFFAFAF7),
    onSurface = Color(0xFF1B1D1A),
    surfaceVariant = Color(0xFFE4E6E0),
    onSurfaceVariant = Color(0xFF5B6059),
    surfaceContainer = Color(0xFFF0F1EC),
    surfaceContainerHigh = Color(0xFFEAEBE6),
    outline = Color(0xFF8B9089),
    outlineVariant = Color(0xFFDDE0D9),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    inverseSurface = Color(0xFF2F312E),
    inverseOnSurface = Color(0xFFF1F1EC),
    inversePrimary = Color(0xFF93D3B1)
)

private val CoresEscuras = darkColorScheme(
    primary = Color(0xFF93D3B1),
    onPrimary = Color(0xFF00391F),
    primaryContainer = Color(0xFF15513A),
    onPrimaryContainer = Color(0xFFD6EADF),
    secondary = Color(0xFFBCCBC0),
    onSecondary = Color(0xFF27332C),
    background = Color(0xFF121411),
    onBackground = Color(0xFFE3E3DE),
    surface = Color(0xFF121411),
    onSurface = Color(0xFFE3E3DE),
    surfaceVariant = Color(0xFF41463F),
    onSurfaceVariant = Color(0xFFC1C6BE),
    surfaceContainer = Color(0xFF1E201D),
    surfaceContainerHigh = Color(0xFF282A27),
    outline = Color(0xFF8B9089),
    outlineVariant = Color(0xFF3A3E38),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    inverseSurface = Color(0xFFE3E3DE),
    inverseOnSurface = Color(0xFF2F312E),
    inversePrimary = Color(0xFF2E6A4F)
)

private val Formas = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp)
)

// Alinha os números verticalmente
private val TextStyle.numerico get() = copy(fontFeatureSettings = "tnum")

@Composable
fun ListaComprasTema(conteudo: @Composable () -> Unit) {
    val cores: ColorScheme = if (isSystemInDarkTheme()) CoresEscuras else CoresClaras
    val base = MaterialTheme.typography
    MaterialTheme(
        colorScheme = cores,
        shapes = Formas,
        typography = base.copy(
            titleLarge = base.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            titleMedium = base.titleMedium.copy(fontWeight = FontWeight.Medium),
            labelLarge = base.labelLarge.copy(fontWeight = FontWeight.SemiBold)
        ),
        content = conteudo
    )
}

private val localeBr: Locale = Locale.forLanguageTag("pt-BR")

private val formatoMoeda: NumberFormat = NumberFormat.getCurrencyInstance(localeBr)

private fun Double.emReais(): String = formatoMoeda.format(this)

/** Aceita "4,50" e "4.50". */
private fun String.paraDecimal(): Double? = trim().replace(',', '.').toDoubleOrNull()



@Composable
fun ListaComprasScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // A lista fica aqui para não se perder ao trocar de tela
    val produtos = remember { mutableStateListOf<Produto>() }
    var abrirPerfil by remember { mutableStateOf(false) }

    val nomeFlow = remember {
        context.dataStore.data.map { preferences ->
            preferences[NOME_USUARIO] ?: ""
        }
    }
    // Começa com null: nenhuma tela é mostrada até o DataStore responder
    val nomeSalvo by nomeFlow.collectAsState(initial = null)
    val nome = nomeSalvo ?: ""

    fun salvarNome(novoNome: String) {
        scope.launch {
            context.dataStore.edit { preferences ->
                preferences[NOME_USUARIO] = novoNome
            }
        }
        abrirPerfil = false
    }

    val tela = when {
        nomeSalvo == null -> Tela.CARREGANDO
        nome.isEmpty() -> Tela.BOAS_VINDAS
        abrirPerfil -> Tela.PERFIL
        else -> Tela.LISTA
    }

    BackHandler(enabled = tela == Tela.PERFIL) { abrirPerfil = false }

    Crossfade(targetState = tela, label = "tela") { atual ->
        when (atual) {
            Tela.CARREGANDO -> Surface(modifier = Modifier.fillMaxSize()) {}
            Tela.BOAS_VINDAS -> TelaBoasVindas(onComecar = { salvarNome(it) })
            Tela.LISTA -> TelaLista(
                nomeUsuario = nome,
                produtos = produtos,
                onAbrirPerfil = { abrirPerfil = true }
            )
            Tela.PERFIL -> TelaPerfil(
                nomeAtual = nome,
                onSalvar = { salvarNome(it) },
                onVoltar = { abrirPerfil = false }
            )
        }
    }
}

@Composable
private fun TelaBoasVindas(onComecar: (String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Espaco.xl, vertical = Espaco.xl),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icones.Carrinho,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(Espaco.lg))
            Text(
                text = "Bem-vindo à Lista de Compras",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(Espaco.xs))
            Text(
                text = "Como podemos te chamar?",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(Espaco.xl))
            FormularioNome(
                nomeInicial = "",
                textoBotao = "Começar",
                onSalvar = onComecar
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TelaPerfil(
    nomeAtual: String,
    onSalvar: (String) -> Unit,
    onVoltar: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Perfil") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icones.Voltar, contentDescription = "Voltar")
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
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(Espaco.lg)
        ) {
            Text(
                text = "Seu nome",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "É assim que o app vai te chamar.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(Espaco.lg))
            FormularioNome(
                nomeInicial = nomeAtual,
                textoBotao = "Salvar nome",
                onSalvar = onSalvar
            )
        }
    }
}

// Campo de nome + botão, usado na tela de boas-vindas e na tela de perfil
@Composable
private fun FormularioNome(
    nomeInicial: String,
    textoBotao: String,
    onSalvar: (String) -> Unit
) {
    var nome by rememberSaveable { mutableStateOf(nomeInicial) }
    var erro by remember { mutableStateOf<String?>(null) }

    fun salvar() {
        val nomeLimpo = nome.trim()
        if (nomeLimpo.isEmpty()) {
            erro = "Informe seu nome"
        } else {
            onSalvar(nomeLimpo)
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(Espaco.md)) {
        CampoTexto(
            valor = nome,
            onValorChange = {
                nome = it
                erro = null
            },
            rotulo = "Seu nome",
            erro = erro,
            modifier = Modifier.fillMaxWidth(),
            teclado = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            acoes = KeyboardActions(onDone = { salvar() })
        )
        Button(
            onClick = { salvar() },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = Espaco.alvoToque)
        ) {
            Text(textoBotao)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TelaLista(
    nomeUsuario: String,
    produtos: MutableList<Produto>,
    onAbrirPerfil: () -> Unit
) {
    var nome by rememberSaveable { mutableStateOf("") }
    var preco by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("1") }
    var tentouEnviar by rememberSaveable { mutableStateOf(false) }

    // Id do produto em edição (0 = novo)
    var idEmEdicao by remember { mutableLongStateOf(0L) }

    val total = produtos.sumOf { it.calcularTotal() }
    val faltaComprar = produtos.filterNot { it.comprado }.sumOf { it.calcularTotal() }
    val quantidadeComprados = produtos.count { it.comprado }

    // Organiza a lista colocando os itens pendentes primeiro
    val produtosOrdenados = produtos.sortedBy { it.comprado }

    val precoDouble = preco.paraDecimal()
    val qtdInt = quantidade.trim().toIntOrNull()
    val erroNome = if (tentouEnviar && nome.isBlank()) "Informe o produto" else null
    val erroPreco = if (tentouEnviar && (precoDouble == null || precoDouble <= 0)) "Preço inválido" else null
    val erroQtd = if (tentouEnviar && (qtdInt == null || qtdInt <= 0)) "Mín. 1" else null

    val snackbar = remember { SnackbarHostState() }
    val escopo = rememberCoroutineScope()
    val focoNome = remember { FocusRequester() }
    val estadoLista = rememberLazyListState()

    fun focarFormulario() {
        escopo.launch {
            runCatching { focoNome.requestFocus() }
        }
    }

    fun limparFormulario() {
        nome = ""
        preco = ""
        quantidade = "1"
        tentouEnviar = false
        idEmEdicao = 0L
    }

    fun enviar() {
        tentouEnviar = true
        if (nome.isNotBlank() && precoDouble != null && precoDouble > 0 && qtdInt != null && qtdInt > 0) {
            val indice = produtos.indexOfFirst { it.id == idEmEdicao }
            if (indice >= 0) {
                produtos[indice] = produtos[indice].copiar(
                    nome = nome.trim(),
                    preco = precoDouble,
                    quantidade = qtdInt
                )
            } else {
                produtos.add(Produto(nome = nome.trim(), preco = precoDouble, quantidade = qtdInt))
            }
            limparFormulario()
            focarFormulario()
        }
    }

    fun editar(produto: Produto) {
        idEmEdicao = produto.id
        nome = produto.nome
        preco = String.format(localeBr, "%.2f", produto.preco)
        quantidade = produto.quantidade.toString()
        tentouEnviar = false
        focarFormulario()
    }

    fun remover(produto: Produto) {
        val indice = produtos.indexOfFirst { it.id == produto.id }
        if (indice < 0) return
        produtos.removeAt(indice)
        if (idEmEdicao == produto.id) limparFormulario()
        escopo.launch {
            snackbar.currentSnackbarData?.dismiss()
            val resultado = snackbar.showSnackbar(
                message = "${produto.nome} removido",
                actionLabel = "Desfazer",
                duration = SnackbarDuration.Short
            )
            if (resultado == SnackbarResult.ActionPerformed) {
                produtos.add(indice.coerceAtMost(produtos.size), produto)
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Olá, $nomeUsuario",
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Lista de compras",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onAbrirPerfil) {
                        Icon(Icones.Perfil, contentDescription = "Perfil")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            if (produtos.isNotEmpty()) {
                Resumo(total = total, faltaComprar = faltaComprar)
            }
        },
        snackbarHost = { SnackbarHost(snackbar) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Formulario(
                nome = nome,
                preco = preco,
                quantidade = quantidade,
                erroNome = erroNome,
                erroPreco = erroPreco,
                erroQuantidade = erroQtd,
                editando = idEmEdicao != 0L,
                focoNome = focoNome,
                onNomeChange = { nome = it },
                onPrecoChange = { preco = it },
                onQuantidadeChange = { quantidade = it.filter(Char::isDigit) },
                onEnviar = ::enviar,
                onCancelar = ::limparFormulario,
                onRemover = {
                    produtos.firstOrNull { it.id == idEmEdicao }?.let(::remover)
                }
            )

            ListaProdutos(
                produtos = produtosOrdenados,
                idEmEdicao = idEmEdicao,
                comprados = quantidadeComprados,
                estadoLista = estadoLista,
                onAlternarComprado = { produto ->
                    val i = produtos.indexOfFirst { it.id == produto.id }
                    if (i >= 0) produtos[i] = produto.copiar(comprado = !produto.comprado)
                },
                onEditar = ::editar,
                modifier = Modifier.weight(1f)
            )
        }
    }
}



@Composable
private fun ListaProdutos(
    produtos: List<Produto>,
    idEmEdicao: Long,
    comprados: Int,
    estadoLista: LazyListState,
    onAlternarComprado: (Produto) -> Unit,
    onEditar: (Produto) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = estadoLista,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = Espaco.xl)
    ) {
        if (produtos.isEmpty()) {
            item(key = "vazio") { EstadoVazio() }
        } else {
            item(key = "cabecalho") {
                CabecalhoLista(comprados = comprados, totalItens = produtos.size)
            }

            items(produtos, key = { it.id }) { produto ->
                Column(Modifier.animateItem()) {
                    ProdutoItem(
                        produto = produto,
                        selecionado = produto.id == idEmEdicao,
                        onAlternarComprado = { onAlternarComprado(produto) },
                        onEditar = { onEditar(produto) }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(start = Espaco.recuoItem),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun Formulario(
    nome: String,
    preco: String,
    quantidade: String,
    erroNome: String?,
    erroPreco: String?,
    erroQuantidade: String?,
    editando: Boolean,
    focoNome: FocusRequester,
    onNomeChange: (String) -> Unit,
    onPrecoChange: (String) -> Unit,
    onQuantidadeChange: (String) -> Unit,
    onEnviar: () -> Unit,
    onCancelar: () -> Unit,
    onRemover: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = Espaco.lg, vertical = Espaco.sm),
        verticalArrangement = Arrangement.spacedBy(Espaco.sm)
    ) {
        if (editando) {
            Text(
                text = "Editando item",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        CampoTexto(
            valor = nome,
            onValorChange = onNomeChange,
            rotulo = "Produto",
            erro = erroNome,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focoNome),
            teclado = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            )
        )

        Row(horizontalArrangement = Arrangement.spacedBy(Espaco.md)) {
            CampoTexto(
                valor = preco,
                onValorChange = onPrecoChange,
                rotulo = "Preço unitário",
                prefixo = "R$ ",
                erro = erroPreco,
                modifier = Modifier.weight(1.6f),
                teclado = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                )
            )
            CampoTexto(
                valor = quantidade,
                onValorChange = onQuantidadeChange,
                rotulo = "Qtd.",
                erro = erroQuantidade,
                modifier = Modifier.weight(1f),
                teclado = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                acoes = KeyboardActions(onDone = { onEnviar() })
            )
        }

        if (editando) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick = onRemover,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.heightIn(min = Espaco.alvoToque)
                ) { Text("Remover") }
                Spacer(Modifier.weight(1f))
                TextButton(
                    onClick = onCancelar,
                    modifier = Modifier.heightIn(min = Espaco.alvoToque)
                ) { Text("Cancelar") }
                Spacer(Modifier.size(Espaco.sm))
                Button(
                    onClick = onEnviar,
                    modifier = Modifier.heightIn(min = Espaco.alvoToque)
                ) { Text("Salvar") }
            }
        } else {
            Button(
                onClick = onEnviar,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = Espaco.alvoToque)
            ) {
                Icon(Icones.Adicionar, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.size(Espaco.sm))
                Text("Adicionar à lista")
            }
        }
    }
}

@Composable
private fun CampoTexto(
    valor: String,
    onValorChange: (String) -> Unit,
    rotulo: String,
    modifier: Modifier = Modifier,
    erro: String? = null,
    prefixo: String? = null,
    teclado: KeyboardOptions = KeyboardOptions.Default,
    acoes: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(rotulo, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        prefix = prefixo?.let { { Text(it) } },
        isError = erro != null,
        supportingText = erro?.let { { Text(it) } },
        singleLine = true,
        keyboardOptions = teclado,
        keyboardActions = acoes,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    )
}

@Composable
private fun CabecalhoLista(comprados: Int, totalItens: Int) {
    Column(
        modifier = Modifier.padding(
            start = Espaco.lg,
            end = Espaco.lg,
            top = Espaco.xl,
            bottom = Espaco.sm
        )
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "Itens",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$comprados de $totalItens comprados",
                style = MaterialTheme.typography.bodySmall.numerico,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(Modifier.height(Espaco.sm))
        LinearProgressIndicator(
            progress = { comprados.toFloat() / totalItens },
            modifier = Modifier
                .fillMaxWidth()
                .height(Espaco.xs),
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            drawStopIndicator = {}
        )
    }
}

@Composable
private fun ProdutoItem(
    produto: Produto,
    selecionado: Boolean,
    onAlternarComprado: () -> Unit,
    onEditar: () -> Unit
) {
    val cores = MaterialTheme.colorScheme
    val corTexto = if (produto.comprado) cores.onSurfaceVariant else cores.onSurface
    val decoracao = if (produto.comprado) TextDecoration.LineThrough else TextDecoration.None

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (selecionado) cores.primaryContainer.copy(alpha = 0.5f) else Color.Transparent)
            .toggleable(
                value = produto.comprado,
                role = Role.Checkbox,
                onValueChange = { onAlternarComprado() }
            )
            .heightIn(min = Espaco.alturaItem)
            .padding(start = Espaco.sm, end = Espaco.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = produto.comprado, onCheckedChange = null, modifier = Modifier.size(Espaco.alvoToque))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = Espaco.sm)
        ) {
            Text(
                text = produto.nome,
                style = MaterialTheme.typography.bodyLarge,
                color = corTexto,
                textDecoration = decoracao,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${produto.quantidade} × ${produto.preco.emReais()}",
                style = MaterialTheme.typography.bodySmall.numerico,
                color = cores.onSurfaceVariant
            )
        }

        Text(
            text = produto.calcularTotal().emReais(),
            style = MaterialTheme.typography.bodyLarge.numerico,
            color = corTexto,
            modifier = Modifier.padding(start = Espaco.md)
        )

        IconButton(onClick = onEditar) {
            Icon(
                imageVector = Icones.Editar,
                contentDescription = "Editar ${produto.nome}",
                tint = cores.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun Resumo(total: Double, faltaComprar: Double) {
    Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
        Column {
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = Espaco.lg, vertical = Espaco.md),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Total da compra",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = total.emReais(),
                        style = MaterialTheme.typography.titleLarge.numerico
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Falta comprar",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = faltaComprar.emReais(),
                        style = MaterialTheme.typography.bodyLarge.numerico,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun EstadoVazio() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Espaco.xl, vertical = Espaco.xxl),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icones.Carrinho,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(40.dp)
        )
        Spacer(Modifier.height(Espaco.lg))
        Text(
            text = "Sua lista está vazia",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(Espaco.xs))
        Text(
            text = "Os produtos adicionados aparecem aqui.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}



private object Icones {
    val Adicionar = icone("M19,13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z")
    val Perfil = icone(
        "M12,12c2.21,0 4,-1.79 4,-4s-1.79,-4 -4,-4 -4,1.79 -4,4 1.79,4 4,4zM12,14c-2.67,0 -8,1.34 -8,4v2h16v-2" +
            "c0,-2.66 -5.33,-4 -8,-4z"
    )
    val Voltar = icone("M20,11H7.83l5.59,-5.59L12,4l-8,8 8,8 1.41,-1.41L7.83,13H20v-2z")
    val Editar = icone(
        "M14.06,9.02l0.92,0.92L5.92,19H5v-0.92l9.06,-9.06M17.66,3c-0.25,0 -0.51,0.1 -0.7,0.29l-1.83,1.83 3.75,3.75" +
            " 1.83,-1.83c0.39,-0.39 0.39,-1.02 0,-1.41l-2.34,-2.34c-0.2,-0.2 -0.45,-0.29 -0.71,-0.29z" +
            "M14.06,6.19L3,17.25V21h3.75L17.81,9.94l-3.75,-3.75z"
    )
    val Carrinho = icone(
        "M15.55,13c0.75,0 1.41,-0.41 1.75,-1.03l3.58,-6.49c0.37,-0.66 -0.11,-1.48 -0.87,-1.48H5.21l-0.94,-2H1v2h2" +
            "l3.6,7.59 -1.35,2.44C4.52,15.37 5.48,17 7,17h12v-2H7l1.1,-2h7.45zM6.16,6h12.15l-2.76,5H8.53L6.16,6z" +
            "M7,18c-1.1,0 -1.99,0.9 -1.99,2S5.9,22 7,22s2,-0.9 2,-2 -0.9,-2 -2,-2zM17,18c-1.1,0 -1.99,0.9 -1.99,2" +
            "s0.89,2 1.99,2 2,-0.9 2,-2 -0.9,-2 -2,-2z"
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
fun ListaComprasPreview() {
    ListaComprasTema {
        ListaComprasScreen()
    }
}
