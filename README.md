# 📱 Programação para Dispositivos Móveis

Materiais, práticas e listas de exercícios de **Programação para Dispositivos Móveis (PDM)**, do curso de **Desenvolvimento de Software Multiplataforma** da **Fatec Diadema Luigi Papaiz**.

<p>
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white" alt="Android Studio" />
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white" alt="IntelliJ IDEA" />
</p>

---

## 📂 Estrutura

```text
programacao-para-dispositivos-moveis/
├── 4SEM-PDM-I/             # 4º semestre: PDM I
│   ├── EXERCÍCIOS/         # Atividades práticas
│   └── MATERIAIS/          # Slides, PDFs e materiais de aula
└── 5SEM-PDM-II/            # 5º semestre: PDM II
    ├── EXERCÍCIOS/
    └── MATERIAIS/
```

---

## 🗓️ Atividades

Todas as pastas ficam em `4SEM-PDM-I/EXERCÍCIOS/`.

| # | Data | Tema | Pasta | Conceitos |
|---|------|------|-------|-----------|
| 01 | 20/08 (entrega 27/08) | Kotlin: introdução | [`ativ-26-08-20`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-08-20/) | Sintaxe, `val`/`var`, tipos primitivos, operadores, entrada e saída no console |
| 02 | 25/08 (entrega 01/09) | Condicionais e loopings | [`ativ-26-08-25`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-08-25/) | `if/else`, `when`, `for`, `while`, `do-while` |
| 03 | 10/09 | Funções | [`ativ-26-09-10`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-10/) | Funções com retorno, parâmetros nomeados e padrão |
| 04 | 15/09 | Carrinho de compras | [`ativ-26-09-15`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-15/) | Modelagem de domínio, lista de itens, totalização |
| 05 | 17/09 | Coleções | [`ativ-26-09-17`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-17/) | `MutableList`, `Set`, `filter`, `map`, `sum` |
| 06 | 22/09 | Estacionamento | [`ativ-26-09-22`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-22/) | POO, encapsulamento, cálculo de permanência e tarifa |
| 07 e 08 | 29/09 (entrega 01/10 e 02/10) | Apps Android | [`ativ-26-09-29`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/) | Jetpack Compose, Material Design 3, `remember`, `mutableStateOf`, `@Preview` |
| 09 | 08/10 | Meu Perfil | [`ativ-26-10-08`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-10-08/) | `DataStore Preferences`, `Flow`, `collectAsState`, corrotinas |

<details>
<summary><b>Detalhes de cada atividade</b></summary>

- **01:** `Ex01.kt` a `Ex15.kt`. Exercícios de fixação com cálculos sequenciais e formatação de saída.
- **02:** `Ex16.kt` a `Ex36.kt`. Validação de intervalos, acumuladores, paridade e menus interativos no terminal.
- **03:** `Ex01.kt` a `Ex05.kt`. Funções de utilidade para cálculos e processamento de dados.
- **04:** `Carrinho.kt`, `Calculadora.kt`, `Ex01.kt`, `Ex02.kt`. Simulação interativa de carrinho e rotinas de apoio.
- **05:** `Ex01.kt`, `Mutablelist.kt`, `Set.kt`. Módulo *Aula03: Kotlin, Coleções*. Cadastro de alunos, notas, médias e status de aprovação. Também usa iteradores.
- **06:** `Estacionamento.kt`, `Ex01.kt`. Módulo *Aula04: Classes e Objetos*. Gestão de vagas, entrada e saída de veículos, recibo e histórico.
- **07 e 08:** Android SDK e componentes reutilizáveis.
  - [`controle-viagens`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/controle-viagens/) (*Aula06: Android Studio, entendendo a estrutura*): km inicial e final, consumo, pedágios, combustível e divisão por passageiro.
  - [`lista-compras`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/lista-compras/) (*Aula07: Composable e Preview*): produtos, quantidades, preços, status de compra e total em tempo real.
- **09:** [`meuperfil`](4SEM-PDM-I/EXERCÍCIOS/ativ-26-10-08/meuperfil/). Salva o nome do usuário com DataStore e o exibe de volta ao reabrir o app.

</details>

---

## 🤖 Apps Android

<table align="center">
  <tr>
    <th align="center" width="50%">🛒 Lista de Compras<br /><sub>Aula07 · Composable e Preview</sub></th>
    <th align="center" width="50%">🚗 Controle de Viagens<br /><sub>Aula06 · Entendendo a estrutura</sub></th>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/videos/app-lista-de-compras.gif" alt="Demonstração do app Lista de Compras" width="250" /><br /><br />
      Produtos, quantidades, preços, status de compra e total em tempo real.<br /><br />
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/lista-compras/"><img src="https://img.shields.io/badge/Código-3DE982?style=for-the-badge&logo=kotlin&logoColor=black" alt="Código" /></a>
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/apks/lista-compras.apk"><img src="https://img.shields.io/badge/APK-000000?style=for-the-badge&logo=android&logoColor=3DE982" alt="APK" /></a>
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/videos/app-lista-de-compras.mp4"><img src="https://img.shields.io/badge/Vídeo-6B6B6B?style=for-the-badge&logo=youtube&logoColor=white" alt="Vídeo" /></a>
    </td>
    <td align="center" width="50%">
      <img src="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/videos/app-viagem.gif" alt="Demonstração do app Controle de Viagens" width="250" /><br /><br />
      Km inicial e final, consumo, pedágios, combustível e divisão por passageiro.<br /><br />
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/controle-viagens/"><img src="https://img.shields.io/badge/Código-3DE982?style=for-the-badge&logo=kotlin&logoColor=black" alt="Código" /></a>
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/apks/controle-viagens.apk"><img src="https://img.shields.io/badge/APK-000000?style=for-the-badge&logo=android&logoColor=3DE982" alt="APK" /></a>
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/videos/app-viagem.mp4"><img src="https://img.shields.io/badge/Vídeo-6B6B6B?style=for-the-badge&logo=youtube&logoColor=white" alt="Vídeo" /></a>
    </td>
  </tr>
  <tr>
    <th align="center" colspan="2">👤 Meu Perfil<br /><sub>Persistência com DataStore</sub></th>
  </tr>
  <tr>
    <td align="center" colspan="2">
      Salva o nome do usuário com DataStore Preferences e o recupera ao reabrir o app.<br /><br />
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-10-08/meuperfil/"><img src="https://img.shields.io/badge/Código-3DE982?style=for-the-badge&logo=kotlin&logoColor=black" alt="Código" /></a>
      <a href="4SEM-PDM-I/EXERCÍCIOS/ativ-26-10-08/apks/meuperfil.apk"><img src="https://img.shields.io/badge/APK-000000?style=for-the-badge&logo=android&logoColor=3DE982" alt="APK" /></a>
    </td>
  </tr>
</table>

> [!TIP]
> **Instalar no celular:** baixe o APK, abra no aparelho e permita a instalação de fontes desconhecidas.

---

## ▶️ Como executar

O repositório é um projeto **Gradle** único. Abra a pasta raiz no **Android Studio** (ou IntelliJ IDEA) e aguarde a sincronização.

- **Exercícios de console:** ficam no módulo `exercicios-console`. Cada arquivo tem seu próprio `package`, então basta clicar em ▶️ ao lado do `fun main()`.
- **Apps Android:** selecione `lista-compras`, `controle-viagens` ou `meuperfil` na configuração de execução e rode no emulador ou no celular.
- **Gerar os APKs pelo terminal:**

  ```bash
  ./gradlew :lista-compras:assembleRelease :controle-viagens:assembleRelease :meuperfil:assembleRelease
  ```

> [!NOTE]
> `ativ-26-09-15/Calculadora.kt` e `ativ-26-09-15/Ex01.kt` são roteiros de aula com várias versões de `main()` no mesmo arquivo, por isso ficam fora da compilação.

---

## 🛠️ Ferramentas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **IDEs:** [IntelliJ IDEA](https://www.jetbrains.com/idea/) e [Android Studio](https://developer.android.com/studio)
- **Execução:** JVM e Kotlin Playground (web)

---

## 👤 Autoria

**Emilly Budri Bognar** · RA 2171392511009
Desenvolvimento de Software Multiplataforma · Fatec Diadema Luigi Papaiz
