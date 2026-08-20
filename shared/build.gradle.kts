plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight")
}

kotlin {
    jvm()


    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.2")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        val jvmMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
            }
        }
    }
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.angelalonso.kanara.db")
            dialect("app.cash.sqldelight:sqlite-3-38-dialect:2.0.2") // agregado
        }
    }
}