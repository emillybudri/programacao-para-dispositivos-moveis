// Módulo Kotlin/JVM com os exercícios de console. Cada arquivo tem seu próprio
// package, então vários `fun main()` convivem sem conflito.
plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    main {
        kotlin {
            setSrcDirs(listOf("."))
            // Apps Android têm módulos próprios
            exclude("ativ-26-09-29/**", "build/**", "*.kts")
            // Roteiros de aula com várias versões de `main()` no mesmo arquivo:
            // não compilam juntos, ficam apenas como referência
            exclude("ativ-26-09-15/Calculadora.kt", "ativ-26-09-15/Ex01.kt")
        }
    }
}
