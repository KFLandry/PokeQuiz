package fr.m2.pokequiz

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        fun greet(): String = "Hello, ${platform.name}!"
        return greet()
    }
}