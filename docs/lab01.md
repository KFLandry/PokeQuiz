1. Les modules gradle sont définis dans settings.gradle.kts, il y en a 3 : 
- androidApp
- desktopApp
- shared
  
iosApp ne fait pas partie des modules Gradle car il n'apparaît pas dans les instructions include(...)

2. kotlin 2.4.20, Compose Multiplatform 1.12.1, AGP 9.1.1

3. shared/build: org.jetbrains.kotlin.multiplatform, com.android.kotlin.multiplatform.library, org.jetbrains.compose, org.jetbrains.kotlin.plugin.compose
androidApp/build : com.android.application, org.jetbrains.kotlin.plugin.compose
org.jetbrains.kotlin.android n'est pas appliqué car avec les versions récentes de Kotlin et AGP, le plugin Android gère déjà l'intégration Kotlin.

4. bibliothèque partagé dans shared
Application dans adnroidApp 

5. androidHostTest androidMain commonMain commonTest iosMain iosTest jvmMain jvmTest. 
Les cibles partagent la meme soufce set : iosMain. Donc pas de iosArm64Main. 

6. déclarée dasn shared/src/commonMain/kotlin/.../Platform.kt
Dans les 3 plateformes ells sont declarées. 

   7. l'app android est fr.m2.pokequiz                                                                                                                                                                                                                   
   main class desktop : fr.m2.pokequiz.MainKt
   le suffixe kt existe car un fichier kotlin qui contient des fonctions top-level est compilé en une classe nommée fichier.kt

8. App() est dfinie dans shared/src/commonMain/kotlin/fr/m2/pokequiz/App.kt.
   appelée dans MainActivity.kt, main.kt, App.kt et MainViewController.kt

9. .gradle
   build/
   .idea
   fichiers temporaires
   local properties
Local.properties doit être ignoré car il contient des informations locales à la machine du développeur, notamment les chemins du SDK Android

10. il ne resout pas la ref java.
    java.io.File n'existe que sur JVM/Android et n'est donc pas disponible dans le code commun multiplateforme pour les autresplatreformes. 