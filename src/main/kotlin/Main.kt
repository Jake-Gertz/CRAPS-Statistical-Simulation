package org.example

fun main() {
    val crapsRound = Craps(1000000000)
    crapsRound.crapsGame()
    println(crapsRound.toString())
}