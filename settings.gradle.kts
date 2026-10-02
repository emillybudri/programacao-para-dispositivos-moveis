pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "programacao-para-dispositivos-moveis"

// Exercícios de console (Kotlin/JVM)
include(":exercicios-console")
project(":exercicios-console").projectDir = file("4SEM-PDM-I/EXERCÍCIOS")

// Apps Android (Jetpack Compose) da atividade 29/09
include(":lista-compras")
project(":lista-compras").projectDir = file("4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/lista-compras")

include(":controle-viagens")
project(":controle-viagens").projectDir = file("4SEM-PDM-I/EXERCÍCIOS/ativ-26-09-29/controle-viagens")
