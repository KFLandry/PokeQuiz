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

11. En tournant l'émulateur Android (rotation), l'activité est recréée. Le compteur mémorisé avec `remember` n'est pas conservé (il revient à 0) car l'état en mémoire de composition est perdu lors de la destruction/recréation de l'activité. Pour persister l'état lors d'un changement de configuration, il conviendrait d'utiliser `rememberSaveable` ou un `ViewModel`. 




1. Parce que le lancement est beaucoup plus rapide sur Desktop, ce qui permet de développer et tester l'UI plus efficacement qu'en passant par un émulateur ou un téléphone

2. Hot Reload : met à jour le code sans redémarrer l'application.
   Redémarrage : relance complètement l'application.
   À l'étape 7, les changements de configuration/projet (Gradle, dépendances, etc) nécessitent un redémarrage complet

3. Le projet risque de ne plus compiler ou d'afficher des avertissements, car androidTarget {} correspond à une ancienne configuration KMP devenue obsolète

4. Greeting est commun, mais il appelle du code spécifique à chaque plateforme via le mécanisme expect/actual. Chaque plateforme fournit donc son propre texte.

5. le bytecode JVM est généralement le plus facile à analyser (nombreux outils de décompilation). C'est important car du code sensible placé dans commonMain pourrait être plus facilement récupéré ou compris par un attaquant.

6. Les tâches d'exécution sont celles contenant run dans leur nom (ex. run, jvmRun, selon le projet).
   La tâche de Hot Reload est généralement celle qui contient hotReload dans son nom.

7. Les fichiers .class se trouvent dans shared/build/classes/....
   Les dossiers/fichiers .klib correspondent aux bibliothèques Kotlin/Native utilisées pour iOS et autres cibles natives.

8. La bibliothèque Skiko apparaît car elle fournit la couche graphique utilisée par Compose Multiplatform pour l'affichage sur Desktop.

9. Les preflight checks vérifient que l'environnement est correctement configuré avant la compilation ou l'exécution